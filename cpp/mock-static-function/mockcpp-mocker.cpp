#include <mockcpp/mokc.h>
#include <gmock/gmock.h>

static int add(int x, int y) {
    return x + y;
}

TEST(Mockcpp, ShouldAbleToMockCFunction) {
    MOCKER(add)
        .expects(once())
        .with(eq(1), eq(2))
        .will(returnValue(0));
    ASSERT_EQ(0, add(1, 2));
}

class Calc {
public:
    static int add(int x, int y) {
        return x + y;
    }
};

TEST(Mockcpp, ShouldAbleToMockStaticFunction) {
    MOCKER(&Calc::add)
        .expects(once())
        .with(eq(1), eq(2))
        .will(returnValue(0));
    ASSERT_EQ(0, Calc::add(1, 2));
}