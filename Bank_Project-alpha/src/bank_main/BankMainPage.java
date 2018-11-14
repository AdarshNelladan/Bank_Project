/**
 * 
 */
package bank_main;

/**
 * @author Adarsh
 *
 */
import java.awt.*;
import java.awt.event.*;
import classes.*;
import sql.SqlFunctions;
public class BankMainPage {

	/**
	 * 
	 */
	Frame mainframe;
	Button balance_button,statement_button,chgpswd_button,deposit_button,withdraw_button,details_button;
	Button transfer_button,delete_button,signout;
	Label welcome_label;
	SqlFunctions sql=new SqlFunctions();
	public String balance;
	public BankMainPage(int id) {
		// TODO Auto-generated constructor stub
		mainframe=new Frame("Welcome");
		mainframe.setBackground(Color.LIGHT_GRAY);
		mainframe.setFont(new Font("Tahoma",Font.BOLD,16));
		mainframe.addWindowListener(new WindowListener() {
	   		 public void windowClosing(WindowEvent w) {
	   			 
	   			 try {
					SqlFunctions.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
	   			mainframe.dispose();
	   			System.exit(0);
	   		 }

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
	   	     });
		mainframe.setSize(1028, 768);
		mainframe.setLayout(null);
		mainframe.setVisible(true);
		
		
		//Label
		welcome_label=new Label();
		welcome_label.setFont(new Font("Tahoma",Font.BOLD,34));
		welcome_label.setText("Welcome "+sql.getName(id)+"!");
		welcome_label.setBounds(350, 50, 600, 50);
		mainframe.add(welcome_label);

		//Buttons
		balance_button=new Button("Check Balance");
		balance_button.setBounds(20, 200, 300, 50);
		balance_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                 balance=sql.balanceCheck(id);
   				 new Balance(balance);
   			 }
   		 });
        mainframe.add(balance_button);        
        statement_button=new Button("Statement");
		statement_button.setBounds(700, 200, 300, 50);
		statement_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				EventQueue.invokeLater(new Runnable() {
   					public void run() {
   						try {
   							new Statement(id);
   							
   						} catch (Exception e) {
   							e.printStackTrace();
   						}
   					}
   				});
   			 }
   		 });
        mainframe.add(statement_button);
        
        chgpswd_button=new Button("Change Password");
		chgpswd_button.setBounds(700, 400, 300, 50);
		chgpswd_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				 new ChangePassword(id);

   			 }
   		 });
        mainframe.add(chgpswd_button);
        
        deposit_button=new Button("Deposit");
		deposit_button.setBounds(20, 300, 300, 50);
		deposit_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                   new Deposit(id);
   			 }
   		 });
        mainframe.add(deposit_button);
        
        withdraw_button=new Button("Withdraw");
		withdraw_button.setBounds(700, 300, 300, 50);
		withdraw_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                    new Withdraw(id);
   			 }
   		 });
        mainframe.add(withdraw_button);
        
        details_button=new Button("User Details");
		details_button.setBounds(20, 400, 300, 50);
		details_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				EventQueue.invokeLater(new Runnable() {
   					public void run() {
   						try {
                            new UserDetails(id);
   						} catch (Exception e) {
   							e.printStackTrace();
   						}
   					}
   				});
   			 }
   		 });
        mainframe.add(details_button);
        transfer_button=new Button("Transfer");
		transfer_button.setBounds(20, 500, 300, 50);
		transfer_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				EventQueue.invokeLater(new Runnable() {
   					public void run() {
   						try {
                            new Transfer(id);
   						} catch (Exception e) {
   							e.printStackTrace();
   						}
   					}
   				});
   			 }
   		 });
        mainframe.add(transfer_button);
        delete_button=new Button("Delete Account");
		delete_button.setBounds(700, 500, 300, 50);
		delete_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				EventQueue.invokeLater(new Runnable() {
   					public void run() {
   						try {
                            new DeleteAccount(id);
   						} catch (Exception e) {
   							e.printStackTrace();
   						}
   					}
   				});

   			 }
   		 });
        mainframe.add(delete_button);
        signout=new Button("Signout");
		signout.setBounds(350, 600, 300, 50);
		signout.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                 new WelcomePage();
   				 mainframe.dispose();  			 
   			 }	
   		 });
        mainframe.add(signout);
	}

}
