/**
 * 
 */
package com.mtanevski.tests.couchdb;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

/**
 * @author Marjan
 * 
 */

public class InitializeCouchDbTest {

	// initialize a client instance - reuse
	private static CouchDbClient dbClient;

	@Before
	public void getCouchDbProperties() throws IOException {
		/*
		 * try (InputStream is =
		 * getClass().getResourceAsStream("/couchdb-test.properties")) {
		 * Properties props = new Properties(); props.load(is);
		 * couchDbProps.setDbName(props.) }
		 */
		dbClient = new CouchDbClient("couchdb-test.properties");
	}
	
	@After
	public void shutdownCouchDbClient() {
		dbClient.shutdown(); //proper release of resources
	}

	@Test
	public void initializeTest() throws IOException {

		// test couchdb.properties
		/*
		 * assertEquals("uacs_blog",couchDbProps.getProperty("couchdb.name"));
		 * assertEquals
		 * ("true",couchDbProps.getProperty("couchdb.createdb.if-not-exist"));
		 * assertEquals("http",couchDbProps.getProperty("couchdb.protocol"));
		 * assertEquals("127.0.0.1",couchDbProps.getProperty("couchdb.host"));
		 * assertEquals("5984",couchDbProps.getProperty("couchdb.port"));
		 * assertEquals("admin",couchDbProps.getProperty("couchdb.username"));
		 * assertEquals("admin",couchDbProps.getProperty("couchdb.password"));
		 */

		/*
		 * // couchdb-test.properties is on the classpath CouchDbClient dbClient1 =
		 * new CouchDbClient("couchdb-test.properties");
		 * 
		 * CouchDbClient dbClient2 = new CouchDbClient("db-name", true, "http",
		 * "127.0.0.1", 5984, "username", "secret");
		 * 
		 * CouchDbProperties properties = new CouchDbProperties()
		 * .setDbName("db-name") .setCreateDbIfNotExist(true)
		 * .setProtocol("https") .setHost("example.com") .setPort(443)
		 * .setUsername("username") .setPassword("secret")
		 * .setMaxConnections(100) .setConnectionTimeout(0);
		 * 
		 * CouchDbClient dbClient3 = new CouchDbClient(properties);
		 */
		
		// test connectivity
		assertEquals("http://127.0.0.1:5984/", dbClient.getBaseUri().toString());
		assertEquals(true, dbClient.contains("test"));
	}
	
	

}
