package model;
import java.awt.Point;

import com.google.gson.annotations.Expose;

public class ShapePoint {

	private Point data;
	private ShapePoint next;
	private ShapePoint previous;
	
	public ShapePoint(Point data) {
		this.data = data;
	}
	
	public Point getData() {
		return data;
	}

	public void setData(Point data) {
		this.data = data;
	}

	public ShapePoint getNext() {
		return next;
	}

	public void setNext(ShapePoint next) {
		this.next = next;
	}

	public ShapePoint getPrevious() {
		return previous;
	}

	public void setPrevious(ShapePoint previous) {
		this.previous = previous;
	}


	@Override
	public String toString() {
		return "ArrayListPoint [data=" + data + "]";
	}
	
}


