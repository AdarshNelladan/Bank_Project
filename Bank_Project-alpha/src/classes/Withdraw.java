/**
 * 
 */
package classes;

import java.awt.*;
import java.awt.event.*;

import sql.SqlFunctions;

import javax.swing.*;

/**
 * @author Adarsh
 *
 */
public class Withdraw {

	/**
	 * 
	 */
	SqlFunctions sql=new SqlFunctions();
	JFrame mainframe;
	JButton back,with_button;
	JLabel amt_label,success,fail_label,head,empty;
	JTextField amt_field;
	double amount;
	String bal;
	double bal1;
	public Withdraw(int id) {
		mainframe=new JFrame("Withdraw");
		mainframe.setSize(600, 600);
		mainframe.setBackground(Color.LIGHT_GRAY);
		mainframe.setFont(new Font("SansSerif",Font.BOLD,20));
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		amt_label=new JLabel("Enter Amount    :");
		amt_label.setBounds(10, 150, 200, 30);
		amt_label.setFont(new Font("SansSerif",Font.BOLD,20));
		mainframe.add(amt_label);
		success=new JLabel("Success");
		success.setBounds(30, 400, 400, 30);
		success.setForeground(Color.GREEN);
		fail_label=new JLabel("Insufficient balance!(Note withdrawal not possible from minimum balance)");
		fail_label.setFont(new Font("SansSerif",Font.PLAIN,15));
		fail_label.setBounds(30, 400, 500, 30);
		fail_label.setForeground(Color.RED);
		amt_field=new JTextField();
		amt_field.setBounds(220, 150, 250, 30);
		mainframe.add(amt_field);
		head=new JLabel("Withdraw");
		head.setBounds(200, 50, 400, 40);
		head.setFont(new Font("Tahoma",Font.BOLD,34));
		mainframe.add(head);
		empty=new JLabel("Empty Field or data error!");
		empty.setBounds(30, 400, 500, 30);
		empty.setForeground(Color.RED);
		empty.setVisible(false);
		mainframe.add(empty);
		
		
		
		with_button=new JButton("Withdraw");
		with_button.setBounds(150, 300, 100, 50);
		with_button.setFont(new Font("Tahoma",Font.BOLD,16));
		with_button.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				try {
					 amount=Double.parseDouble(amt_field.getText());
						bal=sql.balanceCheck(id);
						bal1=Double.parseDouble(bal);
						if(amount>0 && bal1-amount>=1000) {
						  sql.withdraw(id, amount);
						  success.setVisible(true);
						  fail_label.setVisible(false);
						  empty.setVisible(false);
						  mainframe.add(success);
						  
						}
						else if(amount<=0) {
							empty.setVisible(true);
							fail_label.setVisible(false);
							success.setVisible(false);
						}
						else {
							success.setVisible(false);
							fail_label.setVisible(true);
							empty.setVisible(false);
							mainframe.add(fail_label);
						}
					
				}catch(NumberFormatException er) {
					empty.setVisible(true);
					JOptionPane.showMessageDialog(null,"Error in input","Error",JOptionPane.ERROR_MESSAGE);
					fail_label.setVisible(false);
					success.setVisible(false);
				}
				
  			 }
		 });
		mainframe.add(with_button);
		back= new JButton("Back");
		back.setBounds(300, 300, 100, 50);
		back.setFont(new Font("Tahoma",Font.BOLD,16));
		back.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				mainframe.dispose(); 
   			 }
		 });
		mainframe.add(back);
		mainframe.setLayout(null);
		mainframe.setVisible(true);
	}

}
