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

public class ProfileCustomerWindow extends JFrame {

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
	private JTextField textFieldDiscount;
	private JLabel lblDiscountError;
	private JTextField textFieldTotalSpends;

	private boolean isPremium;

	private boolean isCompany;

	private JLabel lblIsCompanyNote;

	private JLabel lblIsPremiumNote;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileCustomerWindow frame = new ProfileCustomerWindow();
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
	public ProfileCustomerWindow() {
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
		JLabel lblTitle = new JLabel("CUSTOMER, (#ID) NAME");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 810, 94);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(ProfileCustomerWindow.class.getResource("/imgs/confirm1.png")));
					lblStorno.setVisible(true);
					isEditPressed = true;
					switchEditable();
					textFieldDiscount.grabFocus();
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
						lblEditCheck.setIcon(new ImageIcon(ProfileCustomerWindow.class.getResource("/imgs/editButton2.png")));
						lblStorno.setVisible(false);
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(ProfileCustomerWindow.class.getResource("/imgs/editButton2.png")));
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
		lblStorno.setIcon(new ImageIcon(ProfileCustomerWindow.class.getResource("/imgs/storno.png")));
		lblStorno.setBounds(1120, 85, 50, 50);
		lblStorno.setVisible(false);
		contentPane.add(lblStorno);
		
//CONTENT		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(ProfileCustomerWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(ProfileCustomerWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
		textFieldDiscount = new JTextField();
		textFieldDiscount.setText("DISCOUNT");
		textFieldDiscount.setBorder(null);
		textFieldDiscount.setDisabledTextColor(Color.WHITE);
		textFieldDiscount.setBackground(Color.WHITE);
		textFieldDiscount.setForeground(new Color(192, 176, 131));
		textFieldDiscount.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldDiscount.setBounds(108, 172, 430, 53);
		textFieldDiscount.setEditable(false);
		contentPane.add(textFieldDiscount);
		
		lblDiscountError = new JLabel("Must be a positive number! (devided by dot)");
		lblDiscountError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblDiscountError.setForeground(Color.RED);
		lblDiscountError.setBounds(110, 225, 240, 14);
		lblDiscountError.setVisible(false);
		contentPane.add(lblDiscountError);
		textFieldDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldDiscount.getText());
					lblDiscountError.setVisible(false);
					if(number < 0)
						lblDiscountError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblDiscountError.setVisible(true);
				}
			}
		});
		
		JLabel lbDiscountDescription = new JLabel("DISCOUNT (%)");
		lbDiscountDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDiscountDescription.setForeground(new Color(255, 238, 202));
		lbDiscountDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbDiscountDescription.setBounds(600, 193, 138, 27);
		contentPane.add(lbDiscountDescription);
		
		textFieldTotalSpends = new JTextField();
		textFieldTotalSpends.setText("TOTAL SPENDS");
		textFieldTotalSpends.setBorder(null);
		textFieldTotalSpends.setDisabledTextColor(Color.WHITE);
		textFieldTotalSpends.setBackground(Color.WHITE);
		textFieldTotalSpends.setForeground(new Color(192, 176, 131));
		textFieldTotalSpends.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldTotalSpends.setBounds(108, 240, 430, 53);
		textFieldTotalSpends.setEditable(false);
		contentPane.add(textFieldTotalSpends);
		
		JLabel lblTotalSpendsDescription = new JLabel("TOTAL SPENDS (\u20AC)");
		lblTotalSpendsDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalSpendsDescription.setForeground(new Color(255, 238, 202));
		lblTotalSpendsDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTotalSpendsDescription.setBounds(548, 261, 189, 27);
		contentPane.add(lblTotalSpendsDescription);
		
		JLabel lblIsPremium = new JLabel("IS PREMIUM");
		lblIsPremium.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsPremium.setForeground(new Color(192, 176, 131));
		lblIsPremium.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblIsPremium.setBounds(108, 308, 118, 27);
		contentPane.add(lblIsPremium);
		
		JLabel lblIsPremiumIcon = new JLabel("");
		//GET DATA
		isPremium = true;
		lblIsPremiumIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed)
					if(isPremium) {
						lblIsPremiumIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIconSmall.png")));
						isPremium = false;
					} else {
						lblIsPremiumIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIconSmall.png")));
						isPremium = true;
					}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isEditPressed)
					lblIsPremiumNote.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblIsPremiumNote.setVisible(false);
			}
		});
		if(isPremium)
			lblIsPremiumIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIconSmall.png")));
		if(!isPremium)
			lblIsPremiumIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIconSmall.png")));
		lblIsPremiumIcon.setBounds(237, 318, 12, 12);
		contentPane.add(lblIsPremiumIcon);
		
		lblIsPremiumNote = new JLabel("Click to change");
		lblIsPremiumNote.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblIsPremiumNote.setBounds(247, 304, 79, 14);
		lblIsPremiumNote.setVisible(false);
		contentPane.add(lblIsPremiumNote);
		
		JLabel lblIsCompany = new JLabel("IS COMPANY");
		lblIsCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsCompany.setForeground(new Color(192, 176, 131));
		lblIsCompany.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblIsCompany.setBounds(324, 308, 125, 27);
		contentPane.add(lblIsCompany);
		
		JLabel lblIsCompanyIcon = new JLabel("");
		//GET DATA
		isCompany = true;
		lblIsCompanyIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed)
					if(isCompany) {
						lblIsCompanyIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIconSmall.png")));
						isCompany = false;
					} else {
						lblIsCompanyIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIconSmall.png")));
						isCompany = true;
					}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isEditPressed)
					lblIsCompanyNote.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblIsCompanyNote.setVisible(false);
			}
		});
		if(isCompany)
			lblIsCompanyIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIconSmall.png")));
		if(!isCompany)
			lblIsCompanyIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIconSmall.png")));
		lblIsCompanyIcon.setBounds(456, 318, 12, 12);
		contentPane.add(lblIsCompanyIcon);
		
		lblIsCompanyNote = new JLabel("Click to change");
		lblIsCompanyNote.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblIsCompanyNote.setBounds(466, 304, 116, 14);
		lblIsCompanyNote.setVisible(false);
		contentPane.add(lblIsCompanyNote);
		
		JLabel lblOrder = new JLabel("ORDER(S)");
		lblOrder.setForeground(new Color(192, 176, 131));
		lblOrder.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOrder.setBounds(760, 172, 186, 53);
		contentPane.add(lblOrder);
		
		
		JLabel lblMoveToOrder = new JLabel("");
		lblMoveToOrder.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToOrder.setBounds(957, 189, 25, 25);
		contentPane.add(lblMoveToOrder);
	}
	
	private boolean haveErrors() {
		return lblDiscountError.isVisible();
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
			textFieldDiscount.setEditable(true);
//			textFieldTotalSpends.setEditable(true);
		} else {
			textFieldDiscount.setEditable(false);
//			textFieldTotalSpends.setEditable(false);
		}
		
	}
}
