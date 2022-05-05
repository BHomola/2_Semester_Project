package model;
import java.awt.Point;

public class DLLNodeShape {

	private Point data;
	private DLLNodeShape next;
	private DLLNodeShape previous;
	
	public DLLNodeShape(Point data, DLLNodeShape next, DLLNodeShape previous) {
		super();
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	
	public Point getData() {
		return data;
	}

	public void setData(Point data) {
		this.data = data;
	}

	public DLLNodeShape getNext() {
		return next;
	}

	public void setNext(DLLNodeShape next) {
		this.next = next;
	}

	public DLLNodeShape getPrevious() {
		return previous;
	}

	public void setPrevious(DLLNodeShape previous) {
		this.previous = previous;
	}

	public DLLNodeShape(Point data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DLLNodeShape [data=" + data + "]";
	}
	
	
}

