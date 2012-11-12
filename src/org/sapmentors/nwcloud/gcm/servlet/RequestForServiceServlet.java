package org.sapmentors.nwcloud.gcm.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.sapmentors.nwcloud.gcm.gcm.GoogleCloudMessagingClient;
import org.sapmentors.nwcloud.gcm.model.PersistenceClient;
import org.sapmentors.nwcloud.gcm.model.PushMessageExternal;
import org.sapmentors.nwcloud.gcm.model.PushMessageResponse;
import org.sapmentors.nwcloud.gcm.model.RequestForService;
import org.sapmentors.nwcloud.gcm.rest.MobileDeviceEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class RequestForServiceServlet
 * http://localhost:8433/nwcloud-teamfamilymatters-backend/RequestForService
 */
public class RequestForServiceServlet extends HttpServlet {
	private final static Logger logger = LoggerFactory
			.getLogger(RequestForServiceServlet.class);
	private static final long serialVersionUID = 1L;

	PersistenceClient persistenceClient;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestForServiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.debug("Constructor of RequestForServiceServlet called");
		persistenceClient = new PersistenceClient();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.error("Got HTTP get to servlet");
		Writer output = response.getWriter();

		output.append("Got HTTP get to servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.error("Got HTTP post to servlet");
		Writer output = response.getWriter();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
		
		
		StringBuffer stringBuffer = new StringBuffer();
		Enumeration<String> enumParameters = request.getParameterNames();
		while(enumParameters.hasMoreElements()){
			String parameter = enumParameters.nextElement();
			stringBuffer.append(parameter+",");
		}
		//request.getP
		
		RequestForService requestForService=new RequestForService();
		requestForService.setServiceId("bs");
		requestForService.setFamilyFrom(1);
		requestForService.setLatitude("56.4596428717208");
		requestForService.setLongitude("9.39419077219749");
		requestForService.setTimeRequested(new Date());
		requestForService.setComment("Comment to service");
		requestForService.setEmailFrom("dagfinn.parnas@gmail.com");
		
		if(request.getParameter("Comments")!=null){
			requestForService.setComment(request.getParameter("Comments"));
		}
		if(request.getParameter("RequestedService")!=null){
			String service = request.getParameter("RequestedService");
			if("bs".equalsIgnoreCase(service) || "Babysitter".equalsIgnoreCase(service)){
				requestForService.setServiceId("bs");
			}else {
				requestForService.setServiceId(service);
			}
		}
		
		
       
        //persist the entry 
		 PushMessageResponse pushMessageResponse=null;
        try { 
        	EntityManager entityManager = persistenceClient.getEntityManager();
        	entityManager.getTransaction().begin();  
        	entityManager.persist(requestForService);
      	  	//Commit transaction
            entityManager.getTransaction().commit(); 
            
            pushMessageResponse = sendRequstForServiceToCandidates(requestForService);
            
        }catch(PersistenceException e){//most likely primary key violation
      	  logger.warn("Primary key of entity already exist",e);
      	  throw new ServletException(e);
        }
		
		//response.
		String strResponse = "Got HTTP post to servlet. Parameters "+ stringBuffer.toString() ;
		if(pushMessageResponse!=null){
			strResponse += " Also got to send push message";
		}
		
		
		logger.error(strResponse);
		output.append(strResponse);
	}

	private PushMessageResponse sendRequstForServiceToCandidates(
			RequestForService requestForService) {
		PushMessageExternal pushMessageExternal = new PushMessageExternal();
		pushMessageExternal.setEmailFrom(requestForService.getEmailFrom());
		
		
		if("bs".equalsIgnoreCase(requestForService.getServiceId())){
			pushMessageExternal.setMessageType(1);
			String emailTo[]={"dagfinn.parnas@gmail.com", "jastill69@gmail.com"};
			pushMessageExternal.setEmailTo(emailTo);
		}else {
			pushMessageExternal.setMessageType(2);
			String emailTo[]={"dagfinn.parnas@gmail.com"};
			pushMessageExternal.setEmailTo(emailTo);
		}
		
		
		
		pushMessageExternal.setMessage(requestForService.getComment());
		
		pushMessageExternal.setId(requestForService.getId()+"");
		GoogleCloudMessagingClient gcmClient = new GoogleCloudMessagingClient(pushMessageExternal);
        PushMessageResponse response = gcmClient.doSendMessage();
        return response;
		
	}
}
