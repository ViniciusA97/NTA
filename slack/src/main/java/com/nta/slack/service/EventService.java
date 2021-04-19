package com.nta.slack.service;

import com.nta.slack.database.model.Event;
import com.nta.slack.web.dto.request.CreateEvent;
import com.nta.slack.web.dto.request.PublishMessage;

import java.util.List;

public interface EventService {

    Event createEvent(CreateEvent createEvent);
    List<Event> getAllEvent();
    boolean publishMessage(PublishMessage publishMessage, int eventId);

}
