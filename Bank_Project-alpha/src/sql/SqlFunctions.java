package sql;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import classes.DialogManagement.ExceptionDialog;

public class SqlFunctions {
    public static String database="online";
	static Statement stmt=null;
    public double bal;
	public String error;
	static Connection con=null;
	ResultSet check_rs=null,check_bal=null,check_ps=null;
    public String name,username,gender,address;
    public int age;
	int idint;
	public LinkedList<String> type=new LinkedList<String>();
	public LinkedList<Double> value=new LinkedList<Double>();
	
	    public static Connection connect()
	    {
	        try
	        {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            if(database.equals("local")) {
		            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_project", "root", "adarsh");
	            }
	            else {
	            	//con=DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/bankprotest", "imcatest", "imcatest");
	            	con=DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/bank_project", "imca17006", "imca17006");
	            }
	            stmt=con.createStatement();

	        }
	        catch(Exception e)
	        {
	            
	            new ExceptionDialog("Error Establishing a Connection");
	        }

	        return con;        
	    }
	

    public void create(String name,String username,String ps,String address,int age,String gender) {
    	try {
    		
            stmt.executeUpdate("insert into bank_project.user_account (NAME,USER_NAME,PASSWORD,ADDRESS,AGE,GENDER) values ('"+name+"','"+username+"','"+ps+"','"+address+"',"+age+",'"+gender+"');");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error=e.getMessage();
			errorCreation(error);
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    public int checkAndProceed(String name,String ps){
    	int flag=0;
    	try {
			check_rs= stmt.executeQuery("select USER_ID from bank_project.user_account where USER_NAME='"+name+"' AND PASSWORD='"+ps+"';");
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
    
    public String passwordCheck(int id) {
    	String pass=null;
    	try {
    		stmt=con.createStatement();
			check_ps=stmt.executeQuery("select PASSWORD from bank_project.user_account where USER_ID="+id+";");
			while(check_ps.next()) {
			pass=check_ps.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		   
		   new ExceptionDialog("Error in SQL");

		}
    	return pass;
    }
    public void passwordChange(int id,String password) {
    	try {
    		stmt=con.createStatement();
			stmt.executeUpdate("update bank_project.user_account set PASSWORD='"+password+"' where USER_ID="+id+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//
			new ExceptionDialog("Error in SQL");
		}
    }
    public String balanceCheck(int id) {
    	
    	
    	try {
    		stmt=con.createStatement();
			check_bal=stmt.executeQuery("select BALANCE from bank_project.account_details where USER_ID="+id+";");
			while(check_bal.next()) {
			bal=check_bal.getDouble(1);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ExceptionDialog("Error Establishing a Connection");
		}
    	String bal1 =new BigDecimal(bal).toPlainString();
    	return bal1;
    }
    public void statement(int id) {
    	  Statement stmt_rev;

		try {
			stmt_rev = con.createStatement(
					                        ResultSet.TYPE_SCROLL_INSENSITIVE,
					                        ResultSet.CONCUR_READ_ONLY,
					                        ResultSet.FETCH_REVERSE);
	    	  ResultSet stmt_rs=stmt_rev.executeQuery("select TYPE,VALUE from bank_project.statement_details where USER_ID="+id+";");
	    	  while(stmt_rs.next()) {
	    		  type.add(stmt_rs.getString("TYPE"));
	    		  value.add(stmt_rs.getDouble("VALUE"));
	    	  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
           
           new ExceptionDialog("Error Establishing a Connection");

		}

    	  
    }
    public void deposit(int id,double amt) {
    	try {
			stmt.executeUpdate("update bank_project.account_details set BALANCE=BALANCE+"+amt+" where USER_ID="+id+";");
			stmt.executeUpdate("insert into bank_project.statement_details values ("+id+",'DEPOSIT',"+amt+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    public void withdraw(int id,double amt) {
    	try {
			stmt.executeUpdate("update bank_project.account_details set BALANCE=BALANCE-"+amt+" where USER_ID="+id+";");
			stmt.executeUpdate("insert into bank_project.statement_details values ("+id+",'WITHDRAW',"+amt+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    public void userDetails(int id) {
    	
    	try {
			ResultSet rs_data = stmt.executeQuery("select * from bank_project.user_account where USER_ID="+id+";");
			
			while(rs_data.next()) {
				name=rs_data.getString("NAME");
				username=rs_data.getString("USER_NAME");
				age=Integer.parseInt(rs_data.getString("AGE"));
				gender=rs_data.getString("GENDER");
				address=rs_data.getString("ADDRESS");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    
    public String getName(int id) {
    	String getname="";
    	try {
			ResultSet rs_name=stmt.executeQuery("select NAME from bank_project.user_account where USER_ID="+id+";");
			while(rs_name.next()) {
				getname=rs_name.getString("NAME");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return getname;
    }
    
    public int getId(String name) {
    	int id=0;
    	try {
			ResultSet rs_name=stmt.executeQuery("select USER_ID from bank_project.user_account where USER_NAME='"+name+"';");
			while(rs_name.next()) {
				id=Integer.parseInt(rs_name.getString("USER_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return id;
    }
    public int checkName(String name) {
    	int id=0;
    	try {
			ResultSet rs_name=stmt.executeQuery("select USER_ID from bank_project.user_account where USER_NAME='"+name+"';");
			while(rs_name.next()) {
				id++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			new ExceptionDialog("Error Establishing a Connection");
		}
    	return id;
    }
    
    public static void disconnect() {
    	try {
			con.close();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
		}
    	catch(Exception e1) {
    		
    	}
    }
    public void transferById(int id,int toid,double amt) {
    	 String getname=getName(toid);
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
					stmt.executeUpdate("update bank_project.account_details set BALANCE=BALANCE+"+amt+" where USER_ID="+toid+";");
					stmt.executeUpdate("update bank_project.account_details set BALANCE=BALANCE-"+amt+" where USER_ID="+id+";");
					stmt.executeUpdate("insert into bank_project.statement_details values ("+toid+",'CREDITED',"+amt+");");
					stmt.executeUpdate("insert into bank_project.statement_details values ("+id+",'DEBITED',"+amt+");");
					new ExceptionDialog("Success!"); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					new ExceptionDialog("Error Establishing a Connection"); 
				}
    			 
    		 }
    	 }
    }
    
    public void transferByName(int id,String name,double amt) {
    	int getid=getId(name);
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
    			try {
    				stmt.executeUpdate("update bank_project.account_details set BALANCE=BALANCE+"+amt+" where USER_ID="+getid+";");
    				stmt.executeUpdate("update bank_project.account_details set BALANCE=BALANCE-"+amt+" where USER_ID="+id+";");
    				stmt.executeUpdate("insert into bank_project.statement_details values ("+getid+",'CREDITED',"+amt+");");
    				stmt.executeUpdate("insert into bank_project.statement_details values ("+id+",'DEBITED',"+amt+");");
    				new ExceptionDialog("Success!");
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				
    				new ExceptionDialog("Error Establishing a Connection");
    			}
    		}
    	}
    	
    }
    public void deleteAccount(int id) {
    	try {
			stmt.executeUpdate("DELETE FROM bank_project.user_account WHERE (USER_ID = '"+id+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ExceptionDialog("Error Establishing a Connection");
		}
    }
    public String errorCreation(String er) {
    	return er;
    }
}
