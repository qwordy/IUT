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

# Utility rule file for bigrecord2_hh.

# Include the progress variables for this target.
include CMakeFiles/bigrecord2_hh.dir/progress.make

CMakeFiles/bigrecord2_hh: bigrecord2.hh

bigrecord2.hh: avrogencpp
bigrecord2.hh: jsonschemas/bigrecord2
	$(CMAKE_COMMAND) -E cmake_progress_report /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating bigrecord2.hh"
	./avrogencpp -p - -i /home/yfy/iut/benchmarks/avro-cpp-1.8.0/jsonschemas/bigrecord2 -o bigrecord2.hh -n testgen2 -U

bigrecord2_hh: CMakeFiles/bigrecord2_hh
bigrecord2_hh: bigrecord2.hh
bigrecord2_hh: CMakeFiles/bigrecord2_hh.dir/build.make
.PHONY : bigrecord2_hh

# Rule to build all files generated by this target.
CMakeFiles/bigrecord2_hh.dir/build: bigrecord2_hh
.PHONY : CMakeFiles/bigrecord2_hh.dir/build

CMakeFiles/bigrecord2_hh.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/bigrecord2_hh.dir/cmake_clean.cmake
.PHONY : CMakeFiles/bigrecord2_hh.dir/clean

CMakeFiles/bigrecord2_hh.dir/depend:
	cd /home/yfy/iut/benchmarks/avro-cpp-1.8.0 && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles/bigrecord2_hh.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/bigrecord2_hh.dir/depend

