package com.nta.slack.entidades.slack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Channel {

    private String id;
    private String name;
    private boolean is_channel;
    private boolean is_group;
    private String name_normalized;
    private boolean is_member;
    private boolean is_private;
    private int num_members;

}
