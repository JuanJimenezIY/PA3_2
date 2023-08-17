package com.tarea.tree;

 final class LeafBinTree<T> implements BinTree2<T>{

	@Override
	public T value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinTree2<T> left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinTree2<T> right() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "Leaf";
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void print(int level) {
		// TODO Auto-generated method stub
		String tab=".".repeat(level+1);
		
		
	}


}
