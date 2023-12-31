package com.tarea;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FuncionesRecursivas2 {

	static Map<Integer, Integer> cache =new HashMap<>();
	
	
	static void pause() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		
	}
	
	static Integer doubleValue(Integer x) {
			pause();
			return x*2;
	}
	
	static Integer doubleValueCache(Integer x) {
		
		
		
		if (cache.containsKey(x)) {
			return cache.get(x);
	}
	else {
		Integer tmp=doubleValue(x);
		cache.put(x, tmp);
		return tmp;
	}
		
	}
	static Function<Integer, Integer> doubleValue2=
			x->cache.computeIfAbsent(x, y->{
				pause();
				return y*2;
			});
			
			
	
	
	
	public static void main(String[] args) {
//		System.out.println(doubleValue(1));
//		System.out.println(doubleValue(1));
//		System.out.println(doubleValue(1));
//		System.out.println(doubleValue(1));
//		System.out.println(doubleValue(1));
		
		System.out.println(doubleValue2.apply(1));
		System.out.println(doubleValue2.apply(1));
		System.out.println(doubleValue2.apply(1));
		System.out.println(doubleValue2.apply(1));
		System.out.println(doubleValue2.apply(1));
	
	}
}
