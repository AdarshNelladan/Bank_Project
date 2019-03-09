/**
 *
 */
package bank_main;

import sql.SqlFunctions;

public class BankProject extends WelcomePage{

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        SqlFunctions.connect();
        new WelcomePage();


    }

}


