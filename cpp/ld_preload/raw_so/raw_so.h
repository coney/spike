#pragma once

#ifdef __cplusplus
extern "C" {
#endif

int raw_soTest();

#ifdef __cplusplus
}

class Test{
public:
    int hello();
    static Test* CreateInstance();
};

#endif
