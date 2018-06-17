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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Choice;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class MainPage_TestBed extends JFrame {

    public Connection con = null;
    public PreparedStatement st = null;
    public ResultSet rs = null;
	ArrayList<String> contents = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	ArrayList<String> friends = new ArrayList<>();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int FullrowCnt = 0;
	
	private JLabel lblNewLabel_3;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                 MainPage_TestBed mn = new MainPage_TestBed("alabester@naver.com", "AA");
            }
        });
    }
	/**
	 * Create the frame.
	 */
//	public MainPage_TestBed() {}
	
	public MainPage_TestBed(String userID, String userName) {

        JFrame frame = new JFrame("SpringLayout");
        frame.getContentPane().setBackground(new Color(233, 235, 238));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(400, 100, 640, 900);
        Container contentPane = frame.getContentPane();
        
        SpringLayout layout = new SpringLayout();
        SpringLayout Searchlayout = new SpringLayout(); 
        SpringLayout Friendlayout = new SpringLayout();
        SpringLayout Pendinglayout = new SpringLayout();
        
        JPanel mainPanel = new JPanel();
        JPanel friendPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel NothingPanel = new JPanel();
        JPanel pendingPanel = new JPanel();
        
        searchPanel.setBackground(Color.WHITE);

        JScrollPane friendScroll = new JScrollPane();
        friendScroll.setBounds(1050, 100, 280, 900);
        friendScroll.setBackground(Color.WHITE);
        
        mainPanel.setLayout(layout);
        searchPanel.setLayout(Searchlayout);
        NothingPanel.setLayout(null);
        friendPanel.setLayout(Friendlayout);
        pendingPanel.setLayout(Pendinglayout);
        
        DBConnection();

		int rowCnt = 0;
		int friendCnt = 0;
		
		try {

			String ContentCount = "select count(*) from content";
			st = con.prepareStatement(ContentCount);
			rs = st.executeQuery();
				while(rs.next()) {
					FullrowCnt = rs.getInt(1);
				};
			
			String FriendCount = "select count(*) from friendlist where userid = ?";
			st = con.prepareStatement(FriendCount);
			st.setString(1, userID);
			rs = st.executeQuery();
				while(rs.next()) {
					friendCnt = rs.getInt(1);
				}
				
			String FriendList = "select friendID from friendlist where userid = ?";
			st = con.prepareStatement(FriendList);
			st.setString(1, userID);
			rs = st.executeQuery();
				while(rs.next()) {
					friends.add(rs.getString("friendID"));
				}
			
				
			String Count = "select count(*) from content where userid in (select friendID from friendlist where userid = ?) ";
			
			st = con.prepareStatement(Count);
			st.setString(1, userID);
			rs = st.executeQuery();
				while(rs.next()) {
					rowCnt = rs.getInt(1);
				}

			String SQL = "select content, userid from content where userid in (select friendID from friendlist where userid = ?)";
			
			st = con.prepareStatement(SQL);
			st.setString(1, userID);
			rs = st.executeQuery();
			
			while(rs.next())
				{
				contents.add(rs.getString("content"));
				names.add(rs.getString("userID"));
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        int j = 300;
        int initialHeight = 0;
        int initialX = 0;
        
        int scrollHeight = 0;

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
        	textField.setBounds(50, 50, 580, 140);
        	Write.add(textField);
        	textField.setColumns(10);
        	
        	JLabel lblNewLabel_1 = new JLabel(userName+"\uB2D8, \uBB34\uC2A8 \uC0DD\uAC01\uC744 \uD558\uC2DC\uACE0 \uACC4\uC2E0\uAC00\uC694?");
        	lblNewLabel_1.setBounds(50, 0, 590, 50);
        	Write.add(lblNewLabel_1);
        	
			
			JLabel Nothing = new JLabel("해당하는 정보의 사람이 없습니다. 다르게 검색해 보시는게 어떠세요?");
        	Nothing.setHorizontalAlignment(SwingConstants.CENTER);
			Nothing.setBounds(0,0,640,150);
			NothingPanel.add(Nothing);

		for(int k = 0; k < friendCnt; k++) {
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
//        	try {
//       		
//        	} catch (Exception e) {
//        		e.printStackTrace();
//        	}

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
    		
    		String StartText = "", RemainText = ""; // 70자로 나눈 문자열의 내용, 나머지 문자열의 내용
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
        frame.getContentPane().setLayout(null);
        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), j));
        scroll.setPreferredSize(new Dimension(100,100));
        scroll.setViewportView(searchPanel);
        scroll.setViewportView(mainPanel);
        friendScroll.setViewportView(friendPanel);

        scroll.setBackground(new Color(233, 235, 238));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        
        JScrollBar vertical = scroll.getVerticalScrollBar();
        vertical.setPreferredSize(new Dimension(0,0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(1150, 55, 225, 70);
        frame.getContentPane().add(panel_1);
    	panel_1.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,0,0,new Color(233, 235, 238)),new EtchedBorder()));
    	GridBagLayout gbl_panel_1 = new GridBagLayout();
    	gbl_panel_1.columnWidths = new int[]{198, 0, 0};
    	gbl_panel_1.rowHeights = new int[]{0, 0, 0};
    	gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    	gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    	panel_1.setLayout(gbl_panel_1);
    	
    	JLabel lblNewLabel_5 = new JLabel("\uB85C\uADF8\uC544\uC6C3");
    	lblNewLabel_5.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg1) {
    			frame.dispose();
    			new Login();
    		}
    	});
    	GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
    	gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_5.gridx = 0;
    	gbc_lblNewLabel_5.gridy = 0;
    	panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
    	
    	JLabel lblNewLabel_6 = new JLabel("\uC0C8 \uCE5C\uAD6C");
    	lblNewLabel_6.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			try {
    			ArrayList<String> Pending = new ArrayList<>();
    			
    			String PendingFriendSQL = "select user_id from pendingfriend where target_id = ?";
    			PreparedStatement SQLpstmt = con.prepareStatement(PendingFriendSQL);
    			SQLpstmt.setString(1, userID);
    			ResultSet Pendingrs = SQLpstmt.executeQuery();
    			

    			while(Pendingrs.next()) {

    				Pending.add(Pendingrs.getString(1));
    			}
    			
    			int pendingHeight = 0;
    			
    				for(int z = 0; z < CheckPendingFriends(userID); z++) {
    					JPanel PendingModeler = new JPanel();
    					PendingModeler.setBackground(Color.WHITE);
    					        		
    					GridBagLayout gbl_panel = new GridBagLayout();
    					gbl_panel.columnWidths = new int[]{50, 346, 0};
    					gbl_panel.rowHeights = new int[]{0, 0};
    					gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
    					gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
    					PendingModeler.setLayout(gbl_panel);
    					
    					JLabel lblNewLabel = new JLabel("");
    					lblNewLabel.setIcon(new ImageIcon("D:\\Downloads\\icons8-user-avatar-filled-50.png"));
    					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    					gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
    					gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
    					gbc_lblNewLabel.gridx = 0;
    					gbc_lblNewLabel.gridy = 0;
    					PendingModeler.add(lblNewLabel, gbc_lblNewLabel);
    					
    					JLabel lblNewLabel_1 = new JLabel(Pending.get(0));
    					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
    					gbc_lblNewLabel_1.gridx = 1;
    					gbc_lblNewLabel_1.gridy = 0;
    					PendingModeler.add(lblNewLabel_1, gbc_lblNewLabel_1);    	
         
    					JButton lblNewLabel_2 = new JButton("수락");
    					lblNewLabel_2.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent arg0) {
    							try {
    							String SQL = "delete from pendingfriend where user_id = ? and target_id = ?";
    							PreparedStatement pstmtDelete = con.prepareStatement(SQL);
    							
    							pstmtDelete.setString(1, userID);
    							pstmtDelete.setString(2, lblNewLabel_1.getText());
    							
    							pstmtDelete.executeUpdate();
    							
    							SQL = "delete from pendingfriend where target_id = ? and user_id = ?";
    							pstmtDelete = con.prepareStatement(SQL);
    							
    							pstmtDelete.setString(1, userID);
    							pstmtDelete.setString(2, lblNewLabel_1.getText());
    							
    							pstmtDelete.executeUpdate();
    							
    							SQL = "insert into friendlist values (?, ?)";
    									
    							PreparedStatement pstmtInsert = con.prepareStatement(SQL);
    							
    							pstmtInsert.setString(1, userID);
    							pstmtInsert.setString(2, lblNewLabel_1.getText());
    							
    							pstmtInsert.executeUpdate();
    							
    							SQL = "insert into friendlist values (?, ?)";
    							
    							pstmtInsert = con.prepareStatement(SQL);
    							
    							pstmtInsert.setString(1, lblNewLabel_1.getText());
    							pstmtInsert.setString(2, userID);
    							
    							pstmtInsert.executeUpdate();
    							
    							frame.dispose();
    							new MainPage_TestBed(userID, userName);
    							
    							
    							} catch (Exception e) {
    								e.printStackTrace();
    							}
    							
    						
    						}
    					});
    					GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
    					gbc_lblNewLabel_2.gridx = 2;
    					gbc_lblNewLabel_2.gridy = 0;
    					PendingModeler.add(lblNewLabel_2, gbc_lblNewLabel_2);
    					Pendinglayout.putConstraint(SpringLayout.NORTH, PendingModeler, pendingHeight, SpringLayout.NORTH,
    				              contentPane);

    				      pendingHeight += 50;
    				      pendingPanel.add(PendingModeler);
    				      PendingModeler.setPreferredSize(new Dimension(640, 50));
    				}
    			scroll.setViewportView(pendingPanel);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    			
    			
    		}
    	});
    	GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
    	gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
    	gbc_lblNewLabel_6.gridx = 0;
    	gbc_lblNewLabel_6.gridy = 1;
    	panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
    	
    	JLabel lblNewLabel_7 = new JLabel("%d");
    	GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
    	gbc_lblNewLabel_7.gridx = 1;
    	gbc_lblNewLabel_7.gridy = 1;
    	panel_1.add(lblNewLabel_7, gbc_lblNewLabel_7);
    	lblNewLabel_7.setText(Integer.toString(CheckPendingFriends(userID)));
        panel_1.setVisible(false);
        
        contentPane.add(scroll);
        mainPanel.setBackground(new Color(233, 235, 238));

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBounds(0, 0, 1440, 80);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(72,103,170));
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		scroll.setViewportView(mainPanel);
        		
        	}
        });
        lblNewLabel.setIcon(new ImageIcon("D:\\Downloads\\icons8-facebook-48.png"));
        lblNewLabel.setBounds(110, 12, 56, 56);
        panel.add(lblNewLabel);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        
        textField_1 = new JTextField();
        textField_1.setBounds(184, 24, 600, 34);

        panel.add(textField_1);
        textField_1.setColumns(10);
        

        
        
        JButton btnNewButton_1 = new JButton(""); // 검색버튼
        btnNewButton_1.addActionListener(new ActionListener() { // 검색기능
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
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
        			String SrchName = SearchResult.getString(2);
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
				        		System.out.println(userID);
				        		pstmtInsert2.setString(2, SrchName);
				        		System.out.println(SrchName);
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
					 	
					 	scroll.setViewportView(NothingPanel);
	        			NothingPanel.setBackground(Color.WHITE);
	        			
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
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(50, 200, 50, 40);
        Write.add(lblNewLabel_3);
                
        JLabel label = new JLabel("");
        label.setBounds(100, 200, 400, 40);
        Write.add(label);
        btnNewButton_1.setBounds(784, 24, 105, 34);
        panel.add(btnNewButton_1);
        
        
        JButton btnNewButton_2 = new JButton("\uAC8C\uC2DC\uD558\uAE30");
        btnNewButton_2.setBackground(new Color(72,103,170));
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		InsertContent(userID, textField.getText());
                lblNewLabel_3.setIcon(new ImageIcon("D:\\Downloads\\icons8-checked-40.png"));
        		label.setForeground(Color.BLUE);
        		label.setText("성공적으로 입력되었습니다.");
        	}
        });
        btnNewButton_2.setBounds(50, 250, 580, 27);
        Write.add(btnNewButton_2);
                
        JLabel lblNewLabel_2 = new JLabel(userName + "("+userID+")");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        		new MyPage_TestBed(userID, userName);
        	}
        });
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(974, 24, 281, 34);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_4 = new JLabel("Label4 입니다");
        lblNewLabel_4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if(!panel_1.isVisible()) {
        			
        			panel_1.setVisible(true);
        		
        		} else {
        			panel_1.setVisible(false);
        		}
        	}
        });
        lblNewLabel_4.setIcon(new ImageIcon("D:\\Downloads\\icons8-menu-24.png"));
        lblNewLabel_4.setBounds(1347, 14, 30, 48);
        panel.add(lblNewLabel_4);
        
        JScrollPane scrollPane = new JScrollPane(); 
        scrollPane.setBounds(110, 100, 280, 900);
        scrollPane.setBackground(Color.WHITE);
        
        frame.getContentPane().add(scrollPane);
        
        

        frame.getContentPane().add(friendScroll);
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
	  public ResultSet Search() {
		  try {
			  String SearchSQL = "select concat(surname, name), id from member where concat(surname, name) like ?";
			  PreparedStatement searchpstmt = con.prepareStatement(SearchSQL);
			  searchpstmt.setString(1, "%" + textField_1.getText() + "%");
			  
			  ResultSet Searchrs = searchpstmt.executeQuery();
//			  while(Searchrs.next()) {
//				  System.out.println(Searchrs.getString(1));
//			  }
			  return Searchrs;		  
	       } catch (Exception e) {
	    	   e.printStackTrace();
	    	   return null;
	       }

	  }
	  
	  public void InsertContent(String userID, String content) {
		  try {
		  	String InsertSQL = "insert into content values (?, ?, ?)";
		  	PreparedStatement Insertpstmt = con.prepareStatement(InsertSQL);
		  	Insertpstmt.setInt(1, FullrowCnt+1);
		  	Insertpstmt.setString(2, userID);
		  	Insertpstmt.setString(3, content);
		  	Insertpstmt.executeUpdate();
		  	
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	  
	  public int CheckPendingFriends(String userID) {
		  try {
		  String CountFriendsSQL = "select count(*) from pendingfriend where target_id = ?";
		  PreparedStatement Countpstmt = con.prepareStatement(CountFriendsSQL);
		  Countpstmt.setString(1, userID);
		  ResultSet Countrs;
		  int Counter = 0;
		  
		  Countrs = Countpstmt.executeQuery();
		  	while(Countrs.next()) {
		  		Counter = Countrs.getInt(1);
		  	}
		  return Counter;
		  
	  } catch (Exception e) {
		  e.printStackTrace();
		  return 0;
	  }
	  }
	}