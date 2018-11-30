package sql;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import classes.DialogManagement.ExceptionDialog;

public class SqlFunctions {
    public static String database="online";
	static Statement stmt=null;
	private final static String DB="bank_project";
    public double bal;
	public String error;
	static Connection con=null;
	ResultSet check_rs=null,check_bal=null,check_ps=null;
    public String name,username,gender,address;
    public int age,accno;
	int idint;
	public LinkedList<String> type=new LinkedList<String>();
	public LinkedList<Double> value=new LinkedList<Double>();
	
	    public static Connection connect()
	    {
	        try
	        {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            if(database.equals("local")) {
		            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"", "root", "adarsh");
	            }
	            else {
	            	con=DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/bank_project", "imca17006", "imca17006");
	            }
	            stmt=con.createStatement();

	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            //new ExceptionDialog("Error Establishing a Connection");
	            JOptionPane.showMessageDialog(null, "Error Establishing a connection. Check your configuration or Internet!", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        return con;        
	    }
	

    /**
     * method to create the account
     * @param name
     * @param username
     * @param ps
     * @param address
     * @param age
     * @param gender
     * Also using exception handling
     */
    public void create(String name,String username,String ps,String address,int age,String gender) {
    	try {
    		
            stmt.executeUpdate("insert into "+DB+".user_account (NAME,USER_NAME,PASSWORD,ADDRESS,AGE,GENDER) values ('"+name+"','"+username+"','"+ps+"','"+address+"',"+age+",'"+gender+"');");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error=e.getMessage();
			errorCreation(error);
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    /**
     * method to check the name and password to login
     * @param name
     * @param ps
     * @return
     */
    public int checkAndProceed(String name,String ps){
    	int flag=0;
    	
    	try {
    		PreparedStatement pstmt=con.prepareStatement("select ACC_NO from "+DB+".user_account where USER_NAME=? AND PASSWORD=?;"); 
			//check_rs= stmt.executeQuery("select ACC_NO from "+DB+".user_account where USER_NAME='"+name+"' AND PASSWORD='"+ps+"';");
    		pstmt.setString(1, name);
    		pstmt.setString(2, ps);
    		check_rs=pstmt.executeQuery();
			while(check_rs.next()) {
				
				if(check_rs.getString(1).isEmpty()==false) {
					idint=Integer.parseInt(check_rs.getString(1));
					flag=1;
				}
				else {
					flag=0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		if(flag==1) {
			
			return idint;
		}
		else
			return 0;    	
    }
    
    /**
     * method to check the password
     * @param id
     * @return
     */
    public String passwordCheck(int id) {
    	String pass=null;
    	try {
    		stmt=con.createStatement();
			check_ps=stmt.executeQuery("select PASSWORD from "+DB+".user_account where ACC_NO="+id+";");
			while(check_ps.next()) {
			pass=check_ps.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		   e.printStackTrace();
		   new ExceptionDialog("Error in SQL");

		}
    	return pass;
    }
    /**
     * method to change the password
     * @param id
     * @param password
     */
    public void passwordChange(int id,String password) {
    	try {
    		stmt=con.createStatement();
			stmt.executeUpdate("update "+DB+".user_account set PASSWORD='"+password+"' where ACC_NO="+id+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ExceptionDialog("Error in SQL");
		}
    }
    /**
     * method to check the balance
     * @param id
     * @return bal1
     */

    public String balanceCheck(int id) {
    	
    	
    	try {
    		stmt=con.createStatement();
			check_bal=stmt.executeQuery("select BALANCE from "+DB+".account_details where ACC_NO="+id+";");
			while(check_bal.next()) {
			bal=check_bal.getDouble(1);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ExceptionDialog("Error Establishing a Connection");
			e.printStackTrace();
		}
    	String bal1 =new BigDecimal(bal).toPlainString();
    	return bal1;
    }
    /**
     * method to get statement of the transfers done
     * @param id
     */
    public void statement(int id) {
    	  Statement stmt_rev;
    	  String uname=getUserName(id);
		try {
			stmt_rev = con.createStatement(
					                        ResultSet.TYPE_SCROLL_INSENSITIVE,
					                        ResultSet.CONCUR_READ_ONLY,
					                        ResultSet.FETCH_REVERSE);
	    	  ResultSet stmt_rs=stmt_rev.executeQuery("select TYPE,VALUE from "+DB+".statement_details where ACC_NO="+id+" and USER_NAME='"+uname+"';");
	    	  while(stmt_rs.next()) {
	    		  type.add(stmt_rs.getString("TYPE"));
	    		  value.add(stmt_rs.getDouble("VALUE"));
	    	  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
           e.printStackTrace();
           new ExceptionDialog("Error Establishing a Connection");

		}

    	  
    }
    /**
     * method to deposit money
     * @param id
     * @param amt
     */
    public void deposit(int id,double amt) {
    	String uname=getUserName(id);
    	try {
			stmt.executeUpdate("update "+DB+".account_details set BALANCE=BALANCE+"+amt+" where ACC_NO="+id+";");
			stmt.executeUpdate("insert into "+DB+".statement_details (ACC_NO,USER_NAME,TYPE,VALUE) values ("+id+",'"+uname+"','DEPOSIT',"+amt+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
			e.printStackTrace();
		}
    }
    /**
     * method to withdraw money
     * @param id
     * @param amt
     */
    public void withdraw(int id,double amt) {
    	String uname=getUserName(id);
    	try {
			stmt.executeUpdate("update "+DB+".account_details set BALANCE=BALANCE-"+amt+" where ACC_NO="+id+";");
			stmt.executeUpdate("insert into "+DB+".statement_details (ACC_NO,USER_NAME,TYPE,VALUE) values ("+id+",'"+uname+"','WITHDRAW',"+amt+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    /**
     * method to get userdetails
     * @param id
     */
    public void userDetails(int id) {
    	
    	try {
			ResultSet rs_data = stmt.executeQuery("select * from "+DB+".user_account where ACC_NO="+id+";");
			
			while(rs_data.next()) {
				accno=Integer.parseInt(rs_data.getString("ACC_NO"));
				name=rs_data.getString("NAME");
				username=rs_data.getString("USER_NAME");
				age=Integer.parseInt(rs_data.getString("AGE"));
				gender=rs_data.getString("GENDER");
				address=rs_data.getString("ADDRESS");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    
    /**
     * method to get name
     * @param id
     * @return
     */
    public String getName(int id) {
    	String getname="";
    	try {
			ResultSet rs_name=stmt.executeQuery("select NAME from "+DB+".user_account where ACC_NO="+id+";");
			while(rs_name.next()) {
				getname=rs_name.getString("NAME");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return getname;
    }
    
    /**
     * method to get username
     * @param id
     * @return
     */
    public String getUserName(int id) {
    	String getname="";
    	try {
			ResultSet rs_name=stmt.executeQuery("select USER_NAME from "+DB+".user_account where ACC_NO="+id+";");
			while(rs_name.next()) {
				getname=rs_name.getString("USER_NAME");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return getname;
    }
    
    /**
     * method to get the ID
     * @param name
     * @return
     */
    public int getId(String name) {
    	int id=0;
    	try {
			ResultSet rs_name=stmt.executeQuery("select ACC_NO from "+DB+".user_account where USER_NAME='"+name+"';");
			while(rs_name.next()) {
				id=Integer.parseInt(rs_name.getString("ACC_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return id;
    }
    /**
     * method to check the given name
     * @param name
     * @return
     */
    public int checkName(String name) {
    	int id=0;
    	try {
			ResultSet rs_name=stmt.executeQuery("select ACC_NO from "+DB+".user_account where USER_NAME='"+name+"';");
			while(rs_name.next()) {
				id++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return id;
    }
    /**
     * method to disconnect
     */
    public static void disconnect() {
    	try {
			con.close();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
		}
    	catch(Exception e1) {
    		e1.printStackTrace();
    	}
    }
    /**
     * method to transfer from one account to another using userID
     * @param id
     * @param toid
     * @param amt
     */
    public void transferById(int id,int toid,double amt) {
    	 String getname=getUserName(toid);
    	 String name=getName(toid);
    	 String uname=getUserName(id);
    	 String bal="";
    	 if (getname.equals("")) {
    		 new ExceptionDialog("No User with this ID!"); 
    	 }
    	 else {
    		 bal=balanceCheck(id);
    		 double balance=Double.parseDouble(bal);
    		 if(balance-amt<1000) {
    			 new ExceptionDialog("Less Balance or Min_Bal_Rule not satisfied!"); 
    		 }
    		 else {
    			 try {
					stmt.executeUpdate("update "+DB+".account_details set BALANCE=BALANCE+"+amt+" where ACC_NO="+toid+";");
					stmt.executeUpdate("update "+DB+".account_details set BALANCE=BALANCE-"+amt+" where ACC_NO="+id+";");
					stmt.executeUpdate("insert into "+DB+".statement_details (ACC_NO,USER_NAME,TYPE,VALUE) values ("+toid+",'"+getname+"','CREDITED',"+amt+");");
					stmt.executeUpdate("insert into "+DB+".statement_details (ACC_NO,USER_NAME,TYPE,VALUE) values ("+id+",'"+uname+"','DEBITED',"+amt+");");
					new ExceptionDialog("Successfully Transferred to "+name+"!"); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					new ExceptionDialog("Error Establishing a Connection"); 
				}
    			 
    		 }
    	 }
    }
    
    /**
     * method to transfer from one account to another using username
     * @param id
     * @param name
     * @param amt
     */
    public void transferByName(int id,String name,double amt) {
    	int getid=getId(name);
    	String toname=getName(getid);
    	String bal="";
    	if(getid==0) {
    		new ExceptionDialog("No User with this Username!"); 
    	}
    	else {
    		bal=balanceCheck(id);
   		    double balance=Double.parseDouble(bal);
    		if(balance-amt<1000) {
    			new ExceptionDialog("Less Balance or Min_Bal_Rule not satisfied!");
    		}
    		else {
    			String uname=getUserName(id);
    			try {
    				stmt.executeUpdate("update "+DB+".account_details set BALANCE=BALANCE+"+amt+" where ACC_NO="+getid+";");
    				stmt.executeUpdate("update "+DB+".account_details set BALANCE=BALANCE-"+amt+" where ACC_NO="+id+";");
    				stmt.executeUpdate("insert into "+DB+".statement_details (ACC_NO,USER_NAME,TYPE,VALUE) values ("+getid+",'"+name+"','CREDITED',"+amt+");");
    				stmt.executeUpdate("insert into "+DB+".statement_details (ACC_NO,USER_NAME,TYPE,VALUE) values ("+id+",'"+uname+"','DEBITED',"+amt+");");
    				new ExceptionDialog("Successfully Transferred to "+toname+"!");
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				new ExceptionDialog("Error Establishing a Connection");
    			}
    		}
    	}
    	
    }
    /**
     * method to delete account
     * @param id
     */
    public void deleteAccount(int id) {
    	try {
			stmt.executeUpdate("DELETE FROM "+DB+".user_account WHERE (ACC_NO = '"+id+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ExceptionDialog("Error Establishing a Connection");
			e.printStackTrace();
		}
    }
    /**
     * 
     * 
     * @param er
     * @return
     */
    public String errorCreation(String er) {
    	return er;
    }
}
