package za.co.ashtech.stroller.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.db.repo.StrollerRepository;
import za.co.ashtech.stroller.util.StrollerServiceException;

@Slf4j
@Service
public class StrollerServiceImpl implements StrollerService {

	@Autowired
	private StrollerRepository strollerRepository;

	@Override
	public Stroll getRandomStroll() throws StrollerServiceException {

		// Get all records
		List<za.co.ashtech.stroller.db.entities.Stroll> strollRecordsList = strollerRepository.findAll();
		Random rand = new Random();

		Optional<za.co.ashtech.stroller.db.entities.Stroll> opStrollDbe = Optional.ofNullable(strollRecordsList.get(rand.nextInt(strollRecordsList.size())));
		log.debug("Random database record successfully returned.");
		
		/*
		 * Map database entity to JSON entity and return else throw
		 * StrollerServiceException
		 */
		return opStrollDbe
			.map(dr -> {
				return new Stroll(dr.getStrollId().toString(),dr.getStrollName(),dr.getDescription(),dr.getLocation(),dr.getLatitude().toString(),dr.getLongitude().toString(),dr.getImage());
			})
			.orElseThrow(() -> new StrollerServiceException("Unable to successfully retrieve a random Stroll."));
	}

}
