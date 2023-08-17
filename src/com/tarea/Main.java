package com.tarea;



import com.tarea.lista.Lista;

record Tupla<T,U>(T _t1, U _t2){    
	public String toString() {        
		return String.format("(%s,%s)",this._t1,this._t2);    
	}
}

public class Main {
	static Lista<Integer> sumarDigito(Integer elem,Lista<Integer> ls){
		
		if (elem == 0) {
			return ls;
		} else if (ls.tail().isEmpty()) {
			return Lista.of(elem);
		} else {
			Integer h = ls.head();
			Lista<Integer> t = ls.tail();
			Integer r = 1 - h;
			return sumarDigito(h, t).prepend(r);
		}
	}
	public static void main(String[] args) {
/*
		Lista<Integer> miLista = Lista.of(1, 2, 3, 4, 5, 6, 7, 8);

		var take = miLista.take(4);
		System.out.println("--take--");
		System.out.println(take);

		var nL = miLista.drop(4);
		System.out.println("--drop--");
		System.out.println(nL);

		BinTree<Integer> arbol = BinTree.of(1, BinTree.of(2), BinTree.of(3, BinTree.of(4), BinTree.of(5)));
		System.out.println("--Arbol--");
		System.out.println(arbol);

		var size = miLista.size();
		System.out.println("--Size Lista--");
		System.out.println(size);

		var sizeArbol = arbol.size();
		System.out.println("--Size Arbol--");
		System.out.println(sizeArbol);

		BinTree<Integer> arbolLista = BinTree.buildTree(miLista);
		System.out.println("--Arbol a partir de Lista--");
		System.out.println(arbolLista);
		*/
		
		Lista<Integer> ls = Lista.of(0,1,1,1);
		var sum=sumarDigito(1, ls);
		System.out.println(sum);
	}

}
