/**Creation of Account 
 * Use MySql
 */
package classes;

import java.awt.*;
import java.awt.event.*;
import sql.SqlFunctions;
import bank_main.WelcomePage;
public class CreateAccount extends Frame  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 SqlFunctions sq=new SqlFunctions();
	 public Frame fr1;
     public TextField name_field,pass_field,age_field,username_field;
     public TextArea address_area;
     public Label name_label,pass_label,age_label,gender_label,address_label,username_label,no_input,checkinput,success,head;
     public Button create_button,discard_button;
     public Checkbox agree;
     public CheckboxGroup gender_box;
     public String input_name="",input_password="",input_gender="",input_address="",input_username="";
     public int input_age=0;
     public CreateAccount(){
    	     
        	 setTitle("Create Account");
        	 setFont(new Font("SansSerif",Font.PLAIN,20));
        	 setBackground(Color.LIGHT_GRAY);
        	 addWindowListener(new WindowListener() {
        		 public void windowClosing(WindowEvent w) {
        			 dispose();
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
     		 setSize(1028,960);
     	     setLayout(null);
     	     setVisible(true);
     	     //Labels
     	     head =new Label();
     	     head.setText("Create your account");
    		 head.setFont(new Font("SansSerif",Font.BOLD,30));
    		 head.setBounds(300, 50, 500, 40);
    		 add(head);
    		 name_label=new Label("Enter Name            :");
    		 name_label.setBounds(50, 130, 200, 60);
    		 add(name_label);
    		 username_label=new Label("Enter User Name    :");
    		 username_label.setBounds(50, 180, 200, 60);
    		 add(username_label);
    		 pass_label=new Label("Enter Password      :");
    		 pass_label.setBounds(50, 230, 200, 60);
    		 add(pass_label);
    		 age_label=new Label("Enter Age              :");
    		 age_label.setBounds(50, 280, 250, 60);
    		 add(age_label);
    		 address_label=new Label("Address                :");
    		 address_label.setBounds(50, 380, 250, 60);
    		 add(address_label);
    		 gender_label=new Label("Gender                 :");
    		 gender_label.setBounds(50, 330, 250, 60);
    		 add(gender_label);
    		 no_input=new Label("All fields are required.Check your inputs.");
    		 no_input.setBounds(20, 800, 1000, 60);
    		 no_input.setVisible(false);
    		 no_input.setForeground(Color.RED);
    		 add(no_input);
    		 checkinput=new Label("Age field can only contain numbers.Check your inputs.");
    		 checkinput.setBounds(20, 800, 1000, 60);
    		 checkinput.setVisible(false);
    		 checkinput.setForeground(Color.RED);
    		 add(checkinput);
    		 success=new Label("Success");
    		 success.setBounds(20, 800, 1000, 60);
    		 success.setVisible(false);
    		 success.setForeground(Color.GREEN);
    		 add(success);
    		 
 
    		 //TextArea
    		 address_area=new TextArea();
    		 address_area.setBounds(400, 400, 300, 200);
    		 add(address_area);
    		 
    		 
    		 //TextFields
    		 name_field=new TextField();
    		 name_field.setBounds(400, 150, 500, 30);
    		 add(name_field);
    		 username_field=new TextField();
    		 username_field.setBounds(400, 200, 500, 30);
    		 add(username_field);
    		 pass_field=new TextField();
    		 pass_field.setEchoChar('*');
    		 pass_field.setBounds(400, 250, 500, 30);
    		 add(pass_field);
    		 age_field=new TextField();
    		 age_field.setBounds(400, 300, 200, 30);
    		 add(age_field);
    		 
    		 
    		 // Buttons
    		 create_button=new Button("Create");
    		 create_button.setBounds(300, 700, 150, 60);
    		 create_button.setEnabled(false);
    		 create_button.addActionListener(new ActionListener() {
    			 public void actionPerformed(ActionEvent e) {
    				 if(name_field.getText().equals("") || username_field.getText().equals("") || pass_field.getText().equals("") || address_area.getText().equals("") || age_field.getText().isEmpty()==true ) {
    			         no_input.setVisible(true);
    			         checkinput.setVisible(false);
    			         success.setVisible(false);
    				 }
    				 else {
    					 try {
    						 checkinput.setVisible(false);
    						 no_input.setVisible(false);
        				     input_name=name_field.getText();
        				     input_username=username_field.getText();
        				     input_password=pass_field.getText();
                             input_address=address_area.getText();
        				     if(age_field.getText().isEmpty()==false ) {
            			     	 input_age= Integer.parseInt(age_field.getText()); 
        				     } 
    					 }catch(NumberFormatException er) {
    						 checkinput.setVisible(true);
    						 no_input.setVisible(false);
    						 success.setVisible(false);
    					 }
    			          
     				     
    					 getDataAccount(input_name,input_username,input_password,input_address,input_age,input_gender);   
    					 success.setVisible(true);
    					 checkinput.setVisible(false);
						 no_input.setVisible(false);
    				     }
    			 }
    		 });
    		 add(create_button);
    		 
    		 discard_button=new Button("Discard");
    		 discard_button.setBounds(600, 700, 150, 60);
    		 discard_button.setEnabled(true);
    		 discard_button.addActionListener(new ActionListener() {
    			 public void actionPerformed(ActionEvent e) {
    				dispose();
    				new WelcomePage();
    			 }
    		 });
    		 add(discard_button);
    		 
    		//Checkbox
    		 agree=new Checkbox("I agree to all terms.");
    		 agree.setBounds(400, 650, 300, 30);
    		 agree.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if(e.getStateChange()==1) {
						create_button.setEnabled(true);
					}
					else {
						create_button.setEnabled(false);
					}
				}
    			 
    		 });
    		 add(agree);
    		 
    		 //Checkbox
    		 gender_box=new CheckboxGroup();
    		 Checkbox gender_male=new Checkbox("Male",gender_box,false);
    		 gender_male.setBounds(400, 350, 100, 30);
    		 gender_male.addItemListener(new ItemListener() {

 				@Override
 				public void itemStateChanged(ItemEvent e) {
 					// TODO Auto-generated method stub
 					if(e.getStateChange()==1) {
 						input_gender="male";
 					}
 				}
     			 
     		 });
    		 add(gender_male);
    		 Checkbox gender_female=new Checkbox("Female",gender_box,false);
    		 
    		 gender_female.setBounds(500, 350, 100, 30);
    		 gender_female.addItemListener(new ItemListener() {

 				@Override
 				public void itemStateChanged(ItemEvent e) {
 					// TODO Auto-generated method stub
 					if(e.getStateChange()==1) {
 						input_gender="female";
 					}
 				}
     			 
     		 });
    		 add(gender_female);
    		 Checkbox gender_other=new Checkbox("Other",gender_box,false);
    		 gender_other.setBounds(600, 350, 100, 30);
    		 gender_other.addItemListener(new ItemListener() {

 				@Override
 				public void itemStateChanged(ItemEvent e) {
 					// TODO Auto-generated method stub
 					if(e.getStateChange()==1) {
 						input_gender="other";
 					}
 				}
     			 
     		 });
    		 add(gender_other);
    		 
    		 
    				 
    	 
     }
    public void getDataAccount(String input_name,String input_username,String input_password,String input_address,int input_age,String input_gender) {
		this.input_name=input_name;
		this.input_username=input_username;
	    this.input_password=input_password;
	    this.input_address=input_address;
	    this.input_age=input_age;
	    this.input_gender=input_gender;
      
    	sq.create(input_name,input_username,input_password,input_address,input_age,input_gender);

        
		 
	 }
    public void windowClosing(WindowEvent e) {  
	    dispose();  
    }
}
