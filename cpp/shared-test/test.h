#ifndef _TEST_H_INCLUDED_
#define _TEST_H_INCLUDED_

#include <stdio.h>

class Test {
public:
    void run(){
        printf("this is %s\n", MODULE_NAME);
    }
};

#endif // _TEST_H_INCLUDED_
