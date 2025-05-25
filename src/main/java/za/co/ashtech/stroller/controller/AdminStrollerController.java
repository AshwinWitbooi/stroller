package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("admin/api/v1")
public class AdminStrollerController {

    @Autowired
    private StrollerAdminService strollerAdminService;

    @PostMapping("stroll")
    public ResponseEntity<Stroll> addStroll(@RequestBody Stroll stroll) {
        return ResponseEntity.ok(strollerAdminService.addStroll(stroll));
    }
    
    
    @PutMapping("stroll/{id}")
    public ResponseEntity<Stroll> updateStroll(@PathVariable Long id,@RequestBody Stroll stroll) {
        return ResponseEntity.ok(strollerAdminService.updateStroll(id, stroll));
    }
    
    @DeleteMapping("stroll/{id}")
    public ResponseEntity<Void> deleteStroll(@PathVariable Long id) {
    	strollerAdminService.deleteStroll(id);
        return ResponseEntity.noContent().build();
    }

}