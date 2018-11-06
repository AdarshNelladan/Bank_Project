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

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlFunctions.connect();
		new WelcomePage();
	
       
	}
	
}


