package gui;

import model.OtherShape;
import model.ShapePoint;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Draw extends JFrame implements MouseListener{

	private OtherShape outline = new OtherShape();
	
	public Draw() {
		setSize(1920, 1080);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub		
		Point p = new Point();
		
		p.setLocation(e.getX(), e.getY());
		
		int x = (int)p.getX();
		int y = (int)p.getY();
		
		if(outline.getPoints().size() == 0) {
			outline.addStartingPoint(p);
		}
		
		if(outline.getPoints().size() > 0) {
			outline.addPoint(p);
		}
		
		drawShape(outline.getPoints());
		
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
		for(int i = 0; i < points.size(); i++) {
			if(points.get(i).getNext() != null) {
				ShapePoint pFirst = points.get(i);
				ShapePoint pNext = points.get(i+1);
				g.fillRect((int)pFirst.getData().getX()-5, (int)pFirst.getData().getY()-5, 10, 10);
				g.drawLine((int)pFirst.getData().getX(), (int)pFirst.getData().getY(), (int)pNext.getData().getX(), (int)pNext.getData().getY());
				ShapePoint pLast = points.get(points.size()-1);
				ShapePoint pFirstActual = points.get(0);
				if(pLast.getData().distance(pFirstActual.getData()) < 25) {
					pLast.setData(pFirstActual.getData());
					g.drawLine((int)pLast.getData().getX(), (int)pLast.getData().getY(), (int)pFirstActual.getData().getX(), (int)pFirstActual.getData().getY());
				}
			}
		}
		
		points.toString();
	}

}
