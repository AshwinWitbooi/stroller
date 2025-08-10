package za.co.ashtech.stroller.services;

import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerUserCommentService {
	
	public void postComment(StrollUserCommentRequest strollUserComment) throws StrollerServiceException;

}
