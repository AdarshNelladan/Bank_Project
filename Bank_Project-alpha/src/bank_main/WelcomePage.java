package bank_main;

import java.awt.*;
import java.awt.event.*;
import classes.CreateAccount;
import sql.SqlFunctions;

import javax.swing.*;

public class WelcomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomePage() {
		// TODO Auto-generated constructor stub
		
		JButton create_account_button,login_account_button;
		 
        setTitle("Welcome to Bank");
        setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
	    setLayout(null);
	    setVisible(true);
	    
	    /**
		 * JLabel area
		 */
	    JLabel head =new JLabel();
		head.setText("Welcome to Bank Of India!");
		head.setFont(new Font("Tahoma",Font.BOLD,34));
		head.setBounds(200, 100, 500, 50);
		add(head);
		
		create_account_button=new JButton("Create Account");
		create_account_button.setBounds(50, 300, 300, 60);
		create_account_button.setFont(new Font("Tahoma",Font.BOLD,20));
		add(create_account_button);
		create_account_button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
            dispose();
			new CreateAccount();
		}
		});
		
		
		login_account_button=new JButton("Login");
		login_account_button.setBounds(450, 300, 300, 60);
		login_account_button.setFont(new Font("Tahoma",Font.BOLD,20));
		add(login_account_button);
		login_account_button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
            dispose();
			new LoginPage();
		    }
		 });
		}

	public void visiblity() {
		// TODO Auto-generated method stub
		setVisible(true);
	}

}
