package za.co.ashtech.stroller.services;

import za.co.ashtech.stroller.controller.entities.StrollUserComment;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerContactService {
	
	public void postContact(StrollUserComment strollUserComment) throws StrollerServiceException;

}
