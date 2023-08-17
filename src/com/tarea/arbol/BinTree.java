package com.tarea.arbol;

import com.tarea.lista.Lista;

public class BinTree<T>  {

	 static BinTree Leaf = new Leaf<>();

	 private final T raiz;

	 private final BinTree<T> left;

	 private final BinTree<T> right;

	public BinTree( T raiz, BinTree<T> left, BinTree<T> right) {
	
		
		this.raiz = raiz;
		this.left = left;
		this.right = right;
	}
	
	  public  BinTree(T raiz) {
	        this.raiz = raiz;
	        this.left = Leaf;
	        this.right = Leaf;
	    }
	
	   public static <T> BinTree<T> of(T raiz) {
	        return new BinTree<>(raiz);
	    }

	    public static <T> BinTree<T> of(T raiz, BinTree<T> left, BinTree<T> right) {
	        return new BinTree<>(raiz, left, right);
	    }

		@Override
		public String toString() {
			return "BinTree[ " + raiz + ",left=" + left + ",right=" + right + "]";
		}
	
		public Integer size() {
			if(this==Leaf) {
				return 0;
			}else
			return 1+right.size()+left.size();
		
	     }
		
		
		public static <T> BinTree<T> buildTree( Lista<T> lista ) {
	        if( lista.isEmpty() ) {
	            return Leaf;
	        }
	        else {
	            Integer mitad;
	            if (lista.tail().isEmpty()) {
					 mitad=0;
				} else {
					mitad=lista.tail().size()/2;
				}
	      
	            return BinTree.of(lista.head(), buildTree(lista.tail().take(mitad)), buildTree(lista.tail().drop(mitad)) );
	        }
	    }
	    
		
}
