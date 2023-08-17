package com.tarea.tree;



import com.tarea.lista.Lista;

public sealed interface BinTree2<T> permits ConsBinTree, LeafBinTree{

	BinTree2 Leaf=new LeafBinTree();
	T value();

	BinTree2<T> left();

	BinTree2<T> right();
	
	static<T> BinTree2<T> of(T value,BinTree2<T> left,BinTree2<T> right){
		return new ConsBinTree<T>(value,left,right);
		
	}
	static<T> BinTree2<T> of(T value){
		return new ConsBinTree<T>(value);
		
	}


	 Integer size();
	
	 static <T> BinTree2<T> builTree(Lista<T> ls){
		 
		 if(ls.isEmpty()) {
			 return BinTree2.Leaf;
		 }else {
		 
		 T h= ls.head();
		 Lista<T> t=ls.tail();
		 
		 int k=t.size()/2;
		 
		 var leftList =t.take(k);
		 
		 var rightList= t.drop(k);
		 
		 
		 return BinTree2.of(h,builTree(leftList),builTree(rightList));
		 
	 }	 }
	 void print(int level);

 }
