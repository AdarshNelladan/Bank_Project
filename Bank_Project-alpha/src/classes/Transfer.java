package classes;
import java.awt.*;
import java.awt.event.*;

import classes.DialogManagement.ExceptionDialog;
import sql.SqlFunctions;
public class Transfer {
	
    Frame mainframe;
    Panel cardpan;
    CardLayout cardlayout;
    Button back,idbt,namebt;
    String toname;
    int toid;
	double idamt,nameamt;
    SqlFunctions sql=new SqlFunctions();
    final static String IDBUTTON = "Using ID";
    final static String NAMEBUTTON = "Using User name";
	public Transfer(int id) {
		mainframe=new Frame();
		mainframe.setTitle("Transfer");
		mainframe.setLayout(null);
		mainframe.setSize(800, 700);
		mainframe.setVisible(true);
		
		//Main Buttons
		idbt=new Button(IDBUTTON);
		idbt.setBounds(250, 150, 100, 50);
		idbt.setFont(new Font("Tahoma",Font.BOLD,16));
		mainframe.add(idbt);
		namebt=new Button(NAMEBUTTON);
		namebt.setBounds(400, 150, 150, 50);
		namebt.setFont(new Font("Tahoma",Font.BOLD,16));
		mainframe.add(namebt);
		back= new Button("Back");
		back.setBounds(650, 560, 100, 50);
		back.setFont(new Font("Tahoma",Font.BOLD,16));
		back.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				mainframe.dispose(); 
   			 }
		 });
		mainframe.add(back);
		

		cardlayout = new CardLayout();
		cardpan = new Panel();
		cardpan.setLayout(cardlayout);

				
		
		
		Panel idpanel =new Panel();
		idpanel.setLayout(null);
		Panel namepanel =new Panel();
		namepanel.setLayout(null);
		

		Label namelabel =new Label("Enter name           :");
		namelabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		namelabel.setBounds(10, 26, 186, 36);
		namepanel.add(namelabel);
		
		Label idlabel = new Label("Enter ID/ACC No    :");
		idlabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		idlabel.setBounds(10, 28, 178, 36);
		idpanel.add(idlabel);
		
		Label idamtlabel = new Label("Enter Amount        :");
		idamtlabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		idamtlabel.setBounds(10, 115, 178, 24);
		idpanel.add(idamtlabel);
		
		TextField idtext = new TextField();
		idtext.setBounds(208, 28, 301, 36);
		idpanel.add(idtext);
		
		TextField idamttext = new TextField();
		idamttext.setBounds(208, 115, 301, 36);
		idpanel.add(idamttext);

		
		Label nameamtlabel = new Label("Enter Amount       :");
		nameamtlabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		nameamtlabel.setBounds(10, 108, 186, 36);
		namepanel.add(nameamtlabel);
		
		TextField nametext = new TextField();
		nametext.setBounds(219, 26, 361, 36);
		namepanel.add(nametext);
		
		TextField nameamttext = new TextField();
		nameamttext.setBounds(219, 108, 361, 36);
		namepanel.add(nameamttext);
		
		Button idconfirm= new Button("Confirm");
		idconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				try {
					toid=Integer.parseInt(idtext.getText());
					idamt=Double.parseDouble(idamttext.getText());
					EventQueue.invokeLater(new Runnable() {
	   					public void run() {
	   						try {
	                            sql.transferById(id, toid, idamt);
	   						} catch (Exception e) {
	   							e.printStackTrace();
	   						}
	   					}
	   				});
					
				}catch(NumberFormatException er) {
					
					new ExceptionDialog("Input Error");
				}
				catch(NullPointerException np) {
					new ExceptionDialog("No Input");
				}

   				

				
			}
		});
		idconfirm.setBounds(323, 239, 100, 50);
		idconfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		idpanel.add(idconfirm);
		
		Button nameconfirm = new Button("Confirm");
		nameconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					toname=nametext.getText();
					nameamt=Double.parseDouble(nameamttext.getText());
					EventQueue.invokeLater(new Runnable() {
	   					public void run() {
	   						try {
	                            sql.transferByName(id, toname, nameamt);
	   						} catch (Exception e) {
	   							e.printStackTrace();
	   						}
	   					}
	   				});
					
				}catch(NumberFormatException er) {
					
					new ExceptionDialog("Input Error");
				}
				catch(NullPointerException np) {
					new ExceptionDialog("No Input");
				}

   				
			}
		});
		nameconfirm.setFont(new Font("SansSerif", Font.BOLD, 18));
		nameconfirm.setBounds(323, 239, 100, 50);
		namepanel.add(nameconfirm);
		
		cardpan.add(idpanel, IDBUTTON);
		cardpan.add(namepanel, NAMEBUTTON);
		cardpan.setBounds(10, 200, 780, 350);
		mainframe.add(cardpan);
		
		idbt.addActionListener((ae) -> cardlayout.show(cardpan, IDBUTTON));
		namebt.addActionListener((ae) -> cardlayout.show(cardpan, NAMEBUTTON));
		
		mainframe.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				cardlayout.next(cardpan);
			}
		});
		
		mainframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mainframe.dispose();
			}
		});
		
	}
}
