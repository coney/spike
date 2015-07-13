extern "C" int app_soTest() {
    static int x = 0;
    x += 2;
    return x;
}