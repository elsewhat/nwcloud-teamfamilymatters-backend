package org.sapmentors.nwcloud.gcm.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the PushMessage in external state
 * This represents the JSON/XML payload that must be provided
 * to the REST API
 *  
 * @author dagfinn.parnas
 *
 */
@XmlRootElement
public class ServiceAvailabilityRequest {
	String familyFrom;
	String serviceId;
	
	public String getFamilyFrom() {
		return familyFrom;
	}
	public void setFamilyFrom(String familyFrom) {
		this.familyFrom = familyFrom;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	} 
	
	
}
