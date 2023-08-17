package com.tarea;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memorizer<T,U> {

	private final Map<T, U> cache= new HashMap<>();
	
	
	private Memorizer() {
		
	}
	
    private Function<T, U> memorizerInternal(Function<T, U> fn){
    	
    	return x->cache.computeIfAbsent(x,fn::apply);
    	
//		return x->{
//			if (cache.containsKey(x)) {
//				return cache.get(x);
//			}else
//			{
//				U tmp=fn.apply(x);
//				cache.put(x, tmp);	;
//				return tmp;
//			}
////		};
    	
    }
    
    public static <T, U>Function<T, U> memorizer(Function<T, U> fn){
    	
    	return new Memorizer<T, U>().memorizerInternal(fn);
    	
    }
		// TODO Auto-generated method stub

	
}
