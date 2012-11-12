package org.sapmentors.nwcloud.gcm.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * CommunityService
 * @author dagfinn.parnas
 *
 */
@XmlRootElement
@Entity
@Table(name = "T_REQUESTFORSERVICE")
@NamedQueries({
	@NamedQuery(name = "AllRequestEntries", query = "select d from RequestForService d")
})
public class RequestForService {
	
	@Id
    @GeneratedValue
    @Basic
	long id;
	
	@Basic
	@Column(nullable = false, length = 512)
	private String serviceId;
	
	@Basic
	private long familyFrom; 
	
	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Basic
	@Column(nullable = false, length = 512)
	private String emailFrom;
	
	@Basic
	@Column(nullable = false, length = 512)
	private String latitude; 
	@Basic
	@Column(nullable = false, length = 512)
	private String longitude; 
	
	@Basic
	@Column(nullable = false, length = 512)
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Basic
	private Date timeRequested;

	
	public RequestForService(){
		super();
	}
	
	public RequestForService(long id, String serviceId, long familyFrom,
			String emailFrom, String latitude, String longitude,
			String comment, Date timeRequested) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.familyFrom = familyFrom;
		this.emailFrom = emailFrom;
		this.latitude = latitude;
		this.longitude = longitude;
		this.comment = comment;
		this.timeRequested = timeRequested;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public long getFamilyFrom() {
		return familyFrom;
	}

	public void setFamilyFrom(long familyFrom) {
		this.familyFrom = familyFrom;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getTimeRequested() {
		return timeRequested;
	}

	public void setTimeRequested(Date timeRequested) {
		this.timeRequested = timeRequested;
	}
	
	
	
	
}
