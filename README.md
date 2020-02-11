# meanmirror

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.meanbeanlib/meanmirror/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.meanbeanlib/meanmirror)
![Java CI](https://github.com/meanbeanlib/meanmirror/workflows/Java%20CI/badge.svg)

## Usage
Simply pass a [method reference](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html) to `Executables`
class. This will provide you with a `java.lang.reflect.Method` or a `java.lang.reflect.Constructor`.

    // Get method
    Method isEmptyMethod = Executables.findMethod(String::isEmpty);
    
    // Get method
    Method getPropertyMethod = Executables.findMethod(MyBean::getProperty);
    
    // Get method that throws checked exception
    Method exceptionThrowingMethod = Executables.findMethod(BufferedReader::readLine);
    
    // to get vararg method you must specify parameters in generics
    Method varargMethod = Executables.<Method, String, Class<?>[]> findMethod(getClass()::getDeclaredMethod)

Notice that you have to provide the method parameters in generics under certain circumstances (When the method is overloaded, or if the method has a varargs parameter).    
    
The project requires Java 8 or above.

## Dependency Management

Since this project is not in any Maven repo you must use [JitPack](https://jitpack.io/) to add the dependency.

## History

This is a fork of [safety-mirror](https://github.com/Hervian/safety-mirror/) project.

## License

This code is released under the Apache 2.0 license.
​​​​​​

	meanmirror
	
	Copyright (C) 2020 the original author or authors.
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	     http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


	