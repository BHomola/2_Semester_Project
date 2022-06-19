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
import model.StoneUnitStatuses;
import javax.swing.SwingConstants;

public class StoneUnitWindow extends JFrame {

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
	private boolean isEditNotesPressed;
	private boolean isEditPressed;
	private boolean isMaximizePressed;
	private int x;
	private int y;
	private JTextField textFieldLocationCity;
	private JTextField textFieldLocationAddress;
	private JTextField textFieldLocation;
	private JTextField textFieldDescription;
	private JTextField textFieldWeight;
	private JTextField textFieldWidth;
	private JTextField textFieldCreatedDate;
	private JTextField textFieldOrigin;
	private JTextField textFieldId;
	private JComboBox<?> comboBoxStatus;
	private JLabel lblSupplierError;
	private JLabel lblOrderPriceError;
	private JLabel lblDepositError;
	private JLabel lblEmployeeError;
	private JLabel lblMaterialTypeError;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoneUnitWindow frame = new StoneUnitWindow();
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
	public StoneUnitWindow() {
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
		JLabel lblTitle = new JLabel("STONE UNIT");
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblTitle.setBounds(105, 60, 420, 100);
		contentPane.add(lblTitle);
		
		lblEditCheck = new JLabel("");
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/confirm1.png")));
					lblDeleteStorno.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/storno.png")));
					isEditPressed = true;
					switchEditable();
					textFieldOrigin.grabFocus();
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
						lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/editButton.png")));
						lblDeleteStorno.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/deleteButton.png")));
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
					
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/editButton.png")));
		lblEditCheck.setBounds(998, 85, 50, 50);
		contentPane.add(lblEditCheck);
		
		lblDeleteStorno = new JLabel("");
		lblDeleteStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed) {
				lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/editButton.png")));
				lblDeleteStorno.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/deleteButton.png")));
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
		lblDeleteStorno.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/deleteButton.png")));
		lblDeleteStorno.setBounds(1060, 85, 50, 50);
		contentPane.add(lblDeleteStorno);
		
		JLabel lblTree = new JLabel("");
		lblTree.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/treeButton.png")));
		lblTree.setBounds(1140, 85, 50, 50);
		contentPane.add(lblTree);
		
		JLabel lblMore = new JLabel("");
		lblMore.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moreButton.png")));
		lblMore.setBounds(1200, 85, 50, 50);
		contentPane.add(lblMore);
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblWindowOrderBar.setBounds(0, 60, 1280, 100);
		contentPane.add(lblWindowOrderBar);
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/splitLine.png")));
		lblSplitLine.setBounds(747, 200, 1, 500);
		contentPane.add(lblSplitLine);
		
//CONTENT		
		textFieldId = new JTextField();
		textFieldId.setText("ID");
		textFieldId.setBorder(null);
		textFieldId.setDisabledTextColor(Color.WHITE);
		textFieldId.setBackground(Color.WHITE);
		textFieldId.setForeground(new Color(192, 176, 131));
		textFieldId.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldId.setBounds(108, 172, 430, 53);
		textFieldId.setEditable(false);
		contentPane.add(textFieldId);
		
		JLabel lblIdDescription = new JLabel("ID");
		lblIdDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdDescription.setForeground(new Color(255, 238, 202));
		lblIdDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblIdDescription.setBounds(712, 193, 22, 27);
		contentPane.add(lblIdDescription);
		
		JLabel lblMaterialType = new JLabel("MATERIAL TYPE");
		lblMaterialType.setForeground(new Color(192, 176, 131));
		lblMaterialType.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblMaterialType.setBounds(108, 240, 430, 53);
		contentPane.add(lblMaterialType);
		
		lblMaterialTypeError = new JLabel("Must not be empty!");
		lblMaterialTypeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMaterialTypeError.setForeground(Color.RED);
		lblMaterialTypeError.setBounds(110, 293, 140, 14);
//		lblPersonError.setVisible(false);
		contentPane.add(lblMaterialTypeError);
		
		JLabel lblMoveToMaterialType = new JLabel("");
		lblMoveToMaterialType.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToMaterialType.setBounds(422, 257, 25, 25);
		contentPane.add(lblMoveToMaterialType);
		
		JLabel lblMoveToMaterialTypedDescription = new JLabel("MATERIAL TYPE");
		lblMoveToMaterialTypedDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoveToMaterialTypedDescription.setForeground(new Color(255, 238, 202));
		lblMoveToMaterialTypedDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMoveToMaterialTypedDescription.setBounds(574, 261, 160, 27);
		contentPane.add(lblMoveToMaterialTypedDescription);
		
		textFieldOrigin = new JTextField();
		textFieldOrigin.setText("ORIGIN");
		textFieldOrigin.setBorder(null);
		textFieldOrigin.setDisabledTextColor(Color.WHITE);
		textFieldOrigin.setBackground(Color.WHITE);
		textFieldOrigin.setForeground(new Color(192, 176, 131));
		textFieldOrigin.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldOrigin.setBounds(108, 308, 430, 53);
		textFieldOrigin.setEditable(false);
		contentPane.add(textFieldOrigin);
		
		JLabel lblOriginDescription = new JLabel("ORIGIN");
		lblOriginDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOriginDescription.setForeground(new Color(255, 238, 202));
		lblOriginDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOriginDescription.setBounds(664, 329, 70, 27);
		contentPane.add(lblOriginDescription);
		
		textFieldWidth = new JTextField();
		textFieldWidth.setText("WIDTH");
		textFieldWidth.setBorder(null);
		textFieldWidth.setDisabledTextColor(Color.WHITE);
		textFieldWidth.setBackground(Color.WHITE);
		textFieldWidth.setForeground(new Color(192, 176, 131));
		textFieldWidth.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldWidth.setBounds(108, 376, 430, 53);
		textFieldWidth.setEditable(false);
		contentPane.add(textFieldWidth);
		
		lblOrderPriceError = new JLabel("Must be a positive number! (devided by dot)");
		lblOrderPriceError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblOrderPriceError.setForeground(Color.RED);
		lblOrderPriceError.setBounds(110, 429, 337, 14);
		lblOrderPriceError.setVisible(false);
		contentPane.add(lblOrderPriceError);
		textFieldWidth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double.parseDouble(textFieldWidth.getText());
					lblOrderPriceError.setVisible(false);
				} catch(NumberFormatException ex) {
					lblOrderPriceError.setVisible(true);
				}
			}
		});
		
		JLabel lblWidthDescription = new JLabel("WIDTH (CM)");
		lblWidthDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidthDescription.setForeground(new Color(255, 238, 202));
		lblWidthDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblWidthDescription.setBounds(615, 397, 119, 27);
		contentPane.add(lblWidthDescription);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setText("WEIGHT");
		textFieldWeight.setBorder(null);
		textFieldWeight.setDisabledTextColor(Color.WHITE);
		textFieldWeight.setBackground(Color.WHITE);
		textFieldWeight.setForeground(new Color(192, 176, 131));
		textFieldWeight.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldWeight.setBounds(108, 444, 430, 53);
		textFieldWeight.setEditable(false);
		contentPane.add(textFieldWeight);
		
		lblDepositError = new JLabel("Must be a positive number! (devided by dot)");
		lblDepositError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblDepositError.setForeground(Color.RED);
		lblDepositError.setBounds(110, 497, 337, 14);
		lblDepositError.setVisible(false);
		contentPane.add(lblDepositError);
		textFieldWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double.parseDouble(textFieldWeight.getText());
					lblDepositError.setVisible(false);
				} catch(NumberFormatException ex) {
					lblDepositError.setVisible(true);
				}
			}
		});
		
		JLabel lblDepositDescription = new JLabel("WEIGHT (KG)");
		lblDepositDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepositDescription.setForeground(new Color(255, 238, 202));
		lblDepositDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDepositDescription.setBounds(604, 465, 130, 27);
		contentPane.add(lblDepositDescription);
		
		textFieldCreatedDate = new JTextField();
		textFieldCreatedDate.setText("CREATED DATE");
		textFieldCreatedDate.setBorder(null);
		textFieldCreatedDate.setDisabledTextColor(Color.WHITE);
		textFieldCreatedDate.setBackground(Color.WHITE);
		textFieldCreatedDate.setForeground(new Color(192, 176, 131));
		textFieldCreatedDate.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldCreatedDate.setBounds(108, 512, 430, 53);
		textFieldCreatedDate.setEditable(false);
		contentPane.add(textFieldCreatedDate);
		
		JLabel lblCreatedDateDescription = new JLabel("CREATED DATE");
		lblCreatedDateDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreatedDateDescription.setForeground(new Color(255, 238, 202));
		lblCreatedDateDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCreatedDateDescription.setBounds(574, 533, 160, 27);
		contentPane.add(lblCreatedDateDescription);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setFocusable(false);
		comboBoxStatus.setModel(new DefaultComboBoxModel(StoneUnitStatuses.values()));
		comboBoxStatus.setBorder(null);
		comboBoxStatus.setBackground(Color.WHITE);
		comboBoxStatus.setForeground(new Color(192, 176, 131));
		comboBoxStatus.setFont(new Font("Segoe UI", Font.BOLD, 40));
		comboBoxStatus.setBounds(108, 580, 340, 53);
		comboBoxStatus.hidePopup();
		comboBoxStatus.setEditable(false);
		comboBoxStatus.setEnabled(false);
		contentPane.add(comboBoxStatus);
		
		JLabel lblStatusDescription = new JLabel("STATUS");
		lblStatusDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusDescription.setForeground(new Color(255, 238, 202));
		lblStatusDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblStatusDescription.setBounds(654, 601, 80, 27);
		contentPane.add(lblStatusDescription);
		
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
		lblDescriptionDescription.setBounds(604, 669, 130, 27);
		contentPane.add(lblDescriptionDescription);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setText("LOCATION");
		textFieldLocation.setBorder(null);
		textFieldLocation.setDisabledTextColor(Color.WHITE);
		textFieldLocation.setBackground(Color.WHITE);
		textFieldLocation.setForeground(new Color(192, 176, 131));
		textFieldLocation.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocation.setBounds(760, 172, 395, 53);
		textFieldLocation.setEditable(false);
		contentPane.add(textFieldLocation);
		
		JLabel lblLocationDescription = new JLabel("LOCATION");
		lblLocationDescription.setForeground(new Color(255, 238, 202));
		lblLocationDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLocationDescription.setBounds(1165, 193, 100, 27);
		contentPane.add(lblLocationDescription);
		
		textFieldLocationAddress = new JTextField();
		textFieldLocationAddress.setText("ADDRESS");
		textFieldLocationAddress.setBorder(null);
		textFieldLocationAddress.setDisabledTextColor(Color.WHITE);
		textFieldLocationAddress.setBackground(Color.WHITE);
		textFieldLocationAddress.setForeground(new Color(192, 176, 131));
		textFieldLocationAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocationAddress.setBounds(760, 240, 395, 53);
		textFieldLocationAddress.setEditable(false);
		contentPane.add(textFieldLocationAddress);
		
		JLabel lblLocationAddressDescription = new JLabel("ADDRESS");
		lblLocationAddressDescription.setForeground(new Color(255, 238, 202));
		lblLocationAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLocationAddressDescription.setBounds(1175, 261, 90, 27);
		contentPane.add(lblLocationAddressDescription);
		
		textFieldLocationCity = new JTextField();
		textFieldLocationCity.setText("CITY");
		textFieldLocationCity.setBorder(null);
		textFieldLocationCity.setDisabledTextColor(Color.WHITE);
		textFieldLocationCity.setBackground(Color.WHITE);
		textFieldLocationCity.setForeground(new Color(192, 176, 131));
		textFieldLocationCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocationCity.setBounds(760, 308, 395, 53);
		textFieldLocationCity.setEditable(false);
		contentPane.add(textFieldLocationCity);
		
		JLabel lblLocationCityDescription = new JLabel("CITY");
		lblLocationCityDescription.setForeground(new Color(255, 238, 202));
		lblLocationCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLocationCityDescription.setBounds(1221, 329, 44, 27);
		contentPane.add(lblLocationCityDescription);
		
		JLabel lblSupplier = new JLabel("SUPPLIER");
		lblSupplier.setForeground(new Color(192, 176, 131));
		lblSupplier.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblSupplier.setBounds(760, 376, 182, 53);
		contentPane.add(lblSupplier);
		
		lblSupplierError = new JLabel("Must not be empty!");
		lblSupplierError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSupplierError.setForeground(Color.RED);
		lblSupplierError.setBounds(762, 429, 140, 14);
//		lblPersonError.setVisible(false);
		contentPane.add(lblSupplierError);
		
		JLabel lblMoveToSupplier = new JLabel("");
		lblMoveToSupplier.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToSupplier.setBounds(953, 393, 25, 25);
		contentPane.add(lblMoveToSupplier);
		
		JLabel lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setForeground(new Color(192, 176, 131));
		lblEmployee.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblEmployee.setBounds(1028, 376, 200, 53);
		contentPane.add(lblEmployee);
		
		lblEmployeeError = new JLabel("Must not be empty!");
		lblEmployeeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmployeeError.setForeground(Color.RED);
		lblEmployeeError.setBounds(1030, 429, 140, 14);
//		lblEmployeeError.setVisible(false);
		contentPane.add(lblEmployeeError);
		
		JLabel lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToEmployee.setBounds(1240, 393, 25, 25);
		contentPane.add(lblMoveToEmployee);
		
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
					lblUpdateConfirmIcon.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/confirmSmall.png")));
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
					lblUpdateConfirmIcon.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/notes2.png")));
					lblStornoSmall.setVisible(false);
					textAreaUpdates.setEditable(false);
					contentPane.grabFocus();
					isEditNotesPressed = false;
				}
			}
		});
		lblUpdateConfirmIcon.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/notes2.png")));
		lblUpdateConfirmIcon.setBounds(948, 460, 25, 25);
		contentPane.add(lblUpdateConfirmIcon);
		
		lblStornoSmall = new JLabel("");
		lblStornoSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblUpdateConfirmIcon.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/notes2.png")));
				lblStornoSmall.setVisible(false);
				textAreaUpdates.setEditable(false);
				contentPane.grabFocus();
				isEditNotesPressed = false;
			}
		});
		lblStornoSmall.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/stornoSmall.png")));
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
		textAreaUpdates.setText("12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created\r\n\r\n12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created\r\n\r\n12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created");
		textAreaUpdates.setForeground(new Color(192, 176, 131));
		textAreaUpdates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaUpdates.setEditable(false);
		updatesScrollPane.setViewportView(textAreaUpdates);
		textAreaUpdates.setCaretPosition(0);
	}
	
	private boolean haveErrors() {
		return lblSupplierError.isVisible() || lblOrderPriceError.isVisible() || lblDepositError.isVisible() || lblEmployeeError.isVisible();
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
//			textFieldId.setEditable(true);
			textFieldOrigin.setEditable(true);
			comboBoxStatus.setEnabled(true);
			textFieldCreatedDate.setEditable(true);
			textFieldWidth.setEditable(true);
			textFieldWeight.setEditable(true);
			textFieldDescription.setEditable(true);
			textFieldLocation.setEditable(true);
			textFieldLocationAddress.setEditable(true);
			textFieldLocationCity.setEditable(true);
		} else {
//			textFieldId.setEditable(false);
			textFieldOrigin.setEditable(false);
			comboBoxStatus.setEnabled(false);
			textFieldCreatedDate.setEditable(false);
			textFieldWidth.setEditable(false);
			textFieldWeight.setEditable(false);
			textFieldDescription.setEditable(false);
			textFieldLocation.setEditable(false);
			textFieldLocationAddress.setEditable(false);
			textFieldLocationCity.setEditable(false);
		}
		
	}
}
