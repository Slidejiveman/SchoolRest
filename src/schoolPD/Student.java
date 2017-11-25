package schoolPD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import schoolDM.Message;
import schoolDM.StudentDBDAO;

@XmlRootElement(name = "student")
public class Student implements Serializable {

	   private static final long serialVersionUID = 1L;
	   //Can also add an integer as a meta table id and another school_id to associate
	   //a student with a particular school
	   private int metaId;
	   private int schoolId;
	   private String id;
	   private String firstName;
	   private String lastName;
	   private String gender;
	   private String email;
	   private String classification;
	   private String zip;
	   private String city;
	   private String state;

	   public Student(){}
	   
	   public Student(int metaId, int schoolId, String id, String firstName, String lastName, String gender, 
			               String email, String classification, String zip, String city, String state){
	      this.metaId = metaId;
	      this.schoolId = schoolId;
		  this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.gender = gender;
	      this.email = email;
	      this.classification = classification;
	      this.zip = zip;
	      this.city = city;
	      this.state = state;
	   }

	   public int getMetaId() {
		   return this.metaId;
	   }
	   @XmlElement
	   public void setMetaId(int metaId) {
		   this.metaId = metaId;
	   }
	   
	   public int getSchoolId() {
		   return this.schoolId;
	   }
	   @XmlElement
	   public void setSchoolId(int schoolId) {
		   this.schoolId = schoolId;
	   }
	   
	   public String getId() {
	      return this.id;
	   }

	   @XmlElement
	   public void setId(String id) {
	      this.id = id;
	   }

	   public String getClassification() {
	      return this.classification;
	   }
	   @XmlElement
	   public void setClassification(String classification) {
	      this.classification = classification;
	   }
	   
		public String getFirstName() {
			return this.firstName;
		}
		@XmlElement
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return this.lastName;
		}
		@XmlElement
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getGender() {
			return this.gender;
		}
		@XmlElement
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getEmail() {
			return this.email;
		}
		@XmlElement
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getZip() {
			return this.zip;
		}
		@XmlElement
		public void setZip(String zip) {
			this.zip = zip;
		}
		
		public String getCity() {
			return this.city;
		}
		@XmlElement
		public void setCity(String city) {
			this.city = city;
		}
		
		public String getState() {
			return this.state;
		}
		@XmlElement
		public void setState(String state) {
			this.state = state;
		}
		
		public List<Message> validate() {
			ArrayList<Message> messages = new ArrayList<Message>();
			
			// id
			if( ( this.getId().equals("") ) || ( this.getId() == null ) || ( this.getId().length() > 10 ) ) {
				Message message = new Message( 1, "Student id value invalid", "id");
				messages.add(message);
			}
			
			// firstName
			if( ( this.getFirstName().equals("") ) || ( this.getFirstName() == null ) || ( this.getFirstName().length() > 20 ) ) {
				Message message = new Message( 2, "Student first_name value invalid", "firstName");
				messages.add(message);
			}
			// lastName
			if( ( this.getLastName().equals("") ) || ( this.getLastName() == null ) || ( this.getLastName().length() > 20 ) ) {
				Message message = new Message( 3, "Student last_name value invalid", "lastName");
				messages.add(message);
			}
			// gender
			if( ( this.getGender().equals("") ) || ( this.getGender() == null ) || ( this.getGender().length() > 6 ) ) {
				Message message = new Message( 4, "Student gender value invalid", "gender");
				messages.add(message);
			}
			// email
			if( ( this.getEmail().equals("") ) || ( this.getEmail() == null ) || ( this.getEmail().length() > 50 ) ) {
				Message message = new Message( 5, "Student email value invalid", "email");
				messages.add(message);
			}
			// classification
			if( ( this.getClassification().equals("") ) || ( this.getClassification() == null ) || ( this.getClassification().length() > 10 ) ) {
				Message message = new Message( 6, "Student classification value invalid", "classification");
				messages.add(message);
			}
		    // zip
			if( ( this.getZip().equals("") ) || ( this.getZip() == null ) || ( this.getZip().length() > 20 ) ) {
				Message message = new Message( 7, "Student zip value invalid", "zip");
				messages.add(message);
			}
		    // city
			if( ( this.getCity().equals("") ) || ( this.getCity() == null ) || ( this.getCity().length() > 100 ) ) {
				Message message = new Message( 8, "Student city value invalid", "city");
				messages.add(message);
			}
			// state
			if( ( this.getState().equals("") ) || ( this.getState() == null ) || ( this.getState().length() > 100 ) ) {
				Message message = new Message( 9, "Student state value invalid", "state");
				messages.add(message);
			}
			
			// Return for error checking
			if( messages.size() == 0 ) {
				return null;
			} else {
			    return messages;
			}
		}
}
