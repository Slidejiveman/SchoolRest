package schoolRest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import schoolDM.Message;
import schoolDM.StudentDBDAO;
import schoolPD.School;
import schoolPD.Student;

@Path("/StudentServices")
public class StudentServices {

	private static final String SUCCESS_RESULT="{\"results\" : \"true\"}";
	private static final String FAILURE_RESULT="{\"results\" : \"false\"}";
	StudentDBDAO studentDBDAO = new StudentDBDAO();
	School school = new School();
	List<Message> messages = new ArrayList<Message>();
	
   @GET
   @Path("/students")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Student> getStudents(
		   @DefaultValue("0")@QueryParam("page") String page,
		   @DefaultValue("5")@QueryParam("per_page") String perPage) 
				   throws Exception {
	   return school.getAllStudents(page, perPage); 
   }
   
   @GET
   @Path("/students/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Student getStudentById(
		   @PathParam("id") String id ) 
				   throws Exception {
	   Student student = school.getStudentById(id);
	   return student;
   }
   // All of these return an array list of messages (success or failure)
   @POST
   @Path("/students/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public List<Message> addStudent(
           Student student, @Context final HttpServletResponse response
           ) throws Exception {
	   
	   try {
	   String newStudentId = student.getId();
	   Student possibleStudent = studentDBDAO.getStudentById(newStudentId);
	   
		   if( possibleStudent != null ) {
			   
			   response.setStatus( HttpServletResponse.SC_CONFLICT ); // 409
			   try {
				   response.flushBuffer();
			   } catch(Exception e) {  
			   }
			   messages.add(new Message( HttpServletResponse.SC_CONFLICT, "A Student with this id already exists" , "StudentServices") );
	           return messages;
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   } 
	   
	   // See that student matches the criteria 
	   messages = student.validate();
	   // If you got error messages, let the user know that it was unacceptable
       if( messages != null ) {
    	   
    	   response.setStatus( HttpServletResponse.SC_NOT_ACCEPTABLE); // 406
    	   try {
			   response.flushBuffer();
		   } catch(Exception e) {  
		   }
    	   messages.add(new Message( HttpServletResponse.SC_NOT_ACCEPTABLE, "Student info is not valid" , "StudentServices") );
           return messages;
       }
	   messages = school.addStudent(student); 
	   response.setStatus( HttpServletResponse.SC_CREATED );
	   try {
		   response.flushBuffer();
	   } catch(Exception e) {  
	   }
	   messages.add(new Message( HttpServletResponse.SC_CREATED, "Student Added!" , "StudentServices") ); //201
	   return messages;
   } 
   
   @PUT
   @Path("/students/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public List<Message> updateStudentById(
          Student student, @PathParam("id") String id, @Context final HttpServletResponse response
           ) throws Exception {
	   
	   Student oldStudent = studentDBDAO.getStudentById(id);
	   if( oldStudent == null ) {
		   response.setStatus( HttpServletResponse.SC_NOT_FOUND ); //404
		   try {
			   response.flushBuffer();
		   } catch( Exception e ) {
		   }
		   messages.add(new Message( HttpServletResponse.SC_NOT_FOUND, "Old Student is Null" , "StudentServices") );
		   return messages;
	   
	   } else {
		   // Validate student update information
		   messages = student.validate();
		   
		   // If you got error messages, let the user know that it was unacceptable
	       if( messages != null ) {
	    	   response.setStatus( HttpServletResponse.SC_NOT_ACCEPTABLE ); // 406
	    	   try {
	    		   response.flushBuffer();
	    	   } catch(Exception e) {  
	    	   }
	    	   messages.add(new Message( HttpServletResponse.SC_NOT_ACCEPTABLE, "Student info is not valid" , "StudentServices") );
	           return messages;
	       }
	       
	       // Attempt to update the student
		   messages = school.updateStudent(student);
	   }
	   
	   response.setStatus( HttpServletResponse.SC_OK );
	   try {
		   response.flushBuffer();
	   } catch(Exception e) {  
	   }
	   messages.add(new Message( HttpServletResponse.SC_OK, "Student updated!" , "StudentServices") ); //200
	   
	   return messages;
   }
   
   @DELETE
   @Path("/students/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Message> deleteStudentById(
		   @PathParam("id") String id, @Context final HttpServletResponse response
		   ) throws Exception {
	   
	   Student tryStudent = studentDBDAO.getStudentById(id);
	   if( tryStudent == null ) {
		   response.setStatus( HttpServletResponse.SC_NOT_FOUND ); //404
		   try {
			   response.flushBuffer();
		   } catch( Exception e ) {
		   }
		   messages.add(new Message( HttpServletResponse.SC_NOT_FOUND, "Student isn't in database" , "StudentServices") );
		   return messages;
	   }
	   
	   messages = school.deleteStudentById(id);
	   
	   response.setStatus( HttpServletResponse.SC_OK ); //200
	   try {
		   response.flushBuffer();
	   } catch( Exception e ) {
	   }
	   messages.add(new Message( HttpServletResponse.SC_OK, "Student Deleted!" , "StudentServices") ); //200
	   
	   return messages;
   }
   
}
