package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.JSONShapeController;
import dataaccess.JSONShapeDAO;
import dataaccess.ShapeDAO;
import model.CircleShape;
import model.ElipseShape;
import model.OtherShape;
import model.Shape;
import model.ShapePoint;

public class DrawShapeAutomatic extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Border border;
	private int x;
	private int y;
	private Graphics g;
	private Shape shapeToDraw;
	
	public DrawShapeAutomatic() {
		this(0, 0);
	}
	
	public DrawShapeAutomatic(int xOffset, int yOffset) {

		
		setBackground(Color.WHITE);
		setBounds(xOffset, yOffset, 1280, 668);
		setVisible(true);
		setEnabled(true);
		setBorder(border);
		paintBorder(g);
		
		x = 1280/2;
		y = 668/2;
		
	}
	public void drawShape(Shape shape) {
		shapeToDraw = shape;
	}
	

	@Override
	public void paintComponent(Graphics g) {
		this.g = g;
		super.paintComponent(g);
		
		drawShape();
		
	}

	private void drawShape() {
		if(shapeToDraw == null) return;
		if (shapeToDraw instanceof OtherShape) {
			Path2D shapePathFirst = new Path2D.Double();
			ArrayList<ShapePoint> points = ((OtherShape) shapeToDraw).getPoints();
			Point firstPoint = ((OtherShape) shapeToDraw).getPoints().get(0).getData();
			int lastIndex = ((OtherShape) shapeToDraw).getPoints().size() - 1;
			Point lastPoint = ((OtherShape) shapeToDraw).getPoints().get(lastIndex).getData();
			for (int i = 0; i < points.size() - 1; i++) {
				ShapePoint shapePointFrom = points.get(i);
				ShapePoint shapePointTo = points.get(i + 1);
				Point pFrom = shapePointFrom.getData();
				Point pTo = shapePointTo.getData();
				shapePathFirst.moveTo(pFrom.getX(), pFrom.getY());
				shapePathFirst.lineTo(pTo.getX(), pTo.getY());
				writeDistance(pFrom, pTo);
			}
			shapePathFirst.moveTo(firstPoint.getX(), firstPoint.getY());
			shapePathFirst.lineTo(lastPoint.getX(), lastPoint.getY());
			writeDistance(firstPoint, lastPoint);
			((Graphics2D) g).draw(shapePathFirst);
		}

		if (shapeToDraw instanceof ElipseShape) {
			double width = ((ElipseShape) shapeToDraw).getDiameterX();
			double height = ((ElipseShape) shapeToDraw).getDiameterY();
			((Graphics2D) g).draw(new Ellipse2D.Double(x - width / 2, y - height / 2, width, height));
		}

		if (shapeToDraw instanceof CircleShape) {
			double diameter = ((CircleShape) shapeToDraw).getDiameter();
			((Graphics2D) g)
					.draw(new Ellipse2D.Double(x - diameter / 4, y - diameter / 4, diameter / 2, diameter / 2));
		}
	}

	public String writeDistance(Point pFrom, Point pTo) {
		String distance = String.valueOf(pFrom.distance(pTo.getX(), pTo.getY()));
		((Graphics2D) g).drawString(distance, (((int) pFrom.getX()) + (int) pTo.getX()) / 2,
				(((int) pFrom.getY()) + (int) pTo.getY()) / 2);
		return distance;
	}
	
	public Shape getShape() {
		return shapeToDraw;
	}
	
}
