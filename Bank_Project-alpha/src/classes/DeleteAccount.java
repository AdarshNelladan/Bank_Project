/**
 *
 */
package classes;

import java.awt.Font;
import sql.SqlFunctions;

import javax.swing.*;
import java.awt.TextArea;

/**
 * @author Adarsh
 *
 */
public class DeleteAccount extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    SqlFunctions sql=new SqlFunctions();
    JButton back,confirm;
    JLabel pass,warning;
    JPasswordField field;
    private TextArea textArea;
    public DeleteAccount(int id) {
        // TODO Auto-generated constructor stub
        setTitle("Delete Account");
        setSize(600,600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JLabel
        pass=new JLabel("Enter Password :");
        pass.setBounds(20, 400, 190, 30);
        pass.setFont(new Font("SansSerif",Font.BOLD,20));
        add(pass);

        field=new JPasswordField();
        field.setBounds(230, 400, 200, 30);
        add(field);

        confirm= new JButton("Confirm");
        confirm.setBounds(150, 500, 100, 30);
        confirm.setFont(new Font("SansSerif",Font.BOLD,15));
        confirm.addActionListener(e -> {
            String pswrd="",dbpswrd="";
            pswrd=field.getText();


            if(pswrd.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No Input","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {
                dbpswrd=sql.passwordCheck(id);
                if(pswrd.equals(dbpswrd)) {
                    try {
                        sql.deleteAccount(id);
                        SqlFunctions.disconnect();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,"Error Establishing a Connection","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    System.exit(0);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Wrong Password","Error",JOptionPane.ERROR_MESSAGE);
                }
            }



        });
        add(confirm);

        back= new JButton("Back");
        back.setBounds(300, 500, 100, 30);
        back.setFont(new Font("SansSerif",Font.BOLD,15));
        back.addActionListener(e -> dispose());
        add(back);
        textArea = new TextArea("Dear Customer,\n If you are not satisfied by our service you can delete your"
                + " account.All your details except transactions will be deleted.If you still want to delete your account,"
                + "type your password and enter confirm.The program will be closed after deletion.  ",3,100,TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setBounds(10, 100, 580, 280);
        textArea.setFont(new Font("Tahoma",Font.BOLD,20));
        add(textArea);

    }

}
