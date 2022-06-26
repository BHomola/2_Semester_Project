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
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.OtherShape;
import model.ShapePoint;

public class DrawingPanelPath extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OtherShape outline;
	private Point cursorLocation;
	private Border border;
	private Graphics g;
	private boolean draw;
	private int closingParameter;
	//private Path2D shapePathFirst;
	//private Path2D shapePathSecond;
	
	public DrawingPanelPath() {
		outline = new OtherShape("", 1);
		g = getGraphics();
		cursorLocation = null;
		//border = new Border();
		setBackground(Color.YELLOW);
		setBounds(320, 180, 1024, 600);
		setVisible(true);
		setEnabled(true);
		setBorder(border);
		paintBorder(g);
		addMouseListener(this);
		addMouseMotionListener(this);
		setLocation(120, 70);
		draw = true;
		closingParameter = 17;
		//shapePathFirst = new Path2D.Double();
		//shapePathSecond = new Path2D.Double();
	}

    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
			Path2D shapePathFirst = new Path2D.Double();
            
            if(outline.getPoints().size() >= 1) {
            	ShapePoint pLast = outline.getPoints().get(outline.getPoints().size()-1);
    			Point lastPoint = pLast.getData();
    			
    			String distance = new String();
    			distance = String.valueOf(lastPoint.distance(cursorLocation.getX(), cursorLocation.getY()));
            	
		        shapePathFirst.moveTo(lastPoint.getX(), lastPoint.getY());
				shapePathFirst.lineTo(cursorLocation.getX(), cursorLocation.getY());
				((Graphics2D) g).draw(shapePathFirst);
				drawShape(outline.getPoints());
            	g.drawString(distance, (((int)cursorLocation.getX())+(int)lastPoint.getX())/2, (((int)cursorLocation.getY())+(int)lastPoint.getY())/2);
            }
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
				/*Path2D shapePathFirst = new Path2D.Double();
				Path2D shapePathSecond = new Path2D.Double();
				PathIterator pathIterator = shapePathSecond.getPathIterator(new AffineTransform()); //might not need this one, would need it to be able to use Path2D.append to move data to new path. Still need to try it....*/
				if(draw) {
					if(outline.getPoints().size() >= 1) {
						cursorLocation = e.getPoint();
						/*String distance = new String();
						distance = String.valueOf(lastPoint.distance(e.getX(), e.getY()));
						
						if(outline.getPoints().size() >= 1) {
							paintImmediately((int)lastPoint.getX(), (int)lastPoint.getY(), (int)cursorLocation.getX(), (int)cursorLocation.getY());
							System.out.println("My current x coordiante is: " + e.getX() + "; and my current y coordinate is: " + e.getY());
						}*/
					}
					
					//paintImmediately();
					repaint();
					
					System.out.println("I am moving!");
				}
				//revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
				if(draw) {
					Point p = new Point();
					
					if(outline.getPoints().size() == 0) {
						p.setLocation(e.getX(), e.getY());
						outline.addStartingPoint(p);
						return;
					}
					
					if(outline.getPoints().size() > 0) {
						ShapePoint pFirstActual = outline.getPoints().get(0);
						if(p.distance(pFirstActual.getData()) > closingParameter) {
							p.setLocation(e.getX(), e.getY());
							outline.addPoint(p);
						}
						else {
							p.setLocation(pFirstActual.getData().getX(), pFirstActual.getData().getY());
							outline.addPoint(p);
						}
					}
					drawShape(outline.getPoints());
				}
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
		drawShape(outline.getPoints());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		drawShape(outline.getPoints());
	}
	
	public void drawShape(ArrayList<ShapePoint> points) {
		Graphics2D g = (Graphics2D)getGraphics();
		//g.setStroke(new BasicStroke(5));
		
		Path2D shapePathFirst = new Path2D.Double();
		
		for(int i = 0; i < points.size()-1; i++) {
			if(points.get(i).getNext() != null) {
				ShapePoint shapePointFrom = points.get(i);
				ShapePoint shapePointTo = points.get(i+1);
				Point pFrom = shapePointFrom.getData();
				Point pTo = shapePointTo.getData();
				g.fillRect((int)pFrom.getX()-5, (int)pFrom.getY()-5, 10, 10);
				shapePathFirst.moveTo(pFrom.getX(), pFrom.getY());
				shapePathFirst.lineTo(pTo.getX(), pTo.getY());
				writeDistance(pFrom, pTo);
				g.draw(shapePathFirst);
				if(outline.getPoints().size() > 2) {
					closeShape(points);
				}
			}
		}
	}

	public void closeShape(ArrayList<ShapePoint> points) {
		Graphics2D g = (Graphics2D) getGraphics();
		
		Path2D shapePathFirst = new Path2D.Double();
		
		ShapePoint pLast = points.get(points.size()-1);
		ShapePoint pFirstActual = points.get(0);
		if(pLast.getData().distance(pFirstActual.getData()) < closingParameter) {
			pLast.setData(pFirstActual.getData());
			shapePathFirst.moveTo(pLast.getData().getX(), pLast.getData().getY());
			shapePathFirst.closePath();
			outline.calculateArea();
			System.out.println(outline.calculateArea());
			setDraw(false);
			g.draw(shapePathFirst);
		}
	}

	public String writeDistance(Point pFrom, Point pTo) {
		Graphics2D g = (Graphics2D)getGraphics();
		
		String distance = String.valueOf(pFrom.distance(pTo.getX(), pTo.getY()));
		g.drawString(distance, (((int)pFrom.getX())+(int)pTo.getX())/2, (((int)pFrom.getY())+(int)pTo.getY())/2);
		return distance;
	}
	
	public void removeLastPointAndRedraw() {
		outline.getPoints().remove(outline.getPoints().size()-1);
		repaint();
		drawShape(outline.getPoints());
	}
	
	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public int getClosingParameter() {
		return closingParameter;
	}

	public void setClosingParameter(int closingParameter) {
		this.closingParameter = closingParameter;
	}
	
	//public void drawLastSegment() {}

}
