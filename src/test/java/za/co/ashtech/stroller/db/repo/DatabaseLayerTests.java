package za.co.ashtech.stroller.db.repo;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.db.entities.Stroll;
import za.co.ashtech.stroller.db.entities.StrollTransactionLog;

@Slf4j
@SpringBootTest
class DatabaseLayerTests {

	@Autowired
	private StrollerRepository strollerRepository;
	@Autowired
	private StrollTransactionLogRepository auditTrailRepository;

	@Test
	void testStrollerRepository() {
		assertNotNull(strollerRepository);
		
		List<Stroll> allStrollerRecord = strollerRepository.findAll();
		assertNotNull(allStrollerRecord);
		allStrollerRecord.forEach(System.out::println);
	}
	
	@Test
	void testAuditTrailPersist() {
		auditTrailRepository.save(new StrollTransactionLog("TEST_ID01", "GETSTROLL", "SUCCESS"));
		List<StrollTransactionLog> verifyRecords = auditTrailRepository.findAll();
		assertNotEquals(0,verifyRecords.size());
		log.info(verifyRecords.get(0).toString()); 
	}

}
