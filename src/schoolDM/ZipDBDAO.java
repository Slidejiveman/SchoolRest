package schoolDM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;
import schoolPD.Zip;

public class ZipDBDAO {
	// figure this out or make a logger
	  private static final Logger log= Logger.getLogger( ClassName.class.getName() );
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  public Zip getLocationByZip(String zipCode) throws Exception {
	    try {
	    	
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/school?" //should be iad_school for school server
	              + "user=webappuser&password=webappuser");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from location where postal_code = " + zipCode + " LIMIT 1");
	      return createZip(resultSet);
	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }
	  }

	  private Zip createZip(ResultSet resultSet) throws SQLException {
	   
		  resultSet.next();
	      String city = resultSet.getString("city");
	      String state = resultSet.getString("state_code");
	      String zipCode = resultSet.getString("postal_code");
	      Zip location = new Zip(city, state, zipCode);
	      
	    return location;
	  }

	  // You need to close the resultSet
	  private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }
}
