package com.tarea.lista;

import java.util.function.Function;
import java.util.function.Predicate;

import com.tarea.recursion.TailCall;

public class ListaTRH {

	public static <T> TailCall<Lista<T>> appendAuxTRH(Lista<T> ls, T elem, Lista<T> tmp) {

		if (ls.isEmpty()) {

			return TailCall.ret(invertir(Lista.of(elem, tmp)));
					
				

		} else

		{
			return TailCall.sus(() -> appendAuxTRH(ls.tail(), elem, Lista.of(ls.head(), tmp)));

		}
	}

	public static <T> Lista<T> appendTRH(Lista<T> ls, T elem) {

		return appendAuxTRH(ls, elem, Lista.of()).eval();

	}

	public static <T> TailCall<Lista<T>> preppendAuxTRH(Lista<T> ls, T elem, Lista<T> tmp) {

		if (ls.isEmpty()) {

			return TailCall.ret(Lista.of(elem, invertir(tmp)));

		} else

		{
			return TailCall.sus(() -> preppendAuxTRH(ls.tail(), elem, Lista.of(ls.head(), tmp)));

		}
	}

	public static <T> Lista<T> preppendTRH(Lista<T> ls, T elem) {

		return preppendAuxTRH(ls, elem, Lista.of()).eval();

	}

	public static <T> TailCall<Lista<T>> removeAuxTRH(Lista<T> ls, T elem, Lista<T> tmp) {
		if (ls.isEmpty()) {
			return TailCall.ret(invertir(tmp));
		}

		else {
			if (ls.head().equals(elem)) {

				return TailCall.sus(() -> removeAuxTRH(ls.tail().tail(), elem, Lista.of(ls.tail().head(), tmp)));

			} else {
				return TailCall.sus(() -> removeAuxTRH(ls.tail(), elem, Lista.of(ls.head(), tmp)));

			}

		}
	}

	public static <T> Lista<T> removeTRH(Lista<T> ls, T elem) {

		return removeAuxTRH(ls, elem, Lista.of()).eval();

	}

	public static <T> TailCall<Lista<T>> drop(Lista<T> ls, Integer n) {

		if (ls.isEmpty() || n <= 0) {
			return TailCall.ret(ls);

		}
		return TailCall.sus(() -> drop(ls.tail(), n - 1));

	}

	static <T> TailCall<Lista<T>> dropWhileAux(Lista<T> ls, Predicate<T> p, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return TailCall.ret(tmp);
		}

		else if (p.test(ls.head())) {

			return TailCall.sus(() -> dropWhileAux(ls.tail(), p, tmp));

		} else {
			return TailCall.sus(() -> dropWhileAux(ls.tail(), p, appendTRH(tmp, ls.head())));

		}

	}

	public static <T> Lista<T> dropWhile(Lista<T> ls, Predicate<T> p) {

		return dropWhileAux(ls, p, Lista.of()).eval();

	}

	static <T> TailCall<Lista<T>> takeAux(Lista<T> ls, Integer n, Lista<T> tmp) {

		if (ls.isEmpty() || n <= 0) {
			return TailCall.ret(tmp);

		}
		return TailCall.sus(() -> takeAux(ls.tail(), n - 1, appendTRH(tmp, ls.head())));

	}

	public static <T> Lista<T> take(Lista<T> ls, Integer elem) {

		return takeAux(ls, elem, Lista.of()).eval();

	}

	static <T> TailCall<Lista<T>> takeWhileAux(Lista<T> ls, Predicate<T> p, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return TailCall.ret(tmp);
		}

		else if (p.test(ls.head())) {

			return TailCall.sus(() -> takeWhileAux(ls.tail(), p, appendTRH(tmp, ls.head())));

		} else {

			return TailCall.sus(() -> takeWhileAux(ls.tail(), p, tmp));

		}

	}

	public static <T> Lista<T> takeWhile(Lista<T> ls, Predicate<T> p) {

		return takeWhileAux(ls, p, Lista.of()).eval();

	}

	public static <T> TailCall<Lista<T>> concatenarTRH(Lista<T> lista1, Lista<T> lista2) {

		if (lista2.isEmpty()) {
			return TailCall.ret(lista1);
		} else {

			return TailCall.sus(() -> concatenarTRH(appendTRH(lista1, lista2.head()), lista2.tail()));

		}

	}

	static <T> TailCall<Lista<T>> invertirTRHAux(Lista<T> ls, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return TailCall.ret(tmp);
		} else {

			return TailCall.sus(() -> invertirTRHAux(ls.tail(), Lista.of(ls.head(), tmp)));

		}

	}

	public static <T> Lista<T> invertir(Lista<T> ls) {

		return invertirTRHAux(ls, Lista.of()).eval();
	}

	static <T> TailCall<Lista<T>> replaceTRHAux(Lista<T> ls, T elem, T newElem, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return TailCall.ret(invertir(tmp));

		} else if (ls.head().equals(elem)) {
			return TailCall.sus(() -> replaceTRHAux(ls.tail(), elem, newElem, Lista.of(newElem, tmp)));

		} else {
			return TailCall.sus(() -> replaceTRHAux(ls.tail(), elem, newElem, Lista.of(ls.head(), tmp)));

		}

	}

	public static <T> Lista<T> replaceTRH(Lista<T> ls, T elem, T newElem) {
		return replaceTRHAux(ls, elem, newElem, Lista.of()).eval();

	}
	static <T> TailCall<Integer> sizeAux(Lista<T> ls, Integer tamanio) {

		if (ls.isEmpty()) {
			return TailCall.ret(tamanio) ;
		} else {
			return TailCall.sus(() -> sizeAux(ls.tail(), tamanio + 1));

		}

	}

	public static <T> Integer size(Lista<T> ls) {
		return sizeAux(ls, 0).eval();
	}
	

	 static <T, U>  TailCall<Lista<U>> mapAuxTRH(Lista<T> ls,Function<T, U> fn,Lista<U> tmp){
		
		if (ls.isEmpty()) {
			return TailCall.ret(invertir(tmp));
		}else {
			return TailCall.sus(() ->mapAuxTRH(ls.tail(), fn, tmp.prepend(fn.apply(ls.head()))));
					
		}
	}
	public static <T, U> Lista<U> mapTRH(Lista<T> ls,Function<T, U> fn){
		return mapAuxTRH(ls, fn,Lista.of()).eval();
	}
	
	public static <T> TailCall<T> reduceTRH(Lista<T>ls, T identity,Function<T,Function<T, T>>fn ) {
		if (ls.isEmpty()) {
			return TailCall.ret(identity);
			
		} else {
			
			
			return TailCall.sus(() ->reduceTRH(ls.tail(), fn.apply(ls.head()).apply(identity), fn));
					
					
		}
	}
	public static <T, U> TailCall<U> foldLeftTRH(Lista<T>ls, U identity,Function<U,Function<T, U>>fn ) {
		if (ls.isEmpty()) {
			return TailCall.ret(identity);
		} else {
			return TailCall.sus(() ->foldLeftTRH(ls.tail(), fn.apply(identity).apply(ls.head()), fn));			
		}
	}
	
	public static <T, U> TailCall<U> foldRightTRH(Lista<T>ls, U identity,Function<T,Function<U, U>>fn ) {
		if (ls.isEmpty()) {
			return TailCall.ret(identity);
		} else {
			
			
			return TailCall.sus(() ->foldRightTRH(ls.tail(), fn.apply(ls.head()).apply(identity), fn));
					
					
		}
	}
	

}
