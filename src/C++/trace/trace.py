#!/usr/bin/python
# Trace whether functions are executed

import os

srcdir = '/home/yfy/iut/benchmarks/cctz'
make = 'make'
makeClean = 'make clean'
run = './civil_time_test; ./time_zone_lookup_test'
parse = '/home/yfy/iut/src/C++/trace/parse'

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
os.system(run)
walk(srcdir)
os.system(parse + ' ' + srcdir)

