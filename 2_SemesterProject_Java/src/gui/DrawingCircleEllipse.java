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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.CircleShape;
import model.ElipseShape;
import model.OtherShape;
import model.Shape;
import model.ShapePoint;

public class DrawingCircleEllipse extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Border border;
	private Graphics g;

	
	private int x;
	private int y;

	Shape finalShape;
	public DrawingCircleEllipse() {
		g = getGraphics();
		//border = new Border();
		setBackground(Color.WHITE);
		setBounds(0, 52, 1280, 668);
		setVisible(true);
		setEnabled(true);
		setBorder(border);
		paintBorder(g);
		//setLocation(120, 70);

		x = 1280/2;
		y = 668/2;
	}

    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
    }


	public void drawCircle(int diameter) {
		paintComponent(getGraphics());
		finalShape = new CircleShape(diameter);
		((Graphics2D) getGraphics()).draw(new Ellipse2D.Double(x - diameter/4, y - diameter/4, diameter/2, diameter/2));

	}
	
	public void drawEllipse(int height, int width) {
		paintComponent(getGraphics());
		finalShape = new ElipseShape(height, width);
		((Graphics2D) getGraphics()).draw(new Ellipse2D.Double(x - width/2, y - height/2, width, height));
	}
	
	
	public Shape getShape() {
		return finalShape;
	}
}
