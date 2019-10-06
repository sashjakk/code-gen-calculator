# Code Generation with KAPT

Simple demonstration how to use KotlinPoet to generate code with combination of 
KAPT and simple yet another calculator example.

## Project structure

* app - cli application module, contains hand written calculator and 
operation implementations.
* annotation - single annotation file module for marking classes suitable for 
annotation processing tool (requires to be in a separate module to avoid 
circular dependencies between modules).
* kapt-tool - annotation processor module, which actually generates code using 
KotlinPoet.

## Useful links

* [KotlinPoet docs](https://square.github.io/kotlinpoet/)
* [Google AutoService library](https://github.com/google/auto/tree/master/service)
* [KotlinConf talk about Annotation Processing](https://youtu.be/a2RoLFzrFG0)
* [Gradle multi - project builds](https://guides.gradle.org/creating-multi-project-builds/)