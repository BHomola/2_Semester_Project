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

import dataaccess.JSONShapeDAO;
import dataaccess.ShapeDAO;
import model.CircleShape;
import model.ElipseShape;
import model.OtherShape;
import model.Shape;
import model.ShapePoint;

public class DrawShapeAutomatic extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Border border;
	private Graphics g;
	private JSONShapeDAO jsonDAO;
	private ShapeDAO shapeDAO;
	private int x;
	private int y;
	
	public DrawShapeAutomatic() {
		g = getGraphics();
		//border = new Border();
		setBackground(Color.WHITE);
		setBounds(320, 180, 1024, 600);
		setVisible(true);
		setEnabled(true);
		setBorder(border);
		paintBorder(g);
		setLocation(120, 70);
		x = 512;
		y = 300;
		
	}

    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
    }
    
    public Shape drawDefaultShape(int id) throws SQLException {
    	Graphics2D g = (Graphics2D)getGraphics();
    	jsonDAO = new JSONShapeDAO();
    	Shape shape = jsonDAO.getById(id);
    	if(shape instanceof OtherShape) {
    		Path2D shapePathFirst = new Path2D.Double();
    		ArrayList<ShapePoint> points = ((OtherShape) shape).getPoints();
    		Point firstPoint = ((OtherShape) shape).getPoints().get(0).getData();
    		int lastIndex = ((OtherShape) shape).getPoints().size()-1;
    		Point lastPoint = ((OtherShape) shape).getPoints().get(lastIndex).getData();
    		for(int i = 0; i < points.size()-1; i++) {
    				ShapePoint shapePointFrom = points.get(i);
    				ShapePoint shapePointTo = points.get(i+1);
    				Point pFrom = shapePointFrom.getData();
    				Point pTo = shapePointTo.getData();
    				shapePathFirst.moveTo(pFrom.getX(), pFrom.getY());
    				shapePathFirst.lineTo(pTo.getX(), pTo.getY());
    				writeDistance(pFrom, pTo);
    		}
    		shapePathFirst.moveTo(firstPoint.getX(), firstPoint.getY());
			shapePathFirst.lineTo(lastPoint.getX(), lastPoint.getY());
    		writeDistance(firstPoint, lastPoint);
    		g.draw(shapePathFirst);
    	}
    	
    	if(shape instanceof ElipseShape) {
    		double width = ((ElipseShape) shape).getDiameterX();
    		double height = ((ElipseShape) shape).getDiameterY();
    		((Graphics2D) getGraphics()).draw(new Ellipse2D.Double(x - width/2, y - height/2, width, height));
    	}

    	if(shape instanceof CircleShape) {
    		double diameter = ((CircleShape) shape).getDiameter();
    	    ((Graphics2D) getGraphics()).draw(new Ellipse2D.Double(x - diameter/4, y - diameter/4, diameter/2, diameter/2));
    	}
    	return shape;
    }
    
    public void drawShapeFromDb(int id) throws SQLException {
    	Graphics2D g = (Graphics2D)getGraphics();
    	Shape shape = shapeDAO.getById(id);
    	if(shape instanceof OtherShape) {
    		Path2D shapePathFirst = new Path2D.Double();
    		ArrayList<ShapePoint> points = ((OtherShape) shape).getPoints();
    		Point firstPoint = ((OtherShape) shape).getPoints().get(0).getData();
    		int lastIndex = ((OtherShape) shape).getPoints().size()-1;
    		Point lastPoint = ((OtherShape) shape).getPoints().get(lastIndex).getData();
    		for(int i = 0; i < points.size()-1; i++) {
    				ShapePoint shapePointFrom = points.get(i);
    				ShapePoint shapePointTo = points.get(i+1);
    				Point pFrom = shapePointFrom.getData();
    				Point pTo = shapePointTo.getData();
    				shapePathFirst.moveTo(pFrom.getX(), pFrom.getY());
    				shapePathFirst.lineTo(pTo.getX(), pTo.getY());
    				writeDistance(pFrom, pTo);
    		}
    		shapePathFirst.moveTo(firstPoint.getX(), firstPoint.getY());
			shapePathFirst.lineTo(lastPoint.getX(), lastPoint.getY());
    		writeDistance(firstPoint, lastPoint);
    		g.draw(shapePathFirst);
    	}
    	
    	if(shape instanceof ElipseShape) {
    		double width = ((ElipseShape) shape).getDiameterX();
    		double height = ((ElipseShape) shape).getDiameterY();
    		((Graphics2D) getGraphics()).draw(new Ellipse2D.Double(x - width/2, y - height/2, width, height));
    	}

    	if(shape instanceof CircleShape) {
    		double diameter = ((CircleShape) shape).getDiameter();
    	    ((Graphics2D) getGraphics()).draw(new Ellipse2D.Double(x - diameter/4, y - diameter/4, diameter/2, diameter/2));
    	}
    }

	public String writeDistance(Point pFrom, Point pTo) {
		Graphics2D g = (Graphics2D)getGraphics();
		
		String distance = String.valueOf(pFrom.distance(pTo.getX(), pTo.getY()));
		g.drawString(distance, (((int)pFrom.getX())+(int)pTo.getX())/2, (((int)pFrom.getY())+(int)pTo.getY())/2);
		return distance;
	}
	
	public static void main(String[] args) throws SQLException {
		DrawShapeAutomatic drawJSON = new DrawShapeAutomatic();
		JFrame frame = new JFrame();
		frame.setBounds(320, 180, 1280, 720);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Drawing Panel");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawJSON);
		drawJSON.drawDefaultShape(2);
	}

}
