package com.huffman;

public class MinBinaryHeap {
	private HuffmanTreeNode[] heap;
	private int size;
	private int capacity;
	private static final int TOP = 1;
	static final String code_table = "";
	public MinBinaryHeap(HuffmanTreeNode[] aheapStorage) {
		this.heap = new HuffmanTreeNode[aheapStorage.length + 1];
		heap[TOP] = null;
		this.size = aheapStorage.length;
		capacity = size;
		System.arraycopy(aheapStorage, 0, heap, 1, size);
		

		buildHeap(size);
	}

	private void buildHeap(int asize) {
		for (int i = getParent(asize); i >= TOP; i--) {
			heapifyUp(i);
		}
	}

	public HuffmanTreeNode extractMin() {
		HuffmanTreeNode result = heap[TOP];

		heap[TOP] = heap[size];
		heap[size] = null;
		size--;
		heapifyUp(TOP);
		return result;
	}

	public void insertItem(HuffmanTreeNode item) {
		if (size < capacity) {
			heap[++size] = item;
			int current = size;
			while (isValid(current) && isValid(getParent(current)) && heap[getParent(current)].getFrequency() > heap[current].getFrequency()) {
				swapTwoValues(getParent(current), current);
				current = current / 2;
			}
		} else {
			System.out.println("cannot insert : no space left");
		}
	}

	private void heapifyUp(int index) {
		int leftChildIdx = getLeftChild(index);
		int rightChildIdx = getRightChild(index);
		int smallestIdx = leftChildIdx;

		if (isValid(rightChildIdx)) {
			if (heap[rightChildIdx].getFrequency() < heap[leftChildIdx].getFrequency()) {
				smallestIdx = rightChildIdx;
			}
		}

		if (isValid(smallestIdx)&& heap[smallestIdx].getFrequency() < heap[index].getFrequency()) {
			swapTwoValues(smallestIdx, index);
			heapifyUp(smallestIdx);
		}
	}

	private void swapTwoValues(int smallestIdx, int index) {
		HuffmanTreeNode temp = heap[smallestIdx];
		heap[smallestIdx] = heap[index];
		heap[index] = temp;
	}

	private boolean isValid(int index) {
		return index >= TOP && index <= size;
	}

	private int getParent(int index) {
		return index / 2;
	}

	private int getLeftChild(int index) {
		return 2 * index;
	}

	private int getRightChild(int index) {
		return 2 * index + 1;
	}

	public int getSize() {
		return size;
	}
	
	
	
	

	
	
	
	
	
	
	
	

}
