package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
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

public class MaterialTypeWindow extends JFrame {

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
	private JTextField textFieldMaterialDescription;
	private JTextField textFieldTypeID;
	private JTextField textFieldTypeName;
	private JTextField textFieldMaterialName;
	private JTextField textFieldMaterialID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaterialTypeWindow frame = new MaterialTypeWindow();
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
	public MaterialTypeWindow() {
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
		JLabel lblTitle = new JLabel("MATERIAL, TYPE");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 550, 94);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/confirm1.png")));
					lblStorno.setVisible(true);
					isEditPressed = true;
					switchEditable();
					textFieldMaterialName.grabFocus();
				} else {
//					if(haveErrors()) {
//						JOptionPane.showMessageDialog(null, "Check Errors!", "ERROR!", JOptionPane.ERROR_MESSAGE);
//						return;
//					}
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
						lblEditCheck.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/editButton2.png")));
						lblStorno.setVisible(false);
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/editButton2.png")));
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
		lblStorno.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/storno.png")));
		lblStorno.setBounds(1120, 85, 50, 50);
		lblStorno.setVisible(false);
		contentPane.add(lblStorno);
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
//CONTENT		
		textFieldMaterialID = new JTextField();
		textFieldMaterialID.setText("MATERIAL'S ID");
		textFieldMaterialID.setBorder(null);
		textFieldMaterialID.setDisabledTextColor(Color.WHITE);
		textFieldMaterialID.setBackground(Color.WHITE);
		textFieldMaterialID.setForeground(new Color(192, 176, 131));
		textFieldMaterialID.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldMaterialID.setBounds(108, 170, 430, 53);
		textFieldMaterialID.setEditable(false);
		contentPane.add(textFieldMaterialID);
		
		JLabel lbPriceDescription = new JLabel("MATERIAL'S ID");
		lbPriceDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPriceDescription.setForeground(new Color(255, 238, 202));
		lbPriceDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbPriceDescription.setBounds(595, 191, 143, 27);
		contentPane.add(lbPriceDescription);
		
		textFieldMaterialName = new JTextField();
		textFieldMaterialName.setText("MATERIAL'S NAME");
		textFieldMaterialName.setBorder(null);
		textFieldMaterialName.setDisabledTextColor(Color.WHITE);
		textFieldMaterialName.setBackground(Color.WHITE);
		textFieldMaterialName.setForeground(new Color(192, 176, 131));
		textFieldMaterialName.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldMaterialName.setBounds(108, 238, 430, 53);
		textFieldMaterialName.setEditable(false);
		contentPane.add(textFieldMaterialName);
		
		JLabel lblTotalSizeDescription = new JLabel("MATERIAL'S NAME");
		lblTotalSizeDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalSizeDescription.setForeground(new Color(255, 238, 202));
		lblTotalSizeDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTotalSizeDescription.setBounds(551, 259, 186, 27);
		contentPane.add(lblTotalSizeDescription);
		
		textFieldMaterialDescription = new JTextField();
		textFieldMaterialDescription.setText("M'S DESCRIPTION");
		textFieldMaterialDescription.setBorder(null);
		textFieldMaterialDescription.setDisabledTextColor(Color.WHITE);
		textFieldMaterialDescription.setBackground(Color.WHITE);
		textFieldMaterialDescription.setForeground(new Color(192, 176, 131));
		textFieldMaterialDescription.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldMaterialDescription.setBounds(108, 306, 368, 53);
		textFieldMaterialDescription.setEditable(false);
		contentPane.add(textFieldMaterialDescription);
		
		JLabel lblMaterialDescriptionDescription = new JLabel("MATERIAL'S DESCRIPTION");
		lblMaterialDescriptionDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaterialDescriptionDescription.setForeground(new Color(255, 238, 202));
		lblMaterialDescriptionDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMaterialDescriptionDescription.setBounds(486, 327, 251, 27);
		contentPane.add(lblMaterialDescriptionDescription);
		
		textFieldTypeID = new JTextField();
		textFieldTypeID.setText("TYPE'S ID");
		textFieldTypeID.setBorder(null);
		textFieldTypeID.setDisabledTextColor(Color.WHITE);
		textFieldTypeID.setBackground(Color.WHITE);
		textFieldTypeID.setForeground(new Color(192, 176, 131));
		textFieldTypeID.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldTypeID.setBounds(108, 374, 430, 53);
		textFieldTypeID.setEditable(false);
		contentPane.add(textFieldTypeID);
		
		JLabel lblTypeIDDescription = new JLabel("TYPE'S ID");
		lblTypeIDDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeIDDescription.setForeground(new Color(255, 238, 202));
		lblTypeIDDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTypeIDDescription.setBounds(551, 395, 186, 27);
		contentPane.add(lblTypeIDDescription);
		
		textFieldTypeName = new JTextField();
		textFieldTypeName.setText("TYPE'S NAME");
		textFieldTypeName.setBorder(null);
		textFieldTypeName.setDisabledTextColor(Color.WHITE);
		textFieldTypeName.setBackground(Color.WHITE);
		textFieldTypeName.setForeground(new Color(192, 176, 131));
		textFieldTypeName.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldTypeName.setBounds(108, 442, 430, 53);
		textFieldTypeName.setEditable(false);
		contentPane.add(textFieldTypeName);
		
		JLabel lblTypeNameDescription = new JLabel("TYPE'S NAME");
		lblTypeNameDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeNameDescription.setForeground(new Color(255, 238, 202));
		lblTypeNameDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTypeNameDescription.setBounds(551, 463, 186, 27);
		contentPane.add(lblTypeNameDescription);
		
		JLabel lblSupplier = new JLabel("SUPPLIER(S)");
		lblSupplier.setForeground(new Color(192, 176, 131));
		lblSupplier.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblSupplier.setBounds(108, 510, 235, 53);
		contentPane.add(lblSupplier);
		
//		lblSupplierError = new JLabel("Must be set!");
//		lblSupplierError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
//		lblSupplierError.setForeground(Color.RED);
//		lblSupplierError.setBounds(107, 563, 108, 14);
////		lblPersonError.setVisible(false);
//		contentPane.add(lblSupplierError);
		
		JLabel lblMoveToSupplier = new JLabel("");
		lblMoveToSupplier.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToSupplier.setBounds(352, 528, 25, 25);
		contentPane.add(lblMoveToSupplier);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon(MaterialTypeWindow.class.getResource("/imgs/Granite/Blue-Pearl.jpg")));
		lblPicture.setBounds(763, 192, 500, 500);
		contentPane.add(lblPicture);
	}
	
//	private boolean haveErrors() {
//		return lblPriceError.isVisible() || lblSupplierError.isVisible() || lblTotalSizeError.isVisible() ;
//	}

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
			textFieldMaterialName.setEditable(true);
			textFieldMaterialDescription.setEditable(true);
			textFieldTypeName.setEditable(true);
		} else {
			textFieldMaterialName.setEditable(false);
			textFieldMaterialDescription.setEditable(false);
			textFieldTypeName.setEditable(false);
		}
		
	}
}
