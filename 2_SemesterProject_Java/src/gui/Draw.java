package gui;

import model.OtherShape;
import model.ShapePoint;
import javax.swing.JFrame;

import dataaccess.ShapeDAO;

import java.awt.Graphics;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;

public class Draw extends JFrame implements MouseListener, MouseMotionListener{

	private OtherShape outline = new OtherShape("test", 1);
	private Point cursorLocation;
	private boolean mouseClickAvailability;
	private ShapeDAO shapeDAO;
	
	public Draw() {
		setSize(600, 600);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cursorLocation = null;
		addMouseListener(this);
		addMouseMotionListener(this);
		mouseClickAvailability = true;
		shapeDAO = new ShapeDAO();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mouseClickAvailability) {
			Point p = new Point();
			
			p.setLocation(e.getX(), e.getY());
			
			int x = (int)p.getX();
			int y = (int)p.getY();
			
			if(outline.getPoints().size() == 0) {
				outline.addStartingPoint(p);
				return;
			}
			
			if(outline.getPoints().size() > 0) {
				outline.addPoint(p);
			}
			
			drawShape(outline.getPoints());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		/*Implement for GUI
		Graphics g = getGraphics();
		
		ShapePoint pLast = outline.getPoints().get(outline.getPoints().size()-1);;
		Point lastPoint = pLast.getData();
		cursorLocation = e.getPoint();
		
		String distance = new String();
		distance = String.valueOf(lastPoint.distance(e.getX(), e.getY()));
		
		if(outline.getPoints().size() >= 1) {
			//ShapePoint pLast = outline.getPoints().get(outline.getPoints().size()-1);
			g.drawString(distance, (((int)e.getX())+(int)lastPoint.getX())/2, (((int)e.getY())+(int)lastPoint.getY())/2);
			g.drawLine((int)pLast.getData().getX(), (int)pLast.getData().getY(), (int)cursorLocation.getX(), (int)cursorLocation.getY());
			System.out.println("My x position is: " + e.getX() + " and my y is: " + e.getY());
		}*/
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawShape(ArrayList<ShapePoint> points) {
		Graphics2D g = (Graphics2D)getGraphics();
		g.setStroke(new BasicStroke(5));
		
		ShapePoint pLast = points.get(points.size()-1);
		ShapePoint pFirstActual = points.get(0);
		int closingParameter = 25;
		
		
		for(int i = 0; i < points.size(); i++) {
			if(points.get(i).getNext() != null) {
				ShapePoint pFirst = points.get(i);
				ShapePoint pNext = points.get(i+1);
				g.fillRect((int)pFirst.getData().getX()-5, (int)pFirst.getData().getY()-5, 10, 10);
				g.drawLine((int)pFirst.getData().getX(), (int)pFirst.getData().getY(), (int)pNext.getData().getX(), (int)pNext.getData().getY());
				if(outline.getPoints().size() > 2 && pLast.getData().distance(pFirstActual.getData()) < closingParameter) {
					closeShape(points);
					mouseClickAvailability = false;
					try {
						shapeDAO.createShape(outline, 1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void closeShape(ArrayList<ShapePoint> points) {
		Graphics g = getGraphics();
		ShapePoint pLast = points.get(points.size()-1);
		ShapePoint pFirstActual = points.get(0);
		pLast.setData(pFirstActual.getData());
		g.drawLine((int)pLast.getData().getX(), (int)pLast.getData().getY(), (int)pFirstActual.getData().getX(), (int)pFirstActual.getData().getY());
		outline.calculateArea();
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*public void paint(Graphics g) {
        super.paint(g);
        if(outline.getPoints().size() >= 1) {
        	g.setColor(Color.RED);
            g.drawLine((int)outline.getPoints().get(outline.getPoints().size()-1).getData().getX(), (int)outline.getPoints().get(outline.getPoints().size()-1).getData().getY(), cursorLocation.x, cursorLocation.y);
        }
        
    }*/

	
}
