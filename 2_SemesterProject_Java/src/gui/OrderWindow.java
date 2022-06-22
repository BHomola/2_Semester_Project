package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LocationCityController;
import controller.PersonController;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import model.DeliveryStatuses;
import model.Employee;
import model.Location;
import model.OrderInfo;

import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

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
	private boolean isNew;
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
	private JTextField txtFieldPersonID;
	private OrderInfo order;

	private JTextField txtFieldEmployeeID;

	private JLabel lblOfficeError;

	private JComboBox comboBoxOffice;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderWindow frame = new OrderWindow(true);
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
	public OrderWindow(Boolean isNew) {
		this.isNew = isNew;
		order = new OrderInfo(null, null, null, null, null, null);
//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		JLabel lblTitle = new JLabel("ORDER NO. #");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 515, 94);
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
							buildOrder();
							break;
						case 1: 
							System.out.println("no");
							if(isNew)
								dispose();
							break;
						case 2:
							return;	
					}
						lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton2.png")));
						lblDeleteStorno.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/deleteButton.png")));
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
					
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton2.png")));
		lblEditCheck.setBounds(1000, 85, 50, 50);
		contentPane.add(lblEditCheck);
		
		lblDeleteStorno = new JLabel("");
		lblDeleteStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed) {
					if(isNew)
						dispose();
					lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton2.png")));
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
		if(isNew)
			lblInvoice.setVisible(false);
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
		if(!isNew)
			lblPersonError.setVisible(false);
		contentPane.add(lblPersonError);
		
		JLabel lblMoveToPerson = new JLabel("");
		lblMoveToPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtFieldPersonID.isVisible()) {
					txtFieldPersonID.setVisible(false);
				} else {
					txtFieldPersonID.setVisible(true);
				}
			}
		});
		lblMoveToPerson.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToPerson.setBounds(275, 189, 25, 25);
		contentPane.add(lblMoveToPerson);
		
		txtFieldPersonID = new JTextField();
		String personIDDefaultInput = "INPUT ID";
		txtFieldPersonID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldPersonID.getText().equals(personIDDefaultInput))
					txtFieldPersonID.setText("");
				}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtFieldPersonID.getText().equals("")) {
					txtFieldPersonID.setText(personIDDefaultInput);
				} else {
					if(!lblPersonError.isVisible())
						txtFieldPersonID.setVisible(false);
				}
			}
		});
//TODO MAYBE NOT KEYRELEASED
		txtFieldPersonID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkCustomerIDError();
			}
		});
		txtFieldPersonID.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtFieldPersonID.setText("INPUT ID");
		txtFieldPersonID.setBounds(310, 189, 86, 25);
		contentPane.add(txtFieldPersonID);
		txtFieldPersonID.setVisible(false);
		txtFieldPersonID.setColumns(10);
		
		JLabel lblProducts = new JLabel("PRODUCT(S)");
		lblProducts.setForeground(new Color(192, 176, 131));
		lblProducts.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblProducts.setBounds(459, 172, 240, 53);
		if(isNew)
			lblProducts.setVisible(false);
		contentPane.add(lblProducts);
		
		JLabel lblMoveToProducts = new JLabel("");
		lblMoveToProducts.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToProducts.setBounds(709, 189, 25, 25);
		if(isNew)
			lblMoveToProducts.setVisible(false);
		contentPane.add(lblMoveToProducts);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setText("ADDRESS");
		textFieldAddress.setBorder(null);
		textFieldAddress.setDisabledTextColor(Color.WHITE);
		textFieldAddress.setBackground(Color.WHITE);
		textFieldAddress.setForeground(new Color(192, 176, 131));
		textFieldAddress.setFont(new Font("Segoe UI", Font.BOLD, 20));
		textFieldAddress.setBounds(108, 240, 430, 53);
		textFieldAddress.setEditable(false);
		contentPane.add(textFieldAddress);
		
		JLabel lblAddressDescription = new JLabel("DELIVERY ADDRESS");
		lblAddressDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddressDescription.setForeground(new Color(255, 238, 202));
		lblAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddressDescription.setBounds(547, 261, 190, 27);
		contentPane.add(lblAddressDescription);
		
		textFieldCity = new JTextField();
		textFieldCity.setText("CITY");
		textFieldCity.setBorder(null);
		textFieldCity.setDisabledTextColor(Color.WHITE);
		textFieldCity.setBackground(Color.WHITE);
		textFieldCity.setForeground(new Color(192, 176, 131));
		textFieldCity.setFont(new Font("Segoe UI", Font.BOLD, 15));
		textFieldCity.setBounds(108, 308, 430, 53);
		textFieldCity.setEditable(false);
		contentPane.add(textFieldCity);
		
		JLabel lblCityDescription = new JLabel("CITY");
		lblCityDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCityDescription.setForeground(new Color(255, 238, 202));
		lblCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCityDescription.setBounds(693, 329, 44, 27);
		contentPane.add(lblCityDescription);
		
		comboBoxDeliveryStatus = new JComboBox();
		comboBoxDeliveryStatus.setFocusable(false);
		comboBoxDeliveryStatus.setModel(new DefaultComboBoxModel(DeliveryStatuses.values()));
		comboBoxDeliveryStatus.setBorder(null);
		comboBoxDeliveryStatus.setBackground(Color.WHITE);
		comboBoxDeliveryStatus.setForeground(new Color(192, 176, 131));
		comboBoxDeliveryStatus.setFont(new Font("Segoe UI", Font.BOLD, 40));
		comboBoxDeliveryStatus.setBounds(108, 376, 300, 53);
		if(!isNew)
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
				if(!isCorrectDoubleNumber(textFieldOrderPrice)) {
					lblOrderPriceError.setVisible(true);
				} else {
					lblOrderPriceError.setVisible(false);
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
				if(!isCorrectDoubleNumber(textFieldOrderPrice)) {
					lblDepositError.setVisible(true);
				} else {
					lblDepositError.setVisible(false);
				}
			}
		});
		
		JLabel lblIsPaid = new JLabel("");
		//GET DATA
		isPaid = true;
		lblIsPaid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed)
					if(isPaid) {
						lblIsPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIcon3.png")));
						isPaid = false;
					} else {
						lblIsPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIcon3.png")));
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
			lblIsPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIcon3.png")));
		if(!isPaid)
			lblIsPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIcon3.png")));
		lblIsPaid.setBounds(286, 597, 28, 28);
		contentPane.add(lblIsPaid);
		
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
		textFieldCustomerNote.setFont(new Font("Segoe UI", Font.BOLD, 20));
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
		if(!isNew)
			lblEmployeeError.setVisible(false);
		contentPane.add(lblEmployeeError);
		
		JLabel lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtFieldEmployeeID.isVisible()) {
					txtFieldEmployeeID.setVisible(false);
				} else {
					txtFieldEmployeeID.setVisible(true);
				}
			}
		});
		lblMoveToEmployee.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToEmployee.setBounds(972, 189, 25, 25);
		contentPane.add(lblMoveToEmployee);
		
		txtFieldEmployeeID = new JTextField();
		String emplopyeeIDDefaultInput = "INPUT ID";
		txtFieldEmployeeID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldEmployeeID.getText().equals(emplopyeeIDDefaultInput))
					txtFieldEmployeeID.setText("");
				}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtFieldEmployeeID.getText().equals("")) {
					txtFieldEmployeeID.setText(emplopyeeIDDefaultInput);
				} else {
					if(!lblEmployeeError.isVisible())
						txtFieldEmployeeID.setVisible(false);
				}
			}
		});
//TODO MAYBE NOT KEYRELEASED
		txtFieldEmployeeID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkEmployeeIDError();
			}
		});
		txtFieldEmployeeID.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtFieldEmployeeID.setText(emplopyeeIDDefaultInput);
		txtFieldEmployeeID.setBounds(1007, 189, 86, 25);
		contentPane.add(txtFieldEmployeeID);
		txtFieldEmployeeID.setVisible(false);
		txtFieldEmployeeID.setColumns(10);
	
		comboBoxOffice = new JComboBox<Location>(new Vector<Location>(Main.cachedLocations));
//		comboBoxOffice.setModel(new DefaultComboBoxModel<String>(Main.cachedLocations.toArray(new String[0])));
//		comboBoxOffice = new JComboBox();
		comboBoxOffice.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				comboBoxOffice.setFont(new Font("Segoe UI", Font.BOLD, 40));
				order.setOffice((Location) comboBoxOffice.getSelectedItem());
				textFieldOfficeAddress.setText(order.getOffice().getAddress());
				textFieldOfficeCity.setText(order.getOffice().getCity().getCityName() + ", " 
						+ order.getOffice().getCity().getZipCode() + ", "
						+ order.getOffice().getCity().getCountry());
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				comboBoxOffice.setFont(new Font("Segoe UI", Font.BOLD, 15));
			}
		});
		comboBoxOffice.setFocusable(false);
		comboBoxOffice.setBorder(null);
		comboBoxOffice.setBackground(Color.WHITE);
		comboBoxOffice.setForeground(new Color(192, 176, 131));
		comboBoxOffice.setFont(new Font("Segoe UI", Font.BOLD, 40));
		comboBoxOffice.setBounds(760, 240, 350, 53);
		if(!isNew) {
			comboBoxOffice.setEnabled(false);
		}
		contentPane.add(comboBoxOffice);
		
		lblOfficeError = new JLabel("Must not be empty!");
		lblOfficeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblOfficeError.setForeground(Color.RED);
		lblOfficeError.setBounds(762, 293, 340, 14);
		if(!isNew)
			lblPersonError.setVisible(false);
		contentPane.add(lblOfficeError);
		
		JLabel lblOfficeDescription = new JLabel();
		lblOfficeDescription.setText("OFFICE");
		lblOfficeDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOfficeDescription.setForeground(new Color(255, 238, 202));
		lblOfficeDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOfficeDescription.setBounds(1149, 261, 116, 27);
		contentPane.add(lblOfficeDescription);
		
		textFieldOfficeAddress = new JTextField();
		textFieldOfficeAddress.setText("ADDRESS");
		textFieldOfficeAddress.setBorder(null);
		textFieldOfficeAddress.setDisabledTextColor(Color.WHITE);
		textFieldOfficeAddress.setBackground(Color.WHITE);
		textFieldOfficeAddress.setForeground(new Color(192, 176, 131));
		textFieldOfficeAddress.setFont(new Font("Segoe UI", Font.BOLD, 20));
		textFieldOfficeAddress.setBounds(760, 310, 390, 53);
		textFieldOfficeAddress.setEditable(false);
		if(isNew)
			textFieldOfficeAddress.setForeground(new Color(172, 172, 172));
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
		textFieldOfficeCity.setFont(new Font("Segoe UI", Font.BOLD, 15));
		textFieldOfficeCity.setBounds(760, 378, 451, 53);
		textFieldOfficeCity.setEditable(false);
		if(isNew)
			textFieldOfficeCity.setForeground(new Color(172, 172, 172));
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
		updatesScrollPane.setBounds(760, 497, 505, 212);
		updatesScrollPane.getVerticalScrollBar().setUnitIncrement(4);
		contentPane.add(updatesScrollPane);
		
		textAreaUpdates = new JTextArea();
		textAreaUpdates.setLineWrap(true);
		textAreaUpdates.setWrapStyleWord(true);
		if(isNew)
			textAreaUpdates.setText("EXAMPLE:\r\n12.06.2022(17:30) - Product \"xxxx\" added to order");
		textAreaUpdates.setForeground(new Color(192, 176, 131));
		textAreaUpdates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaUpdates.setEditable(false);
		updatesScrollPane.setViewportView(textAreaUpdates);
		textAreaUpdates.setCaretPosition(0);
		if(isNew) {
			lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/confirm1.png")));
			lblDeleteStorno.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/storno.png")));
			isEditPressed = true;
			switchEditable();
			textFieldCity.grabFocus();
		}
	}

	private void buildOrder() {
		order.setAddress(textFieldAddress.getText().substring(0, 1).toUpperCase() + textFieldAddress.getText().substring(1));
		order.setCity(null);
	}


//	private boolean isCorrectIntNumber(JTextField textField) {
//		try {
//			Integer number = Integer.parseInt(textField.getText());	
//			if(number < 0)
//				return false;
//		} catch(NumberFormatException e) {
//			return false;
//		}
//		return true;
//	}
	
	private boolean isCorrectDoubleNumber(JTextField textField) {
		try {
			Double number = Double.parseDouble(textField.getText());	
			if(number < 0)
				return false;
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	private void checkEmployeeIDError() {
		try {
			Integer number = Integer.parseInt(txtFieldEmployeeID.getText());	
			PersonController personController = new PersonController();
			if(number < 0) {
				lblEmployeeError.setText("Must be a positive number!");
			} else {
				order.setEmployee((Employee) personController.getByID(number));
				if(order.getEmployee() == null)
					throw new NullPointerException();
				lblEmployeeError.setVisible(false);
			}
		} catch(NumberFormatException ex) {
			lblEmployeeError.setVisible(true);
			lblEmployeeError.setText("Must be a positive number!");
		} catch (SQLException | ClassCastException | NullPointerException exx) {
			lblEmployeeError.setVisible(true);
			lblEmployeeError.setText("No such person!");
		}
	}

	private void checkCustomerIDError() {
		try {
			Integer number = Integer.parseInt(txtFieldPersonID.getText());	
			PersonController personController = new PersonController();
			if(number < 0) {
				lblPersonError.setText("Must be a positive number!");
			} else {
				order.setCustomer(personController.getByID(number));
				if(order.getCustomer() == null)
					throw new NullPointerException();
				lblPersonError.setVisible(false);
			}
		} catch(NumberFormatException ex) {
			lblPersonError.setVisible(true);
			lblPersonError.setText("Must be a positive number!");
		} catch (SQLException | NullPointerException exx) {
			lblPersonError.setVisible(true);
			lblPersonError.setText("No such person!");
		}
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
			comboBoxOffice.setEnabled(true);
			if(!isNew) {
				textFieldOfficeAddress.setEditable(true);
				textFieldOfficeCity.setEditable(true);
			}
		} else {
			textFieldAddress.setEditable(false);
			textFieldCity.setEditable(false);
			comboBoxDeliveryStatus.setEnabled(false);
			textFieldDeliveryDate.setEditable(false);
			textFieldOrderPrice.setEditable(false);
			textFieldDeposit.setEditable(false);
			textFieldCustomerNote.setEditable(false);
			comboBoxOffice.setEnabled(false);
			if(!isNew) {
				textFieldOfficeAddress.setEditable(false);
				textFieldOfficeCity.setEditable(false);
			}
		}
		
	}
}
