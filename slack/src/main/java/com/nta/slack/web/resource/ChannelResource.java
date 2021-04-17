package com.nta.slack.web.resource;

import com.nta.slack.web.dto.request.ConversationCreater;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/channel")
public class ChannelResource {

    @PostMapping("/{teamId}")
    public ResponseEntity<?> createChannel(@PathVariable("teanId") String teamId,
                                           @RequestBody ConversationCreater conversationCreater){

        return ResponseEntity.ok("");

    }

}
