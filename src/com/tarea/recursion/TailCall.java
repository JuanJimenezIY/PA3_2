package com.tarea.recursion;

import java.util.function.Supplier;

public sealed interface TailCall<T> permits Return,Suspend {

	T eval();
	TailCall<T> resume();
	
	boolean isSuspend();
	//-------
	static <T>TailCall<T> ret(T t){
		return new Return<T>(t);
	}
	static <T> TailCall<T> sus( Supplier<TailCall<T>> s){
		return new Suspend<>(s);
	}
}
