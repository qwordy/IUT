CC = g++
OBJ = main.o fly.o d/book.o
CXXFLAGS = -fprofile-arcs -ftest-coverage

main: $(OBJ)
	$(CC) -o $@ $^ -fprofile-arcs

%.o: %.cpp %.h
	$(CC) $(CXXFLAGS) -c -o $@ $<

.PHONY: clean
clean:
	-rm -f $(OBJ) main

