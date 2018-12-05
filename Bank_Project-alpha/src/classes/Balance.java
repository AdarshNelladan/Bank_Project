/**
 *
 */
package classes;

/**
 * @author Adarsh
 *
 */
import java.awt.*;
import java.awt.event.*;
public class Balance {

    /**
     *
     */
    Frame mainframe;
    Button back;
    Label bal,head;

    public Balance(String balance) {
        // TODO Auto-generated constructor stub
        mainframe=new Frame("Balance");
        mainframe.setSize(400, 400);
        mainframe.setBackground(Color.LIGHT_GRAY);
        mainframe.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent w) {
                mainframe.dispose();
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
        bal=new Label(balance);
        bal.setBounds(150, 150, 200, 40);
        bal.setFont(new Font("SansSerif",Font.BOLD,22));
        mainframe.add(bal);
        head=new Label("Your Balance");
        head.setBounds(120, 50, 250, 40);
        head.setFont(new Font("Tahoma",Font.BOLD,25));
        mainframe.add(head);
        back= new Button("Back");
        back.setBounds(150, 300, 100, 40);
        back.setFont(new Font("Tahoma",Font.BOLD,20));
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
