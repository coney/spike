#include <stdio.h>
#include <string>
#include <memory>
#include <mockcpp/types/AnyCast.h>
//
//MOCKCPP_NS_START
//
//template <>
//::std::auto_ptr< ::std::string>* __any_cast(AnyBase* operand)
//{
//	typedef ::std::auto_ptr< ::std::string> nonref;
//	typedef Holder< ::std::string*> holder;
//
//	if (operand == 0 || operand->type() != typeid(::std::auto_ptr< ::std::string>))
//	{
//		printf("%p %s %s\n", operand,
//			operand->type().name(),
//			typeid(::std::auto_ptr< ::std::string>).name());
//		//return 0;
//	}
//
//	holder* p = dynamic_cast<holder*>(operand->getContent());
//	printf("holder:%p inner: %p\n", p, p->getValue());
//
//	::std::auto_ptr< ::std::string> *ret = new ::std::auto_ptr< ::std::string>(p->getValue());
//	printf("create pointer %p\n", ret);
//	return ret;
//}
//
//template<>
//::std::auto_ptr< ::std::string> any_cast < ::std::auto_ptr< ::std::string> >
//(const AnyBase& operand) {
//	typedef ::std::auto_ptr< ::std::string> nonref;
//
//	const nonref * result = any_cast<nonref>(&operand);
//	MOCKCPP_ASSERT(result != 0);
//
//	::std::auto_ptr< ::std::string> ret = *const_cast<nonref*>(result);
//	printf("delete pointer %p\n", result);
//	delete result;
//	return ret;
//}
//
//template <>
//bool any_castable< ::std::auto_ptr< ::std::string> >(const AnyBase& val)
//{
//	// 这里不特化会导致23行那里内存泄露
//	printf("in any_castable return true\n");
//	return true;
//}
//
//MOCKCPP_NS_END
//
#include <mockcpp/mokc.h>
#include <gmock/gmock.h>


#ifdef WITH_AUTO_PTR

class StringCreator {
public:
    static ::std::auto_ptr< ::std::string>create(const ::std::string &val) {
        return ::std::auto_ptr< ::std::string>(new ::std::string(val));
    }
};

class CreatorMock {
public:
    MOCK_METHOD1(create, ::std::string *(const ::std::string &val));

    static CreatorMock &instance() {
        static CreatorMock mocker;
        return mocker;
    }
    static ::std::string* createProxy(const ::std::string &val) {
        std::string *p = instance().create(val);
        printf("create string pointer %p\n", p);
        return p;
    }
};

class StaticMocker : public ::testing::Test {
protected:
    static void SetUpTestCase() {
        MOCKER(StringCreator::create)
            .defaults()
            .with(any())
            .will(invoke(CreatorMock::createProxy));
    }
};

TEST_F(StaticMocker, TestMocker1) {
    EXPECT_CALL(CreatorMock::instance(), create("hello"))
        .WillOnce(::testing::Return(new ::std::string("world")));

    ::std::auto_ptr< ::std::string> str = StringCreator::create("hello");
    ASSERT_EQ("world", *str);
}

#endif // WITH_AUTO_PTR


class Calc {
public:
    static int add(int x, int y) {
        return x + y;
    }
};

class CalcMock {
public:
    static CalcMock &getInstance() {
        static CalcMock calcMock;
        return calcMock;
    }

    MOCK_METHOD2(add, int(int, int));
    static int addProxy(int x, int y) {
        return getInstance().add(x, y);
    }
};

TEST(Mockcpp, ShouldAbleToMockStaticFunctionWithGoogleMock) {
    MOCKER(Calc::add)
        .defaults()
        .with(any(), any())
        .will(invoke(CalcMock::addProxy));

    EXPECT_CALL(CalcMock::getInstance(), add(1, 2))
        .WillRepeatedly(testing::Return(0));
    ASSERT_EQ(0, Calc::add(1, 2));


    EXPECT_CALL(CalcMock::getInstance(), add(1, 2))
        .WillRepeatedly(testing::Return(10));
    ASSERT_EQ(10, Calc::add(1, 2));
}
