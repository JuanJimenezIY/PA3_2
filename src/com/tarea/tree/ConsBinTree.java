package com.tarea.tree;

final class ConsBinTree<T> implements BinTree2<T> {
//debe ser final para no poder heredar y de paquete para no usarlo  afuera
	private final T value;
	private final BinTree2<T> left;
	private final BinTree2<T> right;

	public ConsBinTree(T value, BinTree2<T> left, BinTree2<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;

	}

	public ConsBinTree(T value) {
		this.value = value;
		this.left = Leaf;
		this.right = Leaf;

	}

	@Override
	public T value() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public BinTree2<T> left() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public BinTree2<T> right() {
		// TODO Auto-generated method stub
		return this.right;
	}
	
	public void print(int level) {
		String tab="-".repeat(level+1);
		System.out.println(tab);
		System.out.printf("%s\n",this.value);
		
		left.print(level+2);
		right.print(level+2);
	}

	public String toString() {
		return String.format("BinTree(%s,%s,%s)",
				value.toString(),
				left.toString(),
				right.toString());
	}
	@Override
	public Integer size() {
		return 1+left.size()+right.size();	}
}
