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

	@Test
	void testStrollerRepository() {
		assertNotNull(strollerService);
		
		Stroll stroll = strollerService.getRandomStrol();
		assertNotNull(stroll);
		log.info(stroll.toString());
	}

}
