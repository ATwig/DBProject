//Cameron Burton
//Dan RB

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBProject2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

    
        String url = "jdbc:postgresql://localhost/databaseProject";
        String user = "DBProject";
        String password = "password";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from part3schema.ticket;");

            if (rs.next()) {
                //System.out.println(rs.n);
                
                //rs.
            	
            	do{
            		for(int i = 1; i <= 9; i++)
            			System.out.print(rs.getString(i)+" ");
            		System.out.println("");
            	}while(rs.next());
            	
            	
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProject2.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(DBProject2.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
		
