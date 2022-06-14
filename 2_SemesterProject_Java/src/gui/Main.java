package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.EventTarget;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPleaseLogIn;
	private JLabel lblWelcome;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JPanel welcomePane;
	private JPanel titleBarPane;
	private JPanel cardPane;
	private JSplitPane slideSplitPane;
	private JPanel sidePane;
	private JPanel panel_1;
	private JLabel lblSideBar;
	private JLabel lblLogout;
	private JLabel lblHello_1;
	private int x,y;
	private JLabel lblTime;
	private JLabel lblDate;
	private JLabel lblOrders;
	private JLabel lblInventory;
	private JLabel lblMaterial;
	private JLabel lblCustomers;
	private JLabel lblSuppliers;
	private JLabel lblEmployees;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		CardLayout cardLayout = new CardLayout();
		
//		x = 0;
//		y = 0;
//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
//PANELS		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		titleBarPane = new JPanel();
		titleBarPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		titleBarPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				setLocation(xx-x-300, yy-y);
			}
		});
		titleBarPane.setBackground(Color.WHITE);
		titleBarPane.setBounds(300, 0, 1620, 40);
		contentPane.add(titleBarPane);
		
		cardPane = new JPanel();
		cardPane.setBounds(0, 0, 1920, 1080);
		contentPane.add(cardPane);
		cardPane.setLayout(cardLayout);
		
		welcomePane = new JPanel();
		welcomePane.setBounds(0, 0, 1920, 1080);
		welcomePane.setBackground(Color.WHITE);
		cardPane.add(welcomePane, "name_66960479156300");
		welcomePane.setLayout(null);
		
		slideSplitPane = new JSplitPane();
		slideSplitPane.setBorder(null);
		slideSplitPane.setBounds(-10000, -10000, 1920, 1080);
		slideSplitPane.setBackground(Color.WHITE);
		slideSplitPane.setDividerSize(0);
		cardPane.add(slideSplitPane, "name_66960487401900");
		
		sidePane = new JPanel();
		sidePane.setBackground(Color.WHITE);
		slideSplitPane.setLeftComponent(sidePane);
		sidePane.setLayout(null);
		
		lblHello_1 = new JLabel("Hello, <User>");
		lblHello_1.setForeground(Color.WHITE);
		lblHello_1.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		lblHello_1.setBounds(55, 14, 216, 47);
		sidePane.add(lblHello_1);
		
		lblLogout = new JLabel("");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setBackground(Color.WHITE);
				
				cardLayout.show(cardPane, "name_66960479156300");
			}
		});
		lblLogout.setIcon(new ImageIcon(Main.class.getResource("/imgs/logout.png")));
		lblLogout.setBounds(24, 30, 19, 19);
		sidePane.add(lblLogout);
		
		lblDate = new JLabel();
		lblDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
		lblDate.setForeground(new Color(255, 238, 202));
		lblDate.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		lblDate.setBounds(86, 118, 131, 40);
		sidePane.add(lblDate);
		
		lblTime = new JLabel("10:30");
		lblTime.setForeground(new Color(255, 238, 202));
		lblTime.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		lblTime.setBounds(99, 153, 110, 40);
		sidePane.add(lblTime);
		
		lblOrders = new JLabel("Orders");
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
	});
		
		lblInventory = new JLabel("Inventory");
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
		});
		
		lblMaterial = new JLabel("Material");
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
		});
		
		lblCustomers = new JLabel("Customers");
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
		});
		
		lblSuppliers = new JLabel("Suppliers");
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
		});
		
		lblEmployees = new JLabel("Employees");
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
		});
		
		lblSideBar = new JLabel("");
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
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		slideSplitPane.setRightComponent(panel_1);
		//300/1920
		slideSplitPane.setDividerLocation(0.15625);
		
//CONTENT	
		JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});		
		titleBarPane.setLayout(null);
		lblClose.setIcon(new ImageIcon(Main.class.getResource("/imgs/close.png")));
		lblClose.setBounds(1590, 10, 20, 20);
		titleBarPane.add(lblClose);
		
		JLabel lblWallLogo = new JLabel("");
		lblWallLogo.setIcon(new ImageIcon(Main.class.getResource("/imgs/wallLogo.png")));
		lblWallLogo.setBounds(136, 219, 840, 642);
		welcomePane.add(lblWallLogo);
		
		lblWelcome = new JLabel("WELCOME");
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
		
		lblPleaseLogIn = new JLabel("Please log in");
		lblPleaseLogIn.setForeground(new Color(199, 176, 131));
		lblPleaseLogIn.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblPleaseLogIn.setBounds(1238, 434, 371, 80);
		welcomePane.add(lblPleaseLogIn);
		
		textFieldUsername = new JTextField();
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
		
		JLabel lblLoginError = new JLabel("ERROR! Wrong USERNAME or PASSWORD");
		lblLoginError.setVisible(false);
		lblLoginError.setSize(382, 42);
		lblLoginError.setLocation(1204, 700);
		lblLoginError.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLoginError.setForeground(new Color(230, 57, 70));
		welcomePane.add(lblLoginError);
		
		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(Color.BLACK);
				
				cardLayout.show(cardPane, "name_66960487401900");
			}
		});
		btnSignIn.setBorder(null);
		btnSignIn.setFocusable(false);
		btnSignIn.setBackground(new Color(199, 176, 131));
		btnSignIn.setForeground(new Color(255, 238, 202));
		btnSignIn.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnSignIn.setBounds(1338, 764, 155, 42);
		welcomePane.add(btnSignIn);
		clock();
	}
	private void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						lblTime.setText(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
						// ms.deSeriali();
						sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
}
