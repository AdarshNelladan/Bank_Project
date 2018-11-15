/**  Login Page
 * 
 */
package bank_main;

/**
 * @author Adarsh
 *
 */
import java.awt.*;
import java.awt.event.*;
import classes.DialogManagement.ExceptionDialog;
import sql.SqlFunctions;
public class LoginPage  {
	int res=0;
	SqlFunctions sq=new SqlFunctions();
	public Frame fr1;
	public TextField name_field,pass_field;
	public Button login_button,discard_button;
	public Label name_label,pass_label,incorrect_login,no_username;
    public Checkbox agree;
    public String input_name,input_password;
   public LoginPage(){
   	     
       	 fr1=new Frame("Login");
       	 fr1.setBackground(Color.LIGHT_GRAY);
       	 fr1.addWindowListener(new WindowListener() {
       		 public void windowClosing(WindowEvent w) {
       			 fr1.dispose();
       			 new WelcomePage();
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
         fr1.setSize(800,600);
         fr1.setFont(new Font("Tahoma",Font.BOLD,16));
    	 fr1.setLayout(null);
    	 fr1.setVisible(true);
    	     //Labels
   		 name_label=new Label("Enter User Name :");
   		 name_label.setBounds(10, 150, 150, 60);
   		 fr1.add(name_label);
   		 pass_label=new Label("Enter Password  :");
   		 pass_label.setBounds(10, 205, 150, 60);
   		 fr1.add(pass_label);
   		 incorrect_login=new Label("Username and Password does not match.");
   		 incorrect_login.setForeground(Color.RED);
  		 incorrect_login.setBounds(10, 500, 900, 60);
  		 incorrect_login.setVisible(false);
  		 fr1.add(incorrect_login);
  		 
  		 no_username=new Label("Username does not exist!");
  		 no_username.setForeground(Color.RED);
 		 no_username.setBounds(10, 500, 900, 60);
 		 no_username.setVisible(false);
 		 fr1.add(no_username);
  		 Label head =new Label();
		 head.setText("Login to your account");
		 head.setFont(new Font("Tahoma",Font.BOLD,30));
		 head.setBounds(200, 80, 500, 50);
		 fr1.add(head);
   		 //TextFields
   		 name_field=new TextField();
   		 name_field.setBounds(200, 165, 500, 30);
   		 fr1.add(name_field);
   		 pass_field=new TextField();
   		 pass_field.setEchoChar('*');
   		 pass_field.setBounds(200, 220, 500, 30);
   		 fr1.add(pass_field);
   		 
   		 // Buttons
   		 login_button=new Button("Login");
   		 login_button.setBounds(200, 350, 100, 50);
   		 login_button.setEnabled(true);
   		 login_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				 input_name=name_field.getText();
   				 input_password=pass_field.getText();
    		     checkLoginData(input_name,input_password);

   			 }
   		 });
   		 fr1.add(login_button);
   		 
   		 discard_button=new Button("Discard");
   		 discard_button.setBounds(330, 350, 100, 50);
   		 discard_button.setEnabled(true);
   		 discard_button.addActionListener(new ActionListener() {
   			 public void actionPerformed(ActionEvent e) {
   				fr1.dispose();  
   				new WelcomePage();
   			 }
   		 });
   		 fr1.add(discard_button);
   		
   		 
   		 
   	 
    }
   public void checkLoginData(String input_name,String input_password){
		this.input_name=input_name;
	    this.input_password=input_password;

	    if(input_name.isEmpty() || input_password.isEmpty()) {
	    	new ExceptionDialog("Null input not accepted!");
	    }
	    else {
	    	int check=sq.checkName(input_name);
	    	if(check==0) {
	    		incorrect_login.setVisible(false);
	    		no_username.setVisible(true);
	    	}
	    	else {
	    		res=sq.checkAndProceed(input_name, input_password);	
			    if (res==0) {
			    	 incorrect_login.setVisible(true);
			    	 no_username.setVisible(false);
			    }
			    else {
			    	fr1.dispose();
			    	new BankMainPage(res);
		         }
	         }
	    
	    }
   }

   public void windowClosing(WindowEvent e) {  
	    fr1.dispose();  
   }

}
