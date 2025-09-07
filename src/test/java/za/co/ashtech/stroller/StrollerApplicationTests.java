package za.co.ashtech.stroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
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
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StrollerApplicationTests {
		
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;	

    @Test
    void testRandomStroll() throws Exception {       

    	ResponseEntity<Stroll> responseStroll = restTemplate.getForEntity("http://localhost:" + port + "/stroller/api/v1/stroll", Stroll.class);
        
       //verify response is returned
       assertTrue(responseStroll.getStatusCode().is2xxSuccessful());
       assertNotNull(responseStroll.getBody());
    }
    
    @Test
    void testAddStroll() throws Exception {       
       
       Stroll strollAdd = new Stroll("inTestName","descriptionTest", "inTestLocation", "-12.3364", "6.6364");
       
       ResponseEntity<Void> addStrollResponse =  restTemplate.postForEntity("http://localhost:" + port + "/stroller/admin/api/v1/stroll", strollAdd, Void.class);
      
       //verify response is returned
       assertTrue(addStrollResponse.getStatusCode().is2xxSuccessful());
    }
    
    @Test
	void testUpdateStroll() throws Exception {       
	
	   Stroll strollAdd = new Stroll("inTestName","descriptionTest", "inTestLocation", "-12.3364", "6.6364");
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.set("Content-Type", "application/json");
	   headers.set("Accept", "application/json");
	   headers.set("Cache-Control", "no-cache");
	   
	   @SuppressWarnings({ "rawtypes", "unchecked" })
	   HttpEntity requestHttpEntity = new HttpEntity(strollAdd,headers);
	   
	// Execute PUT request
	   ResponseEntity<Stroll> addStrollResponse = restTemplate.exchange(
			   "http://localhost:" + port + "/stroller/admin/api/v1/stroll/1",
	           HttpMethod.PUT,
	           requestHttpEntity,
	           Stroll.class
	   );
	
	    //verify response is returned
	    assertTrue(addStrollResponse.getStatusCode().is2xxSuccessful());
	}
    
    @Test
    void testDeleteStroll() throws Exception {       
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");
        headers.set("Cache-Control", "no-cache");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> addStrollResponse = restTemplate
                .exchange("http://localhost:" + port + "/stroller/admin/api/v1/stroll/1", HttpMethod.DELETE, entity, Void.class);    	
       
        //verify response is returned
        assertTrue(addStrollResponse.getStatusCode().is2xxSuccessful());
        
    }
    
    @Test
    void testStrollContact() throws Exception {       
        
        
    	StrollUserCommentRequest strollUserComment = new StrollUserCommentRequest("First Name", "Last Name", "test2t.co.za", "This is my comment");

        ResponseEntity<Void> strollContactResponse = restTemplate
                .postForEntity("http://localhost:" + port + "/stroller/public/comment", strollUserComment, Void.class);     
        
       
        //verify response is returned
        assertTrue(strollContactResponse.getStatusCode().is2xxSuccessful());


    }

}
