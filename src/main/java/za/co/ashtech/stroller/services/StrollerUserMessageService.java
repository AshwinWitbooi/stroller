package za.co.ashtech.stroller.services;

import za.co.ashtech.stroller.controller.entities.StrollUserMessageRequest;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerUserMessageService {
	
	public void postMessage(StrollUserMessageRequest strollUserComment) throws StrollerServiceException;

}
