package com.tarea.lista;

import java.util.function.Function;
import java.util.function.Predicate;



public class ListaTR {

	 static <T> Lista<T> appendAuxTR(Lista<T> ls, T elem, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return invertir(Lista.of(elem, tmp));
		} else

		{
			return appendAuxTR(ls.tail(), elem, Lista.of(ls.head(), tmp));
		}
	}

	public static <T> Lista<T> appendTR(Lista<T> ls, T elem) {

		return appendAuxTR(ls, elem, Lista.of());

	}

	 static <T> Lista<T> preppendAuxTR(Lista<T> ls, T elem, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return Lista.of(elem, invertir(tmp));

		} else

		{
			return preppendAuxTR(ls.tail(), elem, Lista.of(ls.head(), tmp));
		}
	}

	public static <T> Lista<T> preppendTR(Lista<T> ls, T elem) {

		return preppendAuxTR(ls, elem, Lista.of());

	}

	 static <T> Lista<T> removeAuxTR(Lista<T> ls, T elem, Lista<T> tmp) {
		if (ls.isEmpty()) {
			return invertir(tmp);
		}

		else {
			if (ls.head().equals(elem)) {

				return removeAuxTR(ls.tail().tail(), elem, Lista.of(ls.tail().head(), tmp));

			} else {
				return removeAuxTR(ls.tail(), elem, Lista.of(ls.head(), tmp));

			}

		}
	}

	public static <T> Lista<T> removeTR(Lista<T> ls, T elem) {

		return removeAuxTR(ls, elem, Lista.of());

	}

	public static <T> Lista<T> drop(Lista<T> ls, Integer n) {

		if (ls.isEmpty() || n <= 0) {
			return ls;

		}
		return drop(ls.tail(), n - 1);

	}

	 static <T> Lista<T> dropWhileAux(Lista<T> ls, Predicate<T> p, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return tmp;
		}

		else if (p.test(ls.head())) {

			return dropWhileAux(ls.tail(), p, tmp);

		} else {
			return dropWhileAux(ls.tail(), p, appendTR(tmp, ls.head()));

		}

	}

	public static <T> Lista<T> dropWhile(Lista<T> ls, Predicate<T> p) {

		return dropWhileAux(ls, p, Lista.of());

	}

	 static <T> Lista<T> takeAux(Lista<T> ls, Integer n, Lista<T> tmp) {

		if (ls.isEmpty() || n <= 0) {
			return tmp;

		}
		return takeAux(ls.tail(), n - 1, appendTR(tmp, ls.head()));

	}

	public static <T> Lista<T> take(Lista<T> ls, Integer elem) {

		return takeAux(ls, elem, Lista.of());

	}

	 static <T> Lista<T> takeWhileAux(Lista<T> ls, Predicate<T> p, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return tmp;
		}

		else if (p.test(ls.head())) {

			return takeWhileAux(ls.tail(), p, appendTR(tmp, ls.head()));

		} else {

			return takeWhileAux(ls.tail(), p, tmp);

		}

	}

	public static <T> Lista<T> takeWhile(Lista<T> ls, Predicate<T> p) {

		return takeWhileAux(ls, p, Lista.of());

	}



	public static <T> Lista<T> concatenarTR(Lista<T> lista1, Lista<T> lista2) {

		if (lista2.isEmpty()) {
			return lista1;
		} else {

			return concatenarTR(appendTR(lista1, lista2.head()), lista2.tail());

		}

	}

	 static <T> Lista<T> replaceTRAux(Lista<T> ls, T elem, T newElem, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return invertir(tmp);

		} else if (ls.head().equals(elem)) {
			return replaceTRAux(ls.tail(), elem, newElem, Lista.of(newElem, tmp));

		} else {
			return replaceTRAux(ls.tail(), elem, newElem, Lista.of(ls.head(), tmp));

		}

	}

	public static <T> Lista<T> replaceTR(Lista<T> ls, T elem, T newElem) {
		return replaceTRAux(ls, elem, newElem, Lista.of());

	}

	static <T> Integer sizeAux(Lista<T> ls, Integer tamanio) {

		if (ls.isEmpty()) {
			return tamanio;
		} else {
			return sizeAux(ls.tail(), tamanio + 1);

		}

	}

	public static <T> Integer size(Lista<T> ls) {
		return sizeAux(ls, 0);
	}

	 static <T> Lista<T> invertirTRAux(Lista<T> ls, Lista<T> tmp) {

		if (ls.isEmpty()) {
			return tmp;
		} else {

			return invertirTRAux(ls.tail(), Lista.of(ls.head(), tmp));

		}

	}
	public static <T> Lista< T> invertir(Lista<T> ls){
		
		return invertirTRAux(ls, Lista.of());
	}
	
	 static <T, U> Lista<U> mapAuxTR(Lista<T> ls,Function<T, U> fn,Lista<U> tmp){
		
		if (ls.isEmpty()) {
			return invertir(tmp);
		}else {
			return mapAuxTR(ls.tail(), fn, tmp.prepend(fn.apply(ls.head())));
					
		}
	}
	public static <T, U> Lista<U> mapTR(Lista<T> ls,Function<T, U> fn){
		return mapAuxTR(ls, fn,Lista.of() );
	}
	
	public static <T> T reduce(Lista<T>ls, T identity,Function<T,Function<T, T>>fn ) {
		if (ls.isEmpty()) {
			return identity;
		} else {
			
			
			return reduce(ls.tail(), fn.apply(ls.head()).apply(identity), fn);
					
					
		}
	}
	
	
	public static <T, U> U foldLeft(Lista<T>ls, U identity,Function<U,Function<T, U>>fn ) {
		if (ls.isEmpty()) {
			return identity;
		} else {
			return foldLeft(ls.tail(), fn.apply(identity).apply(ls.head()), fn);			
		}
	}
	
	public static <T, U> U foldRight(Lista<T>ls, U identity,Function<T,Function<U, U>>fn ) {
		if (ls.isEmpty()) {
			return identity;
		} else {
			
			
			return foldRight(ls.tail(), fn.apply(ls.head()).apply(identity), fn);
					
					
		}
	}
	

}
