package cz.muni.fi.pv243.musiclib.integration;

import cz.muni.fi.pv243.musiclib.logging.LogMessages;
import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@RunWith(Arquillian.class)
public class AuthenticationTest {

    private static final String SECURITY_CHECK = "j_security_check";
    private static final String USERNAME = "j_username";
    private static final String PASSWORD = "j_password";
    private static final String JSESSION_ID = "JSESSIONID";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeAndTestDependencies()
                .resolve()
                .withTransitivity()
                .asFile();

        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml")
                .addAsWebInfResource("WEB-INF/jboss-web.xml", "jboss-web.xml")
                .addAsWebInfResource("WEB-INF/web.xml", "web.xml")
                .addAsManifestResource("MANIFEST.MF")
                .addAsLibraries(files);

        LogMessages.LOGGER.info(war.toString(true));

        return war;
    }


    @ArquillianResource
    private URL baseUri;

    @SuppressWarnings("unchecked")
    public void testGenericLogIn(String username, String password) {

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add(USERNAME, username);
        formData.add(PASSWORD, password);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri + SECURITY_CHECK);
        Response response = target.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(Entity.form(new Form(formData)));

        Assert.assertEquals("User cannot log in", HttpStatus.SC_OK, response.getStatus());
        Assert.assertTrue("No session created on valid log in",
                !response.getCookies().get(JSESSION_ID).getValue().isEmpty());

        response.close();
    }

    @Test
    public void testAdminLogIn() {
        testGenericLogIn("admin@musiclib.com", "pass");
    }

    @Test
    public void testUserLogIn() {
        testGenericLogIn("user1@musiclib.com", "pass");
    }

    @Test
    public void testSuperUserLogIn() {
        testGenericLogIn("superUser1@musiclib.com", "pass");
    }

    @Test
    public void invalidLogIn() {
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add(USERNAME, "");
        formData.add(PASSWORD, "");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri + SECURITY_CHECK);
        Response response = target.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(Entity.form(new Form(formData)));

        Assert.assertEquals("Invalid user logged in", HttpStatus.SC_NOT_FOUND, response.getStatus());

        response.close();
    }

}
