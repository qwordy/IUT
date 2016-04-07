## Technical design

### Execution path

The goal is to obtain, for each function, whether it is entered. gcov is a code
coverage tool for C/C++, which can be used here. Basic steps:
- Modify makefile
- Make and run
- gcov -b filename
- Analyse *.gcov

