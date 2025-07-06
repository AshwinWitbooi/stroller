package za.co.ashtech.stroller.controller;

import za.co.ashtech.stroller.util.StrollerServiceException;

public abstract class BaseController {
	
	/*
	 * This method is used by all token protected Controller and is the common
	 * location where userId request param validation will be done
	 */
	protected void validateUserIdRequestParam(String userId) throws StrollerServiceException{
    	if (userId == null || userId.isBlank()) {
            throw new StrollerServiceException("Bad request send. Contact SPOC.");
        }
	}

}
