package org.sapmentors.nwcloud.gcm.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.sapmentors.nwcloud.gcm.model.CommunityService;
import org.sapmentors.nwcloud.gcm.model.Family;
import org.sapmentors.nwcloud.gcm.model.MobileDevice;
import org.sapmentors.nwcloud.gcm.model.PersistenceClient;
import org.sapmentors.nwcloud.gcm.model.ServiceAvailabilityRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/requestservice")
public class ServiceAvailabilityEndpoint {
	private final static Logger logger = LoggerFactory.getLogger(ServiceAvailabilityEndpoint.class);
	
	//Ask jersey to populate this parameter for one of the REST methods
	@Context
	UriInfo uriInfo;
	
	PersistenceClient persistenceClient;
	
	/**
	 * Constructor must have no parameters (Jersey)
	 * It will initialize the datasource we are using (JPA)
	 */
	public ServiceAvailabilityEndpoint(){
		logger.debug("Constructor of ServiceAvailabilityEndpoint called");
		
		persistenceClient = new PersistenceClient();
	}

 
	/**
	 * 
	 * $ curl --insecure -i -H "Accept: application/json" https://familymatterinnojam.netweaver.ondemand.com/nwcloud-teamfamilymatters-backend/api/requestservice
	 * $ curl --insecure -i -H "Accept: application/json" http://localhost:8433/nwcloud-teamfamilymatters-backend/api/requestservice
	 * 																												  
	 * @param serviceRequest
	 * @return
	 */
    @GET 
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    public List<Family> getAvailableFamiliesForService(ServiceAvailabilityRequest serviceRequest) {  
          //The pushMessage is automatically populated based on the input. Yeah!  
          logger.info("getAvailableFamiliesForService called");  
          
          EntityManager entityManager = persistenceClient.getEntityManager();
          List<Family> resultList = entityManager.createNamedQuery(Family.QUERY_ALL_ENTRIES,  
        		  Family.class).setMaxResults(10).getResultList();  
          
          
          
          return resultList;          
    }


}
