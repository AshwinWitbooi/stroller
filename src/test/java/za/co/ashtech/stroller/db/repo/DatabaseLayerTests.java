package za.co.ashtech.stroller.db.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.ashtech.stroller.db.entities.Stroll;

@SpringBootTest
class DatabaseLayerTests {

	@Autowired
	private StrollerRepository strollerRepository;

	@Test
	void testStrollerRepository() {
		assertNotNull(strollerRepository);
		
		List<Stroll> allStrollerRecord = strollerRepository.findAll();
		assertNotNull(allStrollerRecord);
		allStrollerRecord.forEach(System.out::println);
	}

}
