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
include CMakeFiles/buffertest.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/buffertest.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/buffertest.dir/flags.make

CMakeFiles/buffertest.dir/test/buffertest.cc.o: CMakeFiles/buffertest.dir/flags.make
CMakeFiles/buffertest.dir/test/buffertest.cc.o: test/buffertest.cc
	$(CMAKE_COMMAND) -E cmake_progress_report /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/buffertest.dir/test/buffertest.cc.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/buffertest.dir/test/buffertest.cc.o -c /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/buffertest.cc

CMakeFiles/buffertest.dir/test/buffertest.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/buffertest.dir/test/buffertest.cc.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/buffertest.cc > CMakeFiles/buffertest.dir/test/buffertest.cc.i

CMakeFiles/buffertest.dir/test/buffertest.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/buffertest.dir/test/buffertest.cc.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/buffertest.cc -o CMakeFiles/buffertest.dir/test/buffertest.cc.s

CMakeFiles/buffertest.dir/test/buffertest.cc.o.requires:
.PHONY : CMakeFiles/buffertest.dir/test/buffertest.cc.o.requires

CMakeFiles/buffertest.dir/test/buffertest.cc.o.provides: CMakeFiles/buffertest.dir/test/buffertest.cc.o.requires
	$(MAKE) -f CMakeFiles/buffertest.dir/build.make CMakeFiles/buffertest.dir/test/buffertest.cc.o.provides.build
.PHONY : CMakeFiles/buffertest.dir/test/buffertest.cc.o.provides

CMakeFiles/buffertest.dir/test/buffertest.cc.o.provides.build: CMakeFiles/buffertest.dir/test/buffertest.cc.o

# Object files for target buffertest
buffertest_OBJECTS = \
"CMakeFiles/buffertest.dir/test/buffertest.cc.o"

# External object files for target buffertest
buffertest_EXTERNAL_OBJECTS =

buffertest: CMakeFiles/buffertest.dir/test/buffertest.cc.o
buffertest: CMakeFiles/buffertest.dir/build.make
buffertest: libavrocpp.so.1.8.0.0
buffertest: /usr/lib/x86_64-linux-gnu/libboost_filesystem.so
buffertest: /usr/lib/x86_64-linux-gnu/libboost_system.so
buffertest: /usr/lib/x86_64-linux-gnu/libboost_program_options.so
buffertest: /usr/lib/x86_64-linux-gnu/libboost_iostreams.so
buffertest: CMakeFiles/buffertest.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable buffertest"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/buffertest.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/buffertest.dir/build: buffertest
.PHONY : CMakeFiles/buffertest.dir/build

CMakeFiles/buffertest.dir/requires: CMakeFiles/buffertest.dir/test/buffertest.cc.o.requires
.PHONY : CMakeFiles/buffertest.dir/requires

CMakeFiles/buffertest.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/buffertest.dir/cmake_clean.cmake
.PHONY : CMakeFiles/buffertest.dir/clean

CMakeFiles/buffertest.dir/depend:
	cd /home/yfy/iut/benchmarks/avro-cpp-1.8.0 && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles/buffertest.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/buffertest.dir/depend

