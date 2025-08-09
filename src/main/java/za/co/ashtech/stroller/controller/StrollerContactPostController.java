package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ashtech.stroller.controller.entities.StrollUserComment;
import za.co.ashtech.stroller.services.StrollerContactService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("public")
public class StrollerContactPostController{

    @Autowired
    private StrollerContactService strollerContactService;

    @PostMapping("contact")
    public ResponseEntity<Void> postUserComment(@RequestBody StrollUserComment strollUserComment) throws StrollerServiceException{
    	strollerContactService.postContact(strollUserComment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}