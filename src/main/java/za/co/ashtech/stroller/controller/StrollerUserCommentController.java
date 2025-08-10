package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ashtech.stroller.controller.entities.StrollUserCommentRequest;
import za.co.ashtech.stroller.services.StrollerUserCommentService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("public")
public class StrollerUserCommentController{

    @Autowired
    private StrollerUserCommentService strollerContactService;

    @PostMapping("comment")
    public ResponseEntity<Void> postUserComment(@RequestBody StrollUserCommentRequest strollUserComment) throws StrollerServiceException{
    	strollerContactService.postComment(strollUserComment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}