#!/usr/bin/python
# Trace whether functions are executed

import os

srcdir = '/home/yfy/iut/benchmarks/cctz'
make = 'make'
makeClean = 'make clean'
run = './civil_time_test; ./time_zone_lookup_test'
tests = ['civil_time_test', 'time_zone_lookup_test']
parse = '/home/yfy/iut/src/C++/trace/parse'

testCases = []

class TestCase:
  def __init__(self, filename, testcase):
    self.f = filename
    self.t = testcase

  def __str__(self):
    return self.f + ' ' + self.t

def listTests():
  for test in tests:
    f = os.popen('./' + test + ' --gtest_list_tests')
    f.readline()
    line = f.readline()
    while line != '':
      if line[0] != ' ':  # test suite
        suite = line.strip('\n')
      else:  # test case
        case = line.strip('\n ')
        testCases.append(TestCase(test, suite + case))
      line = f.readline()
    f.close()
  for i in testCases:
    print i

def runTests():
  for test in testCases:
    os.system('./' + test.f + ' --gtest_filter=' + test.t)

def walk(dir):
  for root, dirs, files in os.walk(dir):
    for f in files:
      path = os.path.join(root, f)
      name, ext = os.path.splitext(path)
      if ext == '.gcda':
        cmd = 'gcov -b %s > /dev/null' % name
        print cmd
        os.system(cmd)

os.chdir(srcdir)
#os.system(makeClean)
os.system(make)
listTests()
runTests()
#walk(srcdir)
#os.system(parse + ' ' + srcdir)

