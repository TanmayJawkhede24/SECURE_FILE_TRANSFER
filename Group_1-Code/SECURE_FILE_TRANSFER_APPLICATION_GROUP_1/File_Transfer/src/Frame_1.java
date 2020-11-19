
import java.awt.EventQueue;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.crypto.Cipher;

import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;

import javax.crypto.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import java.awt.List;

public class Frame_1 {
	 ServerSocket server ;	
	 String rcvdPasscode;
	 PrivateKey prikey;
	 String filename ;
	 SecretKey aesKey;
	 PublicKey rcvrPublicRsaKey;	 
	 byte[] bytes;
	private JFrame frame;
	boolean flag=true;
	private JTextField textField;
	private JTextField textField_1;
	int passCode;
	String passCodetoSend;
	String statustoSend ;
	PublicKey publicKeytosend;
	ArrayList <String> list = new ArrayList<String>();
	ArrayList <String> listT = new ArrayList<String>();
	LinkedHashSet<String> hashSet = new LinkedHashSet<String>();
	private JTextField textField_3;
	private JTextField textField_4;
	 String ipA="";	   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_1 window = new Frame_1();
					window.frame.setVisible(true);
					try {						
					
			             
			          } catch (Exception e) {
			        	  System.out.println("eroror-"+e);
			          }
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
	
	public Frame_1() throws IOException {
		initialize();	 
		  
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBackground(Color.YELLOW);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Socket st = new Socket();
		 st.connect(new InetSocketAddress("google.com", 80));
		 System.out.println(st.getLocalAddress());
		 ipA=st.getLocalAddress().toString().substring(1);
		 st.close();
		JPanel panel = new JPanel();
		panel.setBounds(0, 187, 297, 452);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);				
		JLabel lblActivityLog = new JLabel("LIST OF ACTIVE RECEIVER");
		lblActivityLog.setBounds(83, 13, 151, 35);
		panel.add(lblActivityLog);
		JLabel lblName = new JLabel("Name   -");
		lblName.setBounds(36, 70, 96, 16);
		frame.getContentPane().add(lblName);		
		JLabel lblFileServiceIp = new JLabel("File Service IP -");
		lblFileServiceIp.setBounds(25, 112, 147, 16);
		frame.getContentPane().add(lblFileServiceIp);
		
		textField_3 = new JTextField();		
		textField_3.setBounds(167, 64, 162, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);		
		textField_4 = new JTextField();
		textField_4.setBounds(167, 109, 162, 22);
		
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		final JButton btnUpdate = new JButton("Edit");
		btnUpdate.setVisible(false);
		final JButton btnSet = new JButton("Set");
  
		//set your name
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_3.setText(textField_3.getText());
				textField_3.disable();
				btnSet.setVisible(false);
				btnUpdate.setVisible(true);	
				textField_3.setDisabledTextColor(Color.blue);
				
			}
		});
		btnSet.setBounds(357, 63, 97, 25);
		frame.getContentPane().add(btnSet);
		JLabel lblNote = new JLabel("Status -");
		lblNote.setBounds(806, 112, 56, 16);
		frame.getContentPane().add(lblNote);
		
		final JLabel lblConfigureApplicationBefore = new JLabel("Kindly Configure Application Before preceding");
		lblConfigureApplicationBefore.setForeground (Color.red);
		lblConfigureApplicationBefore.setBounds(898, 112, 282, 16);
		frame.getContentPane().add(lblConfigureApplicationBefore);
		
		final JButton btnSet_1 = new JButton("Set");
		//set file service server ip
		btnSet_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.setText(textField_4.getText());
				textField_4.disable();
				lblConfigureApplicationBefore.setText("    Configured");
				lblConfigureApplicationBefore.setForeground (Color.blue);
				btnSet_1.setVisible(false);
				btnUpdate.setVisible(true);
				textField_4.setDisabledTextColor(Color.blue);
				
				
				
			}
		});
		btnSet_1.setBounds(357, 108, 97, 25);
		frame.getContentPane().add(btnSet_1);
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.enable();
				textField_3.enable();
				btnUpdate.setVisible(false);
				btnSet_1.setVisible(true);
				btnSet.setVisible(true);
				
				
			}
		});
		btnUpdate.setBounds(200, 144, 97, 25);
		frame.getContentPane().add(btnUpdate);
		
		
		JLabel lblYourIp_1 = new JLabel("Your IP      -");
		lblYourIp_1.setBounds(806, 70, 114, 16);
		frame.getContentPane().add(lblYourIp_1);
		
		final JLabel lblIp = new JLabel("Ip");
		;
    	 lblIp.setText(ipA);
    	 lblIp.setForeground(Color.blue);
		lblIp.setBounds(915, 70, 156, 16);
		frame.getContentPane().add(lblIp);
		
		
		//list of Receiver
		final List l1 = new List();
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(22, 75, 175, 22);
		panel.add(panel_3);
		JButton btnRefresh = new JButton("Refresh");
		
		//action formed when refresh button click
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				new Thread(new Runnable() {				
					  private ObjectInputStream in;
					  private ObjectOutputStream out1;
					  private Socket socket;					    				       
		            public void run() {
		            	 try {		            	
							socket = new Socket(textField_4.getText().trim(), 9200);
							 //request to file service server
							 out1 = new ObjectOutputStream(socket.getOutputStream());
		                  	 SEND sendData1 = new SEND();
		                  	 sendData1.setStatus("getActiveRecvr");	
		                  	 sendData1.setName(textField_3.getText().trim());
		                     out1.writeObject(sendData1);
		                     out1.flush();
		                     
		                   // reply from file service server
		                     in = new ObjectInputStream(socket.getInputStream());			                     
			                 SEND recvData=(SEND) in.readObject();			                
			                 ArrayList <String> listTemp  = recvData.getActiveRcvr();			                
			                 l1.removeAll();
			                 for(int i=0;i<listTemp.size();i++)
			                 {			             
		       			            l1.add(listTemp.get(i));			                					                    	 
			                	 
			                 }			               
			                 socket.close();
		                     
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
		l1.setBounds(22, 96, 175, 239);
	
  
	
		int index=l1.getSelectedIndex();
				if(index!=-1)
				{
					textField.setText(l1.getItem(index));
					System.out.println(l1.getItem(index));
				}
	
		
		panel.add(l1);
		btnRefresh.setBounds(59, 341, 97, 25);
		panel.add(btnRefresh);
		
		JButton btnSelect = new JButton("Select");
		textField = new JTextField();
		textField.setBounds(169, 79, 312, 34);
		//to choose rcvr ip
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				  String s=l1.getSelectedItem();
				  int index=s.indexOf(":");
				 
				 String finalS= s.substring(index+1);
				  System.out.println("SSSs"+index);
				  textField.setText(finalS.trim());
			}
		});
		btnSelect.setBounds(200, 193, 97, 25);
		panel.add(btnSelect);
			
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(298, 188, 622, 451);
		panel_1.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSendFile = new JLabel("SEND FILE");
		lblSendFile.setBounds(259, 13, 133, 34);
		panel_1.add(lblSendFile);		
		panel_1.add(textField);
		textField.setColumns(10);
		
		final JLabel lblPending = new JLabel("");
		lblPending.setBounds(176, 171, 395, 16);
		panel_1.add(lblPending);
		
		final JLabel lblSendRequestTo = new JLabel("");
		lblSendRequestTo.setBounds(176, 250, 395, 16);
		panel_1.add(lblSendRequestTo);
		
		JButton btnNewButton = new JButton("REQUEST");
		final JButton btnNewButton_2 = new JButton("SEND");
		  btnNewButton_2.setEnabled(false);
		  
				btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					private Socket socket;
				    private ObjectInputStream in;
					private ObjectOutputStream out;
		            public void run() {
		                try {
		                	//to connect with rcvr status="new"
		                  	 socket = new Socket(textField.getText().trim(), 9199);		                   
		                  	 out = new ObjectOutputStream(socket.getOutputStream());
		                  	 
		                  	 SEND sendData = new SEND();		                  	 
   		                   	 sendData.setStatus("new");
		                  	 sendData.setIP(ipA);
		                  	 System.out.println(textField_3.getText().trim());
		                  	 sendData.setName(textField_3.getText().trim());
		                     out.writeObject(sendData);
		                     out.flush();
		                    
		                     lblSendRequestTo.setText("Request send to Receiver");
		                     lblPending.setForeground (Color.blue);
		                     lblPending.setText("File Send  Intiated");
		                     lblSendRequestTo.setForeground (Color.blue);
		                     
		                   //get response from rcvr passcode +aes key
		                     in = new ObjectInputStream(socket.getInputStream());	                    
			                 SEND recvData=(SEND) in.readObject();
			                 String name = recvData.getName();
			                 String  ip= recvData.getIP();
			                 String status=recvData.getStatus();
			                 System.out.println("encodedKey  11"+status );	
			                 
			                 //handle it according response status
			                 if(status.equalsIgnoreCase("fail"))
			                 {
	                            //handle when status "fail"
				                     System.out.println("REPLY SERVER"+ name);
				                     btnNewButton_2.setEnabled(false);			                     
				                     lblSendRequestTo.setText("Receiver Reject your Request");
				                     lblSendRequestTo.setForeground (Color.red);
			                 }
			                 else			                	 
			                 {   
			                	 //handle when status "Success"
			                	 //generate Aes key of sender
			                 SecretKey secretAesKey=getAesKey();  			               
			                 String encodedKey = Base64.getEncoder().encodeToString(secretAesKey.getEncoded());
			                 //store aes +passcode+public key 
			                 aesKey=secretAesKey;
			                 rcvdPasscode=recvData.getPasscode();
			                 rcvrPublicRsaKey=recvData.getPublicKey();			                 
			                 System.out.println("encodedKey"+encodedKey );	
			                 btnNewButton_2.setEnabled(true);			                 			                                           
		                     System.out.println("REPLY SERVER"+ name);		                     
		                     lblSendRequestTo.setText("Receiver Accept your Request");
			                	 
			                 }
			              
							 socket.close();		
                           
                         			                    			                               		                   		                     		                     		                    		          
		                } catch (Exception e) {
		                	System.out.println( "Error"+e);
		                }

		            }
		          
	              private SecretKey getAesKey() throws NoSuchAlgorithmException {
	            	  //generate Aes key
	    	             	 KeyGenerator generator = KeyGenerator.getInstance("AES");
	    		           generator.init(256);
	    		           
	    		           SecretKey secretAesKey = generator.generateKey();		     				       
					       System.out.println("secretAesKey key----"+  secretAesKey);
						   return secretAesKey;
						
					}
		        }).start();
			}
			});
		
		btnNewButton.setBounds(493, 79, 117, 34);
		panel_1.add(btnNewButton);
		
		JLabel lblRecvIp = new JLabel("IP   - ");
		lblRecvIp.setBounds(27, 88, 56, 16);
		panel_1.add(lblRecvIp);
		
		JLabel lblStatus = new JLabel("Status  -");
		lblStatus.setBounds(27, 171, 56, 16);
		panel_1.add(lblStatus);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(223, 171, 56, 16);
		panel_1.add(label_1);
		
		JButton btnNewButton_1 = new JButton("Choose File");
		//method to select file from system
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
	            int r = j.showOpenDialog(null);
	            if (r == JFileChooser.APPROVE_OPTION) 	            	  
	            { 
	                
	            	textField_1.setText(j.getSelectedFile().getAbsolutePath()); 
	            	File file = new File(j.getSelectedFile().getAbsolutePath());
	            	 FileInputStream fis = null;
	            	bytes = new byte[(int) file.length()];
	            	 try {	

	                     fis = new FileInputStream(file);
	                     //read file into bytes[]
	                     fis.read(bytes);	                     
	                     filename = j.getSelectedFile().getName();	
	                     lblSendRequestTo.setText("File Choosed Succesfully");

	                 } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
	                     if (fis != null) {
	                         try {
								fis.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                     }
	            } 
	            }				
			}						
		})
		;
		btnNewButton_1.setBounds(12, 334, 117, 34);
		panel_1.add(btnNewButton_1);		
		textField_1 = new JTextField();
		textField_1.setBounds(169, 334, 312, 34);
		panel_1.add(textField_1);
		textField_1.setColumns(10);		
	//send button function
		btnNewButton_2.addActionListener(new ActionListener() {
			  private ObjectOutputStream out;		
			  private Socket socket;
			  private ObjectInputStream in;
				//file send method
		    	public void actionPerformed(ActionEvent arg0) {
				 try {
					 //to Communicate with rcvr
				   socket = new Socket(textField.getText().trim(), 9199);                                
				   FileInputStream fis = new FileInputStream(textField_1.getText().trim()); 
				   
				   //to create send folder on sender machine
				   Files.createDirectories(Paths.get("/SEND/"+textField.getText().trim()));
				   FileOutputStream fos = new FileOutputStream("/SEND/"+textField.getText().trim()+"encrypted-"+filename);    
				   //Initialize Encryption algo=AES
			   	    Cipher cipher = Cipher.getInstance("AES");                    
                    cipher.init(Cipher.ENCRYPT_MODE, aesKey);  //client scrt key 
                    CipherOutputStream cos = new CipherOutputStream(fos, cipher);
                    int b;
                    //Encrypt file 
                    while((b = fis.read(bytes)) != -1) {
                    	
                        cos.write(bytes, 0,b );
                      
                      }                   
                    cos.close();  
                    //read encrypted file convert to byte array
                     byte[] encoded = Files.readAllBytes(Paths.get("/SEND/"+textField.getText().trim()+"encrypted-"+filename));                     
                 	
                     //Create object to send
                     SEND data = new SEND();
                    data.setStatus("FILE");                  
                    data.setFileName(filename);
                    data.setName(textField_3.getText().trim());
                    data.setpassCode(rcvdPasscode);
                    data.setIP(lblIp.getText().trim());
                    data.setFile(encoded);
                    //encrypt Sender Aes key with help of rcvr public key
                    String encodedKey = Base64.getEncoder().encodeToString(aesKey.getEncoded());;
                    String senderAesKey = encrypt(encodedKey, rcvrPublicRsaKey);	
                    data.setSenderAesKey(senderAesKey);                   
                    //send  object to  rcvr
                    out = new ObjectOutputStream(socket.getOutputStream());
                    lblSendRequestTo.setText("File Send to Receiver");
                    cos.flush();
                    cos.close();
                    fis.close();
                    out.writeObject(data);
                    //rcvr response
                    in = new ObjectInputStream(socket.getInputStream());	                    
		                 SEND recvData=(SEND) in.readObject();
		                 String status = recvData.getStatus();
		               //change activity log according to status
		                 if(status.equalsIgnoreCase("fail"))
		                 {
		                	  lblSendRequestTo.setText("Got Reply from Receiver");
		                	  lblPending.setText("File Rejected");
		                      lblPending.setForeground (Color.red);
		                	 
		                 }
		                 else
		                 {
		                	  lblSendRequestTo.setText("Got Reply from Receiver");
		                	  lblPending.setText("File Send  Successfully");
		                      lblPending.setForeground (Color.green);
		                	 
		                 }
	   	      		 socket.close();
                  					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
				
			}
		    	
		    //to encrypt AES key 
			 public String encrypt(String plainText, PublicKey publicKey) throws Exception {
	                Cipher encryptCipher = Cipher.getInstance("RSA");
	                encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
	                byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8.name()));
	                return Base64.getEncoder().encodeToString(cipherText);
	            }
		});
		btnNewButton_2.setBounds(493, 334, 117, 34);
		panel_1.add(btnNewButton_2);				
		JLabel lblRecentActivity = new JLabel("Recent Activity   -");
		lblRecentActivity.setBounds(27, 250, 102, 16);
		panel_1.add(lblRecentActivity);
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(915, 187, 317, 452);
		panel_2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);		
		JLabel label = new JLabel("");
		label.setBounds(129, 13, 0, 0);
		panel_2.add(label);		
		JLabel lblNewLabel = new JLabel("RECV REQUEST");
		lblNewLabel.setBounds(140, 13, 106, 33);
		panel_2.add(lblNewLabel);
		final JButton btnAccept = new JButton("Start Accepting");
		final JButton btnStopAccepting = new JButton("Stop Accepting");
		btnStopAccepting.setEnabled(false);
		final JLabel lblStarted = new JLabel("");
		lblStarted.setBounds(149, 181, 119, 16);
		panel_2.add(lblStarted);
		
		
		final JLabel lblAcceptRequest = new JLabel("");
		lblAcceptRequest.setBounds(145, 210, 160, 16);
		panel_2.add(lblAcceptRequest);
		
	
		
		JLabel lblPasscode = new JLabel("Passcode   -");
		lblPasscode.setBounds(31, 155, 76, 16);
		panel_2.add(lblPasscode);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(149, 155, 119, 16);
		panel_2.add(lblNewLabel_1);
		final JLabel lblSenderName = new JLabel("Sender Name");
		lblSenderName.setBounds(32, 273, 97, 16);
		lblSenderName.setVisible(false);
		panel_2.add(lblSenderName);
		// sender Name
		final JLabel lblSname = new JLabel("sName");
		lblSname.setBounds(149, 273, 156, 16);
		lblSname.setVisible(false);
		panel_2.add(lblSname);
		final JLabel lblSenderIp = new JLabel("Sender IP -");
		lblSenderIp.setBounds(32, 244, 86, 16);
		lblSenderIp.setVisible(false);
		panel_2.add(lblSenderIp);
		
		final JLabel lblSip = new JLabel("sIp");
		lblSip.setVisible(false);
		lblSip.setBounds(144, 244, 161, 16);
		panel_2.add(lblSip);
	
		//Accept sender Request
		final JButton btnAccept_1 = new JButton("Accept");
		btnAccept_1.setBounds(103, 308, 97, 25);
		btnAccept_1.setVisible(false);
		panel_2.add(btnAccept_1);
		//Reject sender Request
		final JButton btnReject = new JButton("Reject");
		btnReject.setBounds(208, 308, 97, 25);
		btnReject.setVisible(false);
		panel_2.add(btnReject);
		
		//start accepting button
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {				
					 private ObjectOutputStream out;
					  private ObjectInputStream in;
					  private ObjectOutputStream out1;
					  private Socket socket;					    				       
		            public void run() {
		            
		                try {
		                	btnAccept.setEnabled(false);
		                	btnStopAccepting.setEnabled(true);
		                	server = new ServerSocket(9199);
		                	
		                	//to get System ip
		                	 Socket st = new Socket();
		                	 st.connect(new InetSocketAddress("google.com", 80));
		                	 System.out.println(st.getLocalAddress());
		                	 String ipA=st.getLocalAddress().toString().substring(1);
		                	 st.close();
		                	 
		                	 //register yourself to file service server
		                	 try
		                	 {
		                		 //step-1
		          
		                	 socket = new Socket(textField_4.getText().trim(), 9200);		                   
		                  	 out1 = new ObjectOutputStream(socket.getOutputStream());
		                  	 SEND sendData1 = new SEND();
		                  	 sendData1.setStatus("Initialize");
		                  	 sendData1.setIP(ipA);
		                  	 sendData1.setName(textField_3.getText().trim());
		                     out1.writeObject(sendData1);
		                     out1.flush();
		                	 }
		                	 catch (Exception e) {
				                	System.out.println( "Error"+e);
				                }
		                	 //Generate Random number for passcode
		                	  Random r = new Random( System.currentTimeMillis() );
		                	  final String passCode= Integer.toString(10000 + r.nextInt(20000)); 
		                	  lblNewLabel_1.setText(passCode);
		                	
		                	lblStarted.setText("STARTED");
		                  	lblStarted.setForeground (Color.blue);
		                	System.out.println("Server stating ...\n");
		                	//generate Public key /Private key (RSA algo)
		                	final KeyPair pair=getPublicKey();	                  		
	                  		 prikey=pair.getPrivate();	
		                    //to keep server alive
		                    while(true)
		                    {
		                     Socket s = server.accept();		                   		                   		                    
		                     in = new ObjectInputStream(s.getInputStream());
		                    
		                     SEND sendData=(SEND) in.readObject();
		                     
		                     String name = sendData.getName();
		                     String  ip= sendData.getIP();
		                      String status= sendData.getStatus();
		                      
		                     
		                     lblSname.setVisible(true);
		                  	 lblSenderName.setVisible(true);
		                  	lblSenderIp.setVisible(true);
		                  	lblSip.setVisible(true);
		                 	
		                  	lblSip.setText(ip);
		                  	 lblSname.setText(name);
		                  	 lblSenderName.setText("Sender Name -");		                     		                     
		                     
		                  	 System.out.println("New client " + name + " -- "+ip+ " has been connected ...\n"+sendData.getPasscode()+sendData.getStatus()+passCodetoSend);     
		                     out = new ObjectOutputStream(s.getOutputStream());		                    
		        		      SEND replyData = new SEND();
	        		     
	                  	     //handle ReQuest according to status  
	                  	   if(status.equalsIgnoreCase("FILE"))
	                  	   {
	                  		 //Accept file if passcode is correct  
	                  		 if(sendData.getPasscode().equalsIgnoreCase(passCode))
	                  		 {
	                  		 String senderAesKeyEncrypted=sendData.getsenderAesKey();
	                  		
	                  		 
	                  		 long millis=System.currentTimeMillis();  
	                  		java.sql.Date date=new java.sql.Date(millis); 
	                  		
	                  		
	                  		//create new directory if not present in root folder RECEIVED/senderip/date
	                  		 Files.createDirectories(Paths.get("/RECEIVED/"+sendData.getIP()+"/"+date));
	                  		
	                  		 
	                  		 //store encrypted file in  folder RECEIVED/senderip/date
	                  		 FileOutputStream fos = new FileOutputStream("/RECEIVED/"+sendData.getIP()+"/"+date+"/encrypted-"+sendData.getFileName());	                  			                  		 	                  	     
	                  		 fos.write(sendData.getFile());	  
	                  		 fos.close();
	                  		 
	                  	
	                  		 //sender Aes key which used to decrypt
	                  		 String senderAesKeyDcrypted = decrypt(senderAesKeyEncrypted, prikey);
	                   	     System.out.println("senderAesKeyDcrypted key----"+  senderAesKeyDcrypted);
	                     	
	                   	     
	                   	     //store original  file in  folder RECEIVED/senderip/date	                  	     
	                   
	                   	     FileOutputStream fos1 = new FileOutputStream("/RECEIVED/"+sendData.getIP()+"/"+date+"/"+sendData.getFileName());
	                   		//intiate cipher text
	                  	     Cipher cipher1 = Cipher.getInstance("AES");
	                  	     
	                  	     byte[] decodedKey = Base64.getDecoder().decode(senderAesKeyDcrypted);
	                   	     SecretKey originalAesKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");	                  	   	                  		                       	                   	                      
	                         
	                   	     FileInputStream fis = new FileInputStream("/RECEIVED/"+sendData.getIP()+"/"+date+"/encrypted-"+sendData.getFileName());;
	                         File file = new File("/RECEIVED/"+sendData.getIP()+"/"+date+"/"+"encrypted-"+sendData.getFileName());
	                    
	                         
	                         cipher1.init(Cipher.DECRYPT_MODE,originalAesKey); 	                      
	                         CipherInputStream cis = new CipherInputStream(fis, cipher1);	                 	  
	                         byte[] encryptedBytes;
	                 	     encryptedBytes = new byte[(int) file.length()];
	                         fis = new FileInputStream(file);	                         
	                         //read file into bytes[]
	                         fis.read(encryptedBytes);	                      	         
	                         int b1;
	                         
	                         while((b1 = cis.read(encryptedBytes)) != -1) {
	                      	 fos1.write(encryptedBytes, 0,b1 );
	                        }	
	                         fos1.close();
	                         fis.close();
	                         cis.close();
	                         //reply sender about status
	                         lblAcceptRequest.setText("File Accepted");
	                         passCodetoSend="";
	                         statustoSend="success" ;
		        		  	 publicKeytosend=null;
	                        
	                  		 }
	                  		 else
	                  		 {
	                  			//reply sender about status if passcode is not correct
	                  			 lblAcceptRequest.setText("File Rejected ");
	                  			 lblAcceptRequest.setForeground (Color.red);
	                  	         passCodetoSend="";
	                  	         statustoSend="fail" ;
			        		  	 publicKeytosend=null;
	                  			
	                  		 }
	                  		 
	                  		 
	                  	   }
	                  	   else
	                  	   {
	                  		  //initiate connect with sender by sending passcode and public rsa key
	                  	                    		                  		
	                  		btnAccept_1.setVisible(true);
	                	    btnReject.setVisible(true);
	                	    flag=true;
	                	     while(flag)
	                    	 {
	                	    	 
	                    	 btnAccept_1.addActionListener(new ActionListener() {	                    		
	                 			public void actionPerformed(ActionEvent arg0) {
	                 				//if accepted
                 		        		btnAccept_1.setVisible(false);
	   	                    	        btnReject.setVisible(false);
	   	                    	        lblAcceptRequest.setText("You Accepted sender request");
        	                 	     //send passcode+status
	   	                    	        passCodetoSend=passCode;
	     	                	        statustoSend="sucess" ;
		    	        		  	    publicKeytosend=pair.getPublic();	 
	   	                	            lblAcceptRequest.setForeground (Color.BLUE);
	   	                	            flag=false;
	   	                	    
	                 			}
	                 		});
	                    	 
	                    	  btnReject.addActionListener(new ActionListener() {
	                 			  public void actionPerformed(ActionEvent arg0) {
	                 				//if rejected
	                 				btnAccept_1.setVisible(false);
	   	                	        btnReject.setVisible(false);
	   	                	        passCodetoSend="";
	   	                	        statustoSend="fail" ;
			        		  	    publicKeytosend=null;	 			        		  	    
	   	                	        lblAcceptRequest.setText("You reject sender request");
	   	                	        lblAcceptRequest.setForeground (Color.red);
	   	                	        flag=false;
	                 			 }
	                 	   	});
	                     }	                	   	                				                  	    
	                  	   }
	                      	 flag=true;
	                      	replyData.setPublicKey(publicKeytosend);	
	                	    replyData.setpassCode(passCodetoSend);	
	                	    replyData.setStatus(statustoSend);
	                        replyData.setName(textField_3.getText().trim());
	                  	    System.out.println("Reply sennt    --   "+statustoSend);  
	                        out.writeObject(replyData);
	                        out.flush();	             	                  		                    
	                         s.close();
		                    }
			                                                
		               } catch (Exception e) {
		                	System.out.println( "Error"+e);
		                }
		            }
		            //to decrypt sender AES key
		            public  String decrypt(String cipherText, PrivateKey privateKey) throws Exception {		            
		            	byte[] bytes = Base64.getDecoder().decode(cipherText);
		                Cipher decriptCipher = Cipher.getInstance("RSA");
		                decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		                return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8.name());
		            }
		            
		            //RSA algorithm
					private KeyPair getPublicKey() throws NoSuchAlgorithmException {
					KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");						
				        generator.initialize(2048, new SecureRandom());
				        
				        KeyPair pair = generator.generateKeyPair();		
				        
						System.out.println("private key----"+  pair.getPrivate());
						System.out.println("public key----"+  pair.getPublic());
						
						return pair;
						// TODO Auto-generated method stub
						
					}
		        }).start();
				
			}

		});
		
		btnAccept.setBounds(109, 59, 151, 25);
		panel_2.add(btnAccept);
		JLabel lblStatus_1 = new JLabel("Status    -");
		lblStatus_1.setBounds(31, 184, 106, 16);
		panel_2.add(lblStatus_1);
		JLabel lblRecentActivity_1 = new JLabel("Recent Activity   -");
		lblRecentActivity_1.setBounds(32, 210, 106, 16);
		panel_2.add(lblRecentActivity_1);
		
	
		
	//to stop server
		btnStopAccepting.addActionListener(new ActionListener() {
		  private ObjectOutputStream out1;
			  private Socket socket;	
			public void actionPerformed(ActionEvent arg0) {
				
			try {
				//To remove entry from file service server
				 socket = new Socket(textField_4.getText().trim(), 9200);		                   
              	 out1 = new ObjectOutputStream(socket.getOutputStream());
              	 SEND sendData1 = new SEND();
              	 sendData1.setStatus("stopped");
              	 Socket st = new Socket();
            	 st.connect(new InetSocketAddress("google.com", 80));
            	 System.out.println(st.getLocalAddress());
            	 String ipA=st.getLocalAddress().toString().substring(1);
            	 st.close();
              	 sendData1.setIP(ipA);             	 
              	 sendData1.setName(textField_3.getText().trim());
                 out1.writeObject(sendData1);
                 out1.flush();
   				server.close();
				btnStopAccepting.setEnabled(false);
				btnAccept.setEnabled(true);
				lblStarted.setText("STOPPED");
			    lblAcceptRequest.setText(" ");
				lblStarted.setForeground (Color.red);
				lblSname.setVisible(false);
             	lblSenderName.setVisible(false);
             	btnAccept_1.setVisible(false);
             	lblSenderIp.setVisible(false);
              	lblSip.setVisible(false);
           	     btnReject.setVisible(false);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
			}
		});
		btnStopAccepting.setBounds(109, 365, 151, 25);
		panel_2.add(btnStopAccepting);
		
		JLabel lblIitroorkee = new JLabel("IIT -ROORKEE");
		lblIitroorkee.setFont(new Font("Serif", Font.BOLD, 10));
		lblIitroorkee.setBounds(579, 53, 96, 33);
		frame.getContentPane().add(lblIitroorkee);
		
		JLabel lblSecureFileTransfer = new JLabel("SECURE FILE TRANSFER");
		lblSecureFileTransfer.setFont(new Font("Serif", Font.BOLD, 15));
		lblSecureFileTransfer.setBounds(536, 13, 250, 27);
		frame.getContentPane().add(lblSecureFileTransfer);
		
		JLabel lblConfiguration = new JLabel("Configuration");
		lblConfiguration.setBounds(106, 24, 156, 33);
		frame.getContentPane().add(lblConfiguration);
		
	
		frame.setSize(1250, 750);
	}
}
    
 
