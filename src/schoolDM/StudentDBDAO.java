package schoolDM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import schoolPD.School;
import schoolPD.Student;

public class StudentDBDAO {
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  private ArrayList<Message> messages = new ArrayList<Message>();

	  // He has getAllStudentsForSchool( school,
	  public List<Student> getAllStudents(School school, String page, String perPage) throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/school?" //change school to iad_school for other server
	              + "user=webappuser&password=webappuser");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from student where school_id = 1 LIMIT "+perPage+" OFFSET "+page );
	      return createStudentList(resultSet);
	      
	    } catch (Exception e) {
	    	// Add error log statements in where these are thrown
	      throw e;
	    } finally {
	      close();
	    }
	  }

	  private ArrayList<Student> createStudentList(ResultSet resultSet) throws SQLException {
	    // ResultSet is initially before the first data set
		  ArrayList<Student> studentList = new ArrayList<Student>();
	    while (resultSet.next()) {
	      // It is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g. resultSet.getSTring(2);
	    	
	    	
	      int metaId = resultSet.getInt("meta_id");                     // Prof. North's id is my meta_id
	      int schoolId = resultSet.getInt("school_id");
	      String id = resultSet.getString("id");                     // Prof. North's idNumber is my id
	      String first_name = resultSet.getString("first_name");
	      String last_name = resultSet.getString("last_name");
	      String gender = resultSet.getString("gender");
	      String email = resultSet.getString("email");
	      String classification = resultSet.getString("classification");
	      String city = resultSet.getString("city");
	      String state = resultSet.getString("state");
	      String zipCode = resultSet.getString("zip");
	      Student student = new Student(metaId, schoolId, id, first_name, last_name, gender, email, 
	    		                          classification, zipCode, city, state);
	      studentList.add(student);
	   
	    }
	    return studentList;
	  }

	  public Student getStudentById(String id) throws Exception {
		    try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost/school?" // another DB change
		              + "user=webappuser&password=webappuser");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from student where id = " + id);
		      if(resultSet.next()){
		    	  resultSet.previous();
		    	  return createStudent(resultSet);
		      } else {
		          return null;
		      }
		      
		    } catch (Exception e) {
		    	e.printStackTrace();
		      throw e;
		    } finally {
		      close();
		    }
		  }
	  
	  private Student createStudent(ResultSet resultSet) throws SQLException {
		    
			 Student student = new Student();		    	
		    	
			  // this has to be called in order to activate the resultSet
			  resultSet.next();
		      int metaId = resultSet.getInt("meta_id");                     // Prof. North's id is my meta_id
		      int schoolId = resultSet.getInt("school_id");
		      String id = resultSet.getString("id");                     // Prof. North's idNumber is my id
		      String first_name = resultSet.getString("first_name");
		      String last_name = resultSet.getString("last_name");
		      String gender = resultSet.getString("gender");
		      String email = resultSet.getString("email");
		      String classification = resultSet.getString("classification");
		      String city = resultSet.getString("city");
		      String state = resultSet.getString("state");
		      String zipCode = resultSet.getString("zip");
		      student = new Student(metaId, schoolId, id, first_name, last_name, gender, email, 
		    		                          classification, zipCode, city, state);
		      System.out.println("Create Student: " + first_name);
		    return student;
		  }
	  
	  public List<Message> deleteStudentById(String id) throws Exception {
		  Message message = new Message();  
		  try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost/school?" //DB change
		              + "user=webappuser&password=webappuser");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      int result = statement
		          .executeUpdate("delete from student where id = " + id);
		      
		      // Based on the int result you get, make a message type
		      /*if(result >= 1) {
		    	  message.setStatusCode(HttpServletResponse.SC_OK);
		    	  message.setErrorMessage("DELETE: Student deleted.");
		    	  message.setAssociatedAttribute(null);
		    	  messages.add(message);
		      } else {
		    	  message.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		    	  message.setErrorMessage("DELETE: Student not found.");
		    	  message.setAssociatedAttribute(null);
		    	  messages.add(message);
		      } */
		      
		      return this.messages;
		      
		    } catch (Exception e) {
		    	System.out.println("ERROR: In StudentDBDAO Delete Method");
		    	e.printStackTrace();
		      throw e;
		    } finally {
		      close();
		    }
		  }
	 
	  public List<Message> updateStudent(Student student) throws Exception {
		  
		  try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost/school?" //DB change
		              + "user=webappuser&password=webappuser");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      int result = statement
		          .executeUpdate("update student set  "+
		        			" id ='"+student.getId()+"',"+
		        			" first_name ='"+student.getFirstName()+"',"+
		        			" last_name = '" +student.getLastName()+"',"+
		        			" gender = '"+student.getGender()+"',"+
		        			" email = '"+student.getEmail()+"',"+
		        			" classification = '"+student.getClassification()+"',"+
		        			" zip = '"+student.getZip()+"',"+
		        			" city = '"+ student.getCity()+"',"+
		        			" state = '"+ student.getState()+"'"+
		        			" where id="+student.getId()
		        			); // SQL CALL
             
		      return this.messages;
		      
		    } catch (Exception e) {
		    	System.out.println("ERROR: In StudentDBDAO Update Method");
		    	e.printStackTrace();
		      throw e;
		    } finally {
		      close();
		    }
		  }
	  
	
	  public List<Message> addStudent(Student student) throws Exception {  
		 
		  try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost/school?" //DB Change
		              + "user=webappuser&password=webappuser");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result get the result of the SQL query
		      
			int result = statement
		          .executeUpdate("insert into student "+
		  			   	" (id, first_name, last_name, gender, email, school_id, classification, zip, city, state)"+
		  				" values ( '"+
		  				student.getId()+"','"+
		  				student.getFirstName()+"','"+
		  				student.getLastName()+"','"+
		  				student.getGender()+"','"+
		  				student.getEmail()+"','"+
		  				Integer.toString(1)+"' ,'"+ //student.getSchoolId() is what we would use for multiple schools
		  				student.getClassification()+"','"+
		  				student.getZip()+"','"+
		  				student.getCity()+"','"+
		  				student.getState()+"')");  //  SQL CALL TO ADD    
		      
		      return this.messages;
		      
		    } catch (Exception e) {
		    	System.out.println("ERROR: In StudentDBDAO Add Method");
		    	e.printStackTrace();
		      throw e;
		    } finally {
		      close();
		    }
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
