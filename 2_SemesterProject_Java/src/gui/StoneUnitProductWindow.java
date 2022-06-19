package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StoneUnitProductWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblStorno;
	private JLabel lblEditCheck;
	private JLabel lblMaximizeRestore;
	private boolean isEditPressed;
	private boolean isMaximizePressed;
	private int x;
	private int y;
	private JTextField textFieldPrice;
	private JLabel lblPriceError;
	private JLabel lblShapeError;
	private JTextField textFieldTotalSize;
	private JLabel lblTotalSizeError;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoneUnitProductWindow frame = new StoneUnitProductWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StoneUnitProductWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setBounds(320, 180, 1280, 720);

//CONTENT PANE
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
//TITLE BAR	PANE	
		isMaximizePressed = false;
		JPanel titleBarPane = new JPanel();
		titleBarPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(isMaximizePressed) {
					checkMaximizeRestore();	
				}
			}
		});
		titleBarPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				setLocation(xx-x, yy-y);
			}
		});
		titleBarPane.setBackground(Color.WHITE);
		titleBarPane.setBounds(0, 0, 1280, 30);
		contentPane.add(titleBarPane);
		titleBarPane.setLayout(null);
		
		JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.exit(0);
				dispose();
			}
		});		
		
		lblClose.setIcon(new ImageIcon(Main.class.getResource("/imgs/close2.png")));
		lblClose.setBounds(1255, 10, 10, 10);
		titleBarPane.add(lblClose);
//TODO: MAXIMIZE - MOVE - RESTORE	
		lblMaximizeRestore = new JLabel("");
		lblMaximizeRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkMaximizeRestore();
			}
		});
		lblMaximizeRestore.setIcon(new ImageIcon(Main.class.getResource("/imgs/maximize2.png")));
		lblMaximizeRestore.setBounds(1210, 10, 10, 10);
		titleBarPane.add(lblMaximizeRestore);
		
		JLabel lblMinimize = new JLabel("");
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblMinimize.setIcon(new ImageIcon(Main.class.getResource("/imgs/minimalize2.png")));
		lblMinimize.setBounds(1165, 10, 10, 10);
		titleBarPane.add(lblMinimize);
		
//TITLE		
		JLabel lblTitle = new JLabel("STONE PRODUCT");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 580, 94);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/confirm1.png")));
					lblStorno.setVisible(true);
					isEditPressed = true;
					switchEditable();
					textFieldTotalSize.grabFocus();
				} else {
					if(haveErrors()) {
						JOptionPane.showMessageDialog(null, "Check Errors!", "ERROR!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					int answer = JOptionPane.showConfirmDialog(null, "Do you really want to apply changes?", "Are you sure?", JOptionPane.YES_NO_CANCEL_OPTION);
					switch(answer) {
						case 0: 
							System.out.println("yes");
							//SAVE CHANGES
							break;
						case 1: 
							System.out.println("no");
							//ABORT CHANGES
							break;
						case 2:
							return;	
					}
						lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/editButton2.png")));
						lblStorno.setVisible(false);
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/editButton2.png")));
		lblEditCheck.setBounds(1060, 85, 50, 50);
		contentPane.add(lblEditCheck);
		
		lblStorno = new JLabel("");
		lblStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblEditCheck.setIcon(new ImageIcon(StoneUnitRemainsWindow.class.getResource("/imgs/editButton2.png")));
				lblStorno.setVisible(false);
				contentPane.grabFocus();
				isEditPressed = false;
				switchEditable();
			}
		});
		lblStorno.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/storno.png")));
		lblStorno.setBounds(1120, 85, 50, 50);
		lblStorno.setVisible(false);
		contentPane.add(lblStorno);
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setText("PRICE");
		textFieldPrice.setBorder(null);
		textFieldPrice.setDisabledTextColor(Color.WHITE);
		textFieldPrice.setBackground(Color.WHITE);
		textFieldPrice.setForeground(new Color(192, 176, 131));
		textFieldPrice.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldPrice.setBounds(108, 172, 430, 53);
		textFieldPrice.setEditable(false);
		contentPane.add(textFieldPrice);
		
		lblPriceError = new JLabel("Must be a positive number! (devided by dot)");
		lblPriceError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPriceError.setForeground(Color.RED);
		lblPriceError.setBounds(110, 225, 240, 14);
		lblPriceError.setVisible(false);
		contentPane.add(lblPriceError);
		textFieldPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldPrice.getText());
					lblPriceError.setVisible(false);
					if(number < 0)
						lblPriceError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblPriceError.setVisible(true);
				}
			}
		});
		
		JLabel lbPriceDescription = new JLabel("PRICE (\u20AC)");
		lbPriceDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPriceDescription.setForeground(new Color(255, 238, 202));
		lbPriceDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbPriceDescription.setBounds(648, 193, 90, 27);
		contentPane.add(lbPriceDescription);
		
		textFieldTotalSize = new JTextField();
		textFieldTotalSize.setText("TOTAL SIZE");
		textFieldTotalSize.setBorder(null);
		textFieldTotalSize.setDisabledTextColor(Color.WHITE);
		textFieldTotalSize.setBackground(Color.WHITE);
		textFieldTotalSize.setForeground(new Color(192, 176, 131));
		textFieldTotalSize.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldTotalSize.setBounds(108, 240, 430, 53);
		textFieldTotalSize.setEditable(false);
		contentPane.add(textFieldTotalSize);
		
		lblTotalSizeError = new JLabel("Must be a positive number! (devided by dot)");
		lblTotalSizeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTotalSizeError.setForeground(Color.RED);
		lblTotalSizeError.setBounds(110, 293, 240, 14);
		lblTotalSizeError.setVisible(false);
		contentPane.add(lblTotalSizeError);
		textFieldTotalSize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldTotalSize.getText());
					lblTotalSizeError.setVisible(false);
					if(number < 0)
						lblTotalSizeError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblTotalSizeError.setVisible(true);
				}
			}
		});
		
		JLabel lblTotalSizeDescription = new JLabel("TOTAL SIZE (CM^2)");
		lblTotalSizeDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalSizeDescription.setForeground(new Color(255, 238, 202));
		lblTotalSizeDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTotalSizeDescription.setBounds(551, 266, 186, 27);
		contentPane.add(lblTotalSizeDescription);
		
		JLabel lblShape = new JLabel("SHAPE");
		lblShape.setForeground(new Color(192, 176, 131));
		lblShape.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblShape.setBounds(108, 308, 128, 53);
		contentPane.add(lblShape);
		
		lblShapeError = new JLabel("Must not be empty!");
		lblShapeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblShapeError.setForeground(Color.RED);
		lblShapeError.setBounds(110, 361, 108, 14);
//		lblPersonError.setVisible(false);
		contentPane.add(lblShapeError);
		
		JLabel lblMoveToShape = new JLabel("");
		lblMoveToShape.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToShape.setBounds(247, 325, 25, 25);
		contentPane.add(lblMoveToShape);
		
		JLabel lblOrder = new JLabel("ORDER");
		lblOrder.setForeground(new Color(192, 176, 131));
		lblOrder.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOrder.setBounds(108, 376, 134, 53);
		contentPane.add(lblOrder);
		
		JLabel lblMoveToOrder = new JLabel("");
		lblMoveToOrder.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToOrder.setBounds(250, 393, 25, 25);
		contentPane.add(lblMoveToOrder);
	}
	
	private boolean haveErrors() {
		return lblPriceError.isVisible() || lblShapeError.isVisible() || lblTotalSizeError.isVisible() ;
	}

	private void checkMaximizeRestore() {
		if(!isMaximizePressed) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			lblMaximizeRestore.setIcon((new ImageIcon(Main.class.getResource("/imgs/restore.png"))));
			isMaximizePressed = true;
			} else {
				setExtendedState(JFrame.NORMAL);
				lblMaximizeRestore.setIcon(new ImageIcon(Main.class.getResource("/imgs/maximize2.png")));
				isMaximizePressed = false;
			}
		
	}	
	private void switchEditable() {
		if(isEditPressed) {
			textFieldPrice.setEditable(true);
			textFieldTotalSize.setEditable(true);
		} else {
			textFieldPrice.setEditable(false);
			textFieldTotalSize.setEditable(false);
		}
		
	}
}
