/*-
 * ​​​
 * meanmirror
 * ⁣⁣⁣
 * Copyright (C) 2010 - 2020 the original author or authors.
 * ⁣⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ﻿﻿﻿﻿﻿
 */

package com.github.meanbeanlib.mirror;

import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableConsumer1;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableConsumer2;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableConsumer3;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableFunction1;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableFunction2;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableFunction3;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableFunction4;
import com.github.meanbeanlib.mirror.SerializableLambdas.SerializableLambda;
import org.junit.Test;

import java.io.BufferedReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExecutablesTest {

	@Test
	public void testFindMethod() {
		// final class
		assertNotNull(Executables.findMethod(String::isEmpty));

		//throws checked exception
		assertNotNull(Executables.findMethod(BufferedReader::readLine));

		// final method
		assertNotNull(Executables.findMethod(Thread::isAlive));

		// to get vararg method you must specify parameters in generics
		assertNotNull(Executables.<String, Class<?>[]> findMethod(getClass()::getDeclaredMethod));
		assertNotNull(Executables.findMethod((SerializableConsumer2<String, Class<?>[]>) getClass()::getDeclaredMethod));

		// Class.forName is overloaded.
		// to get overloaded method you must specify parameters in generics
		assertNotNull(Executables.<String> findMethod(Class::forName));
		assertNotNull(Executables.findMethod((SerializableConsumer1<String>) Class::forName));
		assertNotNull(Executables.<String, Boolean, ClassLoader> findMethodName(Class::forName));
		assertNotNull(Executables.findMethod((SerializableConsumer3<String, Boolean, ClassLoader>) Class::forName));

		// Works with inherited methods
		assertNotNull(Executables.findMethod(this::toString));

		assertNotNull(Executables.<ThreadGroup, String[], Long, int[]> findMethod(this::newThread));

		assertNotNull(Executables.findMethod(ExecutablesTest::testFindMethod));
		assertNotNull(Executables.findMethod(new ExecutablesTest()::testFindMethod));

		assertNotNull(Executables.findMethod(new HashMap<>()::entrySet));
		assertNotNull(Executables.findMethod(getClass()::getDeclaredMethods));

		assertNotNull(Executables.findExecutable((MySerializableLambda) new MyClass()::manyParams));
	}

	@Test
	public void testConstructor() {
		verifyConstructor(0, Executables.findConstructor(ExecutablesTest::new));
		verifyConstructor(0, Executables.findConstructor0(ExecutablesTest::new));

		verifyConstructor(1, Executables.findConstructor1((SerializableFunction1<String, Thread>) Thread::new));
		verifyConstructor(1, Executables.<String, Thread> findConstructor1(Thread::new));

		verifyConstructor(2, Executables.findConstructor((SerializableFunction2<ThreadGroup, Runnable, Thread>) Thread::new));
		verifyConstructor(2, Executables.<ThreadGroup, Runnable, Thread> findConstructor2(Thread::new));

		verifyConstructor(3,
				Executables.findConstructor((SerializableFunction3<ThreadGroup, Runnable, String, Thread>) Thread::new));
		verifyConstructor(3, Executables.<ThreadGroup, Runnable, String, Thread> findConstructor3(Thread::new));

		verifyConstructor(4, Executables.findConstructor(
				(SerializableFunction4<ThreadGroup, Runnable, String, String, MyConstructorClass>) MyConstructorClass::new));
		verifyConstructor(4, Executables
				.<ThreadGroup, Runnable, String, String, MyConstructorClass> findConstructor4(MyConstructorClass::new));

		verifyConstructor(4, Executables.findConstructor(MyConstructorClass::new));
	}

	@Test
	public void testGetName() throws Throwable {
		assertEquals("concat", Executables.findMethodName(String::concat));
		assertEquals("isEmpty", Executables.findMethodName(String::isEmpty));
		assertEquals("readLine", Executables.findMethodName(BufferedReader::readLine));
		assertEquals("isAlive", Executables.findMethodName(Thread::isAlive));
		assertEquals("getDeclaredField", Executables.findMethodName(getClass()::getDeclaredField));
		assertEquals("forName", Executables.<String> findMethodName(Class::forName));
		assertEquals("forName", Executables.<String, Boolean, ClassLoader> findMethodName(Class::forName));
		assertEquals("toString", Executables.findMethodName(this::toString));
		assertEquals("testFindMethod", Executables.findMethodName(ExecutablesTest::testFindMethod));
		assertEquals("testFindMethod", Executables.findMethodName(new ExecutablesTest()::testFindMethod));
		assertEquals("manyParams", Executables.findName((MySerializableLambda) new MyClass()::manyParams));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegal() {
		Executables.findName(new MyImpropertlyAppliedClass());
	}

	private void verifyConstructor(int paramCount, Constructor<?> constructor) {
		assertEquals(paramCount, constructor.getParameterCount());
	}

	private Thread newThread(ThreadGroup group, String[] name, long stackSize, int[] others) {
		return null;
	}

	private static interface MySerializableLambda extends SerializableLambda {
		public String manyParamsMethod(String a1, String a2, String a3, String a4, String a5, String a6, String a7);
	}

	public static class MyClass {

		public String manyParams(String a1, String a2, String a3, String a4, String a5, String a6, String a7) {
			return "hello world";
		}
	}

	public static class MyImpropertlyAppliedClass implements SerializableLambda {

		private static final long serialVersionUID = 5829601569699926661L;

		public String manyParams(String a1, String a2, String a3, String a4, String a5, String a6, String a7) {
			return "hello world";
		}
	}

	public static class MyConstructorClass implements SerializableLambda {

		public MyConstructorClass(ThreadGroup arg1, Runnable arg2, String arg3, String arg4) {

		}
	}
}
