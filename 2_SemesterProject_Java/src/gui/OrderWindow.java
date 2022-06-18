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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class OrderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private JLabel lblMaximizeRestore;
	private boolean isMaximizePressed;
	private JTextField textField;
	private JTextArea textAreaUpdates;

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
	public OrderWindow() {
//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 180, 1280, 720);

//CONTENT PANE
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
//TITLE BAR		
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
//					setExtendedState(JFrame.NORMAL);
//					setState(JFrame.NORMAL);
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
		
//TITLE BAR		
		JLabel lblTitle = new JLabel("ORDER NO. #");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 515, 100);
		contentPane.add(lblTitle);
		
		JLabel lblEditCheck = new JLabel("");
		lblEditCheck.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/editButton.png")));
		lblEditCheck.setBounds(998, 85, 50, 50);
		contentPane.add(lblEditCheck);
		
		JLabel lblDeleteStorno = new JLabel("");
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
		
		JLabel lblMoveToPerson = new JLabel("");
		lblMoveToPerson.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto.png")));
		lblMoveToPerson.setBounds(275, 189, 25, 25);
		contentPane.add(lblMoveToPerson);
		
		JLabel lblProducts = new JLabel("PRODUCTS");
		lblProducts.setForeground(new Color(192, 176, 131));
		lblProducts.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblProducts.setBounds(487, 172, 210, 53);
		contentPane.add(lblProducts);
		
		JLabel lblMoveToProducts = new JLabel("");
		lblMoveToProducts.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto.png")));
		lblMoveToProducts.setBounds(709, 189, 25, 25);
		contentPane.add(lblMoveToProducts);
		
		JTextField lblAddress = new JTextField();
		lblAddress.setText("ADDRESS");
		lblAddress.setBorder(null);
		lblAddress.setDisabledTextColor(Color.WHITE);
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setForeground(new Color(192, 176, 131));
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblAddress.setBounds(108, 240, 180, 53);
		lblAddress.setEditable(false);
		contentPane.add(lblAddress);
		
		JLabel lblAddressDescription = new JLabel("DELIVERY ADDRESS");
		lblAddressDescription.setForeground(new Color(255, 238, 202));
		lblAddressDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAddressDescription.setBounds(548, 261, 186, 27);
		contentPane.add(lblAddressDescription);
		
		JTextField lblCity = new JTextField();
		lblCity.setText("CITY");
		lblCity.setBorder(null);
		lblCity.setDisabledTextColor(Color.WHITE);
		lblCity.setBackground(Color.WHITE);
		lblCity.setForeground(new Color(192, 176, 131));
		lblCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblCity.setBounds(108, 308, 86, 53);
		lblCity.setEditable(false);
		contentPane.add(lblCity);
		
		JLabel lblCityDescription = new JLabel("CITY");
		lblCityDescription.setForeground(new Color(255, 238, 202));
		lblCityDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCityDescription.setBounds(690, 329, 44, 27);
		contentPane.add(lblCityDescription);
		
		JTextField lblDeliveryStatus = new JTextField();
		lblDeliveryStatus.setText("DELIVERY STATUS");
		lblDeliveryStatus.setBorder(null);
		lblDeliveryStatus.setDisabledTextColor(Color.WHITE);
		lblDeliveryStatus.setBackground(Color.WHITE);
		lblDeliveryStatus.setForeground(new Color(192, 176, 131));
		lblDeliveryStatus.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblDeliveryStatus.setBounds(108, 376, 340, 53);
		lblDeliveryStatus.setEditable(false);
		contentPane.add(lblDeliveryStatus);
		
		JLabel lblDeliveryStatusDescription = new JLabel("DELIVERY STATUS");
		lblDeliveryStatusDescription.setForeground(new Color(255, 238, 202));
		lblDeliveryStatusDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDeliveryStatusDescription.setBounds(556, 397, 168, 27);
		contentPane.add(lblDeliveryStatusDescription);
		
		JTextField lblDeliveryDate = new JTextField();
		lblDeliveryDate.setText("DELIVERY DATE");
		lblDeliveryDate.setBorder(null);
		lblDeliveryDate.setDisabledTextColor(Color.WHITE);
		lblDeliveryDate.setBackground(Color.WHITE);
		lblDeliveryDate.setForeground(new Color(192, 176, 131));
		lblDeliveryDate.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblDeliveryDate.setBounds(108, 444, 300, 53);
		lblDeliveryDate.setEditable(false);
		contentPane.add(lblDeliveryDate);
		
		JLabel lblDeliveryDateDescription = new JLabel("DELIVERY DATE");
		lblDeliveryDateDescription.setForeground(new Color(255, 238, 202));
		lblDeliveryDateDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDeliveryDateDescription.setBounds(588, 465, 146, 27);
		contentPane.add(lblDeliveryDateDescription);
		
		JTextField lblOrderPrice = new JTextField();
		lblOrderPrice.setText("ORDER PRICE");
		lblOrderPrice.setBorder(null);
		lblOrderPrice.setDisabledTextColor(Color.WHITE);
		lblOrderPrice.setBackground(Color.WHITE);
		lblOrderPrice.setForeground(new Color(192, 176, 131));
		lblOrderPrice.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOrderPrice.setBounds(108, 512, 260, 53);
		lblOrderPrice.setEditable(false);
		contentPane.add(lblOrderPrice);
		
		JLabel lblOrderPriceDescription = new JLabel("ODER PRICE");
		lblOrderPriceDescription.setForeground(new Color(255, 238, 202));
		lblOrderPriceDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOrderPriceDescription.setBounds(608, 533, 126, 27);
		contentPane.add(lblOrderPriceDescription);
		
		JTextField lblDeposit = new JTextField();
		lblDeposit.setText("DEPOSIT");
		lblDeposit.setBorder(null);
		lblDeposit.setDisabledTextColor(Color.WHITE);
		lblDeposit.setBackground(Color.WHITE);
		lblDeposit.setForeground(new Color(192, 176, 131));
		lblDeposit.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblDeposit.setBounds(108, 580, 164, 53);
		lblDeposit.setEditable(false);
		contentPane.add(lblDeposit);
		
		JLabel lblisPaid = new JLabel("");
//		lblisPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/trueIcon2.png")));
		lblisPaid.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/falseIcon2.png")));
		lblisPaid.setBounds(286, 597, 28, 28);
		contentPane.add(lblisPaid);
		
		JLabel lblDepositDescription = new JLabel("DEPOPSIT/ISPAID");
		lblDepositDescription.setForeground(new Color(255, 238, 202));
		lblDepositDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDepositDescription.setBounds(580, 601, 154, 27);
		contentPane.add(lblDepositDescription);
		
		JTextField lblCustomerNote = new JTextField();
		lblCustomerNote.setText("CUSTOMER NOTE");
		lblCustomerNote.setBorder(null);
		lblCustomerNote.setDisabledTextColor(Color.WHITE);
		lblCustomerNote.setBackground(Color.WHITE);
		lblCustomerNote.setForeground(new Color(192, 176, 131));
		lblCustomerNote.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblCustomerNote.setBounds(108, 648, 330, 53);
		lblCustomerNote.setEditable(false);
		contentPane.add(lblCustomerNote);
		
		JLabel lblCustomerNoteDescription = new JLabel("CUSTOMER NOTE");
		lblCustomerNoteDescription.setForeground(new Color(255, 238, 202));
		lblCustomerNoteDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCustomerNoteDescription.setBounds(568, 669, 166, 27);
		contentPane.add(lblCustomerNoteDescription);
		
		JLabel lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setForeground(new Color(192, 176, 131));
		lblEmployee.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblEmployee.setBounds(760, 172, 200, 53);
		contentPane.add(lblEmployee);
		
		JLabel lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto.png")));
		lblMoveToEmployee.setBounds(972, 189, 25, 25);
		contentPane.add(lblMoveToEmployee);
		
		JTextField lblOffice = new JTextField();
		lblOffice.setText("OFFICE");
		lblOffice.setBorder(null);
		lblOffice.setDisabledTextColor(Color.WHITE);
		lblOffice.setBackground(Color.WHITE);
		lblOffice.setForeground(new Color(192, 176, 131));
		lblOffice.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOffice.setBounds(760, 240, 145, 53);
		lblOffice.setEditable(false);
		contentPane.add(lblOffice);
		
		JLabel lblOfficeDescription = new JLabel("OFFICE");
		lblOfficeDescription.setForeground(new Color(255, 238, 202));
		lblOfficeDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOfficeDescription.setBounds(1199, 261, 66, 27);
		contentPane.add(lblOfficeDescription);
		
		JTextField lblOfficeAddress = new JTextField();
		lblOfficeAddress.setText("ADDRESS");
		lblOfficeAddress.setBorder(null);
		lblOfficeAddress.setDisabledTextColor(Color.WHITE);
		lblOfficeAddress.setBackground(Color.WHITE);
		lblOfficeAddress.setForeground(new Color(192, 176, 131));
		lblOfficeAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOfficeAddress.setBounds(760, 310, 180, 53);
		lblOfficeAddress.setEditable(false);
		contentPane.add(lblOfficeAddress);
		
		JLabel lblOfficeAddressDescription = new JLabel("ADDRESS");
		lblOfficeAddressDescription.setForeground(new Color(255, 238, 202));
		lblOfficeAddressDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblOfficeAddressDescription.setBounds(1175, 331, 90, 27);
		contentPane.add(lblOfficeAddressDescription);
		
		JTextField lblOfficeCity = new JTextField();
		lblOfficeCity.setText("CITY");
		lblOfficeCity.setBorder(null);
		lblOfficeCity.setDisabledTextColor(Color.WHITE);
		lblOfficeCity.setBackground(Color.WHITE);
		lblOfficeCity.setForeground(new Color(192, 176, 131));
		lblOfficeCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOfficeCity.setBounds(760, 378, 86, 53);
		lblOfficeCity.setEditable(false);
		contentPane.add(lblOfficeCity);
		
		JLabel lblOfficeCityDescription = new JLabel("CITY");
		lblOfficeCityDescription.setForeground(new Color(255, 238, 202));
		lblOfficeCityDescription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
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
		
		JLabel lblUpdatesIcon = new JLabel("");
		lblUpdatesIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textAreaUpdates.setEditable(true);
				textAreaUpdates.grabFocus();
			}
		});
		lblUpdatesIcon.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/notes.png")));
		lblUpdatesIcon.setBounds(948, 460, 25, 25);
		contentPane.add(lblUpdatesIcon);
		
		textAreaUpdates = new JTextArea();
		textAreaUpdates.setLineWrap(true);
		textAreaUpdates.setText("12.06.2022(17:30) - Product \"xxxx\" added to ordereeeeeeeeeeeeeeeeeeeeeeeeeeeee\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n\r\n12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n12.06.2022(17:30) - Product \"xxxx\" added to order\r\n12.06.2022(17:00) - Employee (name) assigned to order\r\n12.06.2022(15:35) - Delivery status changed to \"accepted\"\r\n12.06.2022(15:30) - Employee (name) assigned to order\r\n12.06.2022(15:00) - Order created\r\n\r\n");
		textAreaUpdates.setForeground(new Color(192, 176, 131));
		textAreaUpdates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaUpdates.setEditable(false);
		textAreaUpdates.setBounds(760, 512, 505, 184);
		contentPane.add(textAreaUpdates);
		
	
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
}
