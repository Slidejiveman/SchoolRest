package schoolPD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import schoolDM.Message;

@XmlRootElement(name = "zip")
public class Zip implements Serializable {

   private static final long serialVersionUID = 1L;
   private String zip;
   private String city;
   private String state;


   public Zip(){}
   
   public Zip( String city, String state, String zip){
      this.zip = zip;
      this.city = city;
      this.setState(state);
   }

   public String getZip() {
      return zip;
   }

   @XmlElement
   public void setZip(String zip) {
      this.zip = zip;
   }
   
	public String getCity() {
		return city;
	}
	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	
	@XmlElement
	public void setState(String state) {
		this.state = state;
	}
	
	public List<Message> validate() {
		ArrayList<Message> messages = new ArrayList<Message>();
	    
		//zip
		if( this.getZip() == "" && this.getZip() == null && this.getZip().length() > 20 ) {
			Message message = new Message( 10, "Zip zip value invalid", "zip");
			messages.add(message);
		}
	    //city
		if( this.getCity() == "" && this.getCity() == null && this.getCity().length() > 100 ) {
			Message message = new Message( 11, "Zip city value invalid", "city");
			messages.add(message);
		}
		//state
		if( this.getState() == "" && this.getState() == null && this.getState().length() > 2 ) {
			Message message = new Message( 12, "Zip state value invalid", "state");
			messages.add(message);
		}
		
		return messages;
	}
}