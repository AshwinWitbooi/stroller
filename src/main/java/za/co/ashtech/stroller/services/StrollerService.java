package za.co.ashtech.stroller.services;

import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerService {
	
	public Stroll getRandomStrol() throws StrollerServiceException;

}
