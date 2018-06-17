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
import java.sql.SQLException;
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
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class MyPage_TestBed extends JFrame {

    public Connection con = null;
    public PreparedStatement st = null;
    public ResultSet rs = null;
	ArrayList<String> myContents = new ArrayList<>();
	ArrayList<String> friends = new ArrayList<>();
//	ArrayList<String> myName = new ArrayList<>();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private int FullrowCnt = 0;
	private int rowCnt = 0;
	private int friendCnt = 0;

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
        SpringLayout Friendlayout = new SpringLayout();
        SpringLayout Searchlayout = new SpringLayout();
        
        JPanel mainPanel = new JPanel();
        JPanel friendPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        
        searchPanel.setBackground(Color.WHITE);
        
        mainPanel.setLayout(layout);
        friendPanel.setLayout(Friendlayout);
        searchPanel.setLayout(Searchlayout);

        DBConnection();
		
		try {
			String ContentCount = "select count(*) from myinfo";
			st = con.prepareStatement(ContentCount);
			rs = st.executeQuery();
				while(rs.next()) {
					FullrowCnt = rs.getInt(1);
				};
			
			String myContentCount = "select count(*) from myinfo where myUserID = ?"; 
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
			
			String FriendCount = "select count(*) from friendlist where userid = ?"; // 친구 수 가져오기
			st = con.prepareStatement(FriendCount);
			st.setString(1, userID);
			rs = st.executeQuery();
				while(rs.next()) {
					friendCnt = rs.getInt(1);
				}
				
			String FriendList = "select friendID from friendlist where userid = ?"; // 친구 아이디 가져오기
			st = con.prepareStatement(FriendList);
			st.setString(1, userID);
			rs = st.executeQuery();
				while(rs.next()) {
					friends.add(rs.getString("friendID"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        int j = 300;
//        int initialHeight = 0;
//        int initialX = 0;
        int scrollHeight = 0;
        
        	JPanel Write = new JPanel();
        	layout.putConstraint(SpringLayout.NORTH, Write, 0, SpringLayout.NORTH, mainPanel);
        	layout.putConstraint(SpringLayout.WEST, Write, 0, SpringLayout.WEST, mainPanel);
        	
        	Write.setPreferredSize(new Dimension(1000, 290));
        	Write.setBackground(Color.WHITE);
        	mainPanel.add(Write);
        	Write.setLayout(null);
        	
        	JLabel Tester = new JLabel("Test");
        	Tester.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
        	Tester.setBounds(0, 0, 50, 50);
        	Write.add(Tester);
        	
        	textField = new JTextField();
        	textField.setBounds(50, 50, 928, 140);
        	Write.add(textField);
        	textField.setColumns(10);
        	
        	JLabel lblNewLabel_1 = new JLabel(userName+"\uB2D8, \uBB34\uC2A8 \uC0DD\uAC01\uC744 \uD558\uC2DC\uACE0 \uACC4\uC2E0\uAC00\uC694?");
        	lblNewLabel_1.setBounds(50, 0, 590, 50);
        	Write.add(lblNewLabel_1);
        	
        	for(int k = 0; k < friendCnt; k++) { // 친구목록 가지고오기
    			JPanel Friend = new JPanel();
    			
    			GridBagLayout Friend_Layout = new GridBagLayout();
    			Friend.setBackground(Color.WHITE);
    			Friend.setOpaque(true);
    			Friend.setBorder(BorderFactory.createEmptyBorder());
    			
    			Friend_Layout.columnWidths = new int[]{50, 0, 0};
    			Friend_Layout.rowHeights = new int[]{50, 0};
    			Friend_Layout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    			Friend_Layout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
    			Friend.setLayout(Friend_Layout);
    			
    			JLabel lblNewLabel_10 = new JLabel("");
        		lblNewLabel_10.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
    			GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
    			gbc_lblNewLabel_10.insets = new Insets(0, 0, 0, 5);
    			gbc_lblNewLabel_10.gridx = 0;
    			gbc_lblNewLabel_10.gridy = 0;
    			Friend.add(lblNewLabel_10, gbc_lblNewLabel_10);
    			
    			JLabel lblNewLabel_11 = new JLabel(friends.get(k));
    			GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
    			gbc_lblNewLabel_11.gridx = 1;
    			gbc_lblNewLabel_11.gridy = 0;
    			Friend.add(lblNewLabel_11, gbc_lblNewLabel_11);
    			
    		      Friendlayout.putConstraint(SpringLayout.NORTH, Friend, scrollHeight, SpringLayout.NORTH,
                          contentPane);			
    		      
    		      scrollHeight += 50;
    		      Friend.setPreferredSize(new Dimension(280, 50));
    		      friendPanel.add(Friend);
    		    friendPanel.setPreferredSize(new Dimension(280, scrollHeight));
    		}
        	
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
		    		String PerfectText = ""; // 
		    		
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
        scroll.setViewportView(searchPanel);
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
        
        JButton btnNewButton_1 = new JButton(""); // 검색버튼
        btnNewButton_1.addMouseListener(new MouseAdapter() { // 검색기능
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		
				if(searchPanel.isShowing()) {
			    	searchPanel.removeAll();
			    	searchPanel.repaint();
				}

        		ResultSet SearchResult = Search();
        		
        		int height = 0;
                scroll.setViewportView(searchPanel);
        	try {
        		if(Search().next()) { // 찾는 사람이 존재한다면

        			while(SearchResult.next()) {

					JPanel SearchModeler = new JPanel();

					SearchModeler.setBackground(Color.WHITE);
 		
					GridBagLayout gbl_panel = new GridBagLayout();
					gbl_panel.columnWidths = new int[]{50, 346, 0};
					gbl_panel.rowHeights = new int[]{0, 0};
					gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
					gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
					SearchModeler.setLayout(gbl_panel);
					
					JLabel lblNewLabel = new JLabel("");
					lblNewLabel.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
					gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
					gbc_lblNewLabel.gridx = 0;
					gbc_lblNewLabel.gridy = 0;
					SearchModeler.add(lblNewLabel, gbc_lblNewLabel);
					
					JLabel lblNewLabel_1 = new JLabel(SearchResult.getString(1)+"("+SearchResult.getString(2)+")");
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.gridx = 1;
					gbc_lblNewLabel_1.gridy = 0;
					SearchModeler.add(lblNewLabel_1, gbc_lblNewLabel_1);    	
        			String SrchName = SearchResult.getString(2);

					if(friends.contains(SearchResult.getString(2))) {
						JButton lblNewButton_1 = new JButton("친구입니다.");
						GridBagConstraints gbc_lblNewButton_1 = new GridBagConstraints();
						lblNewButton_1.setEnabled(false);
						gbc_lblNewButton_1.gridx = 2;
						gbc_lblNewButton_1.gridy = 0;
						SearchModeler.add(lblNewButton_1, gbc_lblNewButton_1);
						
					}	
					else if(SrchName.toString().equals(userID.toString())) {
						JButton lblNewButton_1 = new JButton("자기 자신입니다.");
								lblNewButton_1.setEnabled(false);
						GridBagConstraints gbc_lblNewButton_1 = new GridBagConstraints();
						gbc_lblNewButton_1.gridx = 2;
						gbc_lblNewButton_1.gridy = 0;
						SearchModeler.add(lblNewButton_1, gbc_lblNewButton_1);						
						
					} else
						
					{ 
						JButton lblNewButton_1 = new JButton("친구 추가");
				        lblNewButton_1.addActionListener(new ActionListener() {
				        	public void actionPerformed(ActionEvent arg0) {
				        		
				        		try {
				        		String SQL = "insert into pendingfriend values (?, ?)";
				        		PreparedStatement pstmtInsert2 = con.prepareStatement(SQL);
				        		
				        		pstmtInsert2.setString(1, userID);
				        		pstmtInsert2.setString(2, SrchName);
				        		pstmtInsert2.executeUpdate();
				        		
				        		lblNewButton_1.setText("신청했습니다.");
				        		lblNewButton_1.setEnabled(false);
				        		} catch (SQLException e) {
				        		lblNewButton_1.setText("이미 신청했습니다.");
				        		lblNewButton_1.setEnabled(false);
				        		}
				        	}
				        });
						GridBagConstraints gbc_lblNewButton_1 = new GridBagConstraints();
						gbc_lblNewButton_1.gridx = 2;
						gbc_lblNewButton_1.gridy = 0;
						SearchModeler.add(lblNewButton_1, gbc_lblNewButton_1);						
					}
					
				      Searchlayout.putConstraint(SpringLayout.NORTH, SearchModeler, height, SpringLayout.NORTH,
				              contentPane);

				      height += 50;
				      searchPanel.add(SearchModeler);
				    	SearchModeler.setPreferredSize(new Dimension(640, 50));
        			}

				}
				 else { // 찾는 사람이 존재하지 않으면
					 	
//					scroll.setViewportView(NothingPanel);
//	        			NothingPanel.setBackground(Color.WHITE);
	        			
	        		}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            searchPanel.setPreferredSize(new Dimension(searchPanel.getWidth(), height));

        	}
        });
        
        btnNewButton_1.setIcon(new ImageIcon("D:\\Downloads\\icons8-search-25.png"));
        btnNewButton_1.setBackground(new Color(246, 247, 249));
        btnNewButton_1.setBounds(784, 24, 105, 34);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(50, 200, 50, 40);
        Write.add(lblNewLabel_3);
        
        JLabel label = new JLabel("");
        label.setBounds(100, 200, 400, 40);
        Write.add(label);
        btnNewButton_1.setBounds(784, 24, 105, 34);
        panel.add(btnNewButton_1);
        
//      JButton btnNewButton_2 = new JButton("\uAC8C\uC2DC");
//	    	btnNewButton_2.setForeground(Color.BLACK);
//	    	btnNewButton_2.setBackground(new Color(66, 103, 178));
//	    	btnNewButton_2.setBounds(50, 250, 928, 30);
//	    	btnNewButton_2.setContentAreaFilled(false);
//	    	Write.add(btnNewButton_2);
	    	
        JButton btnNewButton_2 = new JButton("\uAC8C\uC2DC\uD558\uAE30"); //글 게시 버튼
        btnNewButton_2.setForeground(Color.BLACK);
	    	btnNewButton_2.setBackground(new Color(66, 103, 178));
	    	btnNewButton_2.setBounds(50, 250, 928, 27);
	    	btnNewButton_2.setContentAreaFilled(false);
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		InsertmyContent(userID, textField.getText());
        		lblNewLabel_3.setIcon(new ImageIcon("D:\\Downloads\\icons8-checked-40.png"));
        		label.setForeground(Color.BLUE);
        		label.setText("성공적으로 입력되었습니다.");
        	}
        });
        Write.add(btnNewButton_2);
        
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
        
        JScrollPane scrollProfile = new JScrollPane(); // 프로필 
        scrollProfile.setBounds(110, 100, 280, 400);
        scrollProfile.setBackground(Color.WHITE);
        
        frame.getContentPane().add(scrollProfile);
        
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(null);
        scrollProfile.setViewportView(profilePanel);
        
        JLabel lblNewLabel_4 = new JLabel("자신을 소개해보세요.");
        lblNewLabel_4.setLocation(16, 6);
        lblNewLabel_4.setSize(127, 60);
        profilePanel.add(lblNewLabel_4);

        
        JTextArea txtAreaProfile = new JTextArea();
        txtAreaProfile.setBounds(16, 64, 250, 300);
        profilePanel.add(txtAreaProfile);
        txtAreaProfile.setText("this is txtarea");
        
        JLabel lblLabelProfile = new JLabel("this is label");
        lblLabelProfile.setBounds(16, 64, 250, 300);
        profilePanel.add(lblLabelProfile);
        lblLabelProfile.setVerticalAlignment(SwingConstants.TOP);
        txtAreaProfile.setVisible(false);
        
        JButton btnProfileUpdate = new JButton("확인");
        
        JButton btnProfileUpdateFocus = new JButton("추가");
        btnProfileUpdateFocus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { // 추가 버튼 클릭, 프로필 내용 업데이트 하는 화면으로 전환
        		String profileText = lblLabelProfile.getText();
        		txtAreaProfile.setVisible(true);
//        		txtAreaProfile.setText(profileText);
        		txtAreaProfile.setText(profileText.replaceAll("<html>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br/>", "\n").replaceAll("</html>", ""));
        		btnProfileUpdateFocus.setEnabled(false);
        		btnProfileUpdate.setVisible(true);
        	}
        });
        btnProfileUpdateFocus.setBounds(127, 23, 117, 29);
        profilePanel.add(btnProfileUpdateFocus);
        
        btnProfileUpdate.setVisible(false);
        btnProfileUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { // 확인 버튼 클릭, 프로필 내용 업데이트
        		String updateText = txtAreaProfile.getText();
        		updatemyProfile(userID, updateText); // 프로필 내용 업데이트 SQL
        		lblLabelProfile.setText("<html>" + updateText.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>"); // 작성한 내용들 lblLabelProfile에 추가
        		txtAreaProfile.setVisible(false);
        		lblLabelProfile.setVisible(true);
        		btnProfileUpdate.setVisible(false);
        		btnProfileUpdateFocus.setEnabled(true);
        	}
        });
        btnProfileUpdate.setBounds(153, 361, 117, 29);
        profilePanel.add(btnProfileUpdate);
        selectMyProfile(userID, lblLabelProfile);
        
        JScrollPane friendScroll = new JScrollPane(); // 친구 리스트
        friendScroll.setBounds(110, 550, 280, 430);
        friendScroll.setBackground(Color.WHITE);
        
        frame.getContentPane().add(friendScroll);
        
        friendScroll.setViewportView(friendPanel);
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
	
		public void InsertmyContent(String userID, String myContent) {
		  try {
		  	String InsertSQL = "insert into myinfo(myContentID, myUserID, myContent) values (?, ?, ?)";
//		  	PreparedStatement Insertpstmt = con.prepareStatement(InsertSQL);
		  	st = con.prepareStatement(InsertSQL);
		  	st.setInt(1, FullrowCnt+1);
		  	st.setString(2, userID);
		  	st.setString(3, myContent);
		  	st.executeUpdate();
		  	
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
		
	  public void updatemyProfile(String userID, String updateText) {
		  try {
				 String SQL = "UPDATE profile SET profileContent = ? WHERE userID = ?";
//				  PreparedStatement pstmt = con.prepareStatement(SQL);
				 PreparedStatement st = con.prepareStatement(SQL);
				 st.setString(1, updateText);
				 st.setString(2, userID);
				 st.executeUpdate();
			 } catch (Exception e) {
				  e.printStackTrace();
			  }
		 }
	
	  public void selectMyProfile(String userID, JLabel lblLabelProfile) {
		  try {
			  String SQL = "SELECT profileContent FROM profile WHERE userID = ?";
//			  PreparedStatement pstmt = con.prepareStatement(SQL);
			  PreparedStatement st = con.prepareStatement(SQL);
			  st.setString(1, userID);
			  rs = st.executeQuery();
			  if(rs.next()) {
				  String profileContent = rs.getString("profileContent");
				  lblLabelProfile.setText("<html>" + profileContent.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>"); // 작성한 내용들 lblLabelProfile에 추가
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	 }
	  
	  public ResultSet Search() { // 검색
		  try {
			  String SearchSQL = "select concat(surname, name), id from member where concat(surname, name) like ?";
			  st = con.prepareStatement(SearchSQL);
			  st.setString(1, "%" + textField_1.getText() + "%");
			  
			  ResultSet Searchrs = st.executeQuery();
//			  while(Searchrs.next()) {
//				  System.out.println(Searchrs.getString(1));
//			  }
			  return Searchrs;		  
	       } catch (Exception e) {
	    	   e.printStackTrace();
	    	   return null;
	       }
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