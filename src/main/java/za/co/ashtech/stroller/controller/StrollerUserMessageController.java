package za.co.ashtech.stroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ashtech.stroller.controller.entities.StrollUserMessageRequest;
import za.co.ashtech.stroller.services.StrollerUserMessageService;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestController
@RequestMapping("public")
public class StrollerUserMessageController{

    @Autowired
    private StrollerUserMessageService strollerContactService;

    @PostMapping("user/message")
    public ResponseEntity<Void> postUserMessagw(@RequestBody StrollUserMessageRequest strollUserMessage) throws StrollerServiceException{
    	strollerContactService.postMessage(strollUserMessage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}