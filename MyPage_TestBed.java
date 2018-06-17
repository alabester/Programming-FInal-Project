import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPage_TestBed extends JFrame {

    public Connection con = null;
    public PreparedStatement st = null;
    public ResultSet rs = null;
	ArrayList<String> myContents = new ArrayList<>();
//	ArrayList<String> myName = new ArrayList<>();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                 MyPage_TestBed mn = new MyPage_TestBed("alabester@naver.com", "AA");
            }
        });
    }
	/**
	 * Create the frame.
	 */
	
	public MyPage_TestBed(String userID, String userName) {

        JFrame frame = new JFrame("SpringLayout");
        frame.getContentPane().setBackground(new Color(233, 235, 238));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(400, 100, 1000, 900);
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);

        DBConnection();
        
		int rowCnt = 0;
		
		try {

			String myContentCount = "select count(*) from myinfo where myUserID = ?"; //
			st = con.prepareStatement(myContentCount);
			st.setString(1, userID);
			rs = st.executeQuery();
				while(rs.next()) {
					rowCnt = rs.getInt(1);
				}
			System.out.println(rowCnt);
			
			String contentSelect = "select myContent from myinfo where myUserID = ? order by myContentID desc"; // myContentID 내림차순으로 글 조회 
			st = con.prepareStatement(contentSelect);
			st.setString(1, userID);
			rs = st.executeQuery();

//			Iterator it = contents.iterator();
			
			while(rs.next()) {
				myContents.add(rs.getString("myContent"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        int j = 300;
//        int initialHeight = 0;
//        int initialX = 0;
        
        
        	JPanel Write = new JPanel();
        	layout.putConstraint(SpringLayout.NORTH, Write, 0, SpringLayout.NORTH, mainPanel);
        	layout.putConstraint(SpringLayout.WEST, Write, 0, SpringLayout.WEST, mainPanel);
        	
        	Write.setPreferredSize(new Dimension(1000,290));
        	Write.setBackground(Color.WHITE);
        	mainPanel.add(Write);
        	Write.setLayout(null);
        	
        	JLabel Tester = new JLabel("Test");
        	Tester.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
        	Tester.setBounds(0, 0, 50, 50);
        	Write.add(Tester);
        	
        	textField = new JTextField();
        	textField.setBounds(50, 50, 928, 190);
        	Write.add(textField);
        	textField.setColumns(10);
        	
        	JLabel lblNewLabel_1 = new JLabel("%d\uB2D8, \uBB34\uC2A8 \uC0DD\uAC01\uC744 \uD558\uC2DC\uACE0 \uACC4\uC2E0\uAC00\uC694?");
        	lblNewLabel_1.setBounds(50, 0, 590, 50);
        	Write.add(lblNewLabel_1);
        	
        	JButton btnNewButton = new JButton("\uAC8C\uC2DC");
        	btnNewButton.setForeground(Color.BLACK);
        	btnNewButton.setBackground(new Color(66, 103, 178));
        	btnNewButton.setBounds(50, 250, 928, 30);
        	Write.add(btnNewButton);
        	
        for(int i =0;i<rowCnt;i++){
        	
		        	JPanel Modeler = new JPanel();
		        	try {
		        		
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	}
		
		        	
		        	GridBagLayout Modeler_Layout = new GridBagLayout();
		        	Modeler.setBackground(Color.WHITE);
		        	Modeler.setOpaque(true);
		        	Modeler.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,10,0,new Color(233, 235, 238)),new EtchedBorder()));
		    		Modeler_Layout.columnWidths = new int[]{0, 0, 0};
		    		Modeler_Layout.rowHeights = new int[]{50, 50, 0};
		    		Modeler_Layout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		    		Modeler_Layout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		    		Modeler.setLayout(Modeler_Layout);
		    		
		//        	JLabel label = new JLabel("Enter Name " + i );
		    		JLabel label = new JLabel("");
		    		label.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
		    		GridBagConstraints gbc_label = new GridBagConstraints();
		    		gbc_label.insets = new Insets(0, 0, 5, 5);
		    		gbc_label.gridx = 0;
		    		gbc_label.gridy = 0;
		    		Modeler.add(label, gbc_label);
		    		 
		       		JLabel text = new JLabel(userID);
		       		
		       		
		       		
		    		text.setHorizontalAlignment(JLabel.LEFT);
		       		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		    		gbc_label_2.anchor = GridBagConstraints.WEST;
		    		gbc_label_2.gridx = 1;
		    		gbc_label_2.gridy = 0;
		    		Modeler.add(text, gbc_label_2);
		    		
		    		JLabel text2 = new JLabel("");
		    		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		    		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		    		gbc_lblNewLabel_1.gridx = 0;
		    		gbc_lblNewLabel_1.gridy = 1;
		    		Modeler.add(text2, gbc_lblNewLabel_1);
		
		    		String GetText = myContents.get(i);
		    		String[] GetTextArray;
		
		    		int k = 0; // 50자로 자른 줄의 갯수, Initialize
		    		int l = 0; // while문 Count 갯수, Initalize
		    		
		    		String StartText = "", RemainText = ""; // 50자로 나눈 문자열의 내용, 나머지 문자열의 내용
		    		String PerfectText = "";
		    		
		    		if(GetText.length() >= 70) {
		       		StartText = GetText.substring(0,70);
		       		RemainText = GetText.substring(70, GetText.length());
		    		} else {
		    			RemainText = myContents.get(i);
		    		}
		    		
		       		PerfectText = StartText + "<br />";
		       		
		       		while(RemainText.length() >= 70) {
		       			PerfectText += StartText + "<br />";
		       			StartText = RemainText.substring(0, 70);
		       			RemainText = RemainText.substring(70, RemainText.length());
		       			k++;
		       		}
		       		
		       		PerfectText += RemainText;
		       		PerfectText = "<html>" + PerfectText + "</html>";
		       		
		       		System.out.println(RemainText.length());
		    		JLabel text3 = new JLabel(PerfectText);
		            
		            
		            GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		    		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHWEST;
		    		gbc_lblNewLabel_3.gridx = 1;
		    		gbc_lblNewLabel_3.gridy = 1;
		    		Modeler.add(text3, gbc_lblNewLabel_3);
		
		      
		//        layout.putConstraint(SpringLayout.WEST, Modeler, 10, SpringLayout.WEST,
		//                        contentPane);
		      layout.putConstraint(SpringLayout.NORTH, Modeler, j, SpringLayout.NORTH,
		                       contentPane);
		//        layout.putConstraint(SpringLayout.NORTH, Modeler, j, SpringLayout.NORTH,
		//                        contentPane);
		//        layout.putConstraint(SpringLayout.WEST, Modeler, 20, SpringLayout.EAST,
		//                        label);
		        j+=150+k*10;
//		        initialX+=100;
//		        initialHeight+=100;
		        mainPanel.add(Modeler);
		    	Modeler.setPreferredSize(new Dimension(1000, 150+k*10));
        }
        frame.getContentPane().setLayout(null); // 컴포넌트의 크기와 위치를 일일이 다 지정해주어야 된다.
        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), j)); // 이 메소드는 Dimension객체를 인자로 받으면서 해당 콤포넌트의 '기본크기'를 결정
        scroll.setPreferredSize(new Dimension(100,100)); //사이즈 정보를 가지고 있는 객체를 이용해 패널의 사이즈 지정
        scroll.setViewportView(mainPanel); //스크롤 팬 위에 패널을 올린다.
        scroll.setBackground(new Color(233, 235, 238));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JScrollBar vertical = scroll.getVerticalScrollBar();
        vertical.setPreferredSize(new Dimension(0,0));

        
        contentPane.add(scroll);
        mainPanel.setBackground(new Color(233, 235, 238));
        
        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBounds(0, 0, 1440, 80);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(72,103,170));
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\Downloads\\icons8-facebook-48.png"));
        lblNewLabel.setBounds(110, 12, 56, 56);
        panel.add(lblNewLabel);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        
        textField_1 = new JTextField();
        textField_1.setBounds(184, 24, 600, 34);

        panel.add(textField_1);
        textField_1.setColumns(10);
        

        
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setIcon(new ImageIcon("D:\\Downloads\\icons8-search-25.png"));
        btnNewButton_1.setBackground(new Color(246, 247, 249));
        btnNewButton.setContentAreaFilled(false);
        btnNewButton_1.setBounds(784, 24, 105, 34);
        panel.add(btnNewButton_1);
        
//        JButton btnNewButton_2 = new JButton("\uAC8C\uC2DC\uD558\uAE30");
//        btnNewButton_2.setBackground(new Color(72,103,170));
//        btnNewButton_2.setForeground(Color.WHITE);
//        btnNewButton_2.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent arg0) {
//        		InsertContent(userID, textField.getText());
//                lblNewLabel_3.setIcon(new ImageIcon("D:\\Downloads\\icons8-checked-40.png"));
//        		label.setForeground(Color.BLUE);
//        		label.setText("성공적으로 입력되었습니다.");
//        	}
//        });
//        
//        btnNewButton_2.setBounds(50, 250, 580, 27);
        JLabel lblNewLabel_2 = new JLabel("\t\t" + userName + "("+userID+")");
        lblNewLabel_2.setIcon(new ImageIcon("/Users/seail/Desktop/imagefile/facebook_man_resize.png"));
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(1104, 6, 260, 68);
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        			dispose();
					new MyPage_TestBed(userID, userName);
	        	}
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        			lblNewLabel_2.setForeground(Color.BLACK);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        			lblNewLabel_2.setForeground(Color.WHITE);
	        	}
        });
        panel.add(lblNewLabel_2);
        
        JScrollPane scrollPane = new JScrollPane(); // 프로필 
        scrollPane.setBounds(110, 100, 280, 900);
        scrollPane.setBackground(Color.WHITE);
        
        frame.getContentPane().add(scrollPane);
        frame.setSize(1440, 1000);
		frame.setUndecorated(true);
        frame.setVisible(true);
//        
//        JScrollPane scrollPane_1 = new JScrollPane();
//        scrollPane_1.setBounds(1050, 100, 280, 900);
//        scrollPane_1.setBackground(Color.WHITE);
//        frame.getContentPane().add(scrollPane_1);
//        //mainWindow.add(contentPane);
//        frame.setSize(1440, 1000);
//        frame.setUndecorated(true);
//        frame.setVisible(true);

	    }
	
	  public void DBConnection() {
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://35.166.92.90:3306/sampledb" , "sampledb_dba", "1234");
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	}