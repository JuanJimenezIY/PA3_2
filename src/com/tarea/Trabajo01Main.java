package com.tarea;

import com.tarea.lista.Lista;
import com.tarea.tree.BinTree2;

public class Trabajo01Main {

	public static void main(String[] args) {
		
		Lista<Integer> ls =Lista.of(1,2,3,4,5,6,7,8);
		var tree=BinTree2.builTree(ls);
		System.out.println(tree.size());
		System.err.println(tree);
		tree.print(0);
		/*
		BinTree2<Integer> n2= BinTree2.of(2);
		
		BinTree2<Integer> n5= BinTree2.of(5);

		
		BinTree2<Integer> n1= BinTree2.of(1,n2,n5);
		
		System.out.println(n1.toString());

		System.out.println(n1.size());
		*/
	
		
		
	}
}
