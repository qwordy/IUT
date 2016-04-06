CC = g++
OBJ = main.o inst.o
CXXFLAGS = -Wall -std=c++11

main: $(OBJ)
	$(CC) -o $@ $^

.PHONY: clean
clean:
	-rm -f $(OBJ) main

