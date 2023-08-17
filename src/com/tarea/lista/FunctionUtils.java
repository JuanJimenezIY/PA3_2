package com.tarea.lista;

import java.util.function.Function;

public class FunctionUtils {
	public static <T,U,V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> comp2(){

	    return  f -> g -> x -> g

	            .apply(f.apply(x));



	}

	   public static <T> Function<Function<T, T>, Function<Function<T, T>, Function<T, T>>> comp(){

	        return  f -> g -> x -> g

	                .apply(f.apply(x));

	    

	    }

	public static Function<Integer, Integer> compo(Function<Integer, Integer> f, Function<Integer, Integer> g) {



	    

	    return new Function<Integer, Integer>() {

	        @Override

	        public Integer apply(Integer x) {







	            return g.apply(f.apply(x));

	        }

	    };

	}

}
