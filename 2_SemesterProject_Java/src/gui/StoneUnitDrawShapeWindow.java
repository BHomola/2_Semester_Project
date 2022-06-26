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
import java.awt.geom.*;
import javax.swing.*;

public class StoneUnitDrawShapeWindow extends JFrame implements MouseListener, MouseMotionListener {

	// private OtherShape outline = new OtherShape("test", 4);
	private Point cursorLocation;
	private boolean mouseClickAvailability;
	private ShapeDAO shapeDAO;
	private DrawingPanelPath drawShapePanel;
	private DrawingCircleEllipse circleAndEllipsePanel;
	private int stoneUnitID;
	private static JToolBar toolBar = new JToolBar();
	private JComboBox comboBoxShapeType;
	private String shapes[] = { "Choose a Shape", "Custom", "Circle", "Ellipse" };
	private JTextField diameterX;
	private JTextField diameterY;
	private JLabel diameterXParameterLabel;
	private JLabel diameterYParameterLabel;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton pathToLineButton;
	private JButton pathUndoButton;

	public StoneUnitDrawShapeWindow(int stoneUnitID) {
		setBounds(320, 180, 1280, 720);
		getContentPane().setLayout(null);
		setTitle("Drawing Panel");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cursorLocation = null;
		addMouseListener(this);
		addMouseMotionListener(this);
		mouseClickAvailability = true;
		shapeDAO = new ShapeDAO();
		drawShapePanel = new DrawingPanelPath();
		circleAndEllipsePanel = new DrawingCircleEllipse();
		this.stoneUnitID = stoneUnitID;
		comboBoxShapeType = new JComboBox(shapes);
		toolBar = new JToolBar();
		toolBar.add(comboBoxShapeType);
		toolBar.add(saveButton);
		toolBar.add(cancelButton);

	}

	public StoneUnitDrawShapeWindow() {
		setBounds(320, 180, 1280, 720);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cursorLocation = null;
		addMouseListener(this);
		addMouseMotionListener(this);
		mouseClickAvailability = true;
		shapeDAO = new ShapeDAO();
		circleAndEllipsePanel = new DrawingCircleEllipse();
		drawShapePanel = new DrawingPanelPath();

		toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 1256, 40);

		comboBoxShapeType = new JComboBox(shapes);
		toolBar.add(comboBoxShapeType);

		// Buttons
		pathUndoButton = new JButton("Undo");
		pathToLineButton = new JButton("Line");
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");

		// Textfields
		diameterX = new JTextField();
		diameterY = new JTextField();

		// Labels
		diameterXParameterLabel = new JLabel();
		diameterYParameterLabel = new JLabel();

		toolBar.addSeparator();
		toolBar.setFloatable(false);
		toolBar.setRollover(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		// comboBoxShapeType.addItem("Custom");
		comboBoxShapeType.setBounds(65, 374, 88, 20);
		comboBoxShapeType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (comboBoxShapeType.getSelectedIndex() == 0) {
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						saveButton.disable();
						saveButton.setVisible(false);
						toolBar.remove(diameterX);
						toolBar.remove(diameterXParameterLabel);
						toolBar.remove(diameterY);
						toolBar.remove(diameterYParameterLabel);
						toolBar.remove(pathToLineButton);
						toolBar.remove(pathUndoButton);
						getContentPane().remove(drawShapePanel);
						getContentPane().remove(circleAndEllipsePanel);
						getContentPane().revalidate();
						getContentPane().repaint();
					}
					if (comboBoxShapeType.getSelectedIndex() == 1) {
						getContentPane().add(drawShapePanel);
						drawShapePanel.repaint(getBounds());
						toolBar.add(pathUndoButton);
						toolBar.add(pathToLineButton);
						pathToLineButton.enable();
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						saveButton.enable();
						saveButton.setVisible(true);
						toolBar.remove(diameterX);
						toolBar.remove(diameterY);
						toolBar.remove(diameterXParameterLabel);
						toolBar.remove(diameterYParameterLabel);
						getContentPane().remove(circleAndEllipsePanel);
						toolBar.revalidate();
						toolBar.repaint();
						getContentPane().revalidate();
						getContentPane().repaint();
						pathUndoButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								drawShapePanel.removeLastPointAndRedraw();
							}
						});
					}

					if (comboBoxShapeType.getSelectedIndex() == 2) {
						getContentPane().add(circleAndEllipsePanel);
						circleAndEllipsePanel.repaint(getBounds());
						toolBar.add(diameterX);
						toolBar.add(diameterXParameterLabel);
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						diameterXParameterLabel.setText("Diameter [mm]");
						saveButton.enable();
						saveButton.setVisible(true);
						toolBar.remove(diameterY);
						toolBar.remove(diameterYParameterLabel);
						toolBar.remove(pathToLineButton);
						toolBar.remove(pathUndoButton);
						getContentPane().remove(drawShapePanel);
						toolBar.revalidate();
						toolBar.repaint();
						getContentPane().revalidate();
						getContentPane().repaint();

						diameterX.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								try {
									if (Integer.parseInt(diameterX.getText()) > 0) {
										circleAndEllipsePanel.drawCircle(Integer.parseInt(diameterX.getText()));
									}
								} catch (Exception ex) {
								}
							}
						});
					}

					if (comboBoxShapeType.getSelectedIndex() == 3) {
						getContentPane().add(circleAndEllipsePanel);
						circleAndEllipsePanel.repaint(getBounds());
						toolBar.add(diameterX);
						toolBar.add(diameterXParameterLabel);
						toolBar.add(diameterY);
						toolBar.add(diameterYParameterLabel);
						diameterXParameterLabel.setText("Height [mm]");
						diameterYParameterLabel.setText("Width [mm]");
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						saveButton.enable();
						saveButton.setVisible(true);
						toolBar.remove(pathToLineButton);
						toolBar.remove(pathUndoButton);
						getContentPane().remove(drawShapePanel);
						getContentPane().revalidate();
						getContentPane().repaint();
						toolBar.revalidate();
						toolBar.repaint();
						diameterX.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									if (Integer.parseInt(diameterX.getText()) > 0 && Integer.parseInt(diameterY.getText()) > 0 && diameterY.isVisible()) {
										circleAndEllipsePanel.drawEllipse(Integer.parseInt(diameterX.getText()) , Integer.parseInt(diameterY.getText()));
									}
								} catch (Exception ex) {
								}
							}
						});
						diameterY.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									if (Integer.parseInt(diameterX.getText()) > 0 && Integer.parseInt(diameterY.getText()) > 0 && diameterY.isVisible()) {
										circleAndEllipsePanel.drawEllipse(Integer.parseInt(diameterX.getText()) , Integer.parseInt(diameterY.getText()));
									}
								} catch (Exception ex) {
								}
							}
						});
					}
				}
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StoneUnitDrawShapeWindow drawingFrame = new StoneUnitDrawShapeWindow();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	public boolean isMouseClickAvailability() {
		return mouseClickAvailability;
	}

	public void setMouseClickAvailability(boolean mouseClickAvailability) {
		this.mouseClickAvailability = mouseClickAvailability;
	}

	public int getStoneUnitID() {
		return stoneUnitID;
	}

	public void setStoneUnitID(int stoneUnitID) {
		this.stoneUnitID = stoneUnitID;
	}
}
