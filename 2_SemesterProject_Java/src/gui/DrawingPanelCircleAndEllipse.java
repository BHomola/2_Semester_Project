package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.CircleShape;
import model.ElipseShape;


public class DrawingPanelCircleAndEllipse extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Border border;
	private Graphics g;
	private int height;
	private int width;
	private int x;
	private int y;
	
	public DrawingPanelCircleAndEllipse() {
		setBackground(Color.YELLOW);
		setBounds(320, 180, 1024, 600);
		setEnabled(true);
		setBorder(border);
		setLocation(120, 70);
		g = getGraphics();
		paintBorder(g);
		x = 512;
		y = 300;
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(10, 10, 200, 200);
	}
	
	public CircleShape drawCircle(int diameter) {
		CircleShape circleShape = new CircleShape(diameter);
		int widthCircle = diameter;
		((Graphics2D) g).draw(new Ellipse2D.Double(x, y, diameter/2, widthCircle/2));
		return circleShape;
	}
	
	public ElipseShape drawEllipse(int height, int width) {
		ElipseShape ellipseShape = new ElipseShape(height, width);
		((Graphics2D) g).draw(new Ellipse2D.Double(x, y, height, width));
		return ellipseShape;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame drawingFrame = new JFrame();
		drawingFrame.setBounds(320, 180, 1280, 720);
		drawingFrame.getContentPane().setLayout(null);
		drawingFrame.setTitle("Drawing Panel");
		drawingFrame.setVisible(true);
		drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawingPanelCircleAndEllipse drawingPanel = new DrawingPanelCircleAndEllipse();
		drawingFrame.add(drawingPanel);
		
	}
	
}

