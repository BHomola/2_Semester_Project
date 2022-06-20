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
import javax.swing.SwingConstants;

public class ProfileWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblDeleteStorno;
	private JLabel lblEditCheck;
	private JLabel lblMaximizeRestore;
	private JLabel lblStornoSmall;
	private JTextArea textAreaNotes;
	private boolean isEditNotesPressed;
	private boolean isEditPressed;
	private boolean isMaximizePressed;
	private int x;
	private int y;
	private JTextField textFieldAddress;
	private JTextField textFieldID;
	private JTextField textFieldDescription;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldCity;
	private JTextField textFieldDateOfBirth;
	private JTextField textFieldName;
	private JLabel lblPhoneNumberError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow frame = new ProfileWindow();
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
	public ProfileWindow() {
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
		JLabel lblTitle = new JLabel("PROFILE");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 515, 94);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/confirm1.png")));
					lblDeleteStorno.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/storno.png")));
					isEditPressed = true;
					switchEditable();
					textFieldName.grabFocus();
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
					lblEditCheck.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/profileEdit.png")));
						lblDeleteStorno.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/deleteButton.png")));
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
					
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/profileEdit.png")));
		lblEditCheck.setBounds(1060, 85, 50, 50);
		contentPane.add(lblEditCheck);
		
		lblDeleteStorno = new JLabel("");
		lblDeleteStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/profileEdit.png")));
				lblDeleteStorno.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/deleteButton.png")));
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
		lblDeleteStorno.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/deleteButton.png")));
		lblDeleteStorno.setBounds(1120, 85, 50, 50);
		contentPane.add(lblDeleteStorno);
		
		JLabel lblInvoice = new JLabel("");
		lblInvoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ProfileCustomerWindow profileCustomer = new ProfileCustomerWindow();
//				profileCustomer.setVisible(true);
				
//				ProfileSupplierWindow profileSupplierWindow = new ProfileSupplierWindow();
//				profileSupplierWindow.setVisible(true);
				
				ProfileEmployeeWindow profileEmployeeWindow = new ProfileEmployeeWindow();
				profileEmployeeWindow.setVisible(true);
			}
		});
		lblInvoice.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/moreButton.png")));
		lblInvoice.setBounds(1200, 85, 50, 50);
		contentPane.add(lblInvoice);
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
//CONTENT	
		textFieldID = new JTextField();
		textFieldID.setText("ID");
		textFieldID.setBorder(null);
		textFieldID.setDisabledTextColor(Color.WHITE);
		textFieldID.setBackground(Color.WHITE);
		textFieldID.setForeground(new Color(192, 176, 131));
		textFieldID.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldID.setBounds(108, 176, 433, 53);
		textFieldID.setEditable(false);
		contentPane.add(textFieldID);
		
		JLabel lblIDDescription = new JLabel("ID");
		lblIDDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIDDescription.setForeground(new Color(255, 238, 202));
		lblIDDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblIDDescription.setBounds(715, 193, 22, 27);
		contentPane.add(lblIDDescription);
		
		textFieldName = new JTextField();
		textFieldName.setText("NAME");
		textFieldName.setBorder(null);
		textFieldName.setDisabledTextColor(Color.WHITE);
		textFieldName.setBackground(Color.WHITE);
		textFieldName.setForeground(new Color(192, 176, 131));
		textFieldName.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldName.setBounds(108, 240, 430, 53);
		textFieldName.setEditable(false);
		contentPane.add(textFieldName);
		
		JLabel lblNameDescription = new JLabel("NAME");
		lblNameDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNameDescription.setForeground(new Color(255, 238, 202));
		lblNameDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNameDescription.setBounds(665, 261, 72, 27);
		contentPane.add(lblNameDescription);
		
		textFieldDateOfBirth = new JTextField();
		textFieldDateOfBirth.setText("DATE OF BIRTH");
		textFieldDateOfBirth.setBorder(null);
		textFieldDateOfBirth.setDisabledTextColor(Color.WHITE);
		textFieldDateOfBirth.setBackground(Color.WHITE);
		textFieldDateOfBirth.setForeground(new Color(192, 176, 131));
		textFieldDateOfBirth.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldDateOfBirth.setBounds(108, 308, 430, 53);
		textFieldDateOfBirth.setEditable(false);
		contentPane.add(textFieldDateOfBirth);
		
		JLabel lblDateOfBirthDescription = new JLabel("DATE OF BIRTH");
		lblDateOfBirthDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirthDescription.setForeground(new Color(255, 238, 202));
		lblDateOfBirthDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDateOfBirthDescription.setBounds(587, 330, 150, 27);
		contentPane.add(lblDateOfBirthDescription);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setText("ADDRESS");
		textFieldAddress.setBorder(null);
		textFieldAddress.setDisabledTextColor(Color.WHITE);
		textFieldAddress.setBackground(Color.WHITE);
		textFieldAddress.setForeground(new Color(192, 176, 131));
		textFieldAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldAddress.setBounds(108, 376, 433, 53);
		textFieldAddress.setEditable(false);
		contentPane.add(textFieldAddress);
		
		JLabel lblAddressDescription = new JLabel("ADDRESS");
		lblAddressDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddressDescription.setForeground(new Color(255, 238, 202));
		lblAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddressDescription.setBounds(647, 397, 90, 27);
		contentPane.add(lblAddressDescription);
		
		textFieldCity = new JTextField();
		textFieldCity.setText("CITY");
		textFieldCity.setBorder(null);
		textFieldCity.setDisabledTextColor(Color.WHITE);
		textFieldCity.setBackground(Color.WHITE);
		textFieldCity.setForeground(new Color(192, 176, 131));
		textFieldCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldCity.setBounds(108, 444, 430, 53);
		textFieldCity.setEditable(false);
		contentPane.add(textFieldCity);
		
		JLabel lblCityDescription = new JLabel("CITY");
		lblCityDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCityDescription.setForeground(new Color(255, 238, 202));
		lblCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCityDescription.setBounds(687, 465, 50, 27);
		contentPane.add(lblCityDescription);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setText("PHONE NUMBER");
		textFieldPhoneNumber.setBorder(null);
		textFieldPhoneNumber.setDisabledTextColor(Color.WHITE);
		textFieldPhoneNumber.setBackground(Color.WHITE);
		textFieldPhoneNumber.setForeground(new Color(192, 176, 131));
		textFieldPhoneNumber.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldPhoneNumber.setBounds(108, 512, 430, 53);
		textFieldPhoneNumber.setEditable(false);
		contentPane.add(textFieldPhoneNumber);
		
		lblPhoneNumberError = new JLabel("Must be a positive number!");
		lblPhoneNumberError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPhoneNumberError.setForeground(Color.RED);
		lblPhoneNumberError.setBounds(110, 565, 172, 14);
		lblPhoneNumberError.setVisible(false);
		contentPane.add(lblPhoneNumberError);
		textFieldPhoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldPhoneNumber.getText());
					lblPhoneNumberError.setVisible(false);
					if(number < 0)
						lblPhoneNumberError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblPhoneNumberError.setVisible(true);
				}
			}
		});
		
		JLabel lblPhoneNumberDescription = new JLabel("PHONE NUMBER");
		lblPhoneNumberDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumberDescription.setForeground(new Color(255, 238, 202));
		lblPhoneNumberDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPhoneNumberDescription.setBounds(565, 533, 172, 27);
		contentPane.add(lblPhoneNumberDescription);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setText("EMAIL");
		textFieldEmail.setBorder(null);
		textFieldEmail.setDisabledTextColor(Color.WHITE);
		textFieldEmail.setBackground(Color.WHITE);
		textFieldEmail.setForeground(new Color(192, 176, 131));
		textFieldEmail.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldEmail.setBounds(108, 580, 405, 53);
		textFieldEmail.setEditable(false);
		contentPane.add(textFieldEmail);
		
		JLabel lblEmailDescription = new JLabel("EMAIL");
		lblEmailDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailDescription.setForeground(new Color(255, 238, 202));
		lblEmailDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblEmailDescription.setBounds(647, 601, 90, 27);
		contentPane.add(lblEmailDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setText("DESCRIPTION");
		textFieldDescription.setBorder(null);
		textFieldDescription.setDisabledTextColor(Color.WHITE);
		textFieldDescription.setBackground(Color.WHITE);
		textFieldDescription.setForeground(new Color(192, 176, 131));
		textFieldDescription.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldDescription.setBounds(108, 648, 430, 53);
		textFieldDescription.setEditable(false);
		contentPane.add(textFieldDescription);
		
		JLabel lblDescriptionDescription = new JLabel("DESCRIPTION");
		lblDescriptionDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescriptionDescription.setForeground(new Color(255, 238, 202));
		lblDescriptionDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDescriptionDescription.setBounds(565, 669, 172, 27);
		contentPane.add(lblDescriptionDescription);
		
		JTextField lblNotes = new JTextField();
		lblNotes.setText("NOTES");
		lblNotes.setBorder(null);
		lblNotes.setDisabledTextColor(Color.WHITE);
		lblNotes.setBackground(Color.WHITE);
		lblNotes.setForeground(new Color(192, 176, 131));
		lblNotes.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblNotes.setBounds(760, 172, 128, 53);
		lblNotes.setEditable(false);
		contentPane.add(lblNotes);
		
		JLabel lblUpdateConfirmIcon = new JLabel("");
		isEditNotesPressed = false;
		lblUpdateConfirmIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditNotesPressed) {
					lblUpdateConfirmIcon.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/confirmSmall.png")));
					lblStornoSmall.setVisible(true);
					textAreaNotes.setEditable(true);
					textAreaNotes.grabFocus();
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
					lblUpdateConfirmIcon.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/notes2.png")));
					lblStornoSmall.setVisible(false);
					textAreaNotes.setEditable(false);
					contentPane.grabFocus();
					isEditNotesPressed = false;
				}
			}
		});
		lblUpdateConfirmIcon.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/notes2.png")));
		lblUpdateConfirmIcon.setBounds(900, 189, 25, 25);
		contentPane.add(lblUpdateConfirmIcon);
		
		lblStornoSmall = new JLabel("");
		lblStornoSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblUpdateConfirmIcon.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/notes2.png")));
				lblStornoSmall.setVisible(false);
				textAreaNotes.setEditable(false);
				contentPane.grabFocus();
				isEditNotesPressed = false;
			}
		});
		lblStornoSmall.setIcon(new ImageIcon(ProfileWindow.class.getResource("/imgs/stornoSmall.png")));
		lblStornoSmall.setBounds(930, 189, 27, 25);
		lblStornoSmall.setVisible(false);
		contentPane.add(lblStornoSmall);
		
		JScrollPane notesScrollPane = new JScrollPane();
		notesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		notesScrollPane.setBorder(null);
		notesScrollPane.setBounds(765, 225, 505, 484);
		notesScrollPane.getVerticalScrollBar().setUnitIncrement(4);
		contentPane.add(notesScrollPane);
		
		textAreaNotes = new JTextArea();
		textAreaNotes.setLineWrap(true);
		textAreaNotes.setWrapStyleWord(true);
		textAreaNotes.setText("12.06.2022(11:30) - Description was changes\r\n12.06.2022(11:00) - Supplier added a new material type\r\n10.06.2022(12:05) - Email updated from ? to ?\r\n10.06.2022(12:00) - Date of birth was set\r\n10.06.2022(11:10) - Address updated from ? to ?\r\n10.06.2022(11:00) - Profile created");
		textAreaNotes.setForeground(new Color(192, 176, 131));
		textAreaNotes.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaNotes.setEditable(false);
		notesScrollPane.setViewportView(textAreaNotes);
		textAreaNotes.setCaretPosition(0);
	}
	
	private boolean haveErrors() {
		return lblPhoneNumberError.isVisible();
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
			textFieldName.setEditable(true);
			textFieldDateOfBirth.setEditable(true);
			textFieldAddress.setEditable(true);
			textFieldCity.setEditable(true);
			textFieldPhoneNumber.setEditable(true);
			textFieldEmail.setEditable(true);
			textFieldDescription.setEditable(true);
			
		} else {
			textFieldName.setEditable(false);
			textFieldDateOfBirth.setEditable(false);
			textFieldAddress.setEditable(false);
			textFieldCity.setEditable(false);
			textFieldPhoneNumber.setEditable(false);
			textFieldEmail.setEditable(false);
			textFieldDescription.setEditable(false);
		}
		
	}
}
