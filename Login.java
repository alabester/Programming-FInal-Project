import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JRadioButton;

import java.sql.*;

public class Login extends JFrame implements FocusListener {

    public Connection con = null;
    public Statement st = null;
    public ResultSet rs = null;
    
    private PreparedStatement pstmtsqlInsert;
    
	private JPanel contentPane;
	private JTextField id_1;
	private JTextField pwd_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private boolean showingHint;
	private String TextContent;
	private JPasswordField passwordField_1;
	private JTextField txtYyyy;
	private JTextField txtMm;
	private JTextField txtDd;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel label_4;
	private JLabel lblNewLabel_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Login() {
		
		DBConnection();

		lblNewLabel_2 = null;
		lblNewLabel_3 = null;

		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(630, 10, 90, 25);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		contentPane.add(lblPassword);
		
		pwd_1 = new JPasswordField();
		pwd_1.setBounds(630, 40, 150, 25);
		pwd_1.setColumns(10);
		contentPane.add(pwd_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 90);
		panel.setBackground(new Color(72,103,170));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbID = new JLabel("이메일, 또는 휴대폰");
		lbID.setBounds(450, 10, 200, 25);
		panel.add(lbID);
		lbID.setForeground(Color.WHITE);
		lbID.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		
		id_1 = new JTextField();
		id_1.setBounds(450, 40, 150, 25);
		panel.add(id_1);
		id_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\Downloads\\facebook_2015_logo_detail (1).png"));
		lblNewLabel.setBounds(31, 10, 371, 69);
		panel.add(lblNewLabel);
		
		JButton button = new JButton("로그인");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginValidate();
			}
		});
		button.setBounds(797, 40, 59, 25);
		panel.add(button);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setPreferredSize(new Dimension(59, 25));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		button.setBackground(new Color(72, 103, 170));
		
		JLabel lblNewLabel_1 = new JLabel("가입하기");
		lblNewLabel_1.setBounds(450, 144, 159, 54);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("다음_Regular", Font.PLAIN, 38));
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("항상 지금처럼 무료로 즐기실 수 있습니다.");
		label.setBounds(450, 210, 424, 34);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("다음_Regular", Font.PLAIN, 22));
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setText("성(姓)");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setBounds(450, 260, 170, 34);
		textField.addFocusListener(this);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("이름");
		textField_1.setColumns(10);
		textField_1.setBounds(647, 260, 159, 34);
		textField_1.addFocusListener(this);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("휴대폰 번호 또는 이메일");
		textField_2.setColumns(10);
		textField_2.setBounds(450, 306, 356, 34);
		textField_2.addFocusListener(this);
		contentPane.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(450, 395, 356, 34);
		passwordField.addFocusListener(this);
		contentPane.add(passwordField);
		
		JLabel label_1 = new JLabel("비밀번호");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("다음_Regular", Font.PLAIN, 18));
		label_1.setBounds(450, 352, 424, 34);
		contentPane.add(label_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(450, 470, 356, 34);
		contentPane.add(passwordField_1);
		
		JLabel label_2 = new JLabel("확인을 위해 다시 입력해 주세요.");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("다음_Regular", Font.PLAIN, 18));
		label_2.setBounds(450, 433, 424, 34);
		contentPane.add(label_2);
		
		JButton btnNewButton = new JButton("가입하기");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(72,103,170));
		btnNewButton.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().equals("") && !passwordField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !txtYyyy.getText().equals("") && !txtMm.getText().equals("") && !txtDd.getText().equals("") && !passwordField_1.getText().equals(""))
				{	lblNewLabel_2.setIcon(new ImageIcon("D:\\Downloads\\icons8-checked-40 (1).png"));
					lblNewLabel_3.setForeground(Color.BLUE);
					lblNewLabel_3.setText("성공적으로 입력되었습니다.");
					InsertQuery();
				}
				else {
				lblNewLabel_3.setForeground(Color.RED);
				lblNewLabel_2.setIcon(new ImageIcon("D:\\Downloads\\icons8-high-priority-48 (1).png"));
				lblNewLabel_3.setText("필수항목이 제대로 입력되지 않았습니다.");
			}
			}
		});
		btnNewButton.setBounds(450, 694, 150, 40);
		contentPane.add(btnNewButton);
		
		JLabel label_3 = new JLabel("생일");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("다음_Regular", Font.PLAIN, 18));
		label_3.setBounds(450, 519, 356, 34);
		contentPane.add(label_3);
		
		txtYyyy = new JTextField();
		txtYyyy.setToolTipText("");
		txtYyyy.setText("YYYY");
		txtYyyy.setHorizontalAlignment(SwingConstants.LEFT);
		txtYyyy.setColumns(10);
		txtYyyy.setBounds(450, 557, 97, 34);
		txtYyyy.addFocusListener(this);
		contentPane.add(txtYyyy);
		
		txtMm = new JTextField();
		txtMm.setToolTipText("");
		txtMm.setText("MM");
		txtMm.setHorizontalAlignment(SwingConstants.LEFT);
		txtMm.setColumns(10);
		txtMm.setBounds(591, 557, 81, 34);
		txtMm.addFocusListener(this);
		contentPane.add(txtMm);
		
		txtDd = new JTextField();
		txtDd.setToolTipText("");
		txtDd.setText("DD");
		txtDd.setHorizontalAlignment(SwingConstants.LEFT);
		txtDd.setColumns(10);
		txtDd.setBounds(725, 557, 81, 34);
		txtDd.addFocusListener(this);
		contentPane.add(txtDd);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(null);
		lblNewLabel_2.setBounds(450, 622, 54, 34);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(488, 617, 292, 40);
		contentPane.add(lblNewLabel_3);
		

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setSize(400, 30);
		lblNewLabel_4.setLocation(490, 105);
		contentPane.add(lblNewLabel_4);

		label_4 = new JLabel("");
		label_4.setBounds(450, 105, 50, 30);
		contentPane.add(label_4);
		
		
        int panelHeight = contentPane.getHeight();
        int panelWidth = contentPane.getWidth();
        GradientPaint gradientPaint = new GradientPaint( panelWidth / 2 , 0 , new Color(0,0,0) , panelWidth / 2 , panelHeight , new Color(255,255,255) );

	}
	
	  public void DBConnection() {
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://35.166.92.90:3306/sampledb" , "sampledb_dba", "1234");
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	  
	  public void InsertQuery() {
		  String SQL = "Insert into member values (?,?,?,?,?)";
		 
		  String birthdate_string = (txtYyyy.getText() + "-" + txtMm.getText() + "-" + txtDd.getText());
		  
		  Date birthdate = Date.valueOf(birthdate_string);
		  try {
		  pstmtsqlInsert = con.prepareStatement(SQL);
		  
		  pstmtsqlInsert.setString(1, textField_2.getText());
		  pstmtsqlInsert.setString(2, passwordField_1.getText());
		  pstmtsqlInsert.setString(3, textField.getText());
		  pstmtsqlInsert.setString(4, textField_1.getText());
		  pstmtsqlInsert.setDate(5, birthdate);
		  
		  pstmtsqlInsert.executeUpdate();
			
		  } catch (SQLException e) {
				lblNewLabel_3.setForeground(Color.RED);
				lblNewLabel_2.setIcon(new ImageIcon("D:\\Downloads\\icons8-high-priority-48 (1).png"));
				lblNewLabel_3.setText("이미 있는 아이디입니다.");
		  }

	  }
	  
	  public void focusLost(FocusEvent e) {
		  Component source = e.getComponent();
		  if(((JTextField)source).getText().isEmpty()) {
		  if(source instanceof JTextField) {
			  ((JTextField)source).setText(TextContent);
		  }
			 }  
	  }
	  
	  public void focusGained(FocusEvent e) {
		  Component source = e.getComponent();
		  if(source instanceof JTextField) {
			  TextContent = ((JTextField)source).getText();
			  ((JTextField)source).setText("");
			  }  
		  }
	  
	  public String getText(String JTextFieldHName) {
		    return showingHint ? "" : JTextFieldHName;
		  }

	  public void LoginValidate() {
		  try {
		  String SQL = "select concat(surname, name), id, pwd from member where id = ?";
				  PreparedStatement pstmtLogin = con.prepareStatement(SQL);
				  pstmtLogin.setString(1, id_1.getText());
				  
		ResultSet rst = pstmtLogin.executeQuery();
			String userID = null;
			String pwd = null;
			String userName = null;
			
			while(rst.next()) {
				userName = rst.getString(1);
				userID = rst.getString(2);
				pwd = rst.getString(3);

				if(pwd.equals(pwd_1.getText())) {
					dispose();
					new MainPage_TestBed(userID, userName);
					
				} else {
					System.out.println("비밀번호 불일치");
					

				}
				break;

			}
			label_4.setIcon(new ImageIcon("D:\\Downloads\\icons8-high-priority-48 (1).png"));
			lblNewLabel_4.setForeground(Color.RED);
			lblNewLabel_4.setText("아이디가 존재하지 않거나, 비밀번호가 일치하지 않습니다.");
			
		} catch (Exception e) {
		  	e.printStackTrace();
		}
	 }
}
	  