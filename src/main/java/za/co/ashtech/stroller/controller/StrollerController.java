package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.services.StrollerService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("/api/v1")
public class StrollerController extends BaseController{

    @Autowired
    private StrollerService strollerService;

    @GetMapping("stroll")
    public ResponseEntity<Stroll> getAllCustomers(@RequestParam(required = false) String userId) throws StrollerServiceException{
    	//validate userId request param
    	validateUserIdRequestParam(userId);
        return ResponseEntity.ok(strollerService.getRandomStroll());
    }

}