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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.StoneController;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import model.IStoneUnit;
import model.StoneUnit;
import model.StoneUnitStatuses;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

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
	private JLabel lblWidthError;
	private JLabel lblWeightError;
	private JLabel lblEmployeeError;
	private JLabel lblMaterialTypeError;
	private CardLayout cardLayout;
	private JPanel cardPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoneUnitWindow frame = new StoneUnitWindow(0);
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
	public StoneUnitWindow(int id) {
		cardLayout = new CardLayout();
		
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
		
//CARD PANE		
		cardPane = new JPanel();
		cardPane.setBackground(Color.WHITE);
		cardPane.setBounds(0, 60, 1280, 720);
		contentPane.add(cardPane);
		cardPane.setLayout(cardLayout);
				
//STONE UNIT PANE		
		JPanel stoneUnitPane = new JPanel();
		stoneUnitPane.setBackground(Color.WHITE);
		cardPane.add(stoneUnitPane, "name_105638542096500");
		stoneUnitPane.setLayout(null);
				
//TITLE		
		JLabel lblTitle = new JLabel("STONE UNIT");
		lblTitle.setBounds(105, 0, 420, 94);
		stoneUnitPane.add(lblTitle);
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		
		lblEditCheck = new JLabel("");
		lblEditCheck.setBounds(1000, 25, 50, 50);
		stoneUnitPane.add(lblEditCheck);
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
						lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/editButton2.png")));
						lblDeleteStorno.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/deleteButton.png")));
						contentPane.grabFocus();
						isEditPressed = false;
						switchEditable();
					
				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/editButton2.png")));
		
		lblDeleteStorno = new JLabel("");
		lblDeleteStorno.setBounds(1060, 25, 50, 50);
		stoneUnitPane.add(lblDeleteStorno);
		lblDeleteStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isEditPressed) {
				lblEditCheck.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/editButton2.png")));
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
		
		JLabel lblTree = new JLabel("");
		lblTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardPane, "name_105672345315800");
			}
		});
		lblTree.setBounds(1140, 25, 50, 50);
		stoneUnitPane.add(lblTree);
		lblTree.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/treeButton.png")));
		
		JLabel lblMore = new JLabel("");
		lblMore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				StoneUnitRemainsWindow stoneUnitRemains = new StoneUnitRemainsWindow();
//				stoneUnitRemains.setVisible(true);
				
//				StoneUnitCuttableWindow stoneUnitCuttable = new StoneUnitCuttableWindow();
//				stoneUnitCuttable.setVisible(true);
				
				StoneUnitProductWindow stoneUnitProduct = new StoneUnitProductWindow();
				stoneUnitProduct.setVisible(true);
			}
		});
		lblMore.setBounds(1200, 25, 50, 50);
		stoneUnitPane.add(lblMore);
		lblMore.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moreButton.png")));
		
		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setBounds(0, 0, 1280, 100);
		stoneUnitPane.add(lblWindowOrderBar);
		lblWindowOrderBar.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/windowTitleBar.png")));
		
		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setBounds(747, 140, 1, 500);
		stoneUnitPane.add(lblSplitLine);
		lblSplitLine.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/splitLine.png")));
		
//CONTENT	
		textFieldId = new JTextField("ID");
		textFieldId.setBounds(108, 112, 430, 34);
		stoneUnitPane.add(textFieldId);
		textFieldId.setBorder(null);
		textFieldId.setDisabledTextColor(new Color(192, 192, 192));
		textFieldId.setBackground(new Color(255, 250, 250));
		textFieldId.setForeground(new Color(47, 79, 79));
		textFieldId.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldId.setEditable(false);
		
		JLabel lblIdDescription = new JLabel("ID");
		lblIdDescription.setBounds(574, 111, 22, 27);
		stoneUnitPane.add(lblIdDescription);
		lblIdDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdDescription.setForeground(new Color(128, 128, 128));
		lblIdDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		lblMaterialTypeError = new JLabel("Must not be empty!");
		lblMaterialTypeError.setBounds(105, 293, 140, 14);
		stoneUnitPane.add(lblMaterialTypeError);
		lblMaterialTypeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMaterialTypeError.setForeground(Color.RED);
		
		JLabel lblMoveToMaterialTypedDescription = new JLabel("MATERIAL");
		lblMoveToMaterialTypedDescription.setBounds(577, 162, 160, 27);
		stoneUnitPane.add(lblMoveToMaterialTypedDescription);
		lblMoveToMaterialTypedDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoveToMaterialTypedDescription.setForeground(new Color(128, 128, 128));
		lblMoveToMaterialTypedDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldOrigin = new JTextField();
		textFieldOrigin.setBounds(105, 264, 430, 34);
		stoneUnitPane.add(textFieldOrigin);
		textFieldOrigin.setText("ORIGIN");
		textFieldOrigin.setBorder(null);
		textFieldOrigin.setDisabledTextColor(new Color(192, 192, 192));
		textFieldOrigin.setBackground(new Color(255, 250, 250));
		textFieldOrigin.setForeground(new Color(47, 79, 79));
		textFieldOrigin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldOrigin.setEditable(false);
		
		JLabel lblOriginDescription = new JLabel("ORIGIN");
		lblOriginDescription.setBounds(574, 269, 70, 27);
		stoneUnitPane.add(lblOriginDescription);
		lblOriginDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblOriginDescription.setForeground(new Color(128, 128, 128));
		lblOriginDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldWidth = new JTextField();
		textFieldWidth.setBounds(105, 316, 430, 34);
		stoneUnitPane.add(textFieldWidth);
		textFieldWidth.setText("WIDTH");
		textFieldWidth.setBorder(null);
		textFieldWidth.setDisabledTextColor(new Color(192, 192, 192));
		textFieldWidth.setBackground(new Color(255, 250, 250));
		textFieldWidth.setForeground(new Color(47, 79, 79));
		textFieldWidth.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldWidth.setEditable(false);
		
		lblWidthError = new JLabel("Must be a positive number! (devided by dot)");
		lblWidthError.setBounds(105, 361, 337, 14);
		stoneUnitPane.add(lblWidthError);
		lblWidthError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWidthError.setForeground(Color.RED);
		
		JLabel lblWidthDescription = new JLabel("WIDTH (MM)");
		lblWidthDescription.setBounds(574, 321, 130, 27);
		stoneUnitPane.add(lblWidthDescription);
		lblWidthDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblWidthDescription.setForeground(new Color(128, 128, 128));
		lblWidthDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(105, 384, 430, 34);
		stoneUnitPane.add(textFieldWeight);
		textFieldWeight.setText("WEIGHT");
		textFieldWeight.setBorder(null);
		textFieldWeight.setDisabledTextColor(new Color(192, 192, 192));
		textFieldWeight.setBackground(new Color(255, 250, 250));
		textFieldWeight.setForeground(new Color(47, 79, 79));
		textFieldWeight.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldWeight.setEditable(false);
		
		lblWeightError = new JLabel("Must be a positive number! (devided by dot)");
		lblWeightError.setBounds(105, 429, 337, 14);
		lblWeightError.setVisible(false);
		stoneUnitPane.add(lblWeightError);
		lblWeightError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWeightError.setForeground(Color.RED);
		textFieldWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldWeight.getText());
					lblWeightError.setVisible(false);
					if(number < 0) 
						lblWeightError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblWeightError.setVisible(true);
				}
			}
		});
		lblWidthError.setVisible(false);
		textFieldWidth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldWidth.getText());
					lblWidthError.setVisible(false);
					if(number < 0)
						lblWidthError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblWidthError.setVisible(true);
				}
			}
		});
		isEditNotesPressed = false;
		
		JLabel lblWeightDescription = new JLabel("WEIGHT (KG)");
		lblWeightDescription.setBounds(574, 389, 130, 27);
		stoneUnitPane.add(lblWeightDescription);
		lblWeightDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeightDescription.setForeground(new Color(128, 128, 128));
		lblWeightDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldCreatedDate = new JTextField();
		textFieldCreatedDate.setBounds(108, 445, 430, 34);
		stoneUnitPane.add(textFieldCreatedDate);
		textFieldCreatedDate.setText("CREATED DATE");
		textFieldCreatedDate.setBorder(null);
		textFieldCreatedDate.setDisabledTextColor(new Color(192, 192, 192));
		textFieldCreatedDate.setBackground(new Color(255, 250, 250));
		textFieldCreatedDate.setForeground(new Color(47, 79, 79));
		textFieldCreatedDate.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldCreatedDate.setEditable(false);
		
		JLabel lblCreatedDateDescription = new JLabel("CREATED DATE");
		lblCreatedDateDescription.setBounds(574, 450, 160, 27);
		stoneUnitPane.add(lblCreatedDateDescription);
		lblCreatedDateDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreatedDateDescription.setForeground(new Color(128, 128, 128));
		lblCreatedDateDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(105, 490, 340, 34);
		stoneUnitPane.add(comboBoxStatus);
		comboBoxStatus.setFocusable(false);
		comboBoxStatus.setModel(new DefaultComboBoxModel(StoneUnitStatuses.values()));
		comboBoxStatus.setBorder(null);
		comboBoxStatus.setBackground(Color.WHITE);
		comboBoxStatus.setForeground(new Color(192, 176, 131));
		comboBoxStatus.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboBoxStatus.hidePopup();
		comboBoxStatus.setEnabled(false);
		
		JLabel lblStatusDescription = new JLabel("STATUS");
		lblStatusDescription.setBounds(574, 495, 80, 27);
		stoneUnitPane.add(lblStatusDescription);
		lblStatusDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatusDescription.setForeground(new Color(128, 128, 128));
		lblStatusDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(108, 535, 430, 34);
		stoneUnitPane.add(textFieldDescription);
		textFieldDescription.setText("DESCRIPTION");
		textFieldDescription.setBorder(null);
		textFieldDescription.setDisabledTextColor(new Color(192, 192, 192));
		textFieldDescription.setBackground(new Color(255, 250, 250));
		textFieldDescription.setForeground(new Color(47, 79, 79));
		textFieldDescription.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldDescription.setEditable(false);
		
		JLabel lblDescriptionDescription = new JLabel("DESCRIPTION");
		lblDescriptionDescription.setBounds(574, 540, 130, 27);
		stoneUnitPane.add(lblDescriptionDescription);
		lblDescriptionDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionDescription.setForeground(new Color(128, 128, 128));
		lblDescriptionDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldLocation = new JTextField();
		textFieldLocation.setBounds(760, 112, 395, 34);
		stoneUnitPane.add(textFieldLocation);
		textFieldLocation.setText("LOCATION");
		textFieldLocation.setBorder(null);
		textFieldLocation.setDisabledTextColor(new Color(192, 192, 192));
		textFieldLocation.setBackground(new Color(255, 250, 250));
		textFieldLocation.setForeground(new Color(47, 79, 79));
		textFieldLocation.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldLocation.setEditable(false);
		
		JLabel lblLocationDescription = new JLabel("LOCATION");
		lblLocationDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationDescription.setBounds(1165, 117, 100, 27);
		stoneUnitPane.add(lblLocationDescription);
		lblLocationDescription.setForeground(new Color(128, 128, 128));
		lblLocationDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldLocationAddress = new JTextField();
		textFieldLocationAddress.setBounds(758, 157, 395, 34);
		stoneUnitPane.add(textFieldLocationAddress);
		textFieldLocationAddress.setText("ADDRESS");
		textFieldLocationAddress.setBorder(null);
		textFieldLocationAddress.setDisabledTextColor(new Color(192, 192, 192));
		textFieldLocationAddress.setBackground(new Color(255, 250, 250));
		textFieldLocationAddress.setForeground(new Color(47, 79, 79));
		textFieldLocationAddress.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldLocationAddress.setEditable(false);
		
		JLabel lblLocationAddressDescription = new JLabel("ADDRESS");
		lblLocationAddressDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationAddressDescription.setBounds(1175, 201, 90, 27);
		stoneUnitPane.add(lblLocationAddressDescription);
		lblLocationAddressDescription.setForeground(new Color(128, 128, 128));
		lblLocationAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldLocationCity = new JTextField();
		textFieldLocationCity.setBounds(758, 202, 395, 34);
		stoneUnitPane.add(textFieldLocationCity);
		textFieldLocationCity.setText("CITY");
		textFieldLocationCity.setBorder(null);
		textFieldLocationCity.setDisabledTextColor(new Color(192, 192, 192));
		textFieldLocationCity.setBackground(new Color(255, 250, 250));
		textFieldLocationCity.setForeground(new Color(47, 79, 79));
		textFieldLocationCity.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldLocationCity.setEditable(false);
		
		JLabel lblLocationCityDescription = new JLabel("CITY");
		lblLocationCityDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationCityDescription.setBounds(1221, 253, 44, 27);
		stoneUnitPane.add(lblLocationCityDescription);
		lblLocationCityDescription.setForeground(new Color(128, 128, 128));
		lblLocationCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblSupplier = new JLabel("SUPPLIER(S)");
		lblSupplier.setBounds(758, 248, 235, 34);
		stoneUnitPane.add(lblSupplier);
		lblSupplier.setForeground(new Color(47, 79, 79));
		lblSupplier.setFont(new Font("Segoe UI", Font.BOLD, 25));
		
		lblSupplierError = new JLabel("Must not be empty!");
		lblSupplierError.setBounds(758, 279, 140, 14);
		stoneUnitPane.add(lblSupplierError);
		lblSupplierError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSupplierError.setForeground(Color.RED);
		
		JLabel lblMoveToSupplier = new JLabel("");
		lblMoveToSupplier.setBounds(948, 257, 25, 25);
		stoneUnitPane.add(lblMoveToSupplier);
		lblMoveToSupplier.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		
		JLabel lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setBounds(1030, 248, 200, 34);
		stoneUnitPane.add(lblEmployee);
		lblEmployee.setForeground(new Color(47, 79, 79));
		lblEmployee.setFont(new Font("Segoe UI", Font.BOLD, 25));
		
		lblEmployeeError = new JLabel("Must not be empty!");
		lblEmployeeError.setBounds(1040, 279, 140, 14);
		stoneUnitPane.add(lblEmployeeError);
		lblEmployeeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmployeeError.setForeground(Color.RED);
		
		JLabel lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.setBounds(1175, 257, 25, 25);
		stoneUnitPane.add(lblMoveToEmployee);
		lblMoveToEmployee.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		
		JTextField lblUpdates = new JTextField();
		lblUpdates.setBounds(758, 316, 180, 34);
		stoneUnitPane.add(lblUpdates);
		lblUpdates.setText("UPDATES");
		lblUpdates.setBorder(null);
		lblUpdates.setDisabledTextColor(new Color(192, 192, 192));
		lblUpdates.setBackground(new Color(255, 250, 250));
		lblUpdates.setForeground(new Color(47, 79, 79));
		lblUpdates.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblUpdates.setEditable(false);
		
		JLabel lblUpdateConfirmIcon = new JLabel("");
		lblUpdateConfirmIcon.setBounds(948, 321, 25, 25);
		stoneUnitPane.add(lblUpdateConfirmIcon);
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
		
		lblStornoSmall = new JLabel("");
		lblStornoSmall.setBounds(983, 321, 27, 25);
		lblStornoSmall.setVisible(false);
		stoneUnitPane.add(lblStornoSmall);
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
		
		JScrollPane updatesScrollPane = new JScrollPane();
		updatesScrollPane.setBounds(760, 361, 505, 212);
		stoneUnitPane.add(updatesScrollPane);
		updatesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		updatesScrollPane.setBorder(null);
		updatesScrollPane.getVerticalScrollBar().setUnitIncrement(4);
		
		textAreaUpdates = new JTextArea();
		textAreaUpdates.setLineWrap(true);
		textAreaUpdates.setWrapStyleWord(true);
		textAreaUpdates.setText("12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created\r\n\r\n12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created\r\n\r\n12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created");
		textAreaUpdates.setForeground(new Color(192, 176, 131));
		textAreaUpdates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaUpdates.setEditable(false);
		updatesScrollPane.setColumnHeaderView(textAreaUpdates);
		textAreaUpdates.setCaretPosition(0);
		
		JComboBox comboMaterials = new JComboBox();
		comboMaterials.setModel(new DefaultComboBoxModel(new String[] {"Material"}));
		comboMaterials.setForeground(new Color(192, 176, 131));
		comboMaterials.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboMaterials.setFocusable(false);
		comboMaterials.setEnabled(false);
		comboMaterials.setEditable(false);
		comboMaterials.setBorder(null);
		comboMaterials.setBackground(Color.WHITE);
		comboMaterials.setBounds(105, 157, 340, 34);
		stoneUnitPane.add(comboMaterials);
		
		JComboBox comboTypes = new JComboBox();
		comboTypes.setModel(new DefaultComboBoxModel(new String[] {"Types"}));
		comboTypes.setForeground(new Color(192, 176, 131));
		comboTypes.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboTypes.setFocusable(false);
		comboTypes.setEnabled(false);
		comboTypes.setEditable(false);
		comboTypes.setBorder(null);
		comboTypes.setBackground(Color.WHITE);
		comboTypes.setBounds(107, 208, 340, 34);
		stoneUnitPane.add(comboTypes);
		
		JLabel lblType = new JLabel("TYPE");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblType.setBounds(574, 215, 160, 27);
		stoneUnitPane.add(lblType);
		
//TREE PANE		
		JPanel treePane = new JPanel();
		treePane.setBackground(Color.WHITE);
		cardPane.add(treePane, "name_105672345315800");
		treePane.setLayout(null);
		
//TREE CONTENT		
		JLabel lblUnitTreeTitle = new JLabel("UNIT TREE");
		lblUnitTreeTitle.setForeground(new Color(144,124,81));
		lblUnitTreeTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblUnitTreeTitle.setBounds(105, 0, 350, 100);
		treePane.add(lblUnitTreeTitle);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardPane, "name_105638542096500");
			}
		});
		lblBack.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/backButton2.png")));
		lblBack.setBounds(30, 27, 50, 50);
		treePane.add(lblBack);
		
		JLabel lblTreeTitle = new JLabel("");
		lblTreeTitle.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/windowTitleBar.png")));
		lblTreeTitle.setBounds(0, 0, 1280, 100);
		treePane.add(lblTreeTitle);
		
		
		loadStoneUnit(id);
	}
		
	private boolean haveErrors() {
		return lblSupplierError.isVisible() || lblWidthError.isVisible() || lblWeightError.isVisible() || lblEmployeeError.isVisible();
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
	
	private void loadStoneUnit(int id) {

		Thread thread = new Thread() {
			public void run() {
				try {
					StoneController sctrl = new StoneController();


					try {
						StoneUnit stoneUnit = (StoneUnit)sctrl.getStoneUnitByID(id);
						textFieldId.setText(stoneUnit.getId()+"");
						textFieldOrigin.setText(stoneUnit.getOrigin());
						comboBoxStatus.setSelectedIndex(stoneUnit.getStatus().getID());
						textFieldCreatedDate.setText(stoneUnit.getCreatedDate().toString());
						textFieldWidth.setText(stoneUnit.getWidth()+"");
						textFieldWeight.setText(stoneUnit.getWeight()+"");
						textFieldDescription.setText(stoneUnit.getDescription());
						textFieldLocation.setText(stoneUnit.getLocation().getLocationName());
						textFieldLocationAddress.setText(stoneUnit.getLocation().getAddress());
						textFieldLocationCity.setText(stoneUnit.getLocation().getCity().getCityName());
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}


				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
		};
		thread.start();

	}
}
