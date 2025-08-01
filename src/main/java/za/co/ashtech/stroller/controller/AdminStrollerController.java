package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.services.StrollerAdminService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("admin/api/v1")
public class AdminStrollerController extends BaseController{

    @Autowired
    private StrollerAdminService strollerAdminService;

    @PostMapping("stroll")
    public ResponseEntity<Void> addStroll(@RequestBody Stroll stroll,@RequestParam(required = false) String userId) throws StrollerServiceException{
    	//validate userId request param
    	validateUserIdRequestParam(userId);
    	strollerAdminService.addStroll(stroll);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
        
    @PutMapping("stroll/{id}")
    public ResponseEntity<Stroll> updateStroll(@PathVariable Long id,@RequestBody Stroll stroll,@RequestParam(required = false) String userId) throws StrollerServiceException{
    	//validate userId request param
    	validateUserIdRequestParam(userId);
    	return ResponseEntity.ok(strollerAdminService.updateStroll(id, stroll));
    }
    
    @DeleteMapping("stroll/{id}")
    public ResponseEntity<Void> deleteStroll(@PathVariable Long id,@RequestParam(required = false) String userId) throws StrollerServiceException{
    	//validate userId request param
    	validateUserIdRequestParam(userId);
    	strollerAdminService.deleteStroll(id);
        return ResponseEntity.noContent().build();
    }

}