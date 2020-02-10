/*-
 * ​​​
 * meanmirror
 * ⁣⁣⁣
 * Copyright (C) 2020 the original author or authors.
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

import java.io.Serializable;

public class SerializableLambdas {

	public static interface SerializableLambda extends Serializable {
	}

	@FunctionalInterface
	public static interface SerializableConsumer0 extends SerializableLambda {
		void exec() throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableConsumer1<T1> extends SerializableLambda {
		void exec(T1 t1) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableConsumer2<T1, T2> extends SerializableLambda {
		void exec(T1 t1, T2 t2) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableConsumer3<T1, T2, T3> extends SerializableLambda {
		void exec(T1 t1, T2 t2, T3 t3) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableConsumer4<T1, T2, T3, T4> extends SerializableLambda {
		void exec(T1 t1, T2 t2, T3 t3, T4 t4) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableFunction0<R> extends SerializableLambda {
		R exec() throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableFunction1<T1, R> extends SerializableLambda {
		R exec(T1 t1) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableFunction2<T1, T2, R> extends SerializableLambda {
		R exec(T1 t1, T2 t2) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableFunction3<T1, T2, T3, R> extends SerializableLambda {
		R exec(T1 t1, T2 t2, T3 t3) throws Throwable;
	}

	@FunctionalInterface
	public static interface SerializableFunction4<T1, T2, T3, T4, R> extends SerializableLambda {
		R exec(T1 t1, T2 t2, T3 t3, T3 t4) throws Throwable;
	}
}
