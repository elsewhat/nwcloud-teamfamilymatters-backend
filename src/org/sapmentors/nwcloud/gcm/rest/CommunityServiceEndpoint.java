package org.sapmentors.nwcloud.gcm.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.sapmentors.nwcloud.gcm.model.CommunityService;
import org.sapmentors.nwcloud.gcm.model.PersistenceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/communityservice")
public class CommunityServiceEndpoint {
	private final static Logger logger = LoggerFactory.getLogger(MobileDeviceEndpoint.class);
	
	//Ask jersey to populate this parameter for one of the REST methods
	@Context
	UriInfo uriInfo;
	
	PersistenceClient persistenceClient;
	
	/**
	 * Constructor must have no parameters (Jersey)
	 * It will initialize the datasource we are using (JPA)
	 */
	public CommunityServiceEndpoint(){
		logger.debug("Constructor of MessagingEndpoint called");
		
		persistenceClient = new PersistenceClient();
	}

 
    @GET 
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    public List<CommunityService> getCommunityServices() {  
          //The pushMessage is automatically populated based on the input. Yeah!  
          logger.info("getCommunityServices called");  
          
          List<CommunityService> listServices = new ArrayList<CommunityService>();
          listServices.add(new CommunityService("bs","Babysitter","http://dummy"));
          listServices.add(new CommunityService("ga","Gardener","http://dummy"));
          listServices.add(new CommunityService("gr","Groceries","http://dummy"));
          listServices.add(new CommunityService("to","Borrow tools","http://dummy"));
          
          return listServices;          
    }


}
