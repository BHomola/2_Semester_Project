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
	private JLabel lblOrderPriceError;
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
		lblEditCheck.setBounds(998, 25, 50, 50);
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
		
		
		
		textFieldId = new JTextField();
		textFieldId.setBounds(108, 112, 430, 53);
		stoneUnitPane.add(textFieldId);
		textFieldId.setText("ID");
		textFieldId.setBorder(null);
		textFieldId.setDisabledTextColor(Color.WHITE);
		textFieldId.setBackground(Color.WHITE);
		textFieldId.setForeground(new Color(192, 176, 131));
		textFieldId.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldId.setEditable(false);
		
		JLabel lblIdDescription = new JLabel("ID");
		lblIdDescription.setBounds(712, 133, 22, 27);
		stoneUnitPane.add(lblIdDescription);
		lblIdDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdDescription.setForeground(new Color(255, 238, 202));
		lblIdDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblMaterialType = new JLabel("MATERIAL TYPE");
		lblMaterialType.setBounds(108, 180, 430, 53);
		stoneUnitPane.add(lblMaterialType);
		lblMaterialType.setForeground(new Color(192, 176, 131));
		lblMaterialType.setFont(new Font("Segoe UI", Font.BOLD, 40));
		
		lblMaterialTypeError = new JLabel("Must not be empty!");
		lblMaterialTypeError.setBounds(110, 233, 140, 14);
		stoneUnitPane.add(lblMaterialTypeError);
		lblMaterialTypeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMaterialTypeError.setForeground(Color.RED);
		
		JLabel lblMoveToMaterialType = new JLabel("");
		lblMoveToMaterialType.setBounds(422, 197, 25, 25);
		stoneUnitPane.add(lblMoveToMaterialType);
		lblMoveToMaterialType.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		
		JLabel lblMoveToMaterialTypedDescription = new JLabel("MATERIAL TYPE");
		lblMoveToMaterialTypedDescription.setBounds(574, 201, 160, 27);
		stoneUnitPane.add(lblMoveToMaterialTypedDescription);
		lblMoveToMaterialTypedDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoveToMaterialTypedDescription.setForeground(new Color(255, 238, 202));
		lblMoveToMaterialTypedDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldOrigin = new JTextField();
		textFieldOrigin.setBounds(108, 248, 430, 53);
		stoneUnitPane.add(textFieldOrigin);
		textFieldOrigin.setText("ORIGIN");
		textFieldOrigin.setBorder(null);
		textFieldOrigin.setDisabledTextColor(Color.WHITE);
		textFieldOrigin.setBackground(Color.WHITE);
		textFieldOrigin.setForeground(new Color(192, 176, 131));
		textFieldOrigin.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldOrigin.setEditable(false);
		
		JLabel lblOriginDescription = new JLabel("ORIGIN");
		lblOriginDescription.setBounds(664, 269, 70, 27);
		stoneUnitPane.add(lblOriginDescription);
		lblOriginDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOriginDescription.setForeground(new Color(255, 238, 202));
		lblOriginDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldWidth = new JTextField();
		textFieldWidth.setBounds(108, 316, 430, 53);
		stoneUnitPane.add(textFieldWidth);
		textFieldWidth.setText("WIDTH");
		textFieldWidth.setBorder(null);
		textFieldWidth.setDisabledTextColor(Color.WHITE);
		textFieldWidth.setBackground(Color.WHITE);
		textFieldWidth.setForeground(new Color(192, 176, 131));
		textFieldWidth.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldWidth.setEditable(false);
		
		lblOrderPriceError = new JLabel("Must be a positive number! (devided by dot)");
		lblOrderPriceError.setBounds(110, 369, 337, 14);
		stoneUnitPane.add(lblOrderPriceError);
		lblOrderPriceError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblOrderPriceError.setForeground(Color.RED);
		
		JLabel lblWidthDescription = new JLabel("WIDTH (MM)");
		lblWidthDescription.setBounds(604, 337, 130, 27);
		stoneUnitPane.add(lblWidthDescription);
		lblWidthDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidthDescription.setForeground(new Color(255, 238, 202));
		lblWidthDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(108, 384, 430, 53);
		stoneUnitPane.add(textFieldWeight);
		textFieldWeight.setText("WEIGHT");
		textFieldWeight.setBorder(null);
		textFieldWeight.setDisabledTextColor(Color.WHITE);
		textFieldWeight.setBackground(Color.WHITE);
		textFieldWeight.setForeground(new Color(192, 176, 131));
		textFieldWeight.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldWeight.setEditable(false);
		
		lblWeightError = new JLabel("Must be a positive number! (devided by dot)");
		lblWeightError.setBounds(110, 437, 337, 14);
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
		lblOrderPriceError.setVisible(false);
		textFieldWidth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double number = Double.parseDouble(textFieldWidth.getText());
					lblOrderPriceError.setVisible(false);
					if(number < 0)
						lblOrderPriceError.setVisible(true);
				} catch(NumberFormatException ex) {
					lblOrderPriceError.setVisible(true);
				}
			}
		});
		isEditNotesPressed = false;
		
		JLabel lblWeightDescription = new JLabel("WEIGHT (KG)");
		lblWeightDescription.setBounds(604, 405, 130, 27);
		stoneUnitPane.add(lblWeightDescription);
		lblWeightDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeightDescription.setForeground(new Color(255, 238, 202));
		lblWeightDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldCreatedDate = new JTextField();
		textFieldCreatedDate.setBounds(108, 452, 430, 53);
		stoneUnitPane.add(textFieldCreatedDate);
		textFieldCreatedDate.setText("CREATED DATE");
		textFieldCreatedDate.setBorder(null);
		textFieldCreatedDate.setDisabledTextColor(Color.WHITE);
		textFieldCreatedDate.setBackground(Color.WHITE);
		textFieldCreatedDate.setForeground(new Color(192, 176, 131));
		textFieldCreatedDate.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldCreatedDate.setEditable(false);
		
		JLabel lblCreatedDateDescription = new JLabel("CREATED DATE");
		lblCreatedDateDescription.setBounds(574, 473, 160, 27);
		stoneUnitPane.add(lblCreatedDateDescription);
		lblCreatedDateDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreatedDateDescription.setForeground(new Color(255, 238, 202));
		lblCreatedDateDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(108, 520, 340, 53);
		stoneUnitPane.add(comboBoxStatus);
		comboBoxStatus.setFocusable(false);
		comboBoxStatus.setModel(new DefaultComboBoxModel(StoneUnitStatuses.values()));
		comboBoxStatus.setBorder(null);
		comboBoxStatus.setBackground(Color.WHITE);
		comboBoxStatus.setForeground(new Color(192, 176, 131));
		comboBoxStatus.setFont(new Font("Segoe UI", Font.BOLD, 40));
		comboBoxStatus.hidePopup();
		comboBoxStatus.setEditable(false);
		comboBoxStatus.setEnabled(false);
		
		JLabel lblStatusDescription = new JLabel("STATUS");
		lblStatusDescription.setBounds(654, 541, 80, 27);
		stoneUnitPane.add(lblStatusDescription);
		lblStatusDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusDescription.setForeground(new Color(255, 238, 202));
		lblStatusDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(108, 588, 430, 53);
		stoneUnitPane.add(textFieldDescription);
		textFieldDescription.setText("DESCRIPTION");
		textFieldDescription.setBorder(null);
		textFieldDescription.setDisabledTextColor(Color.WHITE);
		textFieldDescription.setBackground(Color.WHITE);
		textFieldDescription.setForeground(new Color(192, 176, 131));
		textFieldDescription.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldDescription.setEditable(false);
		
		JLabel lblDescriptionDescription = new JLabel("DESCRIPTION");
		lblDescriptionDescription.setBounds(604, 609, 130, 27);
		stoneUnitPane.add(lblDescriptionDescription);
		lblDescriptionDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescriptionDescription.setForeground(new Color(255, 238, 202));
		lblDescriptionDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldLocation = new JTextField();
		textFieldLocation.setBounds(760, 112, 395, 53);
		stoneUnitPane.add(textFieldLocation);
		textFieldLocation.setText("LOCATION");
		textFieldLocation.setBorder(null);
		textFieldLocation.setDisabledTextColor(Color.WHITE);
		textFieldLocation.setBackground(Color.WHITE);
		textFieldLocation.setForeground(new Color(192, 176, 131));
		textFieldLocation.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocation.setEditable(false);
		
		JLabel lblLocationDescription = new JLabel("LOCATION");
		lblLocationDescription.setBounds(1165, 133, 100, 27);
		stoneUnitPane.add(lblLocationDescription);
		lblLocationDescription.setForeground(new Color(255, 238, 202));
		lblLocationDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldLocationAddress = new JTextField();
		textFieldLocationAddress.setBounds(760, 180, 395, 53);
		stoneUnitPane.add(textFieldLocationAddress);
		textFieldLocationAddress.setText("ADDRESS");
		textFieldLocationAddress.setBorder(null);
		textFieldLocationAddress.setDisabledTextColor(Color.WHITE);
		textFieldLocationAddress.setBackground(Color.WHITE);
		textFieldLocationAddress.setForeground(new Color(192, 176, 131));
		textFieldLocationAddress.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocationAddress.setEditable(false);
		
		JLabel lblLocationAddressDescription = new JLabel("ADDRESS");
		lblLocationAddressDescription.setBounds(1175, 201, 90, 27);
		stoneUnitPane.add(lblLocationAddressDescription);
		lblLocationAddressDescription.setForeground(new Color(255, 238, 202));
		lblLocationAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		textFieldLocationCity = new JTextField();
		textFieldLocationCity.setBounds(760, 248, 395, 53);
		stoneUnitPane.add(textFieldLocationCity);
		textFieldLocationCity.setText("CITY");
		textFieldLocationCity.setBorder(null);
		textFieldLocationCity.setDisabledTextColor(Color.WHITE);
		textFieldLocationCity.setBackground(Color.WHITE);
		textFieldLocationCity.setForeground(new Color(192, 176, 131));
		textFieldLocationCity.setFont(new Font("Segoe UI", Font.BOLD, 40));
		textFieldLocationCity.setEditable(false);
		
		JLabel lblLocationCityDescription = new JLabel("CITY");
		lblLocationCityDescription.setBounds(1221, 269, 44, 27);
		stoneUnitPane.add(lblLocationCityDescription);
		lblLocationCityDescription.setForeground(new Color(255, 238, 202));
		lblLocationCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblSupplier = new JLabel("SUPPLIER");
		lblSupplier.setBounds(760, 316, 182, 53);
		stoneUnitPane.add(lblSupplier);
		lblSupplier.setForeground(new Color(192, 176, 131));
		lblSupplier.setFont(new Font("Segoe UI", Font.BOLD, 40));
		
		lblSupplierError = new JLabel("Must not be empty!");
		lblSupplierError.setBounds(762, 369, 140, 14);
		stoneUnitPane.add(lblSupplierError);
		lblSupplierError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSupplierError.setForeground(Color.RED);
		
		JLabel lblMoveToSupplier = new JLabel("");
		lblMoveToSupplier.setBounds(953, 333, 25, 25);
		stoneUnitPane.add(lblMoveToSupplier);
		lblMoveToSupplier.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		
		JLabel lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setBounds(1028, 316, 200, 53);
		stoneUnitPane.add(lblEmployee);
		lblEmployee.setForeground(new Color(192, 176, 131));
		lblEmployee.setFont(new Font("Segoe UI", Font.BOLD, 40));
		
		lblEmployeeError = new JLabel("Must not be empty!");
		lblEmployeeError.setBounds(1030, 369, 140, 14);
		stoneUnitPane.add(lblEmployeeError);
		lblEmployeeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmployeeError.setForeground(Color.RED);
		
		JLabel lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.setBounds(1240, 333, 25, 25);
		stoneUnitPane.add(lblMoveToEmployee);
		lblMoveToEmployee.setIcon(new ImageIcon(StoneUnitWindow.class.getResource("/imgs/moveto2.png")));
		
		JTextField lblUpdates = new JTextField();
		lblUpdates.setBounds(760, 384, 180, 53);
		stoneUnitPane.add(lblUpdates);
		lblUpdates.setText("UPDATES");
		lblUpdates.setBorder(null);
		lblUpdates.setDisabledTextColor(Color.WHITE);
		lblUpdates.setBackground(Color.WHITE);
		lblUpdates.setForeground(new Color(192, 176, 131));
		lblUpdates.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblUpdates.setEditable(false);
		
		JLabel lblUpdateConfirmIcon = new JLabel("");
		lblUpdateConfirmIcon.setBounds(948, 400, 25, 25);
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
		lblStornoSmall.setBounds(978, 400, 27, 25);
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
		updatesScrollPane.setBounds(760, 452, 505, 184);
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
		updatesScrollPane.setViewportView(textAreaUpdates);
		textAreaUpdates.setCaretPosition(0);
		
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
	}
		
	private boolean haveErrors() {
		return lblSupplierError.isVisible() || lblOrderPriceError.isVisible() || lblWeightError.isVisible() || lblEmployeeError.isVisible();
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
