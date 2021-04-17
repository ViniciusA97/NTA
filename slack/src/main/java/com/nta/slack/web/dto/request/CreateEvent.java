package com.nta.slack.web.dto.request;

import com.nta.slack.database.model.Channel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEvent {

    private String name;
    private List<Channel> channelList;

}
