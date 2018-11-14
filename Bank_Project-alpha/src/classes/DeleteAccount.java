/**
 * 
 */
package classes;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import classes.DialogManagement.ExceptionDialog;
import sql.SqlFunctions;
import java.awt.TextArea;

/**
 * @author Adarsh
 *
 */
public class DeleteAccount extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	SqlFunctions sql=new SqlFunctions();	
	Frame mainframe;
	Button back,confirm;
	Label pass,warning;
	TextField field;
	private TextArea textArea;
	public DeleteAccount(int id) {
		// TODO Auto-generated constructor stub
		mainframe=new Frame("Delete Account");
		setSize(600,600);
		setLayout(null);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
	   		 public void windowClosing(WindowEvent w) {	   			 
	   			dispose();
	   		 }
	   	     });
		
		//Label
		pass=new Label("Enter Password :");
		pass.setBounds(20, 400, 190, 30);
		pass.setFont(new Font("SansSerif",Font.BOLD,20));
		add(pass);
		
		field=new TextField();
		field.setEchoChar('*');
		field.setBounds(230, 400, 200, 30);
		add(field);
		
		confirm= new Button("Confirm");
		confirm.setBounds(150, 500, 100, 30);
		confirm.setFont(new Font("SansSerif",Font.BOLD,15));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pswrd="",dbpswrd="";
			    pswrd=field.getText();
				
				
				if(pswrd.isEmpty()) {
					new ExceptionDialog("No Input");
				}
				else {
					dbpswrd=sql.passwordCheck(id);
					if(pswrd.equals(dbpswrd)) {
						try {
							sql.deleteAccount(id);
							SqlFunctions.disconnect();
						} catch (Exception e1) {
							new ExceptionDialog("Error Establishing a Connection");
						}
						System.exit(0);
					}
					else {
						new ExceptionDialog("Wrong Password!");
					}
				}
				
				
				
			}
		});
		add(confirm);
		
		back= new Button("Back");
		back.setBounds(300, 500, 100, 30);
		back.setFont(new Font("SansSerif",Font.BOLD,15));
		back.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				dispose();
				
   			 }
		 });
		add(back);
		textArea = new TextArea("Dear Customer,\n If you are not satisfied by our service you can delete your"
				+ " account.All your details except transactions will be deleted.If you still want to delete your account,"
				+ "type your password and enter confirm.The program will be closed after deletion.  ",3,100,TextArea.SCROLLBARS_VERTICAL_ONLY);
		textArea.setBounds(10, 100, 580, 280);
		textArea.setFont(new Font("Tahoma",Font.BOLD,20));
		add(textArea);
		
	}

}
