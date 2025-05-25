package za.co.ashtech.stroller.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;

@Slf4j
@SpringBootTest
class ServiceLayerTests {

	@Autowired
	private StrollerService strollerService;
	@Autowired
	private StrollerAdminService strollerAdminService;

	@Test
	void testGetRandomStroll() {
		assertNotNull(strollerService);
		
		Stroll stroll = strollerService.getRandomStroll();
		assertNotNull(stroll);
		log.info(stroll.toString());
	}
	
	@Test
	void testAddStroll() {
		assertNotNull(strollerAdminService);
		
		Stroll stroll = new Stroll("unitName", "unitTestLoc", "132.33", "36.664");
		Stroll strollResponse = strollerAdminService.addStroll(stroll);
		assertNotNull(strollResponse);
		log.info(strollResponse.toString());
	}

	@Test
	void testUpdateStroll() {
		assertNotNull(strollerAdminService);
		
		Stroll stroll = new Stroll("unitName", "unitTestLoc", "132.33", "36.664");
		Stroll strollResponse = strollerAdminService.updateStroll(Long.valueOf(1),stroll);
		assertNotNull(strollResponse);
		log.info(strollResponse.toString());
	}
	
	@Test
	void testDeleteStroll() {
		assertNotNull(strollerAdminService);
		
		strollerAdminService.deleteStroll(Long.valueOf(1));

	}
}
