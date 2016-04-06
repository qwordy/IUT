CC = g++
OBJ = main.o fly.o
CXXFLAGS = -fprofile-arcs -ftest-coverage

main: $(OBJ)
	$(CC) -o $@ $^ -lgcov

%.o: %.cpp %.h
	$(CC) $(CXXFLAGS) -c -o $@ $<

.PHONY: clean
clean:
	-rm -f $(OBJ) main

