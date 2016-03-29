# Design of Iteration 2

Iteration 2 (D1 ~ D4):

C++ IUT basic version - compare two versions of projects in respective
directories

## 1. Instrumentation

Instrument log statements at the beginning of each function to trace the
execution path of each test run. A source-to-source transformation is needed for
the task. Clang library is a candidate for parsing and modifying the source
code. It provides APIs of generating and manipulating the AST(abstract syntax
tree). Other C++ parsers or frontends of compiler possibily work too.

Several C++ parser:
- Clang
- Elsa
- Synopsis
- G++

## 2. Difference analysis

The difference analysis compares corresponding functions of two versions.

**Definition 1:**

Two functions are considered identical if and only if the ASTs of them are
identical, otherwise the original function is considered modified.

We classify the modification of code as
- Modify a function
- Remove a function
- Add a function
- Modification outside functions

#### Modify a function

The function is marked 'modified'

#### Remove a function

The function is marked 'modified'

#### Add a function

Do nothing because simply adding a function does not affect the execution of
program under some assumptions. Another function must be modified if the added
function is invoked.

#### Modification outside functions

Rerun all the test cases.

## 3. Learning testing tools

## 4. Tracing, identification, rerunning


