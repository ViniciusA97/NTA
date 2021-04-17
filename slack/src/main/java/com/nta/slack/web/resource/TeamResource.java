package com.nta.slack.web.resource;

import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamResource {

    @GetMapping("/update/channels/{id}")
    public ResponseEntity<?> updateChannel(@PathVariable("id") String id){
        return ResponseEntity.ok("");
    }

}
