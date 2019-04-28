/**
 * 
 */
package bank_main;

/**
 * @author Adarsh
 *
 */

import classes.*;
import sql.SqlFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankMainPage extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	JButton balance_button,statement_button,chgpswd_button,deposit_button,withdraw_button,details_button;
	JButton transfer_button,delete_button,signout;
	JLabel welcome_label;
	SqlFunctions sql=new SqlFunctions();
	public String balance;
	int x=0,y=360,speed=3;
	public BankMainPage(int id) {
		// TODO Auto-generated constructor stub
		setTitle("Welcome");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("Tahoma",Font.BOLD,16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1028, 768);
		setLayout(null);
		setVisible(true);
		Font font = new Font("Sans-Seriff", Font.BOLD, 14);
		Thread t=new Thread(this);
		t.start();
		
		
		//JLabel
		welcome_label=new JLabel();
		welcome_label.setFont(new Font("Tahoma",Font.BOLD,34));
		welcome_label.setText("Welcome "+sql.getName(id)+"!");
		welcome_label.setBounds(350, 50, 600, 50);
		add(welcome_label);

		//Buttons
		balance_button=new JButton("Check Balance");
		balance_button.setBounds(20, 200, 300, 50);
		balance_button.setFont(font);
		balance_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                 balance=sql.balanceCheck(id);
   				 JOptionPane.showMessageDialog(rootPane,"Your Current balance is "+balance,"Balance",JOptionPane.INFORMATION_MESSAGE);
   			 }
   		 });
        add(balance_button);        
        statement_button=new JButton("Statement");
		statement_button.setBounds(700, 200, 300, 50);
		statement_button.setFont(font);
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
        add(statement_button);
        
        chgpswd_button=new JButton("Change Password");
		chgpswd_button.setBounds(700, 400, 300, 50);
		chgpswd_button.setFont(font);
		chgpswd_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				 new ChangePassword(id);

   			 }
   		 });
        add(chgpswd_button);
        
        deposit_button=new JButton("Deposit");
		deposit_button.setBounds(20, 300, 300, 50);
		deposit_button.setFont(font);
		deposit_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {

                 try {
                     double amount = Double.parseDouble(JOptionPane.showInputDialog(rootPane, "Enter amount : "));
                     if (amount > 0) {
                         if (amount < 1000000000) {

                             sql.deposit(id, amount);
                             JOptionPane.showMessageDialog(null, "Success");
                         } else {
                             JOptionPane.showMessageDialog(null, "Amount above limit,contact bank for assistance", "Error:Limit reached", JOptionPane.INFORMATION_MESSAGE);
                         }

                     } else {
                         JOptionPane.showMessageDialog(null, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
                     }

                 } catch (NumberFormatException er) {

                     JOptionPane.showMessageDialog(null, "Input Error", "Error", JOptionPane.ERROR_MESSAGE);

                 } catch (NullPointerException np) {
                 }
   			 }
   		 });
        add(deposit_button);
        
        withdraw_button=new JButton("Withdraw");
		withdraw_button.setBounds(700, 300, 300, 50);
		withdraw_button.setFont(font);
		withdraw_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                 try {
                     double amount = Double.parseDouble(JOptionPane.showInputDialog(rootPane, "Enter amount : "));
                     String bal = sql.balanceCheck(id);
                     double bal1 = Double.parseDouble(bal);
                     if (amount > 0 && bal1 - amount >= 1000) {
                         sql.withdraw(id, amount);
                         JOptionPane.showMessageDialog(null, "Success");

                     } else if (amount <= 0) {
                         JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(null, "Insufficient balance!(Note withdrawal not possible from minimum balance)", "Error", JOptionPane.ERROR_MESSAGE);
                     }

                 } catch (NumberFormatException er) {
                     JOptionPane.showMessageDialog(null, "Error in input", "Error", JOptionPane.ERROR_MESSAGE);
                 } catch (NullPointerException np) {
                 }
   			 }
   		 });
        add(withdraw_button);
        
        details_button=new JButton("User Details");
		details_button.setBounds(20, 400, 300, 50);
		details_button.setFont(font);
        details_button.addActionListener(e -> EventQueue.invokeLater(() -> {
            try {
                new UserDetails(id);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }));
        add(details_button);
        transfer_button=new JButton("Transfer");
		transfer_button.setBounds(20, 500, 300, 50);
		transfer_button.setFont(font);
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
        add(transfer_button);
        delete_button=new JButton("Delete Account");
		delete_button.setBounds(700, 500, 300, 50);
		delete_button.setFont(font);
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
        add(delete_button);
        signout=new JButton("Signout");
		signout.setBounds(350, 600, 300, 50);
		signout.setFont(font);
		signout.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
                 new WelcomePage();
   				 dispose();  			 
   			 }	
   		 });
        add(signout);
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2=(Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(3));
        g2.drawArc(100, 50, 50, 50, x, 160);
        g2.drawArc(95, 45, 60, 60, y, 180);
	}
	public void movearc() {
		
		if(x>=360) {
			x=0;
		}
		if(y<=0) {
			y=360;
		}
		x=x+speed;
		y=y-speed;
		
}
	public void run() {
		while(true) {
			movearc();
			repaint();
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
