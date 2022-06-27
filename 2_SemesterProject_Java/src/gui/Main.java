package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LocationCityController;
import controller.LoginController;
import controller.OrderController;
import controller.PersonController;
import controller.StoneController;
import controller.StoneTypeMaterialController;
import dataaccess.DBConnection;
import model.City;
import model.IStoneUnit;
import model.Location;
import model.StoneMaterial;
import model.StoneType;
import model.OrderInfo;
import model.Person;
import model.StoneUnit;
import model.Supplier;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Toolkit;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	public ArrayList<StoneMaterial> cachedMaterials;
	public ArrayList<StoneType> cachedStoneTypes;
	public ArrayList<Location> cachedLocations;
	public ArrayList<City> cachedCities;

	public static ArrayList<IStoneUnit> stoneUnits;
	private ArrayList<Person> suppliers;
	
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private int x, y;
	private CardLayout cardLayoutMainPane;
	private JLabel lblTime;
	private JPanel mainPane;
	private JLabel lblDatabaseStatus;
	private JLabel lblCheckOff;
	private static JLabel lblLoading;
	private JLabel lblMaximizeRestore;
	private boolean isMaximizePressed;
	private static JLabel lblLoadingIcon;
	private JLabel lblCacheInfo;
	private static DefaultTableModel defaultTableModelOrders;
	private static DefaultTableModel defaultTableModelSuppliers;
	private static JLabel lblReloadButtonOrders;
	private TableRowSorter<DefaultTableModel> tableRowSorterInventory;
	private static JLabel lblReloadButtonInventory;
	private static JLabel lblReloadButtonMaterial;
	private static JLabel lblReloadButtonCustomers;
	private static JLabel lblReloadButtonSuppliers;
	private static JLabel lblReloadButtonEmployees;
	private TableRowSorter<DefaultTableModel> tableRowSorterOrders;
	
	private static Main instance;
	
	DefaultTableModel defaultTableModelInventory;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		Main frame = new Main();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	//Singleton
	public static Main getInstance() {
		 if (instance != null)
	        return instance;
		 return null;
	}
	public Main() {
		instance = this;
		System.out.println("instance assignedd");
		// Local JComponents


		CardLayout cardLayout = new CardLayout();

//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);

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
				if (isMaximizePressed) {
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
				setLocation(xx - x - 300, yy - y);
			}
		});
		titleBarPane.setBackground(Color.WHITE);
		titleBarPane.setBounds(300, 0, 1620, 30);
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
		lblClose.setBounds(1595, 10, 10, 10);
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
		lblMaximizeRestore.setBounds(1550, 10, 10, 10);
		titleBarPane.add(lblMaximizeRestore);

		JLabel lblMinimize = new JLabel("");
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblMinimize.setIcon(new ImageIcon(Main.class.getResource("/imgs/minimalize2.png")));
		lblMinimize.setBounds(1505, 10, 10, 10);
		titleBarPane.add(lblMinimize);

//INFO 		
		JPanel infoPane = new JPanel();
		infoPane.setBackground(Color.WHITE);
		infoPane.setBounds(300, 30, 1620, 50);
		contentPane.add(infoPane);
		infoPane.setLayout(null);

		lblCheckOff = new JLabel("");
		lblCheckOff.setIcon(new ImageIcon(Main.class.getResource("/imgs/checkOFF.png")));
		lblCheckOff.setBounds(1418, 15, 17, 17);
		infoPane.add(lblCheckOff);

		lblDatabaseStatus = new JLabel("Connecting...");
		lblDatabaseStatus.setForeground(Color.BLACK);
		lblDatabaseStatus.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		lblDatabaseStatus.setBounds(1442, 0, 166, 40);
		infoPane.add(lblDatabaseStatus);

		lblLoading = new JLabel("Loading...");
		lblLoading.setBounds(35, -2, 153, 40);
//		lblLoading.setForeground(new Color(172, 172, 172));
		lblLoading.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
		infoPane.add(lblLoading);
		lblLoading.setVisible(false);

		lblLoadingIcon = new JLabel("");
		lblLoadingIcon.setIcon(new ImageIcon(Main.class.getResource("/imgs/loading3.gif")));
		lblLoadingIcon.setBounds(10, 12, 20, 20);
		infoPane.add(lblLoadingIcon);
		
				lblCacheInfo = new JLabel("Caching Data...");
				lblCacheInfo.setBounds(172, 0, 1236, 40);
				infoPane.add(lblCacheInfo);
				lblCacheInfo.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCacheInfo.setForeground(new Color(172, 172, 172));
				lblCacheInfo.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		lblLoadingIcon.setVisible(false);

//CARD PANE		
		JPanel cardPane = new JPanel();
		cardPane.setBounds(0, 0, 1920, 1080);
		contentPane.add(cardPane);
		cardPane.setLayout(cardLayout);

//WELCOME		
		JPanel welcomePane = new JPanel();
		welcomePane.setBounds(0, 0, 1920, 1080);
		welcomePane.setBackground(Color.WHITE);
		cardPane.add(welcomePane, "name_66960479156300");
		welcomePane.setLayout(null);

		JLabel lblWallLogo = new JLabel("");
		lblWallLogo.setIcon(new ImageIcon(Main.class.getResource("/imgs/wallLogo.png")));
		lblWallLogo.setBounds(136, 219, 840, 642);
		welcomePane.add(lblWallLogo);

		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 120));
		lblWelcome.setBounds(1125, 275, 591, 159);
		welcomePane.add(lblWelcome);

		JLabel lblLogInPan_1 = new JLabel("");
		lblLogInPan_1.setIcon(new ImageIcon(Main.class.getResource("/imgs/LogInPan.png")));
		lblLogInPan_1.setBounds(1048, 478, 181, 5);
		welcomePane.add(lblLogInPan_1);

		JLabel lblLogInPan_2 = new JLabel("");
		lblLogInPan_2.setIcon(new ImageIcon(Main.class.getResource("/imgs/LogInPan.png")));
		lblLogInPan_2.setBounds(1603, 478, 181, 5);
		welcomePane.add(lblLogInPan_2);

		JLabel lblPleaseLogIn = new JLabel("Please log in");
		lblPleaseLogIn.setForeground(new Color(199, 176, 131));
		lblPleaseLogIn.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblPleaseLogIn.setBounds(1238, 434, 371, 80);
		welcomePane.add(lblPleaseLogIn);

		JTextField textFieldUsername = new JTextField();
		String defaultUsernameTxt = "USERNAME";
		textFieldUsername.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textFieldUsername.setText("USERNAME");
		textFieldUsername.setBounds(1204, 586, 425, 42);
		welcomePane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textFieldUsername.setFocusable(true);
				passwordField.setFocusable(true);
			}
		});
		textFieldUsername.setFocusable(false);
		textFieldUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldUsername.getText().equals(defaultUsernameTxt))
					textFieldUsername.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldUsername.getText().equals(""))
					textFieldUsername.setText(defaultUsernameTxt);
			}
		});

		passwordField = new JPasswordField();
		String defaultPassTxt = "*************";
		passwordField.setFocusable(false);
		passwordField.setText(defaultPassTxt);
		passwordField.setBounds(1204, 658, 425, 42);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		welcomePane.add(passwordField);
		passwordField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getText().equals(defaultPassTxt))
					passwordField.setText("");
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (passwordField.getText().equals(""))
					passwordField.setText(defaultPassTxt);
				// System.out.println(passwordField.getPassword());
			}
		});

		JLabel lblLoginError = new JLabel("ERROR! Wrong Username or Password");
		lblLoginError.setVisible(false);
		lblLoginError.setSize(382, 42);
		lblLoginError.setLocation(1204, 700);
		lblLoginError.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLoginError.setForeground(new Color(230, 57, 70));
		welcomePane.add(lblLoginError);

		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				LoginController loginC = new LoginController(); 
				String inputedUsername = textFieldUsername.getText();
				String inputedPassword = passwordField.getText();
				
				cardLayout.show(cardPane, "name_66960487401900");
				contentPane.updateUI();
				updateOrdersTable();
				
//				try {
//					if(inputedPassword.equalsIgnoreCase(loginC.authentication(inputedUsername))) {
//				contentPane.updateUI();
//				cardLayout.show(cardPane, "name_66960487401900");
//				updateOrdersTable();
//				} else {
//						lblLoginError.setVisible(true);
//					}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
			}
	});
		btnSignIn.setBorder(null);
		btnSignIn.setFocusable(false);
		btnSignIn.setBackground(new Color(199, 176, 131));
		btnSignIn.setForeground(new Color(255, 238, 202));
		btnSignIn.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnSignIn.setBounds(1338, 764, 155, 42);
		welcomePane.add(btnSignIn);

//SLIDE SPLIT PANE
		JSplitPane slideSplitPane = new JSplitPane();
		slideSplitPane.setBorder(null);
		slideSplitPane.setBounds(-10000, -10000, 1920, 1080);
		slideSplitPane.setBackground(Color.WHITE);
		slideSplitPane.setDividerSize(0);
		// 300/1920
		slideSplitPane.setDividerLocation(0.15625);
		cardPane.add(slideSplitPane, "name_66960487401900");

//SIDE PANE - MENU BAR		
		JPanel sidePane = new JPanel();
		sidePane.setBackground(Color.WHITE);
		slideSplitPane.setLeftComponent(sidePane);
		sidePane.setLayout(null);

		JLabel lblHello = new JLabel("Hello, <User>");
		lblHello.setForeground(Color.WHITE);
		lblHello.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		lblHello.setBounds(55, 14, 216, 47);
		sidePane.add(lblHello);

		JLabel lblLogout = new JLabel("");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// titleBarPane.setVisible(false);
				contentPane.updateUI();
				cardLayout.show(cardPane, "name_66960479156300");
				// titleBarPane.setVisible(true);
			}
		});
		lblLogout.setIcon(new ImageIcon(Main.class.getResource("/imgs/logout.png")));
		lblLogout.setBounds(24, 30, 19, 19);
		sidePane.add(lblLogout);

		JLabel lblDate = new JLabel();
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
		lblDate.setForeground(new Color(255, 238, 202));
		lblDate.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		lblDate.setBounds(0, 118, 300, 40);
		sidePane.add(lblDate);

		lblTime = new JLabel("10:30:47");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new Color(255, 238, 202));
		lblTime.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		lblTime.setBounds(0, 148, 300, 40);
		sidePane.add(lblTime);

		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setForeground(new Color(255, 238, 202));
		lblOrders.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblOrders.setBounds(74, 295, 155, 67);
		sidePane.add(lblOrders);
		lblOrders.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblOrders.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblOrders.setForeground(new Color(255, 238, 202));
			}

			public void mouseClicked(MouseEvent e) {
				cardLayoutMainPane.show(mainPane, "name_27604993424200");
				contentPane.updateUI();
			}
		});

		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setForeground(new Color(255, 238, 202));
		lblInventory.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblInventory.setBounds(41, 422, 218, 67);
		sidePane.add(lblInventory);
		lblInventory.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblInventory.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblInventory.setForeground(new Color(255, 238, 202));
			}

			public void mouseClicked(MouseEvent e) {

				cardLayoutMainPane.show(mainPane, "name_27605005897300");
				contentPane.updateUI();

				
			}
		});

		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setForeground(new Color(255, 238, 202));
		lblMaterial.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblMaterial.setBounds(56, 549, 188, 67);
		sidePane.add(lblMaterial);
		lblMaterial.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblMaterial.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblMaterial.setForeground(new Color(255, 238, 202));
			}

			public void mouseClicked(MouseEvent e) {
				cardLayoutMainPane.show(mainPane, "name_28649739236300");
				contentPane.updateUI();
			}
		});

		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setForeground(new Color(255, 238, 202));
		lblCustomers.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblCustomers.setBounds(30, 676, 242, 67);
		sidePane.add(lblCustomers);
		lblCustomers.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblCustomers.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblCustomers.setForeground(new Color(255, 238, 202));
			}

			public void mouseClicked(MouseEvent e) {
				cardLayoutMainPane.show(mainPane, "name_28663621444400");
				contentPane.updateUI();
			}
		});

		JLabel lblSuppliers = new JLabel("Suppliers");
		lblSuppliers.setForeground(new Color(255, 238, 202));
		lblSuppliers.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblSuppliers.setBounds(45, 803, 210, 67);
		sidePane.add(lblSuppliers);
		lblSuppliers.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblSuppliers.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblSuppliers.setForeground(new Color(255, 238, 202));
			}

			public void mouseClicked(MouseEvent e) {
				cardLayoutMainPane.show(mainPane, "name_28693995155900");
				contentPane.updateUI();
			}
		});

		JLabel lblEmployees = new JLabel("Employees");
		lblEmployees.setSize(242, 67);
		lblEmployees.setLocation(29, 930);
		lblEmployees.setForeground(new Color(255, 238, 202));
		lblEmployees.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblEmployees.setBounds(29, 930, 244, 67);
		sidePane.add(lblEmployees);
		lblEmployees.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblEmployees.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblEmployees.setForeground(new Color(255, 238, 202));
			}

			public void mouseClicked(MouseEvent e) {
				cardLayoutMainPane.show(mainPane, "name_28705081431500");
				contentPane.updateUI();
			}
		});

		JLabel lblSideBar = new JLabel("");
		lblSideBar.setIcon(new ImageIcon(Main.class.getResource("/imgs/sideBar.png")));
		lblSideBar.setBounds(0, 0, 300, 1080);
		sidePane.add(lblSideBar);
		lblSideBar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblSideBar.setForeground(new Color(144, 124, 81));
			}

			public void mouseExited(MouseEvent e) {
				lblSideBar.setForeground(new Color(255, 238, 202));
			}
		});

//MAIN PANE		
		mainPane = new JPanel();
		mainPane.setBackground(Color.WHITE);
		slideSplitPane.setRightComponent(mainPane);
		cardLayoutMainPane = new CardLayout();
		mainPane.setLayout(cardLayoutMainPane);

//ORDERS
		JPanel orders = new JPanel();
		orders.setBackground(Color.WHITE);
		mainPane.add(orders, "name_27604993424200");
		orders.setLayout(null);

		JTextField textFieldSearchOrders = new JTextField();
		textFieldSearchOrders.setForeground(new Color(199, 176, 131));
		String ordersSearchDefault = "ID, Customer, Date...";
		textFieldSearchOrders.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		textFieldSearchOrders.setText(ordersSearchDefault);
		textFieldSearchOrders.setBorder(null);
		textFieldSearchOrders.setBackground(new Color(255, 238, 202));
		textFieldSearchOrders.setBounds(1222, 130, 348, 50);
		orders.add(textFieldSearchOrders);
		textFieldSearchOrders.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = textFieldSearchOrders.getText();

                if (text.trim().length() == 0) {
                	tableRowSorterOrders.setRowFilter(null);
                } else {
                	tableRowSorterOrders.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
			}
		});
		textFieldSearchOrders.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearchOrders.setForeground(Color.BLACK);
				if (textFieldSearchOrders.getText().equals(ordersSearchDefault))
					textFieldSearchOrders.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldSearchOrders.getText().equals("")) {
					textFieldSearchOrders.setText(ordersSearchDefault);
					textFieldSearchOrders.setForeground(new Color(199, 176, 131));
				}
			}
		});

		JLabel lblOrdersTitle = new JLabel("ORDERS");
		lblOrdersTitle.setToolTipText("");
		lblOrdersTitle.setFont(new Font("Segoe UI", Font.BOLD, 100));
		lblOrdersTitle.setForeground(new Color(144, 124, 81));
		lblOrdersTitle.setBounds(100, 83, 390, 133);
		orders.add(lblOrdersTitle);

		JLabel lblAddButtonOrders = new JLabel("");
		lblAddButtonOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OrderWindow orderWindow = new OrderWindow(true, -1);
				orderWindow.setVisible(true);
			}
		});
		lblAddButtonOrders.setIcon(new ImageIcon(Main.class.getResource("/imgs/addButton.png")));
		lblAddButtonOrders.setBounds(1035, 130, 50, 50);
		orders.add(lblAddButtonOrders);

		lblReloadButtonOrders = new JLabel("");
		lblReloadButtonOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblReloadButtonOrders.isEnabled()) {
					updateOrdersTable();
				}
			}
		});
		lblReloadButtonOrders.setIcon(new ImageIcon(Main.class.getResource("/imgs/reload.png")));
		lblReloadButtonOrders.setBounds(1115, 130, 50, 50);
		orders.add(lblReloadButtonOrders);

		JLabel lblMainBarOrders = new JLabel("");
		lblMainBarOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMainBarOrders.grabFocus();
			}
		});
		lblMainBarOrders.setIcon(new ImageIcon(Main.class.getResource("/imgs/mainBar.png")));
		lblMainBarOrders.setBounds(-300, 80, 1920, 150);
		orders.add(lblMainBarOrders);

		JScrollPane scrollPaneOrders = new JScrollPane();
		scrollPaneOrders.setBackground(Color.WHITE);
		scrollPaneOrders.setBounds(20, 250, 1580, 810);
		orders.add(scrollPaneOrders);

		JTable tableOrders = new JTable();
		tableOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableRowSorterOrders.convertRowIndexToModel(tableOrders.getSelectedRow());
				int id = (int) defaultTableModelOrders.getValueAt(index, 0);
				OrderWindow orderWindow = new OrderWindow(false, id);
				orderWindow.setVisible(true);
			}
		});
		tableOrders.setDefaultEditor(Object.class, null); // non-editable
		tableOrders.setGridColor(new Color(172, 172, 172));
		tableOrders.setBackground(Color.WHITE);

		defaultTableModelOrders = new DefaultTableModel(new Object[][] { null, null, null },
				new String[] { "OrderID", "CustomerID", "DeliveryDate", "Address", "CityID", "OrderPrice", "Deposit",
						"isPaid", "DeliveryStatus", "EmployeeID", "LocationID", "PaymentDate", "VATration",
						"FinalPrice" }) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, Date.class, String.class, Integer.class,
					Double.class, Double.class, Boolean.class, String.class, Integer.class, Integer.class, Date.class,
					Double.class, Double.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableOrders.setModel(defaultTableModelOrders);
		tableRowSorterOrders = new TableRowSorter<DefaultTableModel>(
				defaultTableModelOrders);
		tableOrders.setRowSorter(tableRowSorterOrders);
		scrollPaneOrders.setViewportView(tableOrders);

//INVENTORY		
		JPanel inventory = new JPanel();
		inventory.setBackground(Color.WHITE);
		mainPane.add(inventory, "name_27605005897300");
		inventory.setLayout(null);

		JTextField textFieldSearchInventory = new JTextField();
		String inventorySearchDefault = "ID, Customer, Date...";
		textFieldSearchInventory.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		textFieldSearchInventory.setForeground(new Color(199, 176, 131));
		textFieldSearchInventory.setText(inventorySearchDefault);
		textFieldSearchInventory.setBorder(null);
		textFieldSearchInventory.setBackground(new Color(255, 238, 202));
		textFieldSearchInventory.setBounds(1222, 130, 348, 50);
		inventory.add(textFieldSearchInventory);
		textFieldSearchInventory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
                String text = textFieldSearchInventory.getText();

                if (text.trim().length() == 0) {
                	tableRowSorterInventory.setRowFilter(null);
                } else {
                	tableRowSorterInventory.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
			}
		});
		textFieldSearchInventory.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearchInventory.setForeground(Color.BLACK);
				if (textFieldSearchInventory.getText().equals(inventorySearchDefault))
					textFieldSearchInventory.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldSearchInventory.getText().equals("")) {
					textFieldSearchInventory.setText(inventorySearchDefault);
					textFieldSearchInventory.setForeground(new Color(199, 176, 131));
				}
			}
		});

		JLabel lblInventoryTitle = new JLabel("INVENTORY");
		lblInventoryTitle.setToolTipText("");
		lblInventoryTitle.setFont(new Font("Segoe UI", Font.BOLD, 100));
		lblInventoryTitle.setForeground(new Color(144, 124, 81));
		lblInventoryTitle.setBounds(100, 83, 575, 133);
		inventory.add(lblInventoryTitle);

		JLabel lblAddButtonInventory = new JLabel("");
		lblAddButtonInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			       String[] types= {"Cuttable Stone","Stone Product","Remains"};

			        JComboBox<String> combo = new JComboBox<String>(types);



			        String[] options = { "Create", "Cancel"};

			        int selection = JOptionPane.showOptionDialog(null, combo, "Please Select",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
			                options[0]);
			        
			        if (selection == 0) {
			        	 switch(combo.getSelectedIndex()) {
			        	 case 0: // cuttable
			        		 new StoneUnitCuttableWindowRecreate(-1).setVisible(true);
			        		 break;
			        	 case 1: // product
			        		 new StoneUnitProductWindowRecreate(-1).setVisible(true);
			        		 break;
			        	 case 2://remains
			        		 new StoneUnitRemainsWindow(-1).setVisible(true);
			        		 break;
			        	 }
			         }
			}
		});
		lblAddButtonInventory.setIcon(new ImageIcon(Main.class.getResource("/imgs/addButton.png")));
		lblAddButtonInventory.setBounds(1035, 130, 50, 50);
		inventory.add(lblAddButtonInventory);

		lblReloadButtonInventory = new JLabel("");
		lblReloadButtonInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateInventory();
				
				
			}
		});
		lblReloadButtonInventory.setIcon(new ImageIcon(Main.class.getResource("/imgs/reload.png")));
		lblReloadButtonInventory.setBounds(1115, 130, 50, 50);
		inventory.add(lblReloadButtonInventory);

		JLabel lblMainBarInventory = new JLabel("");
		lblMainBarInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMainBarInventory.grabFocus();
			}
		});
		lblMainBarInventory.setIcon(new ImageIcon(Main.class.getResource("/imgs/mainBar.png")));
		lblMainBarInventory.setBounds(-300, 80, 1920, 150);
		inventory.add(lblMainBarInventory);

		JScrollPane scrollPaneInventory = new JScrollPane();
		scrollPaneInventory.setBackground(Color.WHITE);
		scrollPaneInventory.setBounds(20, 250, 1580, 810);
		inventory.add(scrollPaneInventory);

		JTable tableInventory = new JTable();
		tableInventory.setDefaultEditor(Object.class, null); // non-editable
		tableInventory.setGridColor(new Color(172, 172, 172));
		tableInventory.setBackground(Color.WHITE);

		defaultTableModelInventory = new DefaultTableModel(new Object[][] { null, null, null },
				new String[] { "ID", "Stone Type", "Origin", "Width", "Weight", "Description", "Created Date",
						"Location", "Status", "Material", "Type", "No Of Products" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Double.class, Double.class,
					String.class, String.class, String.class, String.class, String.class, String.class, Integer.class};

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		tableInventory.setModel(defaultTableModelInventory);
		tableInventory.getColumnModel().getColumn(0).setMaxWidth(40);
		tableInventory.getColumnModel().getColumn(3).setMaxWidth(50);
		tableInventory.getColumnModel().getColumn(4).setMaxWidth(50);
		tableRowSorterInventory = new TableRowSorter<DefaultTableModel>(
				defaultTableModelInventory);
		tableInventory.setRowSorter(tableRowSorterInventory);
		tableInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					// handle double click event.
					int rowIndex = tableInventory.getSelectedRow();
					int id = (int) defaultTableModelInventory.getValueAt(rowIndex, 0);
					String stoneType = (String) defaultTableModelInventory.getValueAt(rowIndex, 1);

					if (stoneType.equals("StoneCuttable")) {
						new StoneUnitCuttableWindowRecreate(id).setVisible(true);
					}
					if (stoneType.equals("StoneProduct")) {
						new StoneUnitProductWindowRecreate(id).setVisible(true);
					}
					if (stoneType.equals("Remains")) {
						new StoneUnitRemainsWindow(id).setVisible(true);
					}
				}
			}
		});
		scrollPaneInventory.setViewportView(tableInventory);

//MATERIAL
		JPanel material = new JPanel();
		mainPane.add(material, "name_28649739236300");
		material.setBackground(Color.WHITE);
		material.setLayout(null);

		JTextField textFieldSearchMaterial = new JTextField();
		String materialSearchDefault = "ID, Customer, Date...";
		textFieldSearchMaterial.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		textFieldSearchMaterial.setForeground(new Color(199, 176, 131));
		textFieldSearchMaterial.setText(materialSearchDefault);
		textFieldSearchMaterial.setBorder(null);
		textFieldSearchMaterial.setBackground(new Color(255, 238, 202));
		textFieldSearchMaterial.setBounds(1222, 130, 348, 50);
		material.add(textFieldSearchMaterial);
		textFieldSearchMaterial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				productsList = getProductsList(productsSearchField.getText());
//				updateProductsTable(productsList);
			}
		});
		textFieldSearchMaterial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearchMaterial.setForeground(Color.BLACK);
				if (textFieldSearchMaterial.getText().equals(materialSearchDefault))
					textFieldSearchMaterial.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldSearchMaterial.getText().equals("")) {
					textFieldSearchMaterial.setText(materialSearchDefault);
					textFieldSearchMaterial.setForeground(new Color(199, 176, 131));
				}
			}
		});

		JLabel lblMaterialTitle = new JLabel("MATERIAL");
		lblMaterialTitle.setToolTipText("");
		lblMaterialTitle.setFont(new Font("Segoe UI", Font.BOLD, 100));
		lblMaterialTitle.setForeground(new Color(144, 124, 81));
		lblMaterialTitle.setBounds(100, 83, 500, 133);
		material.add(lblMaterialTitle);

		JLabel lblAddButtonMaterial = new JLabel("");
		lblAddButtonMaterial.setIcon(new ImageIcon(Main.class.getResource("/imgs/addButton.png")));
		lblAddButtonMaterial.setBounds(1035, 130, 50, 50);
		material.add(lblAddButtonMaterial);

		lblReloadButtonMaterial = new JLabel("");
		lblReloadButtonMaterial.setIcon(new ImageIcon(Main.class.getResource("/imgs/reload.png")));
		lblReloadButtonMaterial.setBounds(1115, 130, 50, 50);
		material.add(lblReloadButtonMaterial);

		JLabel lblMainBarMaterial = new JLabel("");
		lblMainBarMaterial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMainBarMaterial.grabFocus();
			}
		});
		lblMainBarMaterial.setIcon(new ImageIcon(Main.class.getResource("/imgs/mainBar.png")));
		lblMainBarMaterial.setBounds(-300, 80, 1920, 150);
		material.add(lblMainBarMaterial);

		JScrollPane scrollPaneMaterial = new JScrollPane();
		scrollPaneMaterial.setBackground(Color.WHITE);
		scrollPaneMaterial.setBounds(20, 250, 1580, 810);
		material.add(scrollPaneMaterial);

		JTable tableMaterial = new JTable();
		tableMaterial.setDefaultEditor(Object.class, null); // non-editable
		tableMaterial.setGridColor(new Color(172, 172, 172));
		tableMaterial.setBackground(Color.WHITE);
		DefaultTableModel defaultTableModelMaterial = new DefaultTableModel(new Object[][] { null, null, null },
				new String[] { "ID", "Name", "Category", "Price", "Sold", "Discount", "Contractor", "Cost Price",
						"Stock", "Location", "Condition", "UnavailableTil", "Type", "Available" }) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Double.class, Boolean.class,
					Double.class, Object.class, Double.class, Integer.class, Object.class, String.class, Object.class,
					Object.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableMaterial.setModel(defaultTableModelMaterial);
		TableRowSorter<DefaultTableModel> tableRowSorterMaterial = new TableRowSorter<DefaultTableModel>(
				defaultTableModelMaterial);
		tableMaterial.setRowSorter(tableRowSorterMaterial);
		scrollPaneMaterial.setViewportView(tableMaterial);

//CUSTOMERS
		JPanel customers = new JPanel();
		mainPane.add(customers, "name_28663621444400");
		customers.setBackground(Color.WHITE);
		customers.setLayout(null);

		JTextField textFieldSearchCustomers = new JTextField();
		String customersSearchDefault = "ID, Customer, Date...";
		textFieldSearchCustomers.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		textFieldSearchCustomers.setForeground(new Color(199, 176, 131));
		textFieldSearchCustomers.setText(customersSearchDefault);
		textFieldSearchCustomers.setBorder(null);
		textFieldSearchCustomers.setBackground(new Color(255, 238, 202));
		textFieldSearchCustomers.setBounds(1222, 130, 348, 50);
		customers.add(textFieldSearchCustomers);
		textFieldSearchCustomers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				productsList = getProductsList(productsSearchField.getText());
//				updateProductsTable(productsList);
			}
		});
		textFieldSearchCustomers.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearchCustomers.setForeground(Color.BLACK);
				if (textFieldSearchCustomers.getText().equals(customersSearchDefault))
					textFieldSearchCustomers.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldSearchCustomers.getText().equals("")) {
					textFieldSearchCustomers.setText(customersSearchDefault);
					textFieldSearchCustomers.setForeground(new Color(199, 176, 131));
				}
			}
		});

		JLabel lblCustomersTitle = new JLabel("CUSTOMERS");
		lblCustomersTitle.setToolTipText("");
		lblCustomersTitle.setFont(new Font("Segoe UI", Font.BOLD, 100));
		lblCustomersTitle.setForeground(new Color(144, 124, 81));
		lblCustomersTitle.setBounds(100, 83, 600, 133);
		customers.add(lblCustomersTitle);

		JLabel lblAddButtonCustomers = new JLabel("");
		lblAddButtonCustomers.setIcon(new ImageIcon(Main.class.getResource("/imgs/addButton.png")));
		lblAddButtonCustomers.setBounds(1035, 130, 50, 50);
		customers.add(lblAddButtonCustomers);

		lblReloadButtonCustomers = new JLabel("");
		lblReloadButtonCustomers.setIcon(new ImageIcon(Main.class.getResource("/imgs/reload.png")));
		lblReloadButtonCustomers.setBounds(1115, 130, 50, 50);
		customers.add(lblReloadButtonCustomers);

		JLabel lblMainBarCustomers = new JLabel("");
		lblMainBarCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMainBarCustomers.grabFocus();
			}
		});
		lblMainBarCustomers.setIcon(new ImageIcon(Main.class.getResource("/imgs/mainBar.png")));
		lblMainBarCustomers.setBounds(-300, 80, 1920, 150);
		customers.add(lblMainBarCustomers);

		JScrollPane scrollPaneCustomers = new JScrollPane();
		scrollPaneCustomers.setBackground(Color.WHITE);
		scrollPaneCustomers.setBounds(20, 250, 1580, 810);
		customers.add(scrollPaneCustomers);

		JTable tableCustomers = new JTable();
		tableCustomers.setDefaultEditor(Object.class, null); // non-editable
		tableCustomers.setGridColor(new Color(172, 172, 172));
		tableCustomers.setBackground(Color.WHITE);
		DefaultTableModel defaultTableModelCustomers = new DefaultTableModel(new Object[][] { null, null, null },
				new String[] { "ID", "Name", "Category", "Price", "Sold", "Discount", "Contractor", "Cost Price",
						"Stock", "Location", "Condition", "UnavailableTil", "Type", "Available" }) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Double.class, Boolean.class,
					Double.class, Object.class, Double.class, Integer.class, Object.class, String.class, Object.class,
					Object.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableCustomers.setModel(defaultTableModelCustomers);
		TableRowSorter<DefaultTableModel> tableRowSorterCustomers = new TableRowSorter<DefaultTableModel>(
				defaultTableModelCustomers);
		tableCustomers.setRowSorter(tableRowSorterCustomers);
		scrollPaneCustomers.setViewportView(tableCustomers);

//SUPPLIERS
		JPanel suppliers = new JPanel();
		mainPane.add(suppliers, "name_28693995155900");
		suppliers.setBackground(Color.WHITE);
		suppliers.setLayout(null);

		JTextField textFieldSearchSuppliers = new JTextField();
		String suppliersSearchDefault = "ID, Customer, Date...";
		textFieldSearchSuppliers.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		textFieldSearchSuppliers.setForeground(new Color(199, 176, 131));
		textFieldSearchSuppliers.setText(suppliersSearchDefault);
		textFieldSearchSuppliers.setBorder(null);
		textFieldSearchSuppliers.setBackground(new Color(255, 238, 202));
		textFieldSearchSuppliers.setBounds(1222, 130, 348, 50);
		suppliers.add(textFieldSearchSuppliers);
		textFieldSearchSuppliers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				productsList = getProductsList(productsSearchField.getText());
//				updateProductsTable(productsList);
			}
		});
		textFieldSearchSuppliers.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearchSuppliers.setForeground(Color.BLACK);
				if (textFieldSearchSuppliers.getText().equals(suppliersSearchDefault))
					textFieldSearchSuppliers.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldSearchSuppliers.getText().equals("")) {
					textFieldSearchSuppliers.setText(suppliersSearchDefault);
					textFieldSearchSuppliers.setForeground(new Color(199, 176, 131));
				}
			}
		});

		JLabel lblSuppliersTitle = new JLabel("SUPPLIERS");
		lblSuppliersTitle.setToolTipText("");
		lblSuppliersTitle.setFont(new Font("Segoe UI", Font.BOLD, 100));
		lblSuppliersTitle.setForeground(new Color(144, 124, 81));
		lblSuppliersTitle.setBounds(100, 83, 510, 133);
		suppliers.add(lblSuppliersTitle);

		JLabel lblAddButtonSuppliers = new JLabel("");
		lblAddButtonSuppliers.setIcon(new ImageIcon(Main.class.getResource("/imgs/addButton.png")));
		lblAddButtonSuppliers.setBounds(1035, 130, 50, 50);
		suppliers.add(lblAddButtonSuppliers);

		lblReloadButtonSuppliers = new JLabel("");
		lblReloadButtonSuppliers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateSupplierList();
			}
		});
		lblReloadButtonSuppliers.setIcon(new ImageIcon(Main.class.getResource("/imgs/reload.png")));
		lblReloadButtonSuppliers.setBounds(1115, 130, 50, 50);
		suppliers.add(lblReloadButtonSuppliers);

		JLabel lblMainBarSuppliers = new JLabel("");
		lblMainBarSuppliers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMainBarSuppliers.grabFocus();
			}
		});
		lblMainBarSuppliers.setIcon(new ImageIcon(Main.class.getResource("/imgs/mainBar.png")));
		lblMainBarSuppliers.setBounds(-300, 80, 1920, 150);
		suppliers.add(lblMainBarSuppliers);

		JScrollPane scrollPaneSuppliers = new JScrollPane();
		scrollPaneSuppliers.setBackground(Color.WHITE);
		scrollPaneSuppliers.setBounds(20, 250, 1580, 810);
		suppliers.add(scrollPaneSuppliers);

		JTable tableSuppliers = new JTable();
		tableSuppliers.setDefaultEditor(Object.class, null); // non-editable
		tableSuppliers.setGridColor(new Color(172, 172, 172));
		tableSuppliers.setBackground(Color.WHITE);
		DefaultTableModel defaultTableModelSuppliers = new DefaultTableModel(new Object[][] { null, null, null },
				new String[] { "ID", "Name", "Address", "City", "PhoneNo", "Email", "Date of birth", "Age",
						"Description", "Note"}) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Object.class, String.class,
					String.class, Date.class, Integer.class, String.class, String.class};

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableSuppliers.setModel(defaultTableModelSuppliers);
		TableRowSorter<DefaultTableModel> tableRowSorterSuppliers = new TableRowSorter<DefaultTableModel>(
				defaultTableModelSuppliers);
		tableSuppliers.setRowSorter(tableRowSorterSuppliers);
		scrollPaneSuppliers.setViewportView(tableSuppliers);

//EMPLOYEES
		JPanel employees = new JPanel();
		mainPane.add(employees, "name_28705081431500");
		employees.setBackground(Color.WHITE);
		employees.setLayout(null);

		JTextField textFieldSearchEmployees = new JTextField();
		String employeesSearchDefault = "ID, Customer, Date...";
		textFieldSearchEmployees.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		textFieldSearchEmployees.setForeground(new Color(199, 176, 131));
		textFieldSearchEmployees.setText(employeesSearchDefault);
		textFieldSearchEmployees.setBorder(null);
		textFieldSearchEmployees.setBackground(new Color(255, 238, 202));
		textFieldSearchEmployees.setBounds(1222, 130, 348, 50);
		employees.add(textFieldSearchEmployees);
		textFieldSearchEmployees.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				productsList = getProductsList(productsSearchField.getText());
//				updateProductsTable(productsList);
			}
		});
		textFieldSearchEmployees.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearchEmployees.setForeground(Color.BLACK);
				if (textFieldSearchEmployees.getText().equals(employeesSearchDefault))
					textFieldSearchEmployees.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldSearchEmployees.getText().equals("")) {
					textFieldSearchEmployees.setText(employeesSearchDefault);
					textFieldSearchEmployees.setForeground(new Color(199, 176, 131));
				}
			}
		});

		JLabel lblEmployeesTitle = new JLabel("EMPLOYEES");
		lblEmployeesTitle.setToolTipText("");
		lblEmployeesTitle.setFont(new Font("Segoe UI", Font.BOLD, 100));
		lblEmployeesTitle.setForeground(new Color(144, 124, 81));
		lblEmployeesTitle.setBounds(100, 83, 560, 133);
		employees.add(lblEmployeesTitle);

		JLabel lblAddButtonEmployees = new JLabel("");
		lblAddButtonEmployees.setIcon(new ImageIcon(Main.class.getResource("/imgs/addButton.png")));
		lblAddButtonEmployees.setBounds(1035, 130, 50, 50);
		employees.add(lblAddButtonEmployees);

		lblReloadButtonEmployees = new JLabel("");
		lblReloadButtonEmployees.setIcon(new ImageIcon(Main.class.getResource("/imgs/reload.png")));
		lblReloadButtonEmployees.setBounds(1115, 130, 50, 50);
		employees.add(lblReloadButtonEmployees);

		JLabel lblMainBarEmployees = new JLabel("");
		lblMainBarEmployees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMainBarEmployees.grabFocus();
			}
		});
		lblMainBarEmployees.setIcon(new ImageIcon(Main.class.getResource("/imgs/mainBar.png")));
		lblMainBarEmployees.setBounds(-300, 80, 1920, 150);
		employees.add(lblMainBarEmployees);

		JScrollPane scrollPaneEmployees = new JScrollPane();
		scrollPaneEmployees.setBackground(Color.WHITE);
		scrollPaneEmployees.setBounds(20, 250, 1580, 810);
		employees.add(scrollPaneEmployees);

		JTable tableEmployees = new JTable();
		tableEmployees.setDefaultEditor(Object.class, null); // non-editable
		tableEmployees.setGridColor(new Color(172, 172, 172));
		tableEmployees.setBackground(Color.WHITE);
		DefaultTableModel defaultTableModelEmployees = new DefaultTableModel(new Object[][] { null, null, null },
				new String[] { "ID", "Name", "Category", "Price", "Sold", "Discount", "Contractor", "Cost Price",
						"Stock", "Location", "Condition", "UnavailableTil", "Type", "Available" }) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Double.class, Boolean.class,
					Double.class, Object.class, Double.class, Integer.class, Object.class, String.class, Object.class,
					Object.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableEmployees.setModel(defaultTableModelEmployees);
		TableRowSorter<DefaultTableModel> tableRowSorterEmployees = new TableRowSorter<DefaultTableModel>(
				defaultTableModelEmployees);
		tableEmployees.setRowSorter(tableRowSorterEmployees);
		scrollPaneEmployees.setViewportView(tableEmployees);
		
		
		
		updateInventory();
		clock();
		databaseCheck();
		cacheData();
	}

	private void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						lblTime.setText(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

	private void databaseCheck() {
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {

						if (DBConnection.getConnection() == null) {
							lblDatabaseStatus.setText("Disconnected");
							lblDatabaseStatus.setBounds(1436, 0, 170, 40);

							lblCheckOff.setIcon(new ImageIcon(Main.class.getResource("/imgs/checkOFF.png")));
							lblCheckOff.setBounds(1411, 15, 17, 17);
						} else {
							lblDatabaseStatus.setText("Connected");
							lblDatabaseStatus.setBounds(1469, 0, 140, 40);

							lblCheckOff.setIcon(new ImageIcon(Main.class.getResource("/imgs/checkON.png")));
							lblCheckOff.setBounds(1444, 15, 17, 17);
						}

						sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	
	public void updateInventory() {
		defaultTableModelInventory.setRowCount(0); // clear the table
		Thread thread = new Thread() {
			public void run() {
				try {
					StoneController sctrl = new StoneController();
					stoneUnits = new ArrayList<IStoneUnit>();

					startLoading();
					try {
						stoneUnits = (ArrayList<IStoneUnit>) sctrl.getAllStoneUnits();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					for (IStoneUnit stoneUnit : stoneUnits) {
						StoneUnit stone = (StoneUnit) stoneUnit;
						defaultTableModelInventory.addRow(
								new Object[] {
										stone.getId(),
										stone.getStoneKind(),
										stone.getOrigin(),
										stone.getWidth(),
										stone.getWeight(),
										stone.getDescription(),
										stone.getCreatedDate(),
										stone.getLocation().getLocationName(),
										stone.getStatus().toString(),
										stone.getStoneType().getMaterial().getName(),
										stone.getStoneType().getName(),
										stoneUnit.getPieces()
										});

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stopLoading();
				}
			}
		};
		thread.start();

	}

	public void updateOrdersTable() {
		OrderController orderController = new OrderController();

		startLoading();
//		lblReloadButtonOrders.setEnabled(false);
		defaultTableModelOrders.setRowCount(0); // clear the table
		Thread thread = new Thread() {
			public void run() {
				try {
//					defaultTableModelOrders.addRow(orderController.getAll().toArray());
					for (OrderInfo order : orderController.getAllInfo())
						defaultTableModelOrders.addRow(new Object[] { order.getId(), order.getCustomer().getId(),
								order.getDeliveryDate(), order.getAddress(), order.getCity().getId(),
								order.getOrderPrice(), order.getDeposit(), order.isPaid(), order.getDeliveryStatus(),
								order.getEmployee().getId(), order.getOffice().getId(),
								order.getInvoice().getPaymentDate(), order.getInvoice().getVATratio(),
								order.getInvoice().getFinalPrice(), });
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
//					lblReloadButtonOrders.setEnabled(true);
					stopLoading();
				}
			}
		};
		thread.start();
	}
	
	public void updateSupplierList() {
		defaultTableModelSuppliers.setRowCount(0);
		Thread thread = new Thread() {
			public void run() {
				try {
					PersonController personController = new PersonController();
					suppliers = new ArrayList<Person>();

					startLoading();
					try {
						suppliers = personController.getAllSupplier();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					for (Person supplier : suppliers) {
						Supplier s = (Supplier )supplier;
						defaultTableModelSuppliers.addRow(
								new Object[] {
										s.getId(),
										s.getName(),
										s.getAddress(),
										s.getCity(),
										s.getPhoneNumber(),
										s.getEmail(),
										s.getDateOfBirth(),
										s.getAge(),
										s.getDescription(),
										s.getNote(),
										});

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stopLoading();
				}
			}
		};
		thread.start();

	}

	public void startLoading() {
		lblLoading.setVisible(true);
		lblLoadingIcon.setVisible(true);
		reloadButtonsSetEnabled(false);
		
	}
	
	public void stopLoading() {
		lblLoading.setVisible(false);
		lblLoadingIcon.setVisible(false);
		reloadButtonsSetEnabled(true);
	}

	private void reloadButtonsSetEnabled(boolean visible) {
		lblReloadButtonOrders.setEnabled(visible);
		lblReloadButtonInventory.setEnabled(visible);
		lblReloadButtonMaterial.setEnabled(visible);
		lblReloadButtonCustomers.setEnabled(visible);
		lblReloadButtonSuppliers.setEnabled(visible);
		lblReloadButtonEmployees.setEnabled(visible);
	}
	
	private void checkMaximizeRestore() {
		if (!isMaximizePressed) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			lblMaximizeRestore.setIcon((new ImageIcon(Main.class.getResource("/imgs/restore.png"))));
			isMaximizePressed = true;
		} else {
			setExtendedState(JFrame.NORMAL);
			lblMaximizeRestore.setIcon(new ImageIcon(Main.class.getResource("/imgs/maximize2.png")));
			isMaximizePressed = false;
		}
	}

	private void cacheData() {
		Thread thread = new Thread() {
			public void run() {
				try {
					lblCacheInfo.setText("Caching Data...");
					StoneTypeMaterialController matCtrl = new StoneTypeMaterialController();
					LocationCityController locCtrl = new LocationCityController();

					cachedMaterials = (ArrayList<StoneMaterial>) matCtrl.getAllStoneMaterials();
					cachedStoneTypes = (ArrayList<StoneType>) matCtrl.getAllStoneTypes();
					cachedLocations = (ArrayList<Location>) locCtrl.getAllLocations();
					cachedCities = (ArrayList<City>) locCtrl.getAllCities();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					/*
					 * lblCacheInfo.setText("<html>Cached data:<br/>" + cachedMaterials.size() +
					 * " Materials<br/>" + cachedStoneTypes.size() + " Stone Types<br/>" +
					 * cachedLocations.size() + " Locations</html>");
					 */
					lblCacheInfo.setText("Cached data: " + cachedMaterials.size() + " Materials | " + cachedStoneTypes.size()
					+ " Stone Types | " + cachedLocations.size() + " Locations | " + cachedCities.size() + " Cities");
					
							
				}
			}
		};
		thread.start();
	}
	
}
