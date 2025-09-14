package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.services.StrollerService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("/api/v1")
public class StrollerController{

    @Autowired
    private StrollerService strollerService;

    @GetMapping("stroll")
    public ResponseEntity<Stroll> getAllCustomers() throws StrollerServiceException{
        return ResponseEntity.ok(strollerService.getRandomStroll());
    }

}