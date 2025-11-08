package za.co.ashtech.stroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.StrollUserMessageRequest;
import za.co.ashtech.stroller.db.repo.StrollUserMessageRepository;
import za.co.ashtech.stroller.util.StrollerServiceException;

@Slf4j
@Service
public class StrollerUserCommentServiceImpl implements StrollerUserMessageService {

	@Autowired
	private StrollUserMessageRepository strollUserMessageRepository;


	@Override
	public void postMessage(StrollUserMessageRequest strollUserComment) throws StrollerServiceException {

		try {
			za.co.ashtech.stroller.db.entities.StrollUserMessage dbRecord = 
					new za.co.ashtech.stroller.db.entities.StrollUserMessage(strollUserComment.getFirstname(), strollUserComment.getEmail(), strollUserComment.getMessage());
			log.debug("New StrollContact record create for persistence.");
			
			strollUserMessageRepository.save(dbRecord);
			log.debug("StrollContact record persistenced.");
		} catch (Exception e) {
			throw new StrollerServiceException("Error saving contact post", e);
		}
	}

}
