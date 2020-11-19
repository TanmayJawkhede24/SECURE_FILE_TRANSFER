

import java.awt.EventQueue;
import java.awt.Font;


import javax.swing.JFrame;

import java.awt.List;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frame {
	ServerSocket server ;
	ArrayList <String> list = new ArrayList<String>();
//	ArrayList <String> listT = new ArrayList<String>();
	LinkedHashSet<String> hashSet = new LinkedHashSet<String>();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame window = new frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public frame() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
	  	frame = new JFrame();
	  	frame.setResizable(false);
		frame.setBounds(100, 100, 1057, 538);
      	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(null);

JPanel panel = new JPanel();
panel.setBackground(Color.LIGHT_GRAY);
panel.setBounds(329, 13, 396, 465);
frame.getContentPane().add(panel);
panel.setLayout(null);

JLabel lblListOfActive = new JLabel("LIST OF ACTIVE RECEIVER");
lblListOfActive.setBounds(129, 13, 201, 32);
panel.add(lblListOfActive);
JPanel panel_1 = new JPanel();
panel_1.setBounds(129, 154, 153, 22);
panel.add(panel_1);
;
 
 final JButton btnStart = new JButton("Start");
 
 final JButton btnStop = new JButton("Stop");
 btnStop.setEnabled(false);
   final List l1 = new List();
 

JLabel lblStaus = new JLabel("Staus   -");
lblStaus.setBounds(119, 125, 56, 16);
panel.add(lblStaus);

final JLabel lblStarted = new JLabel("");
lblStarted.setBounds(207, 125, 75, 16);
panel.add(lblStarted);

 btnStart.addActionListener(new ActionListener() {
 	public void actionPerformed(ActionEvent arg0) {
 		new Thread(new Runnable() {				
			 private ObjectOutputStream out;
			  private ObjectInputStream in;
			 		       
           public void run() {
        	 	try {
        	 		
        	 		btnStart.setEnabled(false);
        	 		 btnStop.setEnabled(true);
        	 		 lblStarted.setText("Started");
                     lblStarted.setForeground (Color.blue);
                     //start sever on port 9200
                     server = new ServerSocket(9200);
				     try
				     {
					  while(true)
	                    {
						//start sever on port 9200
						  Socket  socket= server.accept();
						
	               	      in = new ObjectInputStream(socket.getInputStream());		                    
	                    
	               	      SEND sendData=(SEND) in.readObject();
	                     String status=sendData.getStatus();
	                     //handle request according to satatus
	                     if(status.equalsIgnoreCase("Initialize"))
	                     {
	                    	 //make new entry if not present in server list
	                    	 if (hashSet.add(sendData.getIP()))
	                    	 {
	                    	 list.add(sendData.getName() + " : "+sendData.getIP());
	                    	// listT.add(sendData.getIP()); 
	                    	 }
	                     }
	                     else if(status.equalsIgnoreCase("getActiveRecvr"))
	                     {
	                    	//to provide active rcvr to sender
	                    	
	                    	  SEND replyData = new SEND();
	                    	  replyData.setActiveRcvr(list);
	                    	  System.out.println("sendData");
	                    	  out = new ObjectOutputStream(socket.getOutputStream());
	                    	  out.writeObject(replyData);
		                      out.flush();
	                    
	                     }
	                     else if(status.equalsIgnoreCase("stopped"))
	                     {
	                    	//remove entry from active rcvr
	                    	  System.out.println("stopped");
	                    	  hashSet.remove(sendData.getIP());
	                    	  list.remove(sendData.getName() + " : "+sendData.getIP());	                 	                    	
	                        	l1.removeAll();
	                        	for(int i=0;i<list.size();i++)
	                        	{
	                        		l1.add(list.get(i));
	                        	}
	                    	  
	                     }	                   
	                                
	                     socket.close();
	               
	                 //  panel.add(l1);	                        
	                    }
				     } catch (IOException e) {
	    					// TODO Auto-generated catch block
	        				
	    					e.printStackTrace();
	    				} 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
				
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           
           }}).start();			
 		
 		
 		
 	}
 });
 

 btnStart.setBounds(44, 58, 97, 25);
 panel.add(btnStart);
  
 JButton btnRefresh = new JButton("Refresh");
 btnRefresh.addActionListener(new   ActionListener() {
 	public void actionPerformed(ActionEvent arg0) {
 		l1.removeAll();		
 		for(int i=0;i<list.size();i++)
 		{
 			l1.add(list.get(i));
 			
 		} 		
 	}
 });
 l1.setBounds(129, 176, 153, 228);
 panel.add(l1);
 btnRefresh.setBounds(150, 410, 97, 25);
 panel.add(btnRefresh);
 
btnStop.addActionListener(new ActionListener() {
 	public void actionPerformed(ActionEvent arg0) {
 		try {
 		
 			l1.removeAll();
 			list.clear();			
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		 lblStarted.setText("Stopped");
         lblStarted.setForeground (Color.red);
 		btnStop.setEnabled(false);
 		btnStart.setEnabled(true);
 		
 	}
 });
 btnStop.setBounds(241, 58, 97, 25);
 panel.add(btnStop);
 
 JLabel lblNote = new JLabel("Note   -");
 lblNote.setBounds(66, 436, 56, 16);
 panel.add(lblNote);
 
 JLabel lblPressRefrehTo = new JLabel("Press Refreh to get updated list ");
 lblPressRefrehTo.setBounds(119, 436, 239, 16);
 panel.add(lblPressRefrehTo);
 
 JLabel lblIp = new JLabel("IP   -");
 lblIp.setBounds(119, 96, 72, 16);
 panel.add(lblIp);
 
 JLabel label = new JLabel("");
 Socket st = new Socket();
 st.connect(new InetSocketAddress("google.com", 80));
 System.out.println(st.getLocalAddress());
 label.setText(st.getLocalAddress().toString().substring(1));
 st.close();
 label.setForeground (Color.blue);
 label.setBounds(194, 96, 164, 16);
 panel.add(label);
 
 JLabel lblSecureFileTransfer = new JLabel("FILE SERVICE SERVER - IIT ROORKEE");
 lblSecureFileTransfer.setFont(new Font("Serif", Font.BOLD, 15));
 lblSecureFileTransfer.setBounds(12, 204, 295, 38);
 frame.getContentPane().add(lblSecureFileTransfer);

	}
	 public void update() throws Exception {
        
     }
	}