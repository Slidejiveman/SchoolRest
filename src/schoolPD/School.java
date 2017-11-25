package schoolPD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import schoolDM.Message;
import schoolDM.StudentDBDAO;

@XmlRootElement(name = "school")
public class School implements Serializable {

	private static final long serialVersionUID = 1L;
	private int schoolId = 1;
	private String schoolName;
	StudentDBDAO studentDBDAO = new StudentDBDAO();
	
	public School(){}

    public School(String schoolName) {
    	this.schoolName = schoolName;
    }
	
	public School(int schoolId, String schoolName) {
    	this.schoolId = schoolId;
    	this.schoolName = schoolName;
    }
    
    public int getSchoolId() {
    	return this.schoolId;
    }
    @XmlElement
    public void setSchoolId(int schoolId) {
    	this.schoolId = schoolId;
    }
    
    public String getSchoolName() {
    	return this.schoolName;
    }
    @XmlElement
    public void setSchoolName(String schoolName) {
    	this.schoolName = schoolName;
    }
    
    // Service asks school for its students. DBDAO returns it.
    public List<Student> getAllStudents(String page, String perPage) throws Exception {
    	List<Student> studentList = null;
    	try {
    		studentList = studentDBDAO.getAllStudents(this, page, perPage);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return studentList;
    }
    
    public Student getStudentById(String id) throws Exception {
    	Student student = null;
    	try {
    	    student = studentDBDAO.getStudentById(id);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return student;
    }

	public List<Message> deleteStudentById(String id) {
		List<Message> messages = null;
		
		try {
			messages = studentDBDAO.deleteStudentById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages;
	}

	public List<Message> updateStudent(Student student) {
		List<Message> messages = null;
		
		try {
			messages = studentDBDAO.updateStudent(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public List<Message> addStudent(Student student) {
		List<Message> messages = null;
		
		try {
			messages = studentDBDAO.addStudent(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	
}
