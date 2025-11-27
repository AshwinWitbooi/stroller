package za.co.ashtech.stroller.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.controller.entities.TransactionLogEntry;
import za.co.ashtech.stroller.util.StrollerServiceException;

public interface StrollerAdminService {
	
	 void addStroll(String requestJson, MultipartFile file) throws StrollerServiceException;
	 Stroll updateStroll(String id, Stroll stroll) throws StrollerServiceException;
	 void deleteStroll(String strollId) throws StrollerServiceException;
	 List<Stroll> getAllStrolls() throws StrollerServiceException;
	 Stroll getStrollById(String strollId) throws StrollerServiceException;
	 List<TransactionLogEntry> getAllTransactions() throws StrollerServiceException;

}
