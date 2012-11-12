package org.sapmentors.nwcloud.gcm.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * JPA entity representing a mobile device
 * 
 * 
 * @author dagfinn.parnas
 *
 */
@XmlRootElement
@Entity
@Table(name = "T_FAMILY")
@NamedQueries({
	@NamedQuery(name = "AllFamilyEntries", query = "select d from Family d"),
	@NamedQuery(name = "FamilyEntryByEmail", query = "select d from Family d WHERE d.email=:email")
})
public class Family {
	public static final String QUERY_ALL_ENTRIES= "AllFamilyEntries";
	public static final String QUERY_BY_EMAIL= "FamilyEntryByEmail";
	public static final String QUERY_BY_EMAIL_PARAM= "email";
	
	
	@Id
    @GeneratedValue
    @Basic
	long id;
	
	@Basic
	@Column(nullable = false, length = 512) 
	private String email;

	@Basic
	@Column(nullable = false, length = 1024) 
	private String adresse;
	
	@Basic
	@Column(nullable = false, length = 1024) 
	private String owner;
	
	@Basic
	@Column(nullable = false, length = 128) 
	private String latitude;
	
	@Basic
	@Column(nullable = false, length = 128) 
	private String longitude;
	
	@Basic
	@Column(nullable = false, length = 1024) 
	private String fatherName;
	
	@Basic
	@Column(nullable = false, length = 1024) 
	private String motherName;
	
	@Basic
	private int nrKids;	
	
	
	@Basic
	private int kid1Age;
	
	@Basic
	private int kid2Age;
	
	@Basic
	private int kid3Age;
	
	@Basic
	private boolean kid1IsGirl;
	
	@Basic
	private boolean kid2IsGirl;
	
	@Basic
	private boolean kid3IsGirl;

	public Family(){
	}

	public Family(String email, String adresse, String owner,
			String latitude, String longitude, String fatherName,
			String motherName, int nrKids, int kid1Age, int kid2Age,
			int kid3Age, boolean kid1IsGirl, boolean kid2IsGirl,
			boolean kid3IsGirl) {
		super();
		//this.id = id;
		this.email = email;
		this.adresse = adresse;
		this.owner = owner;
		this.latitude = latitude;
		this.longitude = longitude;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.nrKids = nrKids;
		this.kid1Age = kid1Age;
		this.kid2Age = kid2Age;
		this.kid3Age = kid3Age;
		this.kid1IsGirl = kid1IsGirl;
		this.kid2IsGirl = kid2IsGirl;
		this.kid3IsGirl = kid3IsGirl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public int getNrKids() {
		return nrKids;
	}

	public void setNrKids(int nrKids) {
		this.nrKids = nrKids;
	}

	public int getKid1Age() {
		return kid1Age;
	}

	public void setKid1Age(int kid1Age) {
		this.kid1Age = kid1Age;
	}

	public int getKid2Age() {
		return kid2Age;
	}

	public void setKid2Age(int kid2Age) {
		this.kid2Age = kid2Age;
	}

	public int getKid3Age() {
		return kid3Age;
	}

	public void setKid3Age(int kid3Age) {
		this.kid3Age = kid3Age;
	}

	public boolean isKid1IsGirl() {
		return kid1IsGirl;
	}

	public void setKid1IsGirl(boolean kid1IsGirl) {
		this.kid1IsGirl = kid1IsGirl;
	}

	public boolean isKid2IsGirl() {
		return kid2IsGirl;
	}

	public void setKid2IsGirl(boolean kid2IsGirl) {
		this.kid2IsGirl = kid2IsGirl;
	}

	public boolean isKid3IsGirl() {
		return kid3IsGirl;
	}

	public void setKid3IsGirl(boolean kid3IsGirl) {
		this.kid3IsGirl = kid3IsGirl;
	}

	public static Family createExampleFamily(String adresse, String owner,
			String latitude, String longitude) {
		
		//INSERT INTO T_FAMILY (email, adresse,owner, latitude,longitude,fatherName, motherName,nrKids,kid1Age,kid2Age,kid3Age,kid1IsGirl,kid2IsGirl,kid3IsGirl) VALUES('dagfinn.parnas@gmail.com', 'Vestervangsvej 12', 'Viborg Markjorder','56.4596428717208','9.39419077219749','Phil', 'Martha', 2,12,7,-1,0,1,0);
		Family newFamily = new Family("dagfinn.parnas@gmail.com", adresse, owner, latitude, longitude, "Phil", "Martha", 2, 11, 3, -1, false, true, false);
		return newFamily;
		
		// TODO Auto-generated method stub
		
	}


}
