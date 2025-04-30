package za.co.ashtech.stroller.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.db.entities.StrollerDEntity;
import za.co.ashtech.stroller.db.repo.StrollerRepository;

@Slf4j
@Service
public class StrollerServiceImpl implements StrollerService {
	
	@Autowired
	private StrollerRepository strollerRepository;

	@Override
	public Stroll getRandomStrol() {

		//Get all records and return random one
		Optional<StrollerDEntity> selectedStrollDBE = strollerRepository.findAll().stream().findAny();
		log.debug("Random database record successfully returned.");
		
		StrollerDEntity dEntity = selectedStrollDBE.orElseGet(() -> getDefaultStroll());
		
		Stroll stroll =  new Stroll(dEntity.getName(), dEntity.getLocation(),Double.toString(dEntity.getLongitude()),Double.toString( dEntity.getLatitude()));
		
		return stroll;
		
	}
	
	private StrollerDEntity getDefaultStroll() {
		return strollerRepository.findById(Long.valueOf(1)).get();
	}

}
