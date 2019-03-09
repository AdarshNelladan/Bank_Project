/**
 *
 */
package classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import sql.SqlFunctions;

/**
 * @author Adarsh
 *
 */
public class Deposit {


    SqlFunctions sql=new SqlFunctions();
    JFrame mainframe;
    JButton back,dep_button;
    JLabel amt_label,success,head,empty;
    JTextField amt_field;
    double amount;
    public Deposit(int id) {
        mainframe=new JFrame("Deposit");
        mainframe.setSize(400, 400);
        mainframe.setBackground(Color.LIGHT_GRAY);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        amt_label=new JLabel("Enter Amount :");
        amt_label.setBounds(10, 150, 150, 30);
        amt_label.setFont(new Font("SansSerif",Font.BOLD,17));
        mainframe.add(amt_label);
        success=new JLabel("Success");
        success.setFont(new Font("SansSerif",Font.BOLD,16));
        success.setForeground(Color.GREEN);
        success.setBounds(30, 350, 100, 30);
        success.setVisible(false);
        mainframe.add(success);
        head=new JLabel("Deposit");
        head.setBounds(120, 50, 250, 40);
        head.setFont(new Font("Tahoma",Font.BOLD,30));
        mainframe.add(head);
        empty=new JLabel("Empty value or field!");
        empty.setBounds(30, 350, 300, 30);
        empty.setFont(new Font("SansSerif",Font.BOLD,16));
        empty.setForeground(Color.RED);
        empty.setVisible(false);
        mainframe.add(empty);

        amt_field=new JTextField();
        amt_field.setBounds(160, 150, 200, 30);
        mainframe.add(amt_field);

        dep_button=new JButton("Deposit");
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
                    JOptionPane.showMessageDialog(null,"Input Error","Error",JOptionPane.ERROR_MESSAGE);
                    success.setVisible(false);
                }



            }
        });
        mainframe.add(dep_button);
        back= new JButton("Back");
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
