APP=test-app
SOA=a.so
SOB=b.so



testa: $(SOA) $(SOB)
	g++ main.cpp -o $(APP) a.so b.so
	LD_LIBRARY_PATH=. ./$(APP)

testb: $(SOA) $(SOB)
	g++ main.cpp -o $(APP) b.so a.so
	LD_LIBRARY_PATH=. ./$(APP)

clean:
	rm -rf $(SOA) $(SOB) $(APP)


*.cpp: *.h

$(SOA): a.so.cpp
	g++ -shared -fPIC a.so.cpp -o $@

$(SOB): b.so.cpp
	g++ -shared -fPIC b.so.cpp -o $@

.PHONY: clean testa testb
