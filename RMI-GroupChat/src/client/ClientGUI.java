package client;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * GUI Class for Group Chat
 */

public class ClientGUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;	
	private JPanel textPanel, inputPanel;
	private JTextField textField;
	private String name, message;
	private Font meiryoFont = new Font("Meiryo", Font.PLAIN , 14);
	private Border blankBorder = BorderFactory.createEmptyBorder(10,10,20,10);
	private Client chatClient;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    
    protected JTextArea textArea, userArea;
    protected JFrame frame;
    protected JButton privateMsgButton, startButton, sendButton;
    protected JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    protected JPanel clientPanel, userPanel,emojiPanel; 

	/**
	 * Main method to start client GUI app.
	 * @param args
	 */
	public static void main(String args[]){
		
		try{
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(Exception e){
			}
		new ClientGUI();
		}
	
	
	/**
	 * GUI Constructor
	 */
	public ClientGUI(){
			
		frame = new JFrame("Chat Console");	
	
		//-----------------------------------------
		/*
		 * intercept close method, inform server we are leaving.
		 */
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        
		    	if(chatClient != null){
			    	try {
			        	sendMessage("Bye all, I am leaving");
			        	chatClient.serverIF.leaveChat(name);
					} catch (RemoteException e) {
						e.printStackTrace();
					}		        	
		        }
		        System.exit(0);  
		    }   
		});
		
		//create frame elements
		Container c = getContentPane();
		JPanel outerPanel = new JPanel(new BorderLayout());
		
		outerPanel.add(getInputPanel(), BorderLayout.CENTER);
		outerPanel.add(getTextPanel(), BorderLayout.NORTH);
		outerPanel.add(makeButtonPanel(), BorderLayout.SOUTH);		
		
		c.setLayout(new BorderLayout());
		c.add(outerPanel, BorderLayout.CENTER);
		c.add(getUsersPanel(), BorderLayout.EAST);
		c.add(getEmojiPanel(),BorderLayout.SOUTH);
		frame.add(c);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setLocation(150, 150);
		textField.requestFocus();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	/**
	 * Method to set up the JPanel to display the chat text
	 * @return
	 */
	public JPanel getTextPanel(){
		String welcome = "Enter your name and press Start to begin\n";
		textArea = new JTextArea(welcome, 14, 34);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setFont(meiryoFont);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel = new JPanel();
		textPanel.add(scrollPane);
	
		textPanel.setFont(meiryoFont);
		return textPanel;
	}
	
	/**
	 * Method to build the panel with input field
	 * @return inputPanel
	 */
	public JPanel getInputPanel(){
		inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));	
		textField = new JTextField();
		textField.setFont(meiryoFont);
		textField.setColumns(30);
		sendButton = new JButton("Send ");
		sendButton.addActionListener(this);
		sendButton.setEnabled(false);
		inputPanel.add(textField);
		inputPanel.add(sendButton);
		return inputPanel;
	}
	
	/**
	 * Method to build emoji panel
	 * @return emojiPanel 
	 */
	public JPanel getEmojiPanel() {
		Icon icon1 = new ImageIcon("Images//b1.PNG");
		b1=new JButton(icon1); 
		b1.addActionListener(this);
		b1.setEnabled(false);
		Icon icon2 = new ImageIcon("Images//b2.PNG");
	    b2=new JButton(icon2);
	    b2.addActionListener(this);
	    b2.setEnabled(false);
	    Icon icon3 = new ImageIcon("Images//b3.PNG");
	    b3=new JButton(icon3);  
	    b3.addActionListener(this);
	    b3.setEnabled(false);
	    Icon icon4 = new ImageIcon("Images//b4.PNG");
	    b4=new JButton(icon4);  
	    b4.addActionListener(this);
	    b4.setEnabled(false);
	    Icon icon5 = new ImageIcon("Images//b5.PNG");
	    b5=new JButton(icon5);  
	    b5.addActionListener(this);
	    b5.setEnabled(false);
	    Icon icon6 = new ImageIcon("Images//b6.PNG");
	    b6=new JButton(icon6);  
	    b6.addActionListener(this);
	    b6.setEnabled(false);
	    Icon icon7 = new ImageIcon("Images//b7.PNG");
	    b7=new JButton(icon7);  
	    b7.addActionListener(this);
	    b7.setEnabled(false);
	    Icon icon8 = new ImageIcon("Images//b8.PNG");
	    b8=new JButton(icon8);  
	    b8.addActionListener(this);
	    b8.setEnabled(false);
	    Icon icon9 = new ImageIcon("Images//b9.PNG");
	    b9=new JButton(icon9);  
	    b9.addActionListener(this);
	    b9.setEnabled(false);
	    Icon icon10 = new ImageIcon("Images//b10.PNG");
	    b10=new JButton(icon10);  
	    b10.addActionListener(this);
	    b10.setEnabled(false);
	   
		emojiPanel= new JPanel(new GridLayout(2,5));
		emojiPanel.add(b1);
		emojiPanel.add(b2);
		emojiPanel.add(b3);
		emojiPanel.add(b4);
		emojiPanel.add(b5);
		emojiPanel.add(b6);
		emojiPanel.add(b7);
		emojiPanel.add(b8);
		emojiPanel.add(b9);
		emojiPanel.add(b10);
		emojiPanel.setBorder(blankBorder);
		return emojiPanel;
		
	}

	/**
	 * Method to build the panel displaying currently connected users
	 * with a call to the button panel building method
	 * @return
	 */
	public JPanel getUsersPanel(){
		
		userPanel = new JPanel(new BorderLayout());
		String  userStr = " Current Users      ";
		
		JLabel userLabel = new JLabel(userStr, JLabel.CENTER);
		userPanel.add(userLabel, BorderLayout.NORTH);	
		userLabel.setFont(new Font("Meiryo", Font.PLAIN, 16));

		String[] noClientsYet = {"No user"};
		setClientPanel(noClientsYet);

		clientPanel.setFont(meiryoFont);
		userPanel.setBorder(blankBorder);

		return userPanel;		
	}

	/**
	 * Populate current user panel with a 
	 * selectable list of currently connected users
	 * @param currClients
	 */
    public void setClientPanel(String[] currClients) {  	
    	clientPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<String>();
        
        for(String s : currClients){
        	listModel.addElement(s);
        }
        if(currClients.length > 2){
        	privateMsgButton.setEnabled(true);
        }
        
        //Create the list and put it in a scroll pane.
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(8);
        list.setFont(meiryoFont);
        JScrollPane listScrollPane = new JScrollPane(list);

        clientPanel.add(listScrollPane, BorderLayout.CENTER);
        userPanel.add(clientPanel, BorderLayout.CENTER);
    }
	
	/**
	 * Make the buttons and add the listener
	 * @return
	 */
	public JPanel makeButtonPanel() {		
		
		
        privateMsgButton = new JButton("Send PM");
        privateMsgButton.addActionListener(this);
        privateMsgButton.setEnabled(false);
		
		startButton = new JButton("Start ");
		startButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(new GridLayout());
		buttonPanel.add(startButton);
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(privateMsgButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,12,0,0));
		return buttonPanel;
	}
	
	
	/**
	 * Action handling on the buttons
	 */
	ActionListener listener = new ActionListener(){
        public void actionPerformed(ActionEvent event){
            textArea.setText("");
        }
    };
	@Override
	public void actionPerformed(ActionEvent e){

		try {
			//get connected to chat service
			if(e.getSource() == startButton){
				name = textField.getText();				
				if(name.length() != 0){
						
					frame.setTitle(name + "'s console ");
							
				    
					getConnected(name);
					if(!chatClient.connectionProblem){
						Timer timer = new Timer(3000, listener);
					    timer.setRepeats(false);
					    timer.start();
					    textField.setText("");
						startButton.setEnabled(false);
						sendButton.setEnabled(true);
						b1.setEnabled(true);
						b2.setEnabled(true);
						b3.setEnabled(true);
						b4.setEnabled(true);
						b5.setEnabled(true);
						b6.setEnabled(true);
						b7.setEnabled(true);
						b8.setEnabled(true);
						b9.setEnabled(true);
						b10.setEnabled(true);
						}
				}
				else{
					JOptionPane.showMessageDialog(frame, "Enter your name to Start");
				}
			}

			//get text and clear textField
			if(e.getSource() == sendButton){
				message = textField.getText();
				textField.setText("");
				sendMessage(message);
				System.out.println("Sending message : " + message);
			}
			
			//send a private message, to selected users
			if(e.getSource() == privateMsgButton){
				int[] privateList = list.getSelectedIndices();
				
				for(int i=0; i<privateList.length; i++){
					System.out.println("selected index :" + privateList[i]);
				}
				message = textField.getText();
				textField.setText("");
				sendPrivate(privateList);
			}
			
			/**
			 * send emoji
			 */
			
			//grinning face (U+1F600)
			if(e.getSource()==b1) {
				message="\uD83D\uDE00";
				sendMessage(message);
			}
			
			//'FACE WITH TEARS OF JOY' (U+1F602)
			if(e.getSource()==b2) {
				message="\uD83D\uDE02";
				sendMessage(message);
			}
			
			//'WINKING FACE' (U+1F609)
			if(e.getSource()==b3) {
				message="\uD83D\uDE09";
				sendMessage(message);
			}
			
			//'SMILING FACE WITH SUNGLASSES' (U+1F60E)
			if(e.getSource()==b4) {
				message="\uD83D\uDE0E";
				sendMessage(message);
			}
			
			//'SMILING FACE WITH HEART-SHAPED EYES' (U+1F60D)
			if(e.getSource()==b5) {
				message="\uD83D\uDE0D";
				sendMessage(message);
			}
			
			//'WHITE FROWNING FACE' (U+2639)
			if(e.getSource()==b6) {
				message="\u2639";
				sendMessage(message);
			}
			
			//'FACE SAVOURING DELICIOUS FOOD' (U+1F60B)
			if(e.getSource()==b7) {
				message="\uD83D\uDE0B";
				sendMessage(message);
			}
			
			//'NEUTRAL FACE' (U+1F610)
			if(e.getSource()==b8) {
				message="\uD83D\uDE10";
				sendMessage(message);
			}
			
			//'RELIEVED FACE' (U+1F60C)
			if(e.getSource()==b9) {
				message="\uD83D\uDE0C";
				sendMessage(message);
			}
			
			//'CONFOUNDED FACE' (U+1F616)
			if(e.getSource()==b10) {
				message="\uD83D\uDE16";
				sendMessage(message);
			}
			
			
		}
		catch (RemoteException remoteExc) {			
			remoteExc.printStackTrace();	
		}
		
	}
	
	/**
	 * Make the connection to the chat server
	 * @param userName
	 * @throws RemoteException
	 */
	private void getConnected(String userName) throws RemoteException{
		//remove whitespace and non word characters to avoid malformed url
		String cleanedUserName = userName.replaceAll("\\s+","_");
		cleanedUserName = userName.replaceAll("\\W+","_");
		try {		
			chatClient = new Client(this, cleanedUserName);
			chatClient.startClient();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Send a message, to be relayed to all chatters
	 * @param chatMessage
	 * @throws RemoteException
	 */
	private void sendMessage(String chatMessage) throws RemoteException {
		chatClient.serverIF.updateChat(name, chatMessage);
	}

	/**
	 * Send a message, to be relayed, only to selected chatters
	 * @param chatMessage
	 * @throws RemoteException
	 */
	private void sendPrivate(int[] privateList) throws RemoteException {
		String privateMessage = "[PM from " + name + "] :" + message + "\n";
		chatClient.serverIF.sendPM(privateList, privateMessage);
	}
	

}//end class
