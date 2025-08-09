package za.co.ashtech.stroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.StrollUserComment;
import za.co.ashtech.stroller.db.repo.StrollContactRepository;
import za.co.ashtech.stroller.util.StrollerServiceException;

@Slf4j
@Service
public class StrollerContactServiceImpl implements StrollerContactService {

	@Autowired
	private StrollContactRepository strollContactRepository;


	@Override
	public void postContact(StrollUserComment strollUserComment) throws StrollerServiceException {

		try {
			za.co.ashtech.stroller.db.entities.StrollContact dbRecord = 
					new za.co.ashtech.stroller.db.entities.StrollContact(strollUserComment.getFirstname(), strollUserComment.getLastname(), strollUserComment.getEmail(), strollUserComment.getComment());
			log.debug("New StrollContact record create for persistence.");
			
			strollContactRepository.save(dbRecord);
			log.debug("StrollContact record persistenced.");
		} catch (Exception e) {
			throw new StrollerServiceException("Error saving contact post", e);
		}
	}

}
