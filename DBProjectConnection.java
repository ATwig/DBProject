//Cameron Burton
//Dan RB

//Database Project part 2


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBProjectConnection {

	private ResultSet executeQuery(String query){
		
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;

    
        String url = "jdbc:postgresql://localhost/databaseProject";
        String user = "DBProject";
        String password = "password";
        
        
        //Execute the selected query
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            return rs;

        } 
        
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProject2.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return null;

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
                    con.close();
                }

            } 
            
            catch (SQLException ex) {
            
            	Logger lgr = Logger.getLogger(DBProject2.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            
            }
            
        }
	
	}
	
	//Methods for creating SQL queries to run
	
	public String getCustomers(){
		
		String query = "select * from part3schema.customer;";
		
		ResultSet results = executeQuery(query);
		
		String resultString = "";
		
		try {
			while(results.next()){
				
				for(int i = 1; i <= 9; i++){
					resultString += results.getString(i) + " | ";
				}
				//add a new line between results
				resultString += "\n";
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultString = "SQL Exception";
		}
		
		return resultString;
	}
	
	public String addCar(){
		
		
		//returned string used to set text field in GUI
		return null;
	}
	
	public String addBoat(){
		

		return null;
	}
	
	
}


