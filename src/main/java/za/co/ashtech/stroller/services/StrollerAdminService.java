package za.co.ashtech.stroller.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerAdminService {
	
	public void addStroll(String requestJson, MultipartFile file) throws StrollerServiceException;
	public Stroll updateStroll(String id, Stroll stroll) throws StrollerServiceException;
	public void deleteStroll(String strollId) throws StrollerServiceException;
	public List<Stroll> getAllStrolls() throws StrollerServiceException;
	public Stroll getStrollById(String strollId) throws StrollerServiceException;

}
