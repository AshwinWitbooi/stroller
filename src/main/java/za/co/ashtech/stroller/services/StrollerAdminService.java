package za.co.ashtech.stroller.services;

import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerAdminService {
	
	public Stroll addStroll(Stroll stroll) throws StrollerServiceException;
	public Stroll updateStroll(Long id, Stroll stroll) throws StrollerServiceException;
	public void deleteStroll(Long id) throws StrollerServiceException;

}
