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

public class ProfileEmployeeWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblStorno;
	private JLabel lblEditCheck;
	private JLabel lblMaximizeRestore;
	private JLabel lblSalaryError;
	private boolean isEditPressed;
	private boolean isMaximizePressed;
	private int x;
	private int y;
	private JTextField textFieldStartDayDate;
	private JTextField textFieldLocation;
	private JTextField textFieldAddress;
	private JTextField textFieldSalary;
	private JTextField textFieldPosition;
	private JTextField textFieldCity;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileEmployeeWindow frame = new ProfileEmployeeWindow();
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
	public ProfileEmployeeWindow() {
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
		JLabel lblTitle = new JLabel("EMPLOYEE, NAME");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 600, 94);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/confirm1.png")));
					lblStorno.setVisible(true);
					isEditPressed = true;
					switchEditable();
					textFieldSalary.grabFocus();
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
						lblEditCheck.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/editButton2.png")));
						lblStorno.setVisible(false);
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/editButton2.png")));
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
		lblStorno.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/storno.png")));
		lblStorno.setBounds(1120, 85, 50, 50);
		lblStorno.setVisible(false);
		contentPane.add(lblStorno);
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
//CONTENT		
		textFieldPosition = new JTextField();
		textFieldPosition.setText("POSITION");
		textFieldPosition.setBorder(null);
		textFieldPosition.setDisabledTextColor(Color.WHITE);
		textFieldPosition.setBackground(Color.WHITE);
		textFieldPosition.setForeground(new Color(192, 176, 131));
		textFieldPosition.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldPosition.setBounds(108, 171, 430, 53);
		textFieldPosition.setEditable(false);
		contentPane.add(textFieldPosition);
		
		JLabel lbPositionDescription = new JLabel("POSITION");
		lbPositionDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPositionDescription.setForeground(new Color(255, 238, 202));
		lbPositionDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbPositionDescription.setBounds(595, 192, 143, 27);
		contentPane.add(lbPositionDescription);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setText("SALARY");
		textFieldSalary.setBorder(null);
		textFieldSalary.setDisabledTextColor(Color.WHITE);
		textFieldSalary.setBackground(Color.WHITE);
		textFieldSalary.setForeground(new Color(192, 176, 131));
		textFieldSalary.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldSalary.setBounds(108, 239, 430, 53);
		textFieldSalary.setEditable(false);
		contentPane.add(textFieldSalary);
		

		lblSalaryError = new JLabel("Must be a positive number! (devided by dot)");
		lblSalaryError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSalaryError.setForeground(Color.RED);
		lblSalaryError.setBounds(110, 292, 340, 14);
		lblSalaryError.setVisible(false);
		contentPane.add(lblSalaryError);
		textFieldSalary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldSalary.getText());
					lblSalaryError.setVisible(false);
					if(number < 0)
						lblSalaryError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblSalaryError.setVisible(true);
				}
			}
		});
		
		JLabel lblSalaryDescription = new JLabel("SALARY (\u20AC)");
		lblSalaryDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalaryDescription.setForeground(new Color(255, 238, 202));
		lblSalaryDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSalaryDescription.setBounds(551, 260, 186, 27);
		contentPane.add(lblSalaryDescription);
		
		textFieldStartDayDate = new JTextField();
		textFieldStartDayDate.setText("START DAY DATE");
		textFieldStartDayDate.setBorder(null);
		textFieldStartDayDate.setDisabledTextColor(Color.WHITE);
		textFieldStartDayDate.setBackground(Color.WHITE);
		textFieldStartDayDate.setForeground(new Color(192, 176, 131));
		textFieldStartDayDate.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldStartDayDate.setBounds(108, 307, 430, 53);
		textFieldStartDayDate.setEditable(false);
		contentPane.add(textFieldStartDayDate);
		
		JLabel lblStartDayDateDescription = new JLabel("START DAY DATE");
		lblStartDayDateDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStartDayDateDescription.setForeground(new Color(255, 238, 202));
		lblStartDayDateDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblStartDayDateDescription.setBounds(570, 328, 167, 27);
		contentPane.add(lblStartDayDateDescription);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setText("LOCATION");
		textFieldLocation.setBorder(null);
		textFieldLocation.setDisabledTextColor(Color.WHITE);
		textFieldLocation.setBackground(Color.WHITE);
		textFieldLocation.setForeground(new Color(192, 176, 131));
		textFieldLocation.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocation.setBounds(108, 375, 430, 53);
		textFieldLocation.setEditable(false);
		contentPane.add(textFieldLocation);
		
		JLabel lblLocationDescription = new JLabel("LOCATION");
		lblLocationDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocationDescription.setForeground(new Color(255, 238, 202));
		lblLocationDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLocationDescription.setBounds(551, 396, 186, 27);
		contentPane.add(lblLocationDescription);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setText("ADDRESS");
		textFieldAddress.setBorder(null);
		textFieldAddress.setDisabledTextColor(Color.WHITE);
		textFieldAddress.setBackground(Color.WHITE);
		textFieldAddress.setForeground(new Color(192, 176, 131));
		textFieldAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldAddress.setBounds(108, 443, 430, 53);
		textFieldAddress.setEditable(false);
		contentPane.add(textFieldAddress);
		
		JLabel lblAddressDescription = new JLabel("ADDRESS");
		lblAddressDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddressDescription.setForeground(new Color(255, 238, 202));
		lblAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddressDescription.setBounds(551, 464, 186, 27);
		contentPane.add(lblAddressDescription);
		
		textFieldCity = new JTextField();
		textFieldCity.setText("CITY");
		textFieldCity.setForeground(new Color(192, 176, 131));
		textFieldCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldCity.setEditable(false);
		textFieldCity.setDisabledTextColor(Color.WHITE);
		textFieldCity.setBorder(null);
		textFieldCity.setBackground(Color.WHITE);
		textFieldCity.setBounds(108, 511, 430, 53);
		contentPane.add(textFieldCity);
		
		JLabel lblCityDescription = new JLabel("CITY");
		lblCityDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCityDescription.setForeground(new Color(255, 238, 202));
		lblCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCityDescription.setBounds(551, 532, 186, 27);
		contentPane.add(lblCityDescription);
		
		JLabel lblOrder = new JLabel("ORDER(S)");
		lblOrder.setForeground(new Color(192, 176, 131));
		lblOrder.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblOrder.setBounds(108, 579, 235, 53);
		contentPane.add(lblOrder);
		
		JLabel lblMoveToOrder = new JLabel("");
		lblMoveToOrder.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToOrder.setBounds(305, 596, 25, 25);
		contentPane.add(lblMoveToOrder);
		
		JLabel lblStoneUnit = new JLabel("STONE UNIT(S)");
		lblStoneUnit.setForeground(new Color(192, 176, 131));
		lblStoneUnit.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblStoneUnit.setBounds(108, 647, 293, 53);
		contentPane.add(lblStoneUnit);
		
		JLabel lblMoveToStoneUnit = new JLabel("");
		lblMoveToStoneUnit.setIcon(new ImageIcon(OrderWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToStoneUnit.setBounds(407, 663, 25, 25);
		contentPane.add(lblMoveToStoneUnit);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon(ProfileEmployeeWindow.class.getResource("/imgs/addPhoto.png")));
		lblPicture.setBounds(763, 192, 500, 500);
		contentPane.add(lblPicture);
	}
	
	private boolean haveErrors() {
		return lblSalaryError.isVisible();
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
			textFieldPosition.setEditable(true);
			textFieldSalary.setEditable(true);
			textFieldStartDayDate.setEditable(true);
			textFieldLocation.setEditable(true);
			textFieldAddress.setEditable(true);
			textFieldCity.setEditable(true);
		} else {
			textFieldPosition.setEditable(false);
			textFieldSalary.setEditable(false);
			textFieldStartDayDate.setEditable(false);
			textFieldLocation.setEditable(false);
			textFieldAddress.setEditable(false);
			textFieldCity.setEditable(false);
		}
		
	}
}
