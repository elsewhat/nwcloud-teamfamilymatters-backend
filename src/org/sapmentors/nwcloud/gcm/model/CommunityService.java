package org.sapmentors.nwcloud.gcm.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * CommunityService
 * @author dagfinn.parnas
 *
 */
@XmlRootElement
public class CommunityService {

	public CommunityService() {
		super();
	}
	public CommunityService(String name, String id, String imageURL) {
		super();
		this.name = name;
		this.id = id;
		this.imageURL = imageURL;
	}
	protected String name;
	protected String id;
	protected String imageURL;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
