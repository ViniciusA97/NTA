package com.nta.slack.service;

import com.nta.slack.database.model.Event;
import com.nta.slack.web.dto.request.CreateEvent;
import com.nta.slack.web.dto.request.PublishMessage;
import com.nta.slack.web.dto.response.ApiResponse;
import com.nta.slack.web.dto.response.ApiResponseImpl;

import java.util.List;

public interface EventService {

    ApiResponse createEvent(CreateEvent createEvent);
    ApiResponse getAllEvent();
    ApiResponse publishMessage(PublishMessage publishMessage, int eventId);

}
