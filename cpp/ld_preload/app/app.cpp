#include <iostream>
#include <string>
#include <list>
#include <stdio.h>
#include "raw_so.h"

using namespace std;

extern "C" int app_soTest();

int main(int argc, char *argv[])
{
    printf("%d %d\n", raw_soTest(), app_soTest());
    printf("%d %d\n", raw_soTest(), app_soTest());
    printf("%d %d\n", raw_soTest(), app_soTest());
    printf("%d %d\n", raw_soTest(), app_soTest());
    printf("%p\n", Test::CreateInstance());
    printf("%p\n", Test::CreateInstance());
    printf("%p\n", Test::CreateInstance());
	return 0;
}