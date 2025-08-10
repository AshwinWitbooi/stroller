package za.co.ashtech.stroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;
import za.co.ashtech.stroller.db.repo.StrollUserCommentRepository;
import za.co.ashtech.stroller.util.StrollerServiceException;

@Slf4j
@Service
public class StrollerUserCommentServiceImpl implements StrollerUserCommentService {

	@Autowired
	private StrollUserCommentRepository strollContactRepository;


	@Override
	public void postComment(StrollUserCommentRequest strollUserComment) throws StrollerServiceException {

		try {
			za.co.ashtech.stroller.db.entities.StrollUserComment dbRecord = 
					new za.co.ashtech.stroller.db.entities.StrollUserComment(strollUserComment.getFirstname(), strollUserComment.getLastname(), strollUserComment.getEmail(), strollUserComment.getComment());
			log.debug("New StrollContact record create for persistence.");
			
			strollContactRepository.save(dbRecord);
			log.debug("StrollContact record persistenced.");
		} catch (Exception e) {
			throw new StrollerServiceException("Error saving contact post", e);
		}
	}

}
