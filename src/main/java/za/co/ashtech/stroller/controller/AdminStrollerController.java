package za.co.ashtech.stroller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.controller.entities.TransactionLogEntry;
import za.co.ashtech.stroller.services.StrollerAdminService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("admin/api/v1")
public class AdminStrollerController {

    @Autowired
    private StrollerAdminService strollerAdminService;

    @PostMapping(value="stroll",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addStroll(@RequestPart("stroll") String requestJson,
    												   @RequestPart("file") MultipartFile file) throws StrollerServiceException{
    	
    	strollerAdminService.addStroll(requestJson, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
        
    @PutMapping("stroll/{strollId}")
    public ResponseEntity<Stroll> updateStroll(@PathVariable String strollId,@RequestBody Stroll stroll) throws StrollerServiceException{

    	return ResponseEntity.ok(strollerAdminService.updateStroll(strollId, stroll));
    }
    
    @DeleteMapping("stroll/{strollId}")
    public ResponseEntity<Void> deleteStroll(@PathVariable String strollId) throws StrollerServiceException{
    	strollerAdminService.deleteStroll(strollId);
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping("stroll")
    public ResponseEntity<List<Stroll>> getAllStrolls() throws StrollerServiceException{
    	List<Stroll> strolls = strollerAdminService.getAllStrolls();
    	return ResponseEntity.ok(strolls);
    }
    
    @GetMapping("stroll/{strollId}")
    public ResponseEntity<Stroll>  getUserById(@PathVariable("strollId") String strollId) throws StrollerServiceException{
        return ResponseEntity.ok(strollerAdminService.getStrollById(strollId));
    }
    
    @GetMapping("transaction")
    public ResponseEntity<List<TransactionLogEntry>>  getAllTransactions() throws StrollerServiceException{    	
    	return ResponseEntity.ok(strollerAdminService.getAllTransactions());
    }
}