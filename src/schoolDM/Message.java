package schoolDM;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class Message implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int statusCode;
    private String errorMessage;
    private String associatedAttribute;
    
   
    public Message() {}
    
    public Message(int statusCode, String errorMessage, String associatedAttribute) {
    	this.statusCode = statusCode;
    	this.errorMessage = errorMessage;
    	this.associatedAttribute = associatedAttribute;
    }
    
    public int getStatusCode() {
    	return statusCode;
    }
    
    @XmlElement
    public void setStatusCode(int statusCode) {
    	this.statusCode = statusCode;
    }
    
    public String getErrorMessage() {
    	return this.errorMessage;
    }
    @XmlElement
    public void setErrorMessage(String errorMessage) {
    	this.errorMessage = errorMessage;
    }
    
    public String getAssociatedAttribute() {
    	return this.associatedAttribute;
    }
    @XmlElement
    public void setAssociatedAttribute(String associatedAttribute) {
    	this.associatedAttribute = associatedAttribute;
    }
}
