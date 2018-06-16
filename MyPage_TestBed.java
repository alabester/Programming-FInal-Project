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

public class MyPage_TestBed extends JFrame {

    public Connection con = null;
    public PreparedStatement st = null;
    public ResultSet rs = null;
	ArrayList<String> contents = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	
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
                 MyPage_TestBed mn = new MyPage_TestBed();
            }
        });
    }
	/**
	 * Create the frame.
	 */
	public MyPage_TestBed() {

        JFrame frame = new JFrame("SpringLayout");
        frame.getContentPane().setBackground(new Color(233, 235, 238));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(400, 100, 640, 900);
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);

        DBConnection();
        
		int rowCnt = 0;
		
		try {

			
			String Count = "select count(*) from content";
			st = con.prepareStatement(Count);
			rs = st.executeQuery();
				while(rs.next()) {
					rowCnt = rs.getInt(1);
				}
			System.out.println(rowCnt);
			
			String SQL = "select * from content order by contentID desc";
			st = con.prepareStatement(SQL);
			rs = st.executeQuery();

//			Iterator it = contents.iterator();
			
			while(rs.next()) {
				contents.add(rs.getString("content"));
				names.add(rs.getString("userID"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        int j = 300;
        int initialHeight = 0;
        int initialX = 0;
        
        
        	JPanel Write = new JPanel();
        	layout.putConstraint(SpringLayout.NORTH, Write, 0, SpringLayout.NORTH, mainPanel);
        	layout.putConstraint(SpringLayout.WEST, Write, 0, SpringLayout.WEST, mainPanel);
        	
        	Write.setPreferredSize(new Dimension(640,290));
        	Write.setBackground(Color.WHITE);
        	mainPanel.add(Write);
        	Write.setLayout(null);
        	
        	JLabel Tester = new JLabel("Test");
        	Tester.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
        	Tester.setBounds(0, 0, 50, 50);
        	Write.add(Tester);
        	
        	textField = new JTextField();
        	textField.setBounds(50, 50, 580, 190);
        	Write.add(textField);
        	textField.setColumns(10);
        	
        	JLabel lblNewLabel_1 = new JLabel("%d\uB2D8, \uBB34\uC2A8 \uC0DD\uAC01\uC744 \uD558\uC2DC\uACE0 \uACC4\uC2E0\uAC00\uC694?");
        	lblNewLabel_1.setBounds(50, 0, 590, 50);
        	Write.add(lblNewLabel_1);
        	
        	JButton btnNewButton = new JButton("\uAC8C\uC2DC");
        	btnNewButton.setForeground(Color.WHITE);
        	btnNewButton.setBackground(new Color(66, 103, 178));
        	btnNewButton.setBounds(10, 250, 620, 30);
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
		    		 
		       		JLabel text = new JLabel(names.get(i));
		       		
		       		
		       		
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
		
		    		String GetText = contents.get(i);
		    		String[] GetTextArray;
		
		    		int k = 0; // 50자로 자른 줄의 갯수, Initialize
		    		int l = 0; // while문 Count 갯수, Initalize
		    		
		    		String StartText = "", RemainText = ""; // 50자로 나눈 문자열의 내용, 나머지 문자열의 내용
		    		String PerfectText = "";
		    		
		    		if(GetText.length() >= 70) {
		       		StartText = GetText.substring(0,70);
		       		RemainText = GetText.substring(70, GetText.length());
		    		} else {
		    			RemainText = contents.get(i);
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
		        initialX+=100;
		        initialHeight+=100;
		        mainPanel.add(Modeler);
		    	Modeler.setPreferredSize(new Dimension(640, 150+k*10));
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
        btnNewButton.setBorder(emptyBorder);

        panel.add(textField_1);
        textField_1.setColumns(10);
        

        
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setIcon(new ImageIcon("D:\\Downloads\\icons8-search-25.png"));
        btnNewButton_1.setBackground(new Color(246, 247, 249));
        btnNewButton.setBorder(emptyBorder);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton_1.setBounds(784, 24, 105, 34);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_2 = new JLabel("\t\t" + Login.loginedName);
        lblNewLabel_2.setIcon(new ImageIcon("/Users/seail/Desktop/imagefile/facebook_man_resize.png"));
        lblNewLabel_2.setBounds(1104, 6, 127, 68);
        panel.add(lblNewLabel_2);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(110, 100, 280, 900);
        scrollPane.setBackground(Color.WHITE);
        
        frame.getContentPane().add(scrollPane);
        
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(1050, 100, 280, 900);
        scrollPane_1.setBackground(Color.WHITE);
        frame.getContentPane().add(scrollPane_1);
        //mainWindow.add(contentPane);
        frame.setSize(1440, 1000);
		frame.setUndecorated(true);
        frame.setVisible(true);
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