# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.0

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/yfy/iut/benchmarks/avro-cpp-1.8.0

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/yfy/iut/benchmarks/avro-cpp-1.8.0

# Include any dependencies generated for this target.
include CMakeFiles/AvrogencppTests.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/AvrogencppTests.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/AvrogencppTests.dir/flags.make

CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o: CMakeFiles/AvrogencppTests.dir/flags.make
CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o: test/AvrogencppTests.cc
	$(CMAKE_COMMAND) -E cmake_progress_report /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o -c /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/AvrogencppTests.cc

CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/AvrogencppTests.cc > CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.i

CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/AvrogencppTests.cc -o CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.s

CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.requires:
.PHONY : CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.requires

CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.provides: CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.requires
	$(MAKE) -f CMakeFiles/AvrogencppTests.dir/build.make CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.provides.build
.PHONY : CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.provides

CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.provides.build: CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o

# Object files for target AvrogencppTests
AvrogencppTests_OBJECTS = \
"CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o"

# External object files for target AvrogencppTests
AvrogencppTests_EXTERNAL_OBJECTS =

AvrogencppTests: CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o
AvrogencppTests: CMakeFiles/AvrogencppTests.dir/build.make
AvrogencppTests: libavrocpp.so.1.8.0.0
AvrogencppTests: /usr/lib/x86_64-linux-gnu/libboost_filesystem.so
AvrogencppTests: /usr/lib/x86_64-linux-gnu/libboost_system.so
AvrogencppTests: /usr/lib/x86_64-linux-gnu/libboost_program_options.so
AvrogencppTests: /usr/lib/x86_64-linux-gnu/libboost_iostreams.so
AvrogencppTests: CMakeFiles/AvrogencppTests.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable AvrogencppTests"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/AvrogencppTests.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/AvrogencppTests.dir/build: AvrogencppTests
.PHONY : CMakeFiles/AvrogencppTests.dir/build

CMakeFiles/AvrogencppTests.dir/requires: CMakeFiles/AvrogencppTests.dir/test/AvrogencppTests.cc.o.requires
.PHONY : CMakeFiles/AvrogencppTests.dir/requires

CMakeFiles/AvrogencppTests.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/AvrogencppTests.dir/cmake_clean.cmake
.PHONY : CMakeFiles/AvrogencppTests.dir/clean

CMakeFiles/AvrogencppTests.dir/depend:
	cd /home/yfy/iut/benchmarks/avro-cpp-1.8.0 && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles/AvrogencppTests.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/AvrogencppTests.dir/depend

