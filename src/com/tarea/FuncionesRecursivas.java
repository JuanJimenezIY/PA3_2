package com.tarea;

import java.util.function.Function;

public class FuncionesRecursivas {

	static int addMetodo(int x, int y) {
		
		return y==0
				?x
						:addMetodo(x+1, y-1);
//		if (y==0) 
//			return x;
//		else
//			return addMetodo(x+1, y-1);
	}
	//si quitamos el static seria de isntancia ,or defecto se incialian en null
	static Function<Integer, Function<Integer, Integer>> add=
			
			x->y-> y==0
			?x
					:FuncionesRecursivas.add.apply(x+1).apply(y-1);
//			{
//		if (y==0) 
//		return x;
//	else
//		return FuncionesRecursivas.add.apply(x+1).apply(y-1);
//};
	public static void main(String[] args) {
		
		int ret1 =addMetodo(5, 3);
		System.out.println(ret1);
		
		//---- debe ser de sintacia o estatica
//		Function<Integer, Function<Integer, Integer>> add=x->y->{
//			if (y==0) 
//				return x;
//			else
//				return add.apply(x+1).apply(y-1);
//		};
		
		var ret2=FuncionesRecursivas.add.apply(5).apply(3);
		System.out.println(ret2);
		
		
	}
}
