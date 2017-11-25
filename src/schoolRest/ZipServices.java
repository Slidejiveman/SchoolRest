package schoolRest;

import java.util.logging.Logger;
import java.util.logging.Handler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javassist.bytecode.stackmap.TypeData.ClassName;
import schoolDM.ZipDBDAO;
import schoolPD.Zip;

@Path("/ZipServices")
public class ZipServices {
	
	ZipDBDAO zipDBDAO = new ZipDBDAO();

	
   @GET
   @Path("/zips/{zip}")
   @Produces(MediaType.APPLICATION_JSON)
   public Zip getLocation(
		   @PathParam("zip")
		   String zipCode) 
				   throws Exception { 
           //Zip location = new Zip();
        
	       //return location.getLocation(zipCode);
           return zipDBDAO.getLocationByZip(zipCode);
   }
   
}
