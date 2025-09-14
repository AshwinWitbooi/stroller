//package za.co.ashtech.stroller.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import za.co.ashtech.stroller.util.StrollerServiceException;
//
//@Slf4j
//public abstract class BaseController {
//	
//	/*
//	 * This method is used by all token protected Controller and is the common
//	 * location where userId request param validation will be done
//	 */
//	protected void validateUserIdRequestParam(String userId) throws StrollerServiceException{
//    	if (userId == null || userId.isBlank()) {
//    		log.error("No userId request parameters set.");
//            throw new StrollerServiceException("Bad request send. Contact SPOC.");
//        }
//	}
//
//}
