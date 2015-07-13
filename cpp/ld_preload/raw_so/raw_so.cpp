#include "raw_so.h"

/*
	To test the library, include "raw_so.h" from an application project
	and call raw_soTest().
	
	Do not forget to add the library to Project Dependencies in Visual Studio.
*/

static int s_Test = 0;

extern "C" int raw_soTest();

int raw_soTest()
{
	return ++s_Test;
}

int Test::hello()
{
    return 0;
}

Test* Test::CreateInstance()
{
    return new Test;
}
