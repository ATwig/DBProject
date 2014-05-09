//Cameron Burton
//Dan RB

//Database Project part 2


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DBProjectConnection {
	
private ResultSet executeQuery(String query){
		
		PGConnection con = null;
        Statement st = null;
        ResultSet rs = null;

    
        String url = "jdbc:postgresql://localhost/databaseProject";
        String user = "DBProject";
        String password = "password";
        
        
        //Execute the selected query
        try {
            
        	con = (PGConnection) DriverManager.getConnection(url, user, password);
            
        	st = ((Connection) con).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
        	//con.setAutoCommit(false);
        	
        	rs = st.executeQuery(query);
        	
        	((PGConnection)con).getNotifications();
            
            //con.commit();
            
            return rs;

        } 
        
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProject2.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);         
            
            
        } 
        finally {
            try {
               // if (rs != null) {
               //    rs.close();
               // }
               //if (st != null) {
               //    st.close();
               //}
                if (con != null) {
                    ((Connection) con).close();
                }

            } 
            
            catch (SQLException ex) {
            
            	Logger lgr = Logger.getLogger(DBProject2.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            
            }
            
        }
        
        return null;
	
	}

	private int executeInsert(String query){
		
		PGConnection con = null;
        Statement st = null;
        int rs = -1;

    
        String url = "jdbc:postgresql://localhost/databaseProject";
        String user = "DBProject";
        String password = "password";
        
        
        //Execute the selected query
        try {
            
        	con = (PGConnection) DriverManager.getConnection(url, user, password);
            
        	st = ((Connection) con).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
        	((Connection) con).setAutoCommit(false);
        	
        	rs = st.executeUpdate(query);
            
            ((Connection) con).commit();
            
            ((PGConnection)con).getNotifications();
            
            return rs;

        } 
        
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProject2.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            

            if(con != null){
            	
            	try{
            		((Connection) con).rollback();
            	}
            	catch(SQLException ex2){
            		
            		Logger lgr1 = Logger.getLogger(DBProject2.class.getName());
                    lgr1.log(Level.SEVERE, ex.getMessage(), ex);
            		
            	}
            }
            
            
            
        } 
        finally {
            try {
               // if (rs != null) {
               //    rs.close();
               // }
               //if (st != null) {
               //    st.close();
               //}
                if (con != null) {
                    ((Connection) con).close();
                }

            } 
            
            catch (SQLException ex) {
            
            	Logger lgr = Logger.getLogger(DBProject2.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            
            }
            
        }
        
        return -1;
	
	}
	
private String executeFunction(String query){
		
	PGConnection con = null;
    Statement st = null;
    ResultSet rs = null;


    String url = "jdbc:postgresql://localhost/databaseProject";
    String user = "DBProject";
    String password = "password";
    
    
    //Execute the selected query
    try {
        
    	con = (PGConnection) DriverManager.getConnection(url, user, password);
        
    	st = ((Connection) con).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
    	//con.setAutoCommit(false);
    	
    	rs = st.executeQuery(query);
        
        //con.commit();
        
    	System.out.println(((Connection)con).getWarnings().getMessage());
    	
        PGNotification[] notification = ((PGConnection)con).getNotifications();
        
        String notificationString = "";
        
        for(int i = 0; i < notification.length; i++){
        	
        	notificationString += " "+notification[i].getParameter()+" \n";
        	
        }
        
        return notificationString;

    } 
    
    catch (SQLException ex) {
        Logger lgr = Logger.getLogger(DBProject2.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);         
        
        
    } 
    finally {
        try {
           // if (rs != null) {
           //    rs.close();
           // }
           //if (st != null) {
           //    st.close();
           //}
            if (con != null) {
                ((Connection) con).close();
            }

        } 
        
        catch (SQLException ex) {
        
        	Logger lgr = Logger.getLogger(DBProject2.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        
        }
        
    }
    
    return null;

}
	
	
	public String[] inputPopup(String[] labels) {
	      JTextField[] fields = new JTextField[labels.length];

	      String[] stringResults = new String[labels.length];
	      
	      JPanel myPanel = new JPanel();
	      
	      myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	      
	      for(int i = 0; i < labels.length; i++){
	    	  myPanel.add(new JLabel(labels[i]));

	    	  fields[i] = new JTextField(5);
	    	  
	    	  myPanel.add(fields[i]);
	    	  myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      }
	      

	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	         for(int i = 0; i < labels.length; i++){
	        	 
	        	 stringResults[i] = fields[i].getText();
	        	 
	         }
	         return stringResults;
	      }
	      return null;

	   }
	
	
	public javax.swing.JTable createTable(ResultSet results){
		
		int numRows = 0;
		
		try {
			
			while(results.next()){
				numRows++;	
			}
			
			if(numRows == 0){
				return null;
			}
			
			ResultSetMetaData resultsMeta = results.getMetaData();
			
			results.beforeFirst();
			
			String[][] data = new String[numRows][resultsMeta.getColumnCount()];
			
			String[] colNames = new String[resultsMeta.getColumnCount()];
			
			for(int i = 0; i < resultsMeta.getColumnCount(); i++){
			
				colNames[i] = resultsMeta.getColumnLabel(i+1);
				
			}
			
			numRows = 0;
			
			while(results.next()){
				for(int i = 0; i < resultsMeta.getColumnCount(); i++){
			
					data[numRows][i] = results.getString(i+1);
					
				}
				
				numRows++;
				
			}
			
			
			return new javax.swing.JTable(data, colNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//resultString = "SQL Exception";
		}
		return null;
	
	}
	
public javax.swing.JTable createInsertTable(int results){
		
	String[] colNames = {"Result"};
	
	String[][] data = new String[1][1];
	
	if(results < 1)
		data[0][0] = "Failure";

	else
		data[0][0] = "Sucess";

	return new javax.swing.JTable(data, colNames);
}
	
	
	
	//Methods for creating SQL queries to run
	
	public JTable getCustomers(){
		
		String query = "select * from part3schema.customer;";
		
		ResultSet results = executeQuery(query);
				
		return createTable(results);
	}
	
	
	//Query One
	public javax.swing.JTable getListOfPayments(){
	
		String query = "select payments.paymentid, customer.name, payments.ammount from part3schema.customer id left outer join part3schema.payments on(payments.customerid = id.customerid) left outer join part3schema.customer on(customer.customerid = id.customerid);";

		ResultSet results = executeQuery(query);
		
		return createTable(results);
		
	
	}
	
	//Query Two
	public JTable getAgentsTickets(){
		
		String query = "select agent.agentid, agent.lastname, ticket.ticketid from part3schema.ticket inner join part3schema.agent on agent.agentid = ticket.agentid;";

		ResultSet results = executeQuery(query);
		
		return createTable(results);		
	
	}
	
	//Query Three
	
	public JTable getTicketsandObjects(){
		
		
		//Popup for Type:
		
		String[] inputNames = {"Item Type: Boat, House, Car "};
		
		String[] inputResults = inputPopup(inputNames);
	
		if (inputResults == null)
			return null;
		
		
		//if type = boat:
		String query = "select * from part3schema.ticket NATURAL INNER JOIN part3schema."+inputResults[0]+" where ticket.tickettype = '"+inputResults[0]+"';";

		ResultSet results = executeQuery(query);
		
		return createTable(results);
	
	}
	
	//Query Four
	
	public JTable getSumCustomerPayments(){
		
		
		//getUserInput
		
		String[] inputNames = {"customer ID: "};
		
		String[] inputResults = inputPopup(inputNames);
	
		if (inputResults == null)
			return null;
		
		String query = "select sum(payments.ammount) from part3schema.payments where payments.customerid = "+inputResults[0]+";";

		
		ResultSet results = executeQuery(query);
		
		return createTable(results);
		
	
	}
	
	public JTable addTicket(){
		
		String[] inputNames = {"Item ID", "Ticket Type: Car, Boat, House", "Agent ID", "Date Occured: YYYY-MM-DD", "Date Filed: YYYY-MM-DD", "Description"};
		
		String[] inputResults = inputPopup(inputNames);
		
		if (inputResults == null)
			return null;
		
		String query = "insert into part3schema.ticket (itemid, tickettype, agentid, dateoccured, datefiled, description) values(";
		
		query+= ""+Integer.parseInt(inputResults[0]) +",'"+inputResults[1]+"',"+Integer.parseInt(inputResults[2])+", '"+inputResults[3]+"','"+inputResults[4]+"','"+inputResults[5]+"');";
	
		int results = executeInsert(query);
		
		return createInsertTable(results);
		
	}
	
	public JTable getContactInfo(){
		
		String[] inputNames = {"Name "};
		
		String[] inputResults = inputPopup(inputNames);
	
		if (inputResults == null)
			return null;
		
		String query = "select part3schema.getContactInfo('"+inputResults[0].toLowerCase()+"');";

		ResultSet results = executeQuery(query);
		
		return createTable(results);		
		
		
	}
	
	public JTable getCustomersItems(){
		
		String[] inputNames = {"Customer ID "};
		
		String[] inputResults = inputPopup(inputNames);
	
		if (inputResults == null)
			return null;
		
		String query = "select part3schema.customersItems('"+inputResults[0]+"');";

		ResultSet results = executeQuery(query);
		
		return createTable(results);
		
	}
	
	public JTable mostTicketsByAgent(){
			
		String query = "select part3schema.mostTicketsByAgent();";

		ResultSet results = executeQuery(query);
		
		return createTable(results);
		
	}
	
	public JTable customerHasTickets(){
		
		String query = "select part3schema.clientHasTickets();";

		ResultSet results = executeQuery(query);
		
		return createTable(results);
		
	}
	
	public JTable allResolvedTickets(){
		
		String query = "select part3schema.allAgentsInsured();";

		ResultSet results = executeQuery(query);
		
		return createTable(results);
		
	}
	
	public String addCar(){
		
		
		//returned string used to set text field in GUI
		return null;
	}
	
	public String addBoat(){
		

		return null;
	}
	
	
}


