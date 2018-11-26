/**
 * 
 */
package classes;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

import classes.DialogManagement.ExceptionDialog;
import sql.SqlFunctions;

/**
 * @author Adarsh
 *
 */
public class Deposit {

	/**
	 * 
	 */
	SqlFunctions sql=new SqlFunctions();
	Frame mainframe;
	Button back,dep_button;
	Label amt_label,success,head,empty;
	TextField amt_field;
	double amount;
	public Deposit(int id) {
		mainframe=new Frame("Deposit");
		mainframe.setSize(400, 400);
		mainframe.setBackground(Color.LIGHT_GRAY);
		mainframe.addWindowListener(new WindowAdapter() {
	   		 public void windowClosing(WindowEvent w) {	   			 
	   			mainframe.dispose();
	   		 }
	   	  });
		amt_label=new Label("Enter Amount :");
		amt_label.setBounds(10, 150, 150, 30);
		amt_label.setFont(new Font("SansSerif",Font.BOLD,17));
		mainframe.add(amt_label);
		success=new Label("Success");
		success.setFont(new Font("SansSerif",Font.BOLD,16));
		success.setForeground(Color.GREEN);
		success.setBounds(30, 350, 100, 30);
		success.setVisible(false);
		mainframe.add(success);
		head=new Label("Deposit");
		head.setBounds(120, 50, 250, 40);
		head.setFont(new Font("Tahoma",Font.BOLD,30));
		mainframe.add(head);
		empty=new Label("Empty value or field!");
		empty.setBounds(30, 350, 300, 30);
		empty.setFont(new Font("SansSerif",Font.BOLD,16));
		empty.setForeground(Color.RED);
		empty.setVisible(false);
		mainframe.add(empty);
		
		amt_field=new TextField();
		amt_field.setBounds(160, 150, 200, 30);
		mainframe.add(amt_field);
		
		dep_button=new Button("Deposit");
		dep_button.setBounds(80, 250, 90, 50);
		dep_button.setFont(new Font("Tahoma",Font.BOLD,16));
		dep_button.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 try {
					 amount=Double.parseDouble(amt_field.getText());
					 if(amount>0) {
						 if(amount<1000000000) {
							 
							sql.deposit(id, amount);
							empty.setVisible(false);
							success.setVisible(true);
						  }
						 else {
							 JOptionPane.showMessageDialog(null, "Amount above limit,contact bank for assistance", "Error:Limit reached", JOptionPane.INFORMATION_MESSAGE);
						 }
						 
					 }
					 else {
						 empty.setVisible(true);
						 success.setVisible(false);
					 }
					 
				 }catch(NumberFormatException er) {
					 empty.setVisible(true);
					 new ExceptionDialog("Input Error");
					 success.setVisible(false);
				 }
				
				
				
  			 }
		 });
		mainframe.add(dep_button);
		back= new Button("Back");
		back.setBounds(200, 250, 100, 50);
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
