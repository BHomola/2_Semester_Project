package model;

import java.awt.Point;

public class OtherShape {
	private DLLNodeShape head;
	private DLLNodeShape tail;
	private int size;
	
	
	public OtherShape() {
		this.head = new DLLNodeShape(null, null, null);
		this.tail = new DLLNodeShape(null, head, null);
		head.setNext(tail);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addFirst(Point p) {
		addBetween(p, head, head.getNext());
	}
	
	public void addLast(Point p) {
		addBetween(p, tail.getPrevious(), tail);
	}
	
	public void addBetween(Point p, DLLNodeShape pre, DLLNodeShape nPre) {
		DLLNodeShape newest = new DLLNodeShape(p, pre, nPre);
		pre.setNext(newest);
		nPre.setPrevious(newest);
		size++;
	}
	
	public DLLNodeShape getHead() {
		return head;
	}
	public void setHead(DLLNodeShape head) {
		this.head = head;
	}
	public DLLNodeShape getTail() {
		return tail;
	}
	public void setTail(DLLNodeShape tail) {
		this.tail = tail;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
