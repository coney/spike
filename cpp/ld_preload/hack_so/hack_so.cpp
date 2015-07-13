#include "hack_so.h"
#include "raw_so.h"
#include <stdlib.h>
/*
	To test the library, include "hack_so.h" from an application project
	and call hack_soTest().
	
	Do not forget to add the library to Project Dependencies in Visual Studio.
*/

int raw_soTest() {
    return 11;
}
int app_soTest() {
    return 22;
}


Test* Test::CreateInstance()
{
    return NULL;
}