/**Creation of Account 
 * Use MySql
 */
package classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sql.SqlFunctions;
import bank_main.WelcomePage;
public class CreateAccount extends Frame  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 SqlFunctions sq=new SqlFunctions();
     private TextField name_field,pass_field,age_field,username_field;
     private TextArea address_area;
     private Label name_label,pass_label,age_label,gender_label,address_label,username_label,no_input,checkinput,success,head,checkname;
     private Label agelimit,checkusername,checkusrnmsp,maxage;
     private Button create_button,discard_button;
     private Checkbox agree;
     private CheckboxGroup gender_box;
     private String input_name="",input_password="",input_gender="",input_address="",input_username="";
     private int input_age=0;
     public CreateAccount(){
    	     
        	 setTitle("Create Account");
        	 setFont(new Font("SansSerif",Font.PLAIN,20));
        	 setBackground(Color.LIGHT_GRAY);
        	 addWindowListener(new WindowAdapter() {
        		 public void windowClosing(WindowEvent w) {
        			 dispose();
        			 new WelcomePage();
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
    		 agelimit=new Label("You should be aged above 18 to apply for account.");
    		 agelimit.setBounds(20, 800, 1000, 60);
    		 agelimit.setVisible(false);
    		 agelimit.setForeground(Color.RED);
    		 add(agelimit);
    		 checkname=new Label("Name field cannnot contain numbers.Check your inputs.");
    		 checkname.setBounds(20, 800, 1000, 60);
    		 checkname.setVisible(false);
    		 checkname.setForeground(Color.RED);
    		 add(checkname);
    		 checkusername=new Label("User Name already exist.");
    		 checkusername.setBounds(20, 800, 1000, 60);
    		 checkusername.setVisible(false);
    		 checkusername.setForeground(Color.RED);
    		 add(checkusername);
    		 
    		 checkusrnmsp=new Label("User Name should not contain spaces!");
    		 checkusrnmsp.setBounds(20, 800, 1000, 60);
    		 checkusrnmsp.setVisible(false);
    		 checkusrnmsp.setForeground(Color.RED);
    		 add(checkusrnmsp);
    		 maxage=new Label("The data seems to be not authentic! Check your input");
    		 maxage.setBounds(20, 800, 1000, 60);
    		 maxage.setVisible(false);
    		 maxage.setForeground(Color.RED);
    		 add(maxage);
 
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
    			         visibility("noinput");
    			         JOptionPane.showMessageDialog(null, "No Input", "Error", JOptionPane.ERROR_MESSAGE);
    				 }
    				 else if (name_field.getText().matches(".*\\d+.*")) {
						 visibility("checkname");
    				 }
    				 else if (username_field.getText().matches(".*\\s+.*")) {
    					 visibility("usrspc");
    				 }
    				 else {
    					 try {
        				     input_name=name_field.getText();
        				     input_username=username_field.getText();
        				     input_password=pass_field.getText();
                             input_address=address_area.getText();
        				     if(age_field.getText().isEmpty()==false ) {
            			     	 input_age= Integer.parseInt(age_field.getText()); 
            			     	 
            			     	 if(input_age<18) {
           						    visibility("agelimit");
            			     	 }
            			     	 else if (input_age>120) {
            			     		 visibility("maxage");
            			     	 }
            			     	 else {
            			     		 int accexist=sq.getId(input_username);
            			     		 if(accexist!=0) {
               						    visibility("checkuser");
            			     		 }
            			     		 else {
            			     			getDataAccount(input_name,input_username,input_password,input_address,input_age,input_gender);   
               						    visibility("success");
            			     		 }
                				     
            			     	 }
            			     		
        				     }
        				     

    					 }catch(NumberFormatException er) {
    						 visibility("checkinput");
    					 }
    			          
     				     
    					 
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
    public void visibility(String choice) {
    	    success.setVisible(false);
		    checkinput.setVisible(false);
		    checkname.setVisible(false);
		    no_input.setVisible(false); 
		    agelimit.setVisible(false);
		    checkusername.setVisible(false);
		    checkusrnmsp.setVisible(false);
		    maxage.setVisible(false);
    	switch(choice) {
    	case "success" :  success.setVisible(true);
    	                  break;
    	case "checkinput" : checkinput.setVisible(true);
    	                    break;
    	case "checkname" : checkname.setVisible(true);
    	                   break;
    	case "noinput" : no_input.setVisible(true);
    	                 break;
    	case "agelimit" : agelimit.setVisible(true);
                         break;
    	case "checkuser" : checkusername.setVisible(true);
                         break;
    	case "usrspc" : checkusrnmsp.setVisible(true);
    	                break;
    	case "maxage" : maxage.setVisible(true);
    		            break;
    	}
    }
}
