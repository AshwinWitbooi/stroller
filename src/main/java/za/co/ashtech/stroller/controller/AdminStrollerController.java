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
import org.springframework.web.bind.annotation.RestController;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.services.StrollerAdminService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("admin/api/v1")
public class AdminStrollerController {

    @Autowired
    private StrollerAdminService strollerAdminService;

    @PostMapping("stroll")
    public ResponseEntity<Void> addStroll(@RequestBody Stroll stroll) throws StrollerServiceException{
    	strollerAdminService.addStroll(stroll);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    
    @PutMapping("stroll/{id}")
    public ResponseEntity<Stroll> updateStroll(@PathVariable Long id,@RequestBody Stroll stroll) throws StrollerServiceException{
        return ResponseEntity.ok(strollerAdminService.updateStroll(id, stroll));
    }
    
    @DeleteMapping("stroll/{id}")
    public ResponseEntity<Void> deleteStroll(@PathVariable Long id) throws StrollerServiceException{
    	strollerAdminService.deleteStroll(id);
        return ResponseEntity.noContent().build();
    }

}