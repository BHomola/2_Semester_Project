package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class Main extends JFrame {

	private JPanel contentPane;
	private JLabel lblPleaseLogIn;
	private JLabel lblWelcome;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JPanel welcomePane;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		welcomePane = new JPanel();
		welcomePane.setBackground(Color.WHITE);
		contentPane.add(welcomePane, "name_63410508968500");
		welcomePane.setLayout(null);
		
		JSplitPane slideSplitPane = new JSplitPane();
		slideSplitPane.setBackground(Color.WHITE);
		slideSplitPane.setDividerSize(0);
		contentPane.add(slideSplitPane, "name_63410517035100");
		
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
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setContentPane(slideSplitPane);
			}
		});
		btnSignIn.setBorder(null);
		btnSignIn.setFocusable(false);
		btnSignIn.setBackground(new Color(199, 176, 131));
		btnSignIn.setForeground(new Color(255, 238, 202));
		btnSignIn.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnSignIn.setBounds(1338, 764, 155, 42);
		welcomePane.add(btnSignIn);
		
		
		
		
	}
}
