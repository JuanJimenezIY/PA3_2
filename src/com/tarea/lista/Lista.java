package com.tarea.lista;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public sealed interface Lista<T> permits Nil,Cons {

	Lista NIL = new Nil();

	T head();

	Lista<T> tail();

	boolean isEmpty();

	static <T> Lista<T> of(T h, Lista<T> t) {
		return new Cons<>(h, t);
	}

	static <T> Lista<T> of(T... elems) {
		Lista<T> ret = Lista.NIL;
		for (int i = elems.length - 1; i >= 0; i--) {
			T h = elems[i];
			var tmp = Lista.of(h, ret);
			ret = tmp;
		}
		return ret;

	}

	// imperativo
	default String toString1() {
		StringBuilder sb = new StringBuilder();
		var tmp = this;
		while (!tmp.isEmpty()) {
			sb.append(tmp.head());
			sb.append(",");
			tmp = tmp.tail();
		}
		sb.append("NIL");
		return sb.toString();
	}

	default Lista<T> append(T elem) {

		return isEmpty() 
				? Lista.of(elem)
						: Lista.of(head(), tail().append(elem));
	}
	/*
	 * 
	 * 
	 * 
	 * if(isEmpty()) {
	 *  return Lista.of(elem); 
	 *  }else 
	 *  { return Lista.of(head(),tail().append(elem)); } }
	 */

	default Lista<T> prepend(T elem) {

		return Lista.of(elem, this);

	}

	default Lista<T> remove(T elem) {

		return isEmpty() 
				? NIL 
						: head().equals(elem) 
						? tail() 
								: Lista.of(head(), tail().remove(elem));

		/*
		 * if(isEmpty()) { 
		 * return NIL; }
		 * 
		 * else { if(head().equals(elem)) {
		 * 
		 * return tail(); }else { return Lista.of(head(),tail().remove(elem)); }
		 * 
		 * }
		 */
	}
	
	
	
//	default TailCall<Lista<T>> removeRec(T elem){
//		  if(isEmpty()) { 
//			  
//			  return TailCall.ret(NIL);
//			  }
//		  
//		  else {
//			  if(head().equals(elem)) {
//		  
//				  return  TailCall.ret(tail()) ; 
//		  }else { 
//			  
//			  Supplier<TailCall<Lista<T>>> tmp=()-> Lista.of(head(),tail().removeRec(elem));
//			  return TailCall.sus(()-> tmp );
//					  
//					  }
//		  
//		  }
//	}

	default Lista<T> drop(int n) {

		return isEmpty() || n <= 0 
				? this 
						: drop(n - 1).tail();

		/*
		 * if(isEmpty()|| n<=0) { return this;
		 * 
		 * } return drop(n-1).tail(); /*
		 * 
		 * if(isEmpty()) { return NIL; }else { if(n<=0) { return this; }else { return
		 * drop(n-1).tail(); } }
		 */
	}

	default Lista<T> dropWhile(Predicate<T> p) {

		return isEmpty() ? NIL : p.test(head()) ? tail().dropWhile(p) : Lista.of(head(), tail().dropWhile(p));
		/*
		 * if(isEmpty()) { return NIL; }else if(p.test(head())){
		 * 
		 * return tail().dropWhile(p);
		 * 
		 * }else { return Lista.of(head(),tail().dropWhile(p));
		 * 
		 * }
		 * 
		 */

	}

	default Lista<T> take2(int n) {

		if (isEmpty() || n <= 0) {
			return Lista.of();
		}
		return tail().take2(n - 1).prepend(head());

		/*
		 * if(isEmpty()) { return NIL; }else { if(n<=0) {
		 * 
		 * return Lista.of();
		 * 
		 * }else {
		 * 
		 * return tail().take(n-1).prepend(head());
		 * 
		 * } }
		 */
	}

	default Lista<T> take(int n) {

		return isEmpty() || n <= 0 ? NIL : Lista.of(head(), tail().take(n - 1));

		/*
		 * if(isEmpty()||n<=0 ) { return NIL; } else { return
		 * Lista.of(head(),tail().take(n-1)); } /* if(isEmpty()) { return NIL; }else {
		 * if(n<=0) {
		 * 
		 * return NIL;
		 * 
		 * } return Lista.of(head(),tail().take(n-1)); }
		 */
	}

	default Lista<T> takeWhile(Predicate<T> p) {
		return isEmpty() ? NIL : p.test(head()) ? Lista.of(head(), tail().takeWhile(p)) : tail().takeWhile(p);
		/*
		 * 
		 * 
		 * 
		 * if(isEmpty()) { return NIL; }else if(p.test(head())){ return
		 * Lista.of(head(),tail().takeWhile(p));
		 * 
		 * 
		 * }else {
		 * 
		 * return tail().takeWhile(p); }
		 * 
		 */

	}

	default Lista<T> concatMio(Lista<T> lista) {

		if (isEmpty()) {
			return NIL;
		} else if (lista == NIL) {
			return this;
		} else {

			return this.append(lista.head()).concat(lista.tail());
		}

	}

	default Lista<T> concat(Lista<T> lista) {

		return isEmpty() 
				? lista 
						: Lista.of(head(), tail().concat(lista));
		/*
		 * if(isEmpty()) { 
		 * return lista; 
		 * }else
		 * 
		 * 
		 * return Lista.of(head(),tail().concat(lista));
		 */
	}

	static Integer maxAux(Lista<Integer> l, Integer max) {
		if (l == NIL) {
			return max;
		} else if (max < l.head()) {
			return maxAux(l.tail(), l.head());
		} else
			return maxAux(l.tail(), max);

	}

	static Integer max(Lista<Integer> t) {

		return maxAux(t, t.head());
	}

	static Integer sumaAux(Lista<Integer> lista, Integer t) {
		if (lista == NIL) {
			return t;
		} else

			return t + sumaAux(lista.tail(), lista.head());
	}

	static Integer suma(Lista<Integer> t) {

		return sumaAux(t.tail(), t.head());
	}


	 default Lista<T> reemplazar(T elem,T newElem){
		 
		 
		 if (isEmpty()) {
			return Lista.NIL;
		}
		 
		return head().equals(elem)
				?Lista.of(newElem,tail())
						:Lista.of(head(),tail().reemplazar(elem, newElem));
		 /*
		 if (head().equals(elem)) {
			return Lista.of(newElem,tail());
		}
		 else {
			return Lista.of(head(),tail().reemplazar(elem, newElem));
		}	
		*/
		}
	 default Optional<T> contains(T elem){
		 if (isEmpty()) {
			return Optional.empty();
		} 
		 
		 return head().equals(elem)
				 ?Optional.of(head())
		 			:tail().contains(elem);
		 /*
		 if (head().equals(elem)) {
			return Optional.of(head());
		}else
		{
			return tail().contains(elem);
		}*/
	 }
	 
	 default void forEach(Consumer<T> fn) {
		 if (!isEmpty()) {
			fn.accept(head());
			tail().forEach(fn);
		}
	 }
	 
	 default Lista<T> replace( T elem, T newElem ) {
	        if( isEmpty() ) {
	            return Lista.NIL;
	        }

	        return head().equals(elem)
	                ? Lista.of( newElem, tail() )
	                : Lista.of( head(), tail().replace(elem, newElem) );
	    }

	


	    default Integer size() {
	    	return foldRight(0, t->u->u+1);
//	        if( isEmpty() ) {
//	            return 0;
//	        }
//	        else {
//	            return 1 + tail().size();
//	        }
	    }
	    default Lista<T> inv(){
	    	
	    	return foldLeft(Lista.NIL, list -> t ->list.prepend(t));
//	    	var tmp =this;
//	    	
//	    	Lista<T> retTmp= Lista.NIL;
//	    	while (tmp!= Lista.NIL) {
//	    	
//	    		
//	    		
//	    		retTmp=Lista.of( tmp.head(),retTmp);
//	    		tmp= tmp.tail();
//				
//			}
//	    	
//	    	return retTmp;
	    }
	    //recuersiva
	    default <U>Lista<U> map(Function<T, U> fn){
	    	
	    	return this.isEmpty()
	    			? Lista.NIL
	    	:Lista.of(fn.apply(this.head()),tail().map(fn));
	    	/*
	    	if (this.isEmpty()) {
	    		return Lista.NIL;
			}
	    	else {
	    	/*	var tmp=tail().map(fn);
	    		var newCab= fn.apply(this.head());
	    		return tmp.prepend(newCab);
	    		

	    		return Lista.of(fn.apply(this.head()),tail().map(fn));
	    	*/
	    }
	    //iterativa
	    default <U>Lista<U> mapIt(Function<T, U> fn){
	    	var tmp =this;
	    	
	    	Lista<U> retTmp= Lista.NIL;
	    	while (tmp!= Lista.NIL) {
	    		T elem = tmp.head();
	    		U newElem=fn.apply(elem);
	    		
	    		retTmp=Lista.of(newElem,retTmp);
	    		tmp= tmp.tail();
				
			}
	    	
	    	return retTmp.inv();
	    }/*
	    
	    default Lista<T> invertir() {
			return invertirAux(this, Lista.NIL);
		}
		
		private static <T> Lista<T> invertirAux(Lista<T> list,Lista<T> retorno) {
			return list!=null
				   ? invertirAux(list.tail(), retorno.prepend(list.head())) 
				   : retorno;
		}
		*/
		
	    default T reduce(T identity,Function<T,Function<T, T>>fn ) {
	    	T acum =identity;
	    	var tmp= this;
	    	while (!tmp.isEmpty()) {
	    	
	    		acum=fn.apply(tmp.head()).apply(acum);
	    		tmp=tmp.tail();
				
			}
	    	return acum;
	    	
	    }
	  //f: UXT->U
	    default <U> U foldLeft(U identity,Function<U,Function<T, U>>fn ) {
	    	U acum =identity;
	    	var tmp= this;
	    	while (!tmp.isEmpty()) {
	    	
	    		acum=fn.apply(acum).apply(tmp.head());
	    		tmp=tmp.tail();
				
			}
	    	return acum;
	    }

	  //f: TXU->U
	    default <U> U foldRight(U identity,Function<T,Function<U, U>>fn ) {
	  
	    	return this.isEmpty()
	    			?identity
	    					: fn.apply(this.head()).apply(this.tail().foldRight(identity, fn));
	    	/*
	    	if (this.isEmpty()) {
				return identity;
			} else {
				
				T elem =this.head();
				U tmp = this.tail().foldRight(identity, fn);
				return fn.apply(elem).apply(tmp);
			}*/
	    }
}
