package bank_main;

import java.awt.*;
import java.awt.event.*;
import classes.CreateAccount;
import classes.LoginPage;
import sql.SqlFunctions;

public class WelcomePage extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomePage() {
		// TODO Auto-generated constructor stub
		
		Button create_account_button,login_account_button;
		 
        setTitle("Welcome to Bank");
        setBackground(Color.LIGHT_GRAY);
		addWindowListener(new WindowListener() {
  		 public void windowClosing(WindowEvent w) {
  			 
  			 try {
				SqlFunctions.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
  			 dispose();
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
		setSize(800,600);
	    setLayout(null);
	    setVisible(true);
	    
	    /**
		 * Label area 
		 */
	    Label head =new Label();
		head.setText("Welcome to Bank Of India!");
		head.setFont(new Font("Tahoma",Font.BOLD,34));
		head.setBounds(200, 100, 500, 50);
		add(head);
		
		create_account_button=new Button("Create Account");
		create_account_button.setBounds(50, 300, 300, 60);
		create_account_button.setFont(new Font("Tahoma",Font.BOLD,20));
		add(create_account_button);
		create_account_button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
            dispose();
			new CreateAccount();
		}
		});
		
		
		login_account_button=new Button("Login");
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
