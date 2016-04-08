srcdir=/home/yfy/iut/src/C++/test
make=make
run=./main
bin=/home/yfy/iut/src/C++/trace

cd $srcdir
$make clean
$make
$run

# walk through dir, gcov
walk() {
  for file in `ls $1`
  do
    local path=$1/$file
    if [ -f $path ] && [ ${file##*.} = gcda ]
    then
      echo gcov $path
      gcov -b $1/${file%.*} > /dev/null
    elif [ -d $path ]
    then
      echo enter $path
      walk $path
      echo leave $path
    fi
  done
  rm -f $1/*.gcno $1/*.gcda
}

walk $srcdir
$bin/parse $srcdir
#rm -f *.gcov


