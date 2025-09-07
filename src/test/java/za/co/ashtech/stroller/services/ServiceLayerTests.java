package za.co.ashtech.stroller.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;
import za.co.ashtech.stroller.db.repo.StrollUserCommentRepository;

@Slf4j
@SpringBootTest
class ServiceLayerTests {

	@Autowired
	private StrollerService strollerService;
	@Autowired
	private StrollerAdminService strollerAdminService;
	@Autowired
	private StrollerUserCommentService strollerUserCommentService;
	@Autowired
	private StrollUserCommentRepository strollUserCommentRepository;

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
		
		Stroll stroll = new Stroll("unitName","descriptionTest", "unitTestLoc", "132.33", "36.664");
		Stroll strollResponse = strollerAdminService.addStroll(stroll);
		assertNotNull(strollResponse);
		log.info(strollResponse.toString());
	}

	@Test
	void testUpdateStroll() {
		assertNotNull(strollerAdminService);
		
		Stroll stroll = new Stroll("unitName","descriptionTest", "unitTestLoc", "132.33", "36.664");
		Stroll strollResponse = strollerAdminService.updateStroll(Long.valueOf(1),stroll);
		assertNotNull(strollResponse);
		log.info(strollResponse.toString());
	}
	
	@Test
	void testDeleteStroll() {
		assertNotNull(strollerAdminService);
		
		strollerAdminService.deleteStroll(Long.valueOf(1));
	}
	
	@Test
	void testStrollUserComment() {
		assertNotNull(strollerUserCommentService);

		strollerUserCommentService.postComment(new StrollUserCommentRequest("test val", "test val", "test@val.co.za", "test val comment"));;
		assertNotNull(strollUserCommentRepository.findAll());
	}
	
	@Test
	void testAllStrolls() {
		assertNotNull(strollerAdminService);
		assertNotNull(strollerAdminService.getAllStrolls());
	}
}
