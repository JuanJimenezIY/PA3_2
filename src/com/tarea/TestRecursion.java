package com.tarea;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.Supplier;

import com.tarea.lista.Lista;
import com.tarea.lista.ListaTR;
import com.tarea.lista.ListaTRH;
import com.tarea.recursion.TailCall;

public class TestRecursion {

	public static Integer suma(Integer x,Integer y){
		if (y==0) {
			return x;
		}else {
			return suma(x+1,y-1);
		}
		
	}
	public static TailCall<Integer> suma2(Integer x,Integer y){
		   return y==0
				?TailCall.ret(x)
						: TailCall.sus(()-> suma2(x+1, y-1));
		
//		
//		if (y==0) {
//			return TailCall.ret(x);
//		}else {
//		Supplier<TailCall<Integer>> tmp=()-> suma2(x+1, y-1);
//		    return  TailCall.sus(tmp);
//		 
//		}
		
	}
	
	//serie de fibonacci 
	//version recursiva
	public static Integer fibonacci( Integer elem){
		
		if (elem==0 || elem==1) {
			return elem;
			
		
		}else
		{
			
			return fibonacci(elem-1)+fibonacci(elem-2);
		
			}
		}
		
		
	
	
	//version Tail Recursiva
	 static BigInteger fibonacciTailAux1( BigInteger acc1,BigInteger acc2 ,BigInteger n){
			
			if (n.equals(BigInteger.ZERO) ) {
				return BigInteger.ZERO;
				
			
			}else if(n.equals(BigInteger.ONE)){
				return acc1.add(acc2);
			}
			
			else
			{
			    
				return fibonacciTailAux1(acc2,acc1.add(acc2),n.subtract(BigInteger.ONE));
				}
			}
	 
     
     
		
	static BigInteger fiboT(int n) {
		return fibonacciTailAux1(BigInteger.ZERO, BigInteger.ONE,BigInteger.valueOf(n));
	}
	
	//Version Tail Recrusiva en el heap
	

	 
	 
	  static  TailCall<BigInteger>  fibonacciTailCallAux( BigInteger acc1,BigInteger acc2 ,BigInteger n){
			
			if (n.equals(BigInteger.ZERO) ) {
				return TailCall.ret(BigInteger.ZERO);
				
			
			}else if(n.equals(BigInteger.ONE)){
				return   TailCall.ret( acc1.add(acc2)) ;
			}
			
			else
			{
				Supplier<TailCall<BigInteger>> ret=()-> fibonacciTailCallAux(acc2,acc1.add(acc2),n.subtract(BigInteger.ONE));
				return TailCall.sus(ret);
				
				}
			}
	     
	  
	     static BigInteger fibo(int n) {
	 		return fibonacciTailCallAux(BigInteger.ZERO, BigInteger.ONE,BigInteger.valueOf(n)).eval();
	 	}
	     
	     
	public static void main(String[] args) {
		
//		Return<Integer> ret = new Return<>(5);
//		Suspend<Integer> n1= new Suspend<>(ret);
//		Suspend<Integer> n2= new Suspend<>(n1);
//		Suspend<Integer> ini= new Suspend<>(n2);
//		
//		
//		TailCall<Integer> tmp =ini;
//		while (tmp.isSuspend()) {
//			tmp= tmp.resume();
//		}
//		System.out.println(tmp);
//		var ini = suma2(5, 1000000);
//		Integer suma = ini.eval();
//		System.out.println(suma);
//		var fibo = fibonacci(5);
//		System.out.println(fibo);
		// con tail es mucho mas optimizado
//		var num=fiboT(10000);
//		System.out.println(num);
//		
//	var fibo=fibo(100);
//		System.out.println(fibo);
//		
		
		var ls=Lista.of(1,2,3,4,5);
		var ls2=Lista.of(6,7,8,9,10);
		ls.append(5);
		System.out.println(ls.append(5));
		var lsa=ListaTR.appendTR(ls, 10);
		System.out.println(lsa);
		var lstrh=ListaTRH.appendTRH(ls, 1);
		System.out.println(lstrh);
		
		var lspre=ListaTR.preppendTR(ls, 10);
		System.out.println(lspre);
		var lspreh=ListaTRH.preppendTRH(ls, 10);
		System.out.println(lspreh);
		
		var lsremove = ls.remove(2);
		System.out.println(lsremove);
		var lsrr=ListaTR.removeTR(ls, 2);
		System.out.println(lsrr);
		var lsrrh=ListaTRH.removeTRH(ls, 2);
		System.out.println(lsrrh);
		
		var drop = ls.drop(3);
		System.out.println(drop);
		
		var dropr = ListaTR.drop(ls,2);
		System.out.println("drop"+dropr);
		
		var droprh = ListaTRH.drop(ls,2);
		System.out.println("droph"+droprh.eval());
		
		var take =ls.take(3);
		System.out.println(take);
		var taker = ListaTR.take(ls,2);
		System.out.println(taker);
		
		var takerh = ListaTRH.take(ls,2);
		System.out.println("take heap"+takerh);
		
		var dropwhile= ls.dropWhile(x->x%2==0);
		System.out.println(dropwhile);
		
		var dropwhiler= ListaTR.dropWhile(ls,x->x%2==0);
		System.out.println("dw"+dropwhiler);
		
		var dropwhilerh= ListaTRH.dropWhile(ls,x->x%2==0);
		System.out.println("dwh"+dropwhilerh);
		
		var takewhile= ls.takeWhile(x->x%2==0);
		System.out.println(takewhile);
		
		var takewhiler= ListaTR.takeWhile(ls,x->x%2==0);
		System.out.println("tw"+takewhiler);
		
		var takewhilerh= ListaTRH.takeWhile(ls,x->x%2==0);
		System.out.println("twh"+takewhilerh);
		
		
		var concatenar =ListaTR.concatenarTR(ls, ls2 );
		System.out.println(concatenar);
		
		var concatenarh =ListaTRH.concatenarTRH(ls, ls2 );
		System.out.println("conch"+concatenarh.eval());
		
		var repla= ls.replace(2, 50);
		System.out.println(repla);
		var replatr= ListaTR.replaceTR(ls, 2, 50);
		System.out.println(replatr);
		
		var replatrh= ListaTRH.replaceTRH(ls, 2, 50);
		System.out.println("reph"+replatrh);
		
		var size = ListaTR.size(ls);
		System.out.println(size);
		var sizeh = ListaTRH.size(ls);
		System.out.println(sizeh);
		
		var invertirr =ListaTR.invertir(ls);
		System.out.println(invertirr);
		var invertirrh =ListaTRH.invertir(ls);
		System.out.println("invh"+invertirrh);
		
		Function<Integer, Double> fn= x->x.doubleValue();
		
		var mapr= ListaTR.mapTR(ls, fn);
		System.out.println(mapr);
		
		var maprh= ListaTRH.mapTRH(ls, fn);
		System.out.println("mapeoh"+maprh);
		
		var reduce= ListaTR.reduce(ls, 0, t->u->u+t);
		System.out.println(reduce);
		
		var reduceh= ListaTRH.reduceTRH(ls, 0, t->u->u+t);
		System.out.println("REDUCEH"+reduceh.eval());
		
		var fold= ls.foldRight(0, t->u->u+t);
		System.out.println(fold);
		
		Integer foldl = ListaTR.foldLeft(ls, 0,u->lista->u+lista);
		System.out.println(foldl);
		
		TailCall<Integer> foldlh = ListaTRH.foldLeftTRH(ls, 0,u->lista->u+lista);
		System.out.println(foldlh.eval());
		
		var foldr = ListaTR.foldRight(ls, 0, t->u->u+t);
		System.out.println(foldr);
		
		var foldrH = ListaTRH.foldRightTRH(ls, 0, t->u->u+t);
		System.out.println(foldrH.eval());
	}
}
