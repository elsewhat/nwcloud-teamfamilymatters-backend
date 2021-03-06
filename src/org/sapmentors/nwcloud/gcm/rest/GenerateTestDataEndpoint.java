package org.sapmentors.nwcloud.gcm.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.sapmentors.nwcloud.gcm.model.CommunityService;
import org.sapmentors.nwcloud.gcm.model.Family;
import org.sapmentors.nwcloud.gcm.model.PersistenceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/testdata")
public class GenerateTestDataEndpoint {
	private final static Logger logger = LoggerFactory
			.getLogger(GenerateTestDataEndpoint.class);

	// Ask jersey to populate this parameter for one of the REST methods
	@Context
	UriInfo uriInfo;

	PersistenceClient persistenceClient;

	/**
	 * Constructor must have no parameters (Jersey) It will initialize the
	 * datasource we are using (JPA)
	 */
	public GenerateTestDataEndpoint() {
		logger.debug("Constructor of MessagingEndpoint called");

		persistenceClient = new PersistenceClient();
	}

	/**
	 * curl -i -X DELETE -H "Accept: application/json"
	 * https://familymatterinnojam
	 * .netweaver.ondemand.com/nwcloud-teamfamilymatters-backend/api/testdata
	 * curl -i -X DELETE -H "Accept: application/json"
	 * http://localhost:8433/nwcloud-teamfamilymatters-backend/api/testdata
	 */
	@DELETE
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void generateTestData() {
		// The pushMessage is automatically populated based on the input. Yeah!
		logger.error("generateTestData called This may crash");
		createDatabase();

	}

	//
	private void createDatabase() {
		// get one element pr line in the input file
		List<String> listAdresse = loadDataToLines(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream("database.csv"));

		logger.info("generateTestData  attempting to insert " + listAdresse.size() + " records");
		EntityManager em = persistenceClient.getEntityManager();
		EntityTransaction et = null;

		String sqlCommand = null;
		try {
			et = em.getTransaction();
			et.begin();

			for (Iterator<String> iterator = listAdresse.iterator(); iterator
					.hasNext();) {
				String listAddresse = iterator.next();

				StringTokenizer tokenizer = new StringTokenizer(listAddresse,
						"|");
				if (tokenizer.countTokens() == 4) {
					String adresse = tokenizer.nextToken();
					String owner = tokenizer.nextToken();
					String latitude = tokenizer.nextToken();
					String longitude = tokenizer.nextToken();

					Family newFamily = Family.createExampleFamily(adresse,
							owner, latitude, longitude);
					em.persist(newFamily);

				} else {
					logger.warn("Not correct syntax in line " + listAddresse);
				}

			}

		} catch (Exception ex) {
			logger.error("Problem to execute the sql command: " + sqlCommand,
					ex);
			et.rollback();
		} finally {
			et.commit();
			em.close();
		}
	}

	private List<String> loadSQLCommands(InputStream stream) {
		List<String> sqlCommands = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line.trim());
			}
			StringTokenizer st = new StringTokenizer(sb.toString(), ";");
			while (st.hasMoreTokens()) {
				sqlCommands.add(st.nextToken());
			}
			return sqlCommands;
		} catch (MalformedURLException ex) {
			logger.error("Malformed URI of the file.", ex);
		} catch (FileNotFoundException fnfe) {
			logger.error("Database script not found.", fnfe);
		} catch (IOException ioe) {
			logger.error("Problem to read the script.", ioe);
		}
		return null;
	}

	private List<String> loadDataToLines(InputStream stream) {
		List<String> listData = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream));
			String line = null;
			while ((line = reader.readLine()) != null) {
				listData.add(line.trim());

			}
			return listData;
		} catch (MalformedURLException ex) {
			logger.error("Malformed URI of the file.", ex);
		} catch (FileNotFoundException fnfe) {
			logger.error("Database script not found.", fnfe);
		} catch (IOException ioe) {
			logger.error("Problem to read the script.", ioe);
		}
		return null;
	}
}
