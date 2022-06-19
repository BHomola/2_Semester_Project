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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import model.DeliveryStatuses;
import javax.swing.SwingConstants;

public class OrderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblDeleteStorno;
	private JLabel lblEditCheck;
	private JLabel lblMaximizeRestore;
	private JLabel lblStornoSmall;
	private JTextArea textAreaUpdates;
	private JLabel lblPaidNote;
	private boolean isEditNotesPressed;
	private boolean isEditPressed;
	private boolean isMaximizePressed;
	private boolean isPaid;
	private int x;
	private int y;
	private JTextField textFieldOfficeCity;
	private JTextField textFieldOfficeAddress;
	private JTextField textFieldOffice;
	private JTextField textFieldCustomerNote;
	private JTextField textFieldDeposit;
	private JTextField textFieldOrderPrice;
	private JTextField textFieldDeliveryDate;
	private JTextField textFieldCity;
	private JTextField textFieldAddress;
	private JComboBox<?> comboBoxDeliveryStatus;
	private JLabel lblPersonError;
	private JLabel lblOrderPriceError;
	private JLabel lblDepositError;
	private JLabel lblEmployeeError;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderWindow frame = new OrderWindow();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OrderWindow() {
//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				System.exit(0);
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
		JLabel lblTitle = new JLabel("ORDER NO. #");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 515, 100);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/confirm1.png")));
					lblDeleteStorno.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/storno.png")));
					isEditPressed = true;
					switchEditable();
					textFieldCity.grabFocus();
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
						lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton.png")));
						lblDeleteStorno.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/deleteButton.png")));
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
					
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton.png")));
		lblEditCheck.setBounds(998, 85, 50, 50);
		contentPane.add(lblEditCheck);
		
		lblDeleteStorno = new JLabel("");
		lblDeleteStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed) {
				lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton.png")));
				lblDeleteStorno.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/deleteButton.png")));
				contentPane.grabFocus();
				isEditPressed = false;
				switchEditable();
				} else {
					int answer = JOptionPane.showConfirmDialog(null, "Do you really want to delete Order No. #?", "Are you sure?", JOptionPane.YES_NO_OPTION);
					if(answer == 1) {}
						//DELETE 
				}
			}
		});
		lblDeleteStorno.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/deleteButton.png")));
		lblDeleteStorno.setBounds(1060, 85, 50, 50);
		contentPane.add(lblDeleteStorno);
		
		JLabel lblInvoice = new JLabel("");
		lblInvoice.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/invoiceButton.png")));
		lblInvoice.setBounds(1200, 85, 50, 50);
		contentPane.add(lblInvoice);
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
//CONTENT		
		JLabel lblPerson = new JLabel("PERSON");
		lblPerson.setForeground(new Color(192, 176, 131));
		lblPerson.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblPerson.setBounds(108, 172, 156, 53);
		contentPane.add(lblPerson);
		
		lblPersonError = new JLabel("Must not be empty!");
		lblPersonError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPersonError.setForeground(Color.RED);
		lblPersonError.setBounds(110, 225, 340, 14);
//		lblPersonError.setVisible(false);
		contentPane.add(lblPersonError);
		
		JLabel lblMoveToPerson = new JLabel("");
		lblMoveToPerson.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToPerson.setBounds(275, 189, 25, 25);
		contentPane.add(lblMoveToPerson);
		
		JLabel lblProducts = new JLabel("PRODUCTS");
		lblProducts.setForeground(new Color(192, 176, 131));
		lblProducts.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblProducts.setBounds(487, 172, 210, 53);
		contentPane.add(lblProducts);
		
		JLabel lblMoveToProducts = new JLabel("");
		lblMoveToProducts.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToProducts.setBounds(709, 189, 25, 25);
		contentPane.add(lblMoveToProducts);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setText("ADDRESS");
		textFieldAddress.setBorder(null);
		textFieldAddress.setDisabledTextColor(Color.WHITE);
		textFieldAddress.setBackground(Color.WHITE);
		textFieldAddress.setForeground(new Color(192, 176, 131));
		textFieldAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldAddress.setBounds(108, 240, 430, 53);
		textFieldAddress.setEditable(false);
		contentPane.add(textFieldAddress);
		
		JLabel lblAddressDescription = new JLabel("DELIVERY ADDRESS");
		lblAddressDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddressDescription.setForeground(new Color(255, 238, 202));
		lblAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddressDescription.setBounds(547, 262, 190, 27);
		contentPane.add(lblAddressDescription);
		
		textFieldCity = new JTextField();
		textFieldCity.setText("CITY");
		textFieldCity.setBorder(null);
		textFieldCity.setDisabledTextColor(Color.WHITE);
		textFieldCity.setBackground(Color.WHITE);
		textFieldCity.setForeground(new Color(192, 176, 131));
		textFieldCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldCity.setBounds(108, 308, 430, 53);
		textFieldCity.setEditable(false);
		contentPane.add(textFieldCity);
		
		JLabel lblCityDescription = new JLabel("CITY");
		lblCityDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCityDescription.setForeground(new Color(255, 238, 202));
		lblCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCityDescription.setBounds(693, 330, 44, 27);
		contentPane.add(lblCityDescription);
		
		comboBoxDeliveryStatus = new JComboBox();
		comboBoxDeliveryStatus.setFocusable(false);
		comboBoxDeliveryStatus.setModel(new DefaultComboBoxModel(DeliveryStatuses.values()));
		comboBoxDeliveryStatus.setBorder(null);
		comboBoxDeliveryStatus.setBackground(Color.WHITE);
		comboBoxDeliveryStatus.setForeground(new Color(192, 176, 131));
		comboBoxDeliveryStatus.setFont(new Font("Segoe UI", Font.BOLD, 40));
		comboBoxDeliveryStatus.setBounds(108, 376, 300, 53);
		comboBoxDeliveryStatus.hidePopup();
		comboBoxDeliveryStatus.setEditable(false);
		comboBoxDeliveryStatus.setEnabled(false);
		contentPane.add(comboBoxDeliveryStatus);
		
		JLabel lblDeliveryStatusDescription = new JLabel("DELIVERY STATUS");
		lblDeliveryStatusDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeliveryStatusDescription.setForeground(new Color(255, 238, 202));
		lblDeliveryStatusDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDeliveryStatusDescription.setBounds(565, 397, 172, 27);
		contentPane.add(lblDeliveryStatusDescription);
		
		textFieldDeliveryDate = new JTextField();
		textFieldDeliveryDate.setText("DELIVERY DATE");
		textFieldDeliveryDate.setBorder(null);
		textFieldDeliveryDate.setDisabledTextColor(Color.WHITE);
		textFieldDeliveryDate.setBackground(Color.WHITE);
		textFieldDeliveryDate.setForeground(new Color(192, 176, 131));
		textFieldDeliveryDate.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldDeliveryDate.setBounds(108, 444, 430, 53);
		textFieldDeliveryDate.setEditable(false);
		contentPane.add(textFieldDeliveryDate);
		
		JLabel lblDeliveryDateDescription = new JLabel("DELIVERY DATE");
		lblDeliveryDateDescription.setForeground(new Color(255, 238, 202));
		lblDeliveryDateDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDeliveryDateDescription.setBounds(587, 466, 150, 27);
		contentPane.add(lblDeliveryDateDescription);
		
		textFieldOrderPrice = new JTextField();
		textFieldOrderPrice.setText("ORDER PRICE");
		textFieldOrderPrice.setBorder(null);
		textFieldOrderPrice.setDisabledTextColor(Color.WHITE);
		textFieldOrderPrice.setBackground(Color.WHITE);
		textFieldOrderPrice.setForeground(new Color(192, 176, 131));
		textFieldOrderPrice.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldOrderPrice.setBounds(108, 512, 430, 53);
		textFieldOrderPrice.setEditable(false);
		contentPane.add(textFieldOrderPrice);
		
		lblOrderPriceError = new JLabel("Must be a positive number! (devided by dot)");
		lblOrderPriceError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblOrderPriceError.setForeground(Color.RED);
		lblOrderPriceError.setBounds(110, 565, 340, 14);
		lblOrderPriceError.setVisible(false);
		contentPane.add(lblOrderPriceError);
		textFieldOrderPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldOrderPrice.getText());
					lblOrderPriceError.setVisible(false);
					if(number < 0)
						lblOrderPriceError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblOrderPriceError.setVisible(true);
				}
			}
		});
		
		JLabel lblOrderPriceDescription = new JLabel("ODER PRICE (\u20AC)");
		lblOrderPriceDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrderPriceDescription.setForeground(new Color(255, 238, 202));
		lblOrderPriceDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOrderPriceDescription.setBounds(587, 534, 150, 27);
		contentPane.add(lblOrderPriceDescription);
		
		textFieldDeposit = new JTextField();
		textFieldDeposit.setText("DEPOSIT");
		textFieldDeposit.setBorder(null);
		textFieldDeposit.setDisabledTextColor(Color.WHITE);
		textFieldDeposit.setBackground(Color.WHITE);
		textFieldDeposit.setForeground(new Color(192, 176, 131));
		textFieldDeposit.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldDeposit.setBounds(108, 580, 164, 53);
		textFieldDeposit.setEditable(false);
		contentPane.add(textFieldDeposit);
		
		lblDepositError = new JLabel("Must be a positive number! (devided by dot)");
		lblDepositError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblDepositError.setForeground(Color.RED);
		lblDepositError.setBounds(110, 635, 340, 14);
		lblDepositError.setVisible(false);
		contentPane.add(lblDepositError);
		textFieldDeposit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldDeposit.getText());
					lblDepositError.setVisible(false);
					if(number < 0)
						lblDepositError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblDepositError.setVisible(true);
				}
			}
		});
		
		JLabel lblisPaid = new JLabel("");
		//GET DATA
		isPaid = true;
		lblisPaid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed)
					if(isPaid) {
						lblisPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIcon3.png")));
						isPaid = false;
					} else {
						lblisPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIcon3.png")));
						isPaid = true;
					}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isEditPressed)
					lblPaidNote.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPaidNote.setVisible(false);
			}
		});
		if(isPaid)
			lblisPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIcon3.png")));
		if(!isPaid)
			lblisPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIcon3.png")));
		lblisPaid.setBounds(286, 597, 28, 28);
		contentPane.add(lblisPaid);
		
		lblPaidNote = new JLabel("Click to change");
		lblPaidNote.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPaidNote.setBounds(310, 597, 116, 14);
		lblPaidNote.setVisible(false);
		contentPane.add(lblPaidNote);
		
		JLabel lblDepositDescription = new JLabel("DEPOPSIT/ISPAID");
		lblDepositDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepositDescription.setForeground(new Color(255, 238, 202));
		lblDepositDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDepositDescription.setBounds(565, 602, 172, 27);
		contentPane.add(lblDepositDescription);
		
		textFieldCustomerNote = new JTextField();
		textFieldCustomerNote.setText("CUSTOMER NOTE");
		textFieldCustomerNote.setBorder(null);
		textFieldCustomerNote.setDisabledTextColor(Color.WHITE);
		textFieldCustomerNote.setBackground(Color.WHITE);
		textFieldCustomerNote.setForeground(new Color(192, 176, 131));
		textFieldCustomerNote.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldCustomerNote.setBounds(108, 648, 430, 53);
		textFieldCustomerNote.setEditable(false);
		contentPane.add(textFieldCustomerNote);
		
		JLabel lblCustomerNoteDescription = new JLabel("CUSTOMER NOTE");
		lblCustomerNoteDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerNoteDescription.setForeground(new Color(255, 238, 202));
		lblCustomerNoteDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCustomerNoteDescription.setBounds(565, 670, 172, 27);
		contentPane.add(lblCustomerNoteDescription);
		
		JLabel lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setForeground(new Color(192, 176, 131));
		lblEmployee.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblEmployee.setBounds(760, 172, 200, 53);
		contentPane.add(lblEmployee);
		
		lblEmployeeError = new JLabel("Must not be empty!");
		lblEmployeeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmployeeError.setForeground(Color.RED);
		lblEmployeeError.setBounds(762, 225, 340, 14);
//		lblEmployeeError.setVisible(false);
		contentPane.add(lblEmployeeError);
		
		JLabel lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToEmployee.setBounds(972, 189, 25, 25);
		contentPane.add(lblMoveToEmployee);
		
		textFieldOffice = new JTextField();
		textFieldOffice.setText("OFFICE");
		textFieldOffice.setBorder(null);
		textFieldOffice.setDisabledTextColor(Color.WHITE);
		textFieldOffice.setBackground(Color.WHITE);
		textFieldOffice.setForeground(new Color(192, 176, 131));
		textFieldOffice.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldOffice.setBounds(760, 240, 405, 53);
		textFieldOffice.setEditable(false);
		contentPane.add(textFieldOffice);
		
		JLabel lblOfficeDescription = new JLabel("OFFICE");
		lblOfficeDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOfficeDescription.setForeground(new Color(255, 238, 202));
		lblOfficeDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOfficeDescription.setBounds(1199, 261, 66, 27);
		contentPane.add(lblOfficeDescription);
		
		textFieldOfficeAddress = new JTextField();
		textFieldOfficeAddress.setText("ADDRESS");
		textFieldOfficeAddress.setBorder(null);
		textFieldOfficeAddress.setDisabledTextColor(Color.WHITE);
		textFieldOfficeAddress.setBackground(Color.WHITE);
		textFieldOfficeAddress.setForeground(new Color(192, 176, 131));
		textFieldOfficeAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldOfficeAddress.setBounds(760, 310, 405, 53);
		textFieldOfficeAddress.setEditable(false);
		contentPane.add(textFieldOfficeAddress);
		
		JLabel lblOfficeAddressDescription = new JLabel("ADDRESS");
		lblOfficeAddressDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOfficeAddressDescription.setForeground(new Color(255, 238, 202));
		lblOfficeAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOfficeAddressDescription.setBounds(1175, 331, 90, 27);
		contentPane.add(lblOfficeAddressDescription);
		
		textFieldOfficeCity = new JTextField();
		textFieldOfficeCity.setText("CITY");
		textFieldOfficeCity.setBorder(null);
		textFieldOfficeCity.setDisabledTextColor(Color.WHITE);
		textFieldOfficeCity.setBackground(Color.WHITE);
		textFieldOfficeCity.setForeground(new Color(192, 176, 131));
		textFieldOfficeCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldOfficeCity.setBounds(760, 378, 405, 53);
		textFieldOfficeCity.setEditable(false);
		contentPane.add(textFieldOfficeCity);
		
		JLabel lblOfficeCityDescription = new JLabel("CITY");
		lblOfficeCityDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOfficeCityDescription.setForeground(new Color(255, 238, 202));
		lblOfficeCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOfficeCityDescription.setBounds(1221, 399, 44, 27);
		contentPane.add(lblOfficeCityDescription);
		
		JTextField lblUpdates = new JTextField();
		lblUpdates.setText("UPDATES");
		lblUpdates.setBorder(null);
		lblUpdates.setDisabledTextColor(Color.WHITE);
		lblUpdates.setBackground(Color.WHITE);
		lblUpdates.setForeground(new Color(192, 176, 131));
		lblUpdates.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblUpdates.setBounds(760, 444, 180, 53);
		lblUpdates.setEditable(false);
		contentPane.add(lblUpdates);
		
		JLabel lblUpdateConfirmIcon = new JLabel("");
		isEditNotesPressed = false;
		lblUpdateConfirmIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditNotesPressed) {
					lblUpdateConfirmIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/confirmSmall.png")));
					lblStornoSmall.setVisible(true);
					textAreaUpdates.setEditable(true);
					textAreaUpdates.grabFocus();
					isEditNotesPressed = true;
				} else {
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
					lblUpdateConfirmIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/notes2.png")));
					lblStornoSmall.setVisible(false);
					textAreaUpdates.setEditable(false);
					contentPane.grabFocus();
					isEditNotesPressed = false;
				}
			}
		});
		lblUpdateConfirmIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/notes2.png")));
		lblUpdateConfirmIcon.setBounds(948, 460, 25, 25);
		contentPane.add(lblUpdateConfirmIcon);
		
		lblStornoSmall = new JLabel("");
		lblStornoSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblUpdateConfirmIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/notes2.png")));
				lblStornoSmall.setVisible(false);
				textAreaUpdates.setEditable(false);
				contentPane.grabFocus();
				isEditNotesPressed = false;
			}
		});
		lblStornoSmall.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/stornoSmall.png")));
		lblStornoSmall.setBounds(978, 460, 27, 25);
		lblStornoSmall.setVisible(false);
		contentPane.add(lblStornoSmall);
		
		JScrollPane updatesScrollPane = new JScrollPane();
		updatesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		updatesScrollPane.setBorder(null);
		updatesScrollPane.setBounds(760, 512, 505, 184);
		updatesScrollPane.getVerticalScrollBar().setUnitIncrement(4);
		contentPane.add(updatesScrollPane);
		
		textAreaUpdates = new JTextArea();
		textAreaUpdates.setLineWrap(true);
		textAreaUpdates.setWrapStyleWord(true);
		textAreaUpdates.setText("12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n\r\n12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n\r\n");
		textAreaUpdates.setForeground(new Color(192, 176, 131));
		textAreaUpdates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaUpdates.setEditable(false);
		updatesScrollPane.setViewportView(textAreaUpdates);
		textAreaUpdates.setCaretPosition(0);
	}
	
	private boolean haveErrors() {
		return lblPersonError.isVisible() || lblOrderPriceError.isVisible() || lblDepositError.isVisible() || lblEmployeeError.isVisible();
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
			textFieldAddress.setEditable(true);
			textFieldCity.setEditable(true);
			comboBoxDeliveryStatus.setEnabled(true);
			textFieldDeliveryDate.setEditable(true);
			textFieldOrderPrice.setEditable(true);
			textFieldDeposit.setEditable(true);
			textFieldCustomerNote.setEditable(true);
			textFieldOffice.setEditable(true);
			textFieldOfficeAddress.setEditable(true);
			textFieldOfficeCity.setEditable(true);
		} else {
			textFieldAddress.setEditable(false);
			textFieldCity.setEditable(false);
			comboBoxDeliveryStatus.setEnabled(false);
			textFieldDeliveryDate.setEditable(false);
			textFieldOrderPrice.setEditable(false);
			textFieldDeposit.setEditable(false);
			textFieldCustomerNote.setEditable(false);
			textFieldOffice.setEditable(false);
			textFieldOfficeAddress.setEditable(false);
			textFieldOfficeCity.setEditable(false);
		}
		
	}
}
