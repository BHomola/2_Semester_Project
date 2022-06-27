package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.PersonController;
import controller.StoneController;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import model.Employee;
import model.IStoneUnit;
import model.Location;
import model.OrderInfo;
import model.Shape;
import model.StoneMaterial;
import model.StoneProduct;
import model.StoneType;
import model.StoneUnit;
import model.StoneUnitStatuses;
import model.Supplier;

import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import java.awt.Dimension;

public class StoneUnitProductWindow extends JFrame implements IShapeSave{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblDeleteStorno;
	private JLabel lblEditCheck;
	private JLabel lblMaximizeRestore;
	private JTextArea textAreaUpdates;
	private boolean isEditNotesPressed;
	private boolean isEditPressed;
	private boolean isMaximizePressed;
	private int x;
	private int y;
	private JTextField textFieldLocationCity;
	private JTextField textFieldLocationAddress;
	private JTextField textFieldDescription;
	private JTextField textFieldWeight;
	private JTextField textFieldWidth;
	private JTextField textFieldCreatedDate;
	private JTextField textFieldOrigin;
	private JTextField textFieldId;
	private JComboBox<?> comboBoxStatus;
	private JLabel lblWidthError;
	private JLabel lblWeightError;
	private CardLayout cardLayout;
	private JPanel cardPane;
	JTextField textFieldPrice;
	JLabel lblPriceError;
	JPanel UI_Blocker;
	JPanel stoneUnitPane;
	JComboBox<StoneType> comboTypes;
	JComboBox<StoneMaterial> comboMaterials;
	JComboBox<Location> comboLocations;
	JLabel lblSupplier;
	JLabel lblEmployee;
	JLabel lblSupplierError;
	JLabel lblEmployeeError;
	JButton btnChangeSupplier;
	JButton btnToday;
	JButton btnChangeEmployee;
	JLabel lblMoveToSupplier;
	JLabel lblMoveToEmployee;
	JLabel lblOriginError;
	JLabel lblDateError;
	JLabel lblUpdateConfirmIcon;
	JButton btnChangeOrderID;
	JLabel lblOrderError;
	private JTextField txtTotalSize;
	private JLabel lblOrderIDDescription;
	JLabel lblOrderID;
	JButton btnChangeShape;
	JLabel lblMoveToShape;

	private StoneProduct cachedStoneProduct;
	private JLabel lblShape;
	private JLabel lblShapeError;
	
	private IShapeSave stoneSave;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StoneUnitProductWindow(int id) {
		stoneSave = this;
		cardLayout = new CardLayout();

//FRAME		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/imgs/logo4.png")));
		setTitle("Santorina");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(320, 130, 1280, 900);

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
				if (isMaximizePressed) {
					checkMaximizeRestore();
				}
			}
		});
		titleBarPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				setLocation(xx - x, yy - y);
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
		cardPane.setBounds(0, 60, 1280, 900);
		contentPane.add(cardPane);
		cardPane.setLayout(cardLayout);

//UI BLOCKER
		UI_Blocker = new JPanel();
		UI_Blocker.setLayout(null);
		UI_Blocker.setBackground(Color.WHITE);
		cardPane.add(UI_Blocker, "name_498834100950500");

		JLabel lblLoadingProduct = new JLabel("Loading Stone Product...");
		lblLoadingProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadingProduct.setForeground(new Color(144, 124, 81));
		lblLoadingProduct.setFont(new Font("Segoe UI", Font.BOLD, 70));
		lblLoadingProduct.setBounds(0, 0, 1280, 720);
		UI_Blocker.add(lblLoadingProduct);
//STONE UNIT PANE		
		stoneUnitPane = new JPanel();
		stoneUnitPane.setBackground(Color.WHITE);
		cardPane.add(stoneUnitPane, "name_105638542096500");
		stoneUnitPane.setLayout(null);

//TITLE		
		JLabel lblTitle = new JLabel("Stone Product");
		lblTitle.setBounds(105, 0, 580, 94);
		stoneUnitPane.add(lblTitle);
		lblTitle.setForeground(new Color(144, 124, 81));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));

		lblEditCheck = new JLabel("");
		lblEditCheck.setBounds(1000, 25, 50, 50);
		stoneUnitPane.add(lblEditCheck);
		lblEditCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/confirm1.png")));
					lblDeleteStorno.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/storno.png")));
					isEditPressed = true;
					switchEditable();
					textFieldOrigin.grabFocus();
				} else {
					if (haveErrors()) {
						JOptionPane.showMessageDialog(null, "Check Errors!", "ERROR!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					int answer = JOptionPane.showConfirmDialog(null, "Do you really want to apply changes?",
							"Are you sure?", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (answer) {
					case 0:
						// System.out.println("yes");
						// SAVE CHANGES
						try {
							saveStoneUnit();
							Main.getInstance().updateInventory();

						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Something went wrong. :(", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 1:
						// System.out.println("no");
						// ABORT CHANGES
						break;
					case 2:
						return;
					}
					lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/editButton2.png")));
					lblDeleteStorno.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/deleteButton.png")));
					contentPane.grabFocus();
					isEditPressed = false;
					switchEditable();

				}
			}
		});
		lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/editButton2.png")));

		lblDeleteStorno = new JLabel("");
		lblDeleteStorno.setBounds(1060, 25, 50, 50);
		stoneUnitPane.add(lblDeleteStorno);
		lblDeleteStorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isEditPressed) {
					lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/editButton2.png")));
					lblDeleteStorno.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/deleteButton.png")));
					contentPane.grabFocus();
					isEditPressed = false;
					switchEditable();
					UI_Blocker.setVisible(true);
					stoneUnitPane.setVisible(false);
					loadStoneUnit(id);
				} else {
					int answer = JOptionPane.showConfirmDialog(null,
							"Do you really want to delete stone with ID " + cachedStoneProduct.getId(), "Are you sure?",
							JOptionPane.YES_NO_OPTION);
					if (answer == 0) {
						// delete
						StoneController sctrl = new StoneController();
						try {
							sctrl.deleteStone(cachedStoneProduct.getId());
							Main.getInstance().updateInventory();
							JOptionPane.showMessageDialog(null, "Stone has been successfully deleted.");
							setVisible(false);
							dispose();
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Something went wrong. :(", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}
			}
		});
		lblDeleteStorno.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/deleteButton.png")));

		JLabel lblWindowOrderBar = new JLabel("");
		lblWindowOrderBar.setBounds(0, 0, 1280, 100);
		stoneUnitPane.add(lblWindowOrderBar);
		lblWindowOrderBar.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/windowTitleBar.png")));

		JLabel lblSplitLine = new JLabel("");
		lblSplitLine.setBounds(747, 140, 1, 500);
		stoneUnitPane.add(lblSplitLine);
		lblSplitLine.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/splitLine.png")));

//CONTENT	
		textFieldId = new JTextField("ID");
		textFieldId.setEnabled(false);
		textFieldId.setBounds(108, 112, 337, 34);
		stoneUnitPane.add(textFieldId);
		textFieldId.setBorder(null);
		textFieldId.setDisabledTextColor(Color.DARK_GRAY);
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

		JLabel lblMoveToMaterialTypedDescription = new JLabel("MATERIAL");
		lblMoveToMaterialTypedDescription.setBounds(574, 179, 160, 27);
		stoneUnitPane.add(lblMoveToMaterialTypedDescription);
		lblMoveToMaterialTypedDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoveToMaterialTypedDescription.setForeground(new Color(128, 128, 128));
		lblMoveToMaterialTypedDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		textFieldOrigin = new JTextField();
		textFieldOrigin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldOrigin.getText().length() > 0) {
					cachedStoneProduct.setOrigin(textFieldOrigin.getText());
					lblOriginError.setVisible(false);
				} else {
					lblOriginError.setVisible(true);
				}
			}
		});
		textFieldOrigin.setEnabled(false);
		textFieldOrigin.setBounds(105, 264, 340, 34);
		stoneUnitPane.add(textFieldOrigin);
		textFieldOrigin.setText("ORIGIN");
		textFieldOrigin.setBorder(null);
		textFieldOrigin.setDisabledTextColor(Color.DARK_GRAY);
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
		textFieldWidth.setEnabled(false);
		textFieldWidth.setBounds(105, 316, 340, 34);
		stoneUnitPane.add(textFieldWidth);
		textFieldWidth.setText("WIDTH");
		textFieldWidth.setBorder(null);
		textFieldWidth.setDisabledTextColor(Color.DARK_GRAY);
		textFieldWidth.setBackground(new Color(255, 250, 250));
		textFieldWidth.setForeground(new Color(47, 79, 79));
		textFieldWidth.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldWidth.setEditable(false);

		lblWidthError = new JLabel("Must be a positive number! (devided by dot)");
		lblWidthError.setBounds(105, 361, 337, 14);
		stoneUnitPane.add(lblWidthError);
		lblWidthError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWidthError.setForeground(Color.RED);

		lblOriginError = new JLabel("Cannot be empty!");
		lblOriginError.setBounds(105, 302, 337, 14);
		stoneUnitPane.add(lblOriginError);
		lblOriginError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblOriginError.setForeground(Color.RED);

		JLabel lblWidthDescription = new JLabel("WIDTH (MM)");
		lblWidthDescription.setBounds(574, 321, 130, 27);
		stoneUnitPane.add(lblWidthDescription);
		lblWidthDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblWidthDescription.setForeground(new Color(128, 128, 128));
		lblWidthDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		textFieldWeight = new JTextField();
		textFieldWeight.setEnabled(false);
		textFieldWeight.setBounds(105, 384, 340, 34);
		stoneUnitPane.add(textFieldWeight);
		textFieldWeight.setText("WEIGHT");
		textFieldWeight.setBorder(null);
		textFieldWeight.setDisabledTextColor(Color.DARK_GRAY);
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
					if (number < 0)
						lblWeightError.setVisible(true);
					else {
						cachedStoneProduct.setWeight(number);
					}
				} catch (NumberFormatException ex) {
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
					if (number < 0)
						lblWidthError.setVisible(true);
					else
						cachedStoneProduct.setWidth(number);
				} catch (NumberFormatException ex) {
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
		textFieldCreatedDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String sDate1 = textFieldCreatedDate.getText();
					java.sql.Date date1 = java.sql.Date.valueOf(sDate1);
					cachedStoneProduct.setCreatedDate(date1);
					lblDateError.setVisible(false);
				} catch (Exception ex) {
					// lblWidthError.setVisible(true);
					// System.out.println("invalid Date format. use yyyy-mm-dd");
					lblDateError.setVisible(true);
				}
			}
		});
		textFieldCreatedDate.setEnabled(false);
		textFieldCreatedDate.setBounds(108, 445, 337, 34);
		stoneUnitPane.add(textFieldCreatedDate);
		textFieldCreatedDate.setText("CREATED DATE");
		textFieldCreatedDate.setBorder(null);
		textFieldCreatedDate.setDisabledTextColor(Color.DARK_GRAY);
		textFieldCreatedDate.setBackground(new Color(255, 250, 250));
		textFieldCreatedDate.setForeground(new Color(47, 79, 79));
		textFieldCreatedDate.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldCreatedDate.setEditable(false);

		lblDateError = new JLabel("Invalid format. Use yyyy-mm-dd");
		lblDateError.setBounds(105, 475, 337, 14);
		lblDateError.setVisible(false);
		stoneUnitPane.add(lblDateError);
		lblDateError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblDateError.setForeground(Color.RED);

		JLabel lblCreatedDateDescription = new JLabel("CREATED DATE");
		lblCreatedDateDescription.setBounds(574, 450, 160, 27);
		stoneUnitPane.add(lblCreatedDateDescription);
		lblCreatedDateDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreatedDateDescription.setForeground(new Color(128, 128, 128));
		lblCreatedDateDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		comboBoxStatus = new JComboBox();
		comboBoxStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cachedStoneProduct != null) {
					cachedStoneProduct.setStatus((StoneUnitStatuses) comboBoxStatus.getSelectedItem());
				}
			}
		});
		comboBoxStatus.setEnabled(false);
		comboBoxStatus.setBounds(105, 490, 340, 34);
		stoneUnitPane.add(comboBoxStatus);
		comboBoxStatus.setFocusable(false);
		comboBoxStatus.setModel(new DefaultComboBoxModel(StoneUnitStatuses.values()));
		comboBoxStatus.setBorder(null);
		comboBoxStatus.setBackground(Color.WHITE);
		comboBoxStatus.setForeground(Color.DARK_GRAY);
		comboBoxStatus.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboBoxStatus.hidePopup();

		JLabel lblStatusDescription = new JLabel("STATUS");
		lblStatusDescription.setBounds(574, 495, 80, 27);
		stoneUnitPane.add(lblStatusDescription);
		lblStatusDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatusDescription.setForeground(new Color(128, 128, 128));
		lblStatusDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		textFieldDescription = new JTextField();
		textFieldDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cachedStoneProduct.setDescription(textFieldDescription.getText());
			}
		});
		textFieldDescription.setEnabled(false);
		textFieldDescription.setBounds(108, 535, 337, 34);
		stoneUnitPane.add(textFieldDescription);
		textFieldDescription.setText("DESCRIPTION");
		textFieldDescription.setBorder(null);
		textFieldDescription.setDisabledTextColor(Color.DARK_GRAY);
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

		JLabel lblLocationDescription = new JLabel("LOCATION");
		lblLocationDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationDescription.setBounds(1175, 117, 100, 27);
		stoneUnitPane.add(lblLocationDescription);
		lblLocationDescription.setForeground(new Color(128, 128, 128));
		lblLocationDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		textFieldLocationAddress = new JTextField();
		textFieldLocationAddress.setEnabled(false);
		textFieldLocationAddress.setBounds(758, 157, 395, 34);
		stoneUnitPane.add(textFieldLocationAddress);
		textFieldLocationAddress.setText("ADDRESS");
		textFieldLocationAddress.setBorder(null);
		textFieldLocationAddress.setDisabledTextColor(Color.DARK_GRAY);
		textFieldLocationAddress.setBackground(new Color(255, 250, 250));
		textFieldLocationAddress.setForeground(new Color(47, 79, 79));
		textFieldLocationAddress.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldLocationAddress.setEditable(false);

		JLabel lblLocationAddressDescription = new JLabel("ADDRESS");
		lblLocationAddressDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationAddressDescription.setBounds(1175, 162, 90, 27);
		stoneUnitPane.add(lblLocationAddressDescription);
		lblLocationAddressDescription.setForeground(new Color(128, 128, 128));
		lblLocationAddressDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		textFieldLocationCity = new JTextField();
		textFieldLocationCity.setEnabled(false);
		textFieldLocationCity.setBounds(758, 202, 395, 34);
		stoneUnitPane.add(textFieldLocationCity);
		textFieldLocationCity.setText("CITY");
		textFieldLocationCity.setBorder(null);
		textFieldLocationCity.setDisabledTextColor(Color.DARK_GRAY);
		textFieldLocationCity.setBackground(new Color(255, 250, 250));
		textFieldLocationCity.setForeground(new Color(47, 79, 79));
		textFieldLocationCity.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldLocationCity.setEditable(false);

		JLabel lblLocationCityDescription = new JLabel("CITY");
		lblLocationCityDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationCityDescription.setBounds(1175, 207, 44, 27);
		stoneUnitPane.add(lblLocationCityDescription);
		lblLocationCityDescription.setForeground(new Color(128, 128, 128));
		lblLocationCityDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));

		textFieldPrice = new JTextField();
		textFieldPrice.setEnabled(false);
		textFieldPrice.setText("PRICE");
		textFieldPrice.setBorder(null);
		textFieldPrice.setDisabledTextColor(Color.DARK_GRAY);
		textFieldPrice.setBackground(new Color(255, 250, 250));
		textFieldPrice.setForeground(new Color(47, 79, 79));
		textFieldPrice.setFont(new Font("Segoe UI", Font.BOLD, 25));
		textFieldPrice.setBounds(111, 655, 340, 34);
		textFieldPrice.setEditable(false);
		stoneUnitPane.add(textFieldPrice);

		lblPriceError = new JLabel("Must be a positive number! (devided by dot)");
		lblPriceError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPriceError.setForeground(Color.RED);
		lblPriceError.setBounds(111, 688, 234, 14);
		lblPriceError.setVisible(false);
		stoneUnitPane.add(lblPriceError);

		textFieldPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					float number = Float.parseFloat(textFieldPrice.getText());
					lblPriceError.setVisible(false);
					cachedStoneProduct.setPrice(number);
					if (number < 0)
						lblPriceError.setVisible(true);
				} catch (NumberFormatException ex) {
					lblPriceError.setVisible(true);
				}
			}
		});

		JLabel lblPiecesDescription = new JLabel("PRICE");
		lblPiecesDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblPiecesDescription.setForeground(new Color(128, 128, 128));
		lblPiecesDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPiecesDescription.setBounds(580, 655, 64, 27);
		stoneUnitPane.add(lblPiecesDescription);

		lblSupplier = new JLabel("NONE");
		lblSupplier.setBounds(758, 248, 329, 34);
		stoneUnitPane.add(lblSupplier);
		lblSupplier.setForeground(new Color(47, 79, 79));
		lblSupplier.setFont(new Font("Segoe UI", Font.BOLD, 25));

		lblSupplierError = new JLabel("Supplier is empty.");
		lblSupplierError.setBounds(758, 279, 111, 14);
		stoneUnitPane.add(lblSupplierError);
		lblSupplierError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSupplierError.setForeground(Color.RED);

		lblMoveToSupplier = new JLabel("");
		lblMoveToSupplier.setBounds(1111, 257, 25, 25);
		stoneUnitPane.add(lblMoveToSupplier);
		lblMoveToSupplier.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/moveto2.png")));

		lblEmployee = new JLabel("NONE");
		lblEmployee.setBounds(758, 302, 200, 34);
		stoneUnitPane.add(lblEmployee);
		lblEmployee.setForeground(new Color(47, 79, 79));
		lblEmployee.setFont(new Font("Segoe UI", Font.BOLD, 25));

		lblMoveToEmployee = new JLabel("");
		lblMoveToEmployee.setBounds(1111, 302, 25, 25);
		stoneUnitPane.add(lblMoveToEmployee);
		lblMoveToEmployee.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/moveto2.png")));

		lblUpdateConfirmIcon = new JLabel("");
		lblUpdateConfirmIcon.setVisible(false);
		lblUpdateConfirmIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateConfirmIcon.setBounds(1018, 362, 35, 35);
		stoneUnitPane.add(lblUpdateConfirmIcon);
		lblUpdateConfirmIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog("Enter update");
				if (input.length() > 0) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					String date = dtf.format(now);
					String updateText = "(" + date + ") " + input + "\n";

					textAreaUpdates.setText(textAreaUpdates.getText() + updateText);
					cachedStoneProduct.setUpdates(textAreaUpdates.getText());

				}
			}
		});
		lblUpdateConfirmIcon
				.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/addButton_small.png")));

		JScrollPane updatesScrollPane = new JScrollPane();
		updatesScrollPane.setBackground(Color.WHITE);
		updatesScrollPane.setBounds(760, 408, 505, 212);
		stoneUnitPane.add(updatesScrollPane);
		updatesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		updatesScrollPane.setBorder(null);

		textAreaUpdates = new JTextArea();
		updatesScrollPane.setViewportView(textAreaUpdates);
		textAreaUpdates.setLineWrap(true);
		textAreaUpdates.setWrapStyleWord(true);
		// textAreaUpdates.setText(
		// "12.06.2022(17:30) - Status changed to \"unavailable\"\r\n12.06.2022(17:00) -
		// Employee (name) assigned to unit\r\n12.06.2022(15:35) - Status changed to
		// \"WIP\"\r\n12.06.2022(15:30) - Employee (name) assigned to
		// unit\r\n12.06.2022(15:00) - Profile created\r\n\r\n12.06.2022(17:30) - Status
		// changed to \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to
		// unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) -
		// Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile
		// created\r\n\r\n12.06.2022(17:30) - Status changed to
		// \"unavailable\"\r\n12.06.2022(17:00) - Employee (name) assigned to
		// unit\r\n12.06.2022(15:35) - Status changed to \"WIP\"\r\n12.06.2022(15:30) -
		// Employee (name) assigned to unit\r\n12.06.2022(15:00) - Profile created");
		textAreaUpdates.setForeground(new Color(192, 176, 131));
		textAreaUpdates.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textAreaUpdates.setEditable(false);
		textAreaUpdates.setCaretPosition(0);
		updatesScrollPane.getVerticalScrollBar().setUnitIncrement(4);

		comboMaterials = new JComboBox();
		comboMaterials.setEnabled(false);
		for (StoneMaterial mat : Main.getInstance().cachedMaterials) {
			comboMaterials.addItem(mat);
		}

		comboMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoneMaterial mat = (StoneMaterial) comboMaterials.getSelectedItem();
				loadStoneTypes(mat.getId());
			}
		});

		comboMaterials.setForeground(Color.DARK_GRAY);
		comboMaterials.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboMaterials.setFocusable(false);
		comboMaterials.setBorder(null);
		comboMaterials.setBackground(Color.WHITE);
		comboMaterials.setBounds(105, 174, 340, 34);
		stoneUnitPane.add(comboMaterials);

		comboTypes = new JComboBox();
		comboTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cachedStoneProduct != null)
					cachedStoneProduct.setStoneType((StoneType) comboTypes.getSelectedItem());
			}
		});
		comboTypes.setEnabled(false);
		comboTypes.setModel(new DefaultComboBoxModel(new String[] { "Types" }));
		comboTypes.setForeground(Color.DARK_GRAY);
		comboTypes.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboTypes.setFocusable(false);
		comboTypes.setBorder(null);
		comboTypes.setBackground(Color.WHITE);
		comboTypes.setBounds(105, 219, 340, 34);
		stoneUnitPane.add(comboTypes);

		JLabel lblType = new JLabel("TYPE");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblType.setBounds(574, 217, 160, 27);
		stoneUnitPane.add(lblType);

		comboLocations = new JComboBox();
		comboLocations.setEnabled(false);
		comboLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cachedStoneProduct != null)
					loadLocation(((Location) comboLocations.getSelectedItem()).getId());
			}
		});
		for (Location loc : Main.getInstance().cachedLocations) {
			comboLocations.addItem(loc);
		}
		comboLocations.setForeground(Color.DARK_GRAY);
		comboLocations.setFont(new Font("Segoe UI", Font.BOLD, 25));
		comboLocations.setFocusable(false);
		comboLocations.setBorder(null);
		comboLocations.setBackground(Color.WHITE);
		comboLocations.setBounds(758, 111, 395, 34);
		stoneUnitPane.add(comboLocations);

		JLabel lblSupplierDescription = new JLabel("SUPPLIER");
		lblSupplierDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblSupplierDescription.setForeground(Color.GRAY);
		lblSupplierDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSupplierDescription.setBounds(1175, 253, 100, 27);
		stoneUnitPane.add(lblSupplierDescription);

		JLabel lblEmployeeDescription = new JLabel("EMPLOYEE");
		lblEmployeeDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployeeDescription.setForeground(Color.GRAY);
		lblEmployeeDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblEmployeeDescription.setBounds(1169, 302, 111, 27);
		stoneUnitPane.add(lblEmployeeDescription);

		lblEmployeeError = new JLabel("Employee is empty.");
		lblEmployeeError.setForeground(Color.RED);
		lblEmployeeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmployeeError.setBounds(758, 332, 111, 14);
		stoneUnitPane.add(lblEmployeeError);

		btnChangeSupplier = new JButton("Change");
		btnChangeSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option = JOptionPane.showInputDialog("Enter supplier's ID.");
				if (option != null) {
					try {
						int id = Integer.parseInt(option);
						PersonController pctrl = new PersonController();
						Supplier s = (Supplier) pctrl.getByID(id);
						cachedStoneProduct.setSupplier(s);
						lblSupplier.setText(s.getName());
						lblSupplierError.setVisible(false);
					} catch (Exception ne) {
						JOptionPane.showMessageDialog(null, "Could not find a supplier with ID {" + option + "}",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnChangeSupplier.setVisible(false);
		btnChangeSupplier.setBounds(1047, 260, 89, 23);
		stoneUnitPane.add(btnChangeSupplier);

		btnChangeEmployee = new JButton("Change");
		btnChangeEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option = JOptionPane.showInputDialog("Enter employee's ID.");
				if (option != null) {
					try {
						int id = Integer.parseInt(option);
						PersonController pctrl = new PersonController();
						Employee s = (Employee) pctrl.getByID(id);
						cachedStoneProduct.setEmployee(s);
						lblEmployee.setText(s.getName());
						lblEmployeeError.setVisible(false);
					} catch (Exception ne) {
						JOptionPane.showMessageDialog(null, "Could not find a employee with ID {" + option + "}",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnChangeEmployee.setVisible(false);
		btnChangeEmployee.setBounds(1047, 314, 89, 23);
		stoneUnitPane.add(btnChangeEmployee);

		btnToday = new JButton("Today");
		btnToday.setVisible(false);
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate dateObj = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String date = dateObj.format(formatter);
				textFieldCreatedDate.setText(date);

				try {
					String sDate1 = textFieldCreatedDate.getText();
					java.sql.Date date1 = java.sql.Date.valueOf(sDate1);
					cachedStoneProduct.setCreatedDate(date1);
					lblDateError.setVisible(false);
				} catch (Exception ex) {
					// System.out.println("invalid Date format. use yyyy-mm-dd");
					lblDateError.setVisible(true);
				}
			}
		});
		btnToday.setBounds(455, 454, 89, 23);
		stoneUnitPane.add(btnToday);

		JLabel lblUpdates = new JLabel("Updates");
		lblUpdates.setForeground(new Color(47, 79, 79));
		lblUpdates.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblUpdates.setBounds(898, 363, 130, 34);
		stoneUnitPane.add(lblUpdates);

		txtTotalSize = new JTextField();
		txtTotalSize.setText("TOTAL SIZE");
		txtTotalSize.setForeground(new Color(47, 79, 79));
		txtTotalSize.setFont(new Font("Segoe UI", Font.BOLD, 25));
		txtTotalSize.setEnabled(false);
		txtTotalSize.setEditable(false);
		txtTotalSize.setDisabledTextColor(Color.DARK_GRAY);
		txtTotalSize.setBorder(null);
		txtTotalSize.setBackground(new Color(255, 250, 250));
		txtTotalSize.setBounds(758, 650, 366, 34);
		stoneUnitPane.add(txtTotalSize);

		JLabel lblTotalSize = new JLabel("TOTAL SIZE");
		lblTotalSize.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalSize.setForeground(Color.GRAY);
		lblTotalSize.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTotalSize.setBounds(1150, 655, 130, 27);
		stoneUnitPane.add(lblTotalSize);

		lblOrderID = new JLabel("NONE");
		lblOrderID.setForeground(new Color(47, 79, 79));
		lblOrderID.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblOrderID.setBounds(111, 729, 234, 34);
		stoneUnitPane.add(lblOrderID);

		btnChangeOrderID = new JButton("Change");
		btnChangeOrderID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option = JOptionPane.showInputDialog("Enter Order ID.");
				if (option != null) {
					try {
						int id = Integer.parseInt(option);
						OrderController octrl = new OrderController();
						OrderInfo o = octrl.getByID(id);
						if (o != null) {
							cachedStoneProduct.setOrderID(id);
							lblOrderID.setText(id + "");
							lblOrderError.setVisible(false);
						}
						else throw new Exception("No Order");
					} catch (Exception ne) {
						// JOptionPane.showMessageDialog(null, "Could not find an order with ID {" +
						// option + "}", "Error",
						// JOptionPane.ERROR_MESSAGE);
						JOptionPane.showMessageDialog(null, ne.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnChangeOrderID.setVisible(false);
		btnChangeOrderID.setBounds(362, 729, 89, 23);
		stoneUnitPane.add(btnChangeOrderID);

		lblOrderIDDescription = new JLabel("ORDER ID");
		lblOrderIDDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrderIDDescription.setForeground(Color.GRAY);
		lblOrderIDDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOrderIDDescription.setBounds(574, 734, 130, 27);
		stoneUnitPane.add(lblOrderIDDescription);

		lblOrderError = new JLabel("Order ID is empty.");
		lblOrderError.setForeground(Color.RED);
		lblOrderError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblOrderError.setBounds(111, 761, 111, 14);
		stoneUnitPane.add(lblOrderError);
		
		lblShape = new JLabel("NONE");
		lblShape.setForeground(new Color(47, 79, 79));
		lblShape.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblShape.setBounds(758, 719, 292, 34);
		stoneUnitPane.add(lblShape);
		
		lblShapeError = new JLabel("Shape is not set.");
		lblShapeError.setForeground(Color.RED);
		lblShapeError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblShapeError.setBounds(758, 749, 111, 14);
		stoneUnitPane.add(lblShapeError);
		
		
		
		lblMoveToShape = new JLabel("");
		lblMoveToShape.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFrame frame = new JFrame();
				frame.setBounds(320, 180, 1280, 668);
				frame.getContentPane().setLayout(null);
				frame.setTitle("Drawing Panel");
				frame.setVisible(true);

				DrawShapeAutomatic drawer = new DrawShapeAutomatic();
				frame.getContentPane().add(drawer);
				drawer.drawShape(cachedStoneProduct.getShape());

			}
		});
		lblMoveToShape.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/moveto2.png")));
		lblMoveToShape.setBounds(1111, 729, 25, 25);
		stoneUnitPane.add(lblMoveToShape);
		
		btnChangeShape = new JButton("Change");
		btnChangeShape.setVisible(false);
		btnChangeShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new StoneUnitDrawShapeWindow(stoneSave);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnChangeShape.setBounds(1060, 731, 89, 23);
		stoneUnitPane.add(btnChangeShape);

		if (id > 0) {
			loadStoneUnit(id);
		} else {
			loadCreatingStone();
		}
	}

	private boolean haveErrors() {
		return lblSupplierError.isVisible() || lblWidthError.isVisible() || lblWeightError.isVisible()
				|| lblEmployeeError.isVisible() || lblPriceError.isVisible() || lblOriginError.isVisible()
				|| lblDateError.isVisible() || lblOrderError.isVisible() || lblShapeError.isVisible() ;
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

	private void switchEditable() {
		if (isEditPressed) {
			textFieldOrigin.setEditable(true);
			textFieldCreatedDate.setEditable(true);
			textFieldWidth.setEditable(true);
			textFieldWeight.setEditable(true);
			textFieldDescription.setEditable(true);
			textFieldPrice.setEditable(true);

			comboTypes.setEnabled(true);
			comboBoxStatus.setEnabled(true);
			comboMaterials.setEnabled(true);
			comboLocations.setEnabled(true);

			textFieldOrigin.setEnabled(true);
			textFieldCreatedDate.setEnabled(true);
			textFieldWeight.setEnabled(true);
			textFieldWidth.setEnabled(true);
			textFieldDescription.setEnabled(true);
			textFieldPrice.setEnabled(true);

			btnChangeSupplier.setVisible(true);
			btnToday.setVisible(true);
			btnChangeEmployee.setVisible(true);
			lblUpdateConfirmIcon.setVisible(true);
			btnChangeOrderID.setVisible(true);
			btnChangeShape.setVisible(true);

			lblMoveToSupplier.setVisible(false);
			lblMoveToEmployee.setVisible(false);
			lblMoveToShape.setVisible(false);

		} else {
			textFieldOrigin.setEditable(false);
			textFieldCreatedDate.setEditable(false);
			textFieldWidth.setEditable(false);
			textFieldWeight.setEditable(false);
			textFieldDescription.setEditable(false);
			textFieldPrice.setEditable(false);

			comboMaterials.setEnabled(false);
			comboTypes.setEnabled(false);
			comboBoxStatus.setEnabled(false);
			comboLocations.setEnabled(false);

			textFieldOrigin.setEnabled(false);
			textFieldCreatedDate.setEnabled(false);
			textFieldWidth.setEnabled(false);
			textFieldWeight.setEnabled(false);
			textFieldDescription.setEnabled(false);
			textFieldPrice.setEnabled(false);

			btnChangeSupplier.setVisible(false);
			btnToday.setVisible(false);
			btnChangeEmployee.setVisible(false);
			lblUpdateConfirmIcon.setVisible(false);
			btnChangeOrderID.setVisible(false);
			btnChangeShape.setVisible(false);

			lblMoveToSupplier.setVisible(true);
			lblMoveToEmployee.setVisible(true);
			lblMoveToShape.setVisible(true);

		}
	}

	private void loadCreatingStone() {
		UI_Blocker.setVisible(false);
		stoneUnitPane.setVisible(true);

		isEditPressed = true;
		switchEditable();

		lblEditCheck.setIcon(new ImageIcon(StoneUnitProductWindow.class.getResource("/imgs/confirm1.png")));
		lblDeleteStorno.setVisible(false);

		cachedStoneProduct = new StoneProduct(-1, Main.getInstance().cachedStoneTypes.get(0), "", null, 0, 0, "", null,
				Main.getInstance().cachedLocations.get(0), null, StoneUnitStatuses.WIP, null, 0, 0, 0, "");
		loadStoneTypes(1);
		loadLocation(1);
		lblSupplierError.setVisible(true);
		lblWidthError.setVisible(true);
		lblWeightError.setVisible(true);
		lblEmployeeError.setVisible(true);
		lblPriceError.setVisible(true);
		lblOriginError.setVisible(true);
		lblDateError.setVisible(true);
		lblPriceError.setVisible(true);
		lblOrderError.setVisible(true);
		lblShapeError.setVisible(true);

		textFieldId.setText("Automatically generated.");
	}

	private void loadStoneUnit(int id) {

		Thread thread = new Thread() {
			public void run() {
				try {
					StoneController sctrl = new StoneController();

					try {
						cachedStoneProduct = (StoneProduct) sctrl.getStoneUnitByID(id);
						textFieldId.setText(cachedStoneProduct.getId() + "");
						textFieldOrigin.setText(cachedStoneProduct.getOrigin());
						lblOriginError.setVisible(false);
						comboBoxStatus.setSelectedIndex(cachedStoneProduct.getStatus().getID());
						textFieldCreatedDate.setText(cachedStoneProduct.getCreatedDate().toString());
						textFieldWidth.setText(cachedStoneProduct.getWidth() + "");
						textFieldWeight.setText(cachedStoneProduct.getWeight() + "");
						textFieldDescription.setText(cachedStoneProduct.getDescription());
						lblSupplier.setText(cachedStoneProduct.getSupplier().getName());
						lblSupplierError.setVisible(false);
						lblEmployee.setText(cachedStoneProduct.getEmployee().getName());
						lblEmployeeError.setVisible(false);
						textFieldPrice.setText(cachedStoneProduct.getPrice() + "");
						lblPriceError.setVisible(false);
						textAreaUpdates.setText(cachedStoneProduct.getUpdates());
						lblOrderID.setText(cachedStoneProduct.getOrderID() + "");
						lblOrderError.setVisible(false);

						for (StoneMaterial mat : Main.getInstance().cachedMaterials) {
							if (mat.getId() == cachedStoneProduct.getStoneType().getMaterial().getId()) {
								comboMaterials.setSelectedItem(mat);
							}
						}

						for (StoneType type : ((StoneMaterial) comboMaterials.getSelectedItem()).getAllTypes()) {
							if (type.getId() == cachedStoneProduct.getStoneType().getId()) {
								comboTypes.setSelectedItem(type);
							}
						}
						for (Location loc : Main.getInstance().cachedLocations) {
							if (loc.getId() == cachedStoneProduct.getLocation().getId())
								comboLocations.setSelectedItem(loc);
						}
						
						
						Shape shape = cachedStoneProduct.getShape();
						
						if (shape != null) {
							lblShape.setText(shape.getName());
							lblShapeError.setVisible(false);
							txtTotalSize.setText(shape.calculateArea() + "");
						}
						
						loadLocation(cachedStoneProduct.getLocation().getId());

						UI_Blocker.setVisible(false);
						stoneUnitPane.setVisible(true);

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

	private void loadStoneTypes(int matID) {
		comboTypes.removeAllItems();
		StoneMaterial mat = null;
		for (StoneMaterial m : Main.getInstance().cachedMaterials) {
			if (m.getId() == matID)
				mat = m;
		}
		for (StoneType type : mat.getAllTypes()) {
			comboTypes.addItem(type);
		}
		comboTypes.setSelectedIndex(0);
		if (cachedStoneProduct != null)
			cachedStoneProduct.setStoneType((StoneType) comboTypes.getSelectedItem());
	}

	private void loadLocation(int locID) {
		Location location = null;
		for (Location loc : Main.getInstance().cachedLocations) {
			if (loc.getId() == locID)
				location = loc;
		}

		textFieldLocationAddress.setText(location.getAddress());
		textFieldLocationCity.setText(location.getCity().getCityName());

		if (cachedStoneProduct != null)
			cachedStoneProduct.setLocation(location);
	}

	private void saveStoneUnit() throws SQLException {

		StoneController sctrl = new StoneController();
		try {
			if (cachedStoneProduct != null) {
				if (cachedStoneProduct.getId() > 0) {
					sctrl.updateStone(cachedStoneProduct);
					JOptionPane.showMessageDialog(null, "Stone has been successfully updated.");
				} else {
					// create a new stone
					sctrl.createStone(cachedStoneProduct, null);
					JOptionPane.showMessageDialog(null, "Stone has been successfully created.");
					setVisible(false);
					dispose();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveShape(Shape shape) {
		shape.setId(cachedStoneProduct.getId());
		cachedStoneProduct.setShape(shape);
		txtTotalSize.setText(shape.calculateArea() + "");
		lblShape.setText(shape.getName());
		lblShapeError.setVisible(false);
	}
}
