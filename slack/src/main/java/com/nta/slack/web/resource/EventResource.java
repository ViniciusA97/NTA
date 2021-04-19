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

        Event event = this.eventService.createEvent(createEvent);



        ApiResponse apiResponse = ApiResponse.builder()
                .success(event!=null)
                .content(event)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/messsage/{id}")
    public ResponseEntity<?> publishMessage(@PathVariable int id,
                                            @RequestBody PublishMessage publishMessage){

        eventService.publishMessage(publishMessage,id);

        return ResponseEntity.ok("");

    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
