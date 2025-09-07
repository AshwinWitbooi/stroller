package za.co.ashtech.stroller.services;

import java.util.List;

import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerAdminService {
	
	public Stroll addStroll(Stroll stroll) throws StrollerServiceException;
	public Stroll updateStroll(Long id, Stroll stroll) throws StrollerServiceException;
	public void deleteStroll(Long id) throws StrollerServiceException;
	public List<Stroll> getAllStrolls() throws StrollerServiceException;
	public Stroll getStrollByName(String name) throws StrollerServiceException;

}
