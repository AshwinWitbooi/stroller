package za.co.ashtech.stroller.services;

import org.springframework.web.multipart.MultipartFile;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerAdminService {
	
	public void addStroll(String requestJson, MultipartFile file) throws StrollerServiceException;
	public Stroll updateStroll(String id, Stroll stroll) throws StrollerServiceException;
	public void deleteStroll(String strollId) throws StrollerServiceException;

}
