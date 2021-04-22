package com.nta.slack.web.resource;

import com.nta.slack.database.model.Event;
import com.nta.slack.service.EventService;
import com.nta.slack.web.dto.request.CreateEvent;
import com.nta.slack.web.dto.request.PublishMessage;
import com.nta.slack.web.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventResource {


    @Autowired
    private EventService eventService;

    @PostMapping()
    public ResponseEntity<ApiResponse> createEvent(@RequestBody CreateEvent createEvent){

        ApiResponse event = this.eventService.createEvent(createEvent);
        return new ResponseEntity(event, event.getHttpStatus());
    }


    @PostMapping("/messsage/{id}")
    public ResponseEntity<ApiResponse> publishMessage(@PathVariable int id,
                                            @RequestBody PublishMessage publishMessage){

        ApiResponse apiResponse = eventService.publishMessage(publishMessage, id);

        HttpStatus httpStatus = apiResponse.getHttpStatus();
        Object content = apiResponse.getContent();

        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity(apiResponse, apiResponse.getHttpStatus());

        return responseEntity;

    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(){

        ApiResponse allEvent = this.eventService.getAllEvent();
        return new ResponseEntity<>(allEvent, allEvent.getHttpStatus());
    }


}
