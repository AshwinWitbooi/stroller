package za.co.ashtech.stroller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.AuthTokenResponse;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;
import za.co.ashtech.stroller.db.entities.StrollTransactionLog;
import za.co.ashtech.stroller.db.repo.StrollTransactionLogRepository;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StrollerApplicationTests {
		
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;	
	private String accessToken = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private StrollTransactionLogRepository auditTrailRepository;
	private	String userId = "test_me1@testcom.co.za";
    
	@Test
	@Order(1)
	void getAccessToken() throws Exception {
    	
		ResponseEntity<AuthTokenResponse> authTokenResponse = this.restTemplate
    																		  .withBasicAuth("appuser", "password")
    			                                                              .getForEntity("http://localhost:" + port + "/stroller/oauth/access/token",AuthTokenResponse.class);
       	//assert access token is returned
    	assertNotNull(authTokenResponse.getBody().getAccessToken());

    	this.accessToken = authTokenResponse.getBody().getAccessToken();
    	
    	 log.info("ACCESS TOKEN:"+this.accessToken);
    }
   

    @Test
    @Order(2)
    void testRandomStroll() throws Exception {       

       HttpEntity<String> entity = new HttpEntity<>(null, this.setUpHeaders(this.accessToken));

       ResponseEntity<Stroll> responseStroll = restTemplate
               .exchange("http://localhost:" + port + "/stroller/api/v1/stroll?userId="+userId, HttpMethod.GET, entity, Stroll.class);
        
       //verify response is returned
       assertTrue(responseStroll.getStatusCode().is2xxSuccessful());
       assertNotNull(responseStroll.getBody());
       //verify audit trail execution
       assertNotEquals(0, auditTrailRepository.findAll().size());
    }
    
    @Test
    @Order(3)
    void testAddStroll() throws Exception {       

       
       Stroll strollAdd = new Stroll("inTestName", "inTestLocation", "-12.3364", "6.6364");

       HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(strollAdd), setUpHeaders(this.accessToken));

       ResponseEntity<Void> addStrollResponse = restTemplate
               .exchange("http://localhost:" + port + "/stroller/admin/api/v1/stroll?userId="+userId, HttpMethod.POST, entity, Void.class);
      
       //verify response is returned
       assertTrue(addStrollResponse.getStatusCode().is2xxSuccessful());
       //verify audit trail execution
       assertNotEquals(0, auditTrailRepository.findAll().size());
    }
    
    @Test
    @Order(4)
    void testUpdateStroll() throws Exception {       

       Stroll strollAdd = new Stroll("inTestName", "inTestLocation", "-12.3364", "6.6364");

        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(strollAdd), setUpHeaders(this.accessToken));

        ResponseEntity<Void> addStrollResponse = restTemplate
                .exchange("http://localhost:" + port + "/stroller/admin/api/v1/stroll/1?userId="+userId, HttpMethod.PUT, entity, Void.class);
        List<StrollTransactionLog> recs = auditTrailRepository.findAll();
        log.debug(""+recs.size());
        //verify response is returned
        assertTrue(addStrollResponse.getStatusCode().is2xxSuccessful());
        //verify audit trail execution
        assertNotEquals(0, auditTrailRepository.findAll().size());
    }
    
    @Test
    @Order(5)
    void testDeleteStroll() throws Exception {       
        
        HttpEntity<String> entity = new HttpEntity<>(null, setUpHeaders(this.accessToken));

        ResponseEntity<Void> addStrollResponse = restTemplate
                .exchange("http://localhost:" + port + "/stroller/admin/api/v1/stroll/1?userId="+userId, HttpMethod.DELETE, entity, Void.class);
       
        //verify response is returned
        assertTrue(addStrollResponse.getStatusCode().is2xxSuccessful());

    }
    
    @Test
    @Order(6)
    void testStrollUserComment() throws Exception {       
        
        
    	StrollUserCommentRequest strollUserComment = new StrollUserCommentRequest("First Name", "Last Name", "test2t.co.za", "This is my comment");

        ResponseEntity<Void> strollContactResponse = restTemplate
                .postForEntity("http://localhost:" + port + "/stroller/public/comment", strollUserComment, Void.class);     
        
       
        //verify response is returned
        assertTrue(strollContactResponse.getStatusCode().is2xxSuccessful());  
        //verify audit trail execution
        assertNotEquals(0, auditTrailRepository.findAll().size());


    }
    
    
	/*
	 * Method that return headers ready for use in test
	 */
    private HttpHeaders setUpHeaders(String accessToken) {
    	log.debug("HTTP Headers set up for test");
    	
        // Set custom headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept","*/*");
        headers.set("Accept-Encoding","gzip, deflate, br");
        headers.setConnection("Keep Alive");
        
        return headers;
    	
    }

}
