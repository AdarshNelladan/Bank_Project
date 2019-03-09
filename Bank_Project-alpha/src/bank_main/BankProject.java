/**
 *
 */
package bank_main;

import sql.SqlFunctions;

import javax.swing.*;

public class BankProject extends WelcomePage{

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        SqlFunctions.connect();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new WelcomePage();


    }

}


