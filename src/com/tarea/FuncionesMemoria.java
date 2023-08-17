package com.tarea;

import java.util.function.Function;

public class FuncionesMemoria {
	
	static void pause() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		
	}
	public static void main(String[] args) {
//		Function<Integer, Integer> doubleValue= x->{
//			pause();
//			return x*2;
//		};
//		
		

//		var doubleValueCache= Memorizer.memorizer(doubleValue);
		
		Function<Integer, Integer> doubleValue= Memorizer.memorizer(x->x*2);
		System.out.println(doubleValue.apply(1));
		System.out.println(doubleValue.apply(1));
		System.out.println(doubleValue.apply(1));
		System.out.println(doubleValue.apply(1));
		System.out.println(doubleValue.apply(1));
	}

}
