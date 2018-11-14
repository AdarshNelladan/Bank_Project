package classes.DialogManagement;

import java.awt.*;
import java.awt.event.*;

public class ExceptionDialog {
	

	private Dialog d;
    
	public ExceptionDialog(String error) {
		Frame f= new Frame(); 
		d = new Dialog(f , "Error!", true); 
		d.addWindowListener(new WindowListener() {
	   		 public void windowClosing(WindowEvent w) {	   			 
	   			f.dispose();
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
        d.setLayout( null);  
        Button b = new Button ("OK");  
        b.setBounds(200, 200, 100, 50);
        b.setFont(new Font("SansSerif",Font.BOLD,25));
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                d.setVisible(false);  
                d.dispose();
            }  
        });  
        Label err=new Label();
        err.setText(error);
        err.setBounds(10, 100, 400, 50);
        err.setFont(new Font("SansSerif",Font.BOLD,18));
        d.add(err);  
        d.add(b);   
        d.setSize(500,300);    
        d.setVisible(true); 
		
	}


}
