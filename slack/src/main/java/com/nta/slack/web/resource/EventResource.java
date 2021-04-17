package com.nta.slack.web.resource;

import com.nta.slack.web.dto.request.CreateEvent;
import com.nta.slack.web.dto.request.PublishMessage;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventResource {

    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody CreateEvent createEvent){
        return ResponseEntity.ok("");
    }

    @PostMapping("/publish/messsage/{eventId}")
    public ResponseEntity<?> publishMessage(@PathVariable("eventId") int eventId,
                                            @RequestBody PublishMessage publishMessage){

        return ResponseEntity.ok("");

    }


}
