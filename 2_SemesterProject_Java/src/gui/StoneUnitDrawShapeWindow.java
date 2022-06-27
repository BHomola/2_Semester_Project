package gui;

import model.Location;
import model.OtherShape;
import model.Shape;
import model.ShapePoint;
import javax.swing.JFrame;

import controller.JSONShapeController;
import controller.ShapeController;
import dataaccess.ShapeDAO;

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
	private DrawShapeAutomatic defaultShapesPanel;
	private int stoneUnitID;
	private static JToolBar toolBar = new JToolBar();
	private JComboBox comboBoxShapeType;
	private JComboBox<model.Shape> comboBoxDefaultShape;
	private String shapes[] = { "Choose a Shape", "Pre-made", "Custom Shape", "Circle", "Ellipse" };
	private JTextField diameterX;
	private JTextField diameterY;
	private JLabel diameterXParameterLabel;
	private JLabel diameterYParameterLabel;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton pathToLineButton;
	private JButton pathUndoButton;
	private JButton drawCircleEllipseButton;
	private ShapeController shapeController;
	private IShapeSave stoneWindow;

	public StoneUnitDrawShapeWindow(IShapeSave stoneWindow) throws SQLException {
		this.stoneWindow = stoneWindow;
		setBounds(320, 180, 1280, 720);
		getContentPane().setLayout(null);
		setTitle("Drawing Panel");
		setVisible(true);
		cursorLocation = null;
		addMouseListener(this);
		addMouseMotionListener(this);
		mouseClickAvailability = true;
		shapeDAO = new ShapeDAO();
		circleAndEllipsePanel = new DrawingCircleEllipse();
		drawShapePanel = new DrawingPanelPath();
		defaultShapesPanel = new DrawShapeAutomatic(0, 52);
		shapeController = new ShapeController();

		toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 1256, 40);
		comboBoxShapeType = new JComboBox(shapes);
		toolBar.add(comboBoxShapeType);

		// loading default shapes and insterting them into combobox;
		comboBoxDefaultShape = new JComboBox<Shape>();
		loadDefaultShapes();
		comboBoxDefaultShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultShapesPanel.revalidate();
				defaultShapesPanel.drawShape((model.Shape) comboBoxDefaultShape.getSelectedItem());
				defaultShapesPanel.paintComponent(defaultShapesPanel.getGraphics());

			}
		});

		// Buttons
		pathUndoButton = new JButton("Undo");
		pathToLineButton = new JButton("Line");
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		drawCircleEllipseButton = new JButton("Draw");

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
						toolBar.remove(drawCircleEllipseButton);
						toolBar.remove(comboBoxDefaultShape);
						getContentPane().remove(drawShapePanel);
						getContentPane().remove(circleAndEllipsePanel);
						getContentPane().remove(defaultShapesPanel);
						getContentPane().revalidate();
						getContentPane().repaint();
					}

					if (comboBoxShapeType.getSelectedIndex() == 1) {
						getContentPane().add(defaultShapesPanel);
						// defaultShapesPanel.repaint(getBounds());
						toolBar.remove(pathUndoButton);
						toolBar.remove(pathToLineButton);
						toolBar.add(comboBoxDefaultShape);
						comboBoxDefaultShape.setSelectedIndex(0);
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						saveButton.enable();
						saveButton.setVisible(true);
						toolBar.remove(diameterX);
						toolBar.remove(diameterY);
						toolBar.remove(diameterXParameterLabel);
						toolBar.remove(diameterYParameterLabel);
						toolBar.remove(drawCircleEllipseButton);
						getContentPane().remove(circleAndEllipsePanel);
						getContentPane().remove(drawShapePanel);
						toolBar.revalidate();
						toolBar.repaint();
						getContentPane().revalidate();
						getContentPane().repaint();

					}

					if (comboBoxShapeType.getSelectedIndex() == 2) {
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
						toolBar.remove(drawCircleEllipseButton);
						toolBar.remove(comboBoxDefaultShape);
						getContentPane().remove(circleAndEllipsePanel);
						getContentPane().remove(defaultShapesPanel);
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

					if (comboBoxShapeType.getSelectedIndex() == 3) {
						getContentPane().remove(circleAndEllipsePanel);
						getContentPane().add(circleAndEllipsePanel);
						circleAndEllipsePanel.repaint(getBounds());
						toolBar.add(diameterX);
						toolBar.add(diameterXParameterLabel);
						toolBar.add(drawCircleEllipseButton);
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						diameterX.setText("");
						diameterXParameterLabel.setText("Diameter [mm]");
						saveButton.enable();
						saveButton.setVisible(true);
						toolBar.remove(diameterY);
						toolBar.remove(diameterYParameterLabel);
						toolBar.remove(pathToLineButton);
						toolBar.remove(pathUndoButton);
						toolBar.remove(comboBoxDefaultShape);
						getContentPane().remove(drawShapePanel);
						getContentPane().remove(defaultShapesPanel);
						toolBar.revalidate();
						toolBar.repaint();
						getContentPane().revalidate();
						getContentPane().repaint();

						drawCircleEllipseButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (Integer.parseInt(diameterX.getText()) > 0) {
									int diameter = Integer.parseInt(diameterX.getText());
									circleAndEllipsePanel.drawCircle(diameter);
								}
							}
						});
					}

					if (comboBoxShapeType.getSelectedIndex() == 4) {
						getContentPane().remove(circleAndEllipsePanel);
						toolBar.remove(pathToLineButton);
						toolBar.remove(pathUndoButton);
						toolBar.remove(comboBoxDefaultShape);
						getContentPane().add(circleAndEllipsePanel);
						circleAndEllipsePanel.repaint(getBounds());
						toolBar.remove(drawCircleEllipseButton);
						toolBar.add(diameterX);
						toolBar.add(diameterXParameterLabel);
						toolBar.add(diameterY);
						toolBar.add(diameterYParameterLabel);
						diameterX.setText("");
						diameterY.setText("");
						diameterXParameterLabel.setText("Height [mm]");
						diameterYParameterLabel.setText("Width [mm]");
						toolBar.add(drawCircleEllipseButton);
						toolBar.add(saveButton);
						toolBar.add(cancelButton);
						saveButton.enable();
						saveButton.setVisible(true);
						getContentPane().remove(drawShapePanel);
						getContentPane().revalidate();
						getContentPane().repaint();
						toolBar.revalidate();
						toolBar.repaint();
						drawCircleEllipseButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								try {
									if (Integer.parseInt(diameterX.getText()) > 0
											&& Integer.parseInt(diameterY.getText()) > 0 && diameterY.isVisible()) {
										int height = Integer.parseInt(diameterX.getText());
										int width = Integer.parseInt(diameterY.getText());
										circleAndEllipsePanel.drawEllipse(height, width);
									}
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						});
					}
				}
			}
		});

		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Saving..  "+ comboBoxShapeType.getSelectedIndex());
				if (comboBoxShapeType.getSelectedIndex() == 1) {
					// default shape
					Shape shape = (Shape) comboBoxDefaultShape.getSelectedItem();
					stoneWindow.saveShape(shape);
					JOptionPane.showMessageDialog(null, "Shape has been successfully saved.");
					setVisible(false);
					dispose();
				}
				if (comboBoxShapeType.getSelectedIndex() == 2) {
					// custom shape
					if (drawShapePanel.getShape() != null) {
						Shape shape = (Shape) drawShapePanel.getShape();
						shape.setName("Custom Shape");
						stoneWindow.saveShape(shape);
						JOptionPane.showMessageDialog(null, "Shape has been successfully saved.");
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect shape. Check your input."," ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (comboBoxShapeType.getSelectedIndex() == 3) {
					// circle
					if (circleAndEllipsePanel.getShape() != null) {
						Shape shape = (Shape) circleAndEllipsePanel.getShape();
						shape.setName("Circle");
						stoneWindow.saveShape(shape);
						JOptionPane.showMessageDialog(null, "Shape has been successfully saved.");
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect shape. Check your input."," ERROR", JOptionPane.ERROR_MESSAGE);
					}

				}
				if (comboBoxShapeType.getSelectedIndex() == 4) {
					// ellipse
					if (circleAndEllipsePanel.getShape() != null) {
						Shape shape = (Shape) circleAndEllipsePanel.getShape();
						shape.setName("Ellipse");
						stoneWindow.saveShape(shape);
						JOptionPane.showMessageDialog(null, "Shape has been successfully saved.");
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect shape. Check your input."," ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}

	private void loadDefaultShapes() throws SQLException {
		JSONShapeController sctrl = new JSONShapeController();

		for (model.Shape defaultShape : sctrl.getAllShapes()) {
			comboBoxDefaultShape.addItem(defaultShape);
		}
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
