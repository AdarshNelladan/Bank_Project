/**
 *
 */
package classes;

import java.awt.*;
import java.awt.event.*;
import sql.SqlFunctions;

import javax.swing.*;

/**
 * Class to change password
 * @author Adarsh
 *
 */
public class ChangePassword {

    /**
     *
     */
    SqlFunctions sql=new SqlFunctions();
    JFrame mainframe;
    JButton back,change;
    JLabel old_pass,new_pass,wrngpass,nopass,nopassold,head,success;
    JPasswordField old_field,new_field;
    public ChangePassword(int id) {
        mainframe=new JFrame("Change Password");
        mainframe.setSize(600,600);
        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                mainframe.dispose();
            }
        });

        //JLabel

        old_pass=new JLabel("Old Password   :");
        old_pass.setBounds(20, 200, 190, 30);
        old_pass.setFont(new Font("SansSerif",Font.BOLD,20));
        mainframe.add(old_pass);

        new_pass=new JLabel("New Password  :");
        new_pass.setBounds(20, 250, 190, 30);
        new_pass.setFont(new Font("SansSerif",Font.BOLD,20));
        mainframe.add(new_pass);
        wrngpass=new JLabel("Check your old password");
        wrngpass.setBounds(20, 500, 300, 30);
        wrngpass.setFont(new Font("SansSerif",Font.BOLD,15));
        wrngpass.setForeground(Color.RED);
        nopass=new JLabel("New password field should not be blank");
        nopass.setBounds(20, 500, 300, 30);
        nopass.setFont(new Font("SansSerif",Font.BOLD,15));
        nopass.setForeground(Color.RED);
        nopassold=new JLabel("Old password field should not be blank");
        nopassold.setBounds(20, 500, 300, 30);
        nopassold.setFont(new Font("SansSerif",Font.BOLD,15));
        nopassold.setForeground(Color.RED);
        head=new JLabel("Change Password");
        head.setBounds(150, 50, 400, 40);
        head.setFont(new Font("Tahoma",Font.BOLD,34));
        mainframe.add(head);
        success=new JLabel("Success");
        success.setBounds(20, 500, 200, 60);
        success.setVisible(false);
        success.setFont(new Font("SansSerif",Font.BOLD,15));
        success.setForeground(Color.GREEN);
        mainframe.add(success);

        old_field=new JPasswordField();
        old_field.setBounds(230, 200, 200, 30);
        mainframe.add(old_field);

        new_field=new JPasswordField();
        new_field.setBounds(230, 250, 200, 30);
        mainframe.add(new_field);

        change= new JButton("Change");
        change.setBounds(150, 400, 100, 30);
        change.setFont(new Font("SansSerif",Font.BOLD,15));
        change.addActionListener(e -> {
            String old_pswrd="",db_pswrd="",new_pswrd="";
            old_pswrd=old_field.getText();
            new_pswrd=new_field.getText();
            db_pswrd=sql.passwordCheck(id);
            if(old_pswrd.isEmpty()|| new_pswrd.isEmpty()) {
                if(new_pswrd.isEmpty()) {
                    wrngpass.setVisible(false);
                    nopassold.setVisible(false);
                    nopass.setVisible(true);
                    success.setVisible(false);
                    mainframe.add(nopass);
                }
                else {
                    wrngpass.setVisible(false);
                    nopassold.setVisible(true);
                    nopass.setVisible(false);
                    success.setVisible(false);
                    mainframe.add(nopassold);
                }

            }
            else {
                if(old_pswrd.equals(db_pswrd)) {
                    sql.passwordChange(id, new_pswrd);
                    success.setVisible(true);
                }
                else {
                    nopassold.setVisible(false);
                    success.setVisible(false);
                    wrngpass.setVisible(true);
                    nopass.setVisible(false);
                    mainframe.add(wrngpass);
                }
            }



        });
        mainframe.add(change);
        back= new JButton("Back");
        back.setBounds(300, 400, 100, 30);
        back.setFont(new Font("SansSerif",Font.BOLD,15));
        back.addActionListener(e -> mainframe.dispose());
        mainframe.add(back);
        mainframe.setLayout(null);
        mainframe.setVisible(true);
    }

}
