package com.nta.slack.web.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationCreater {

    private String name;
    private boolean is_private;
    private String team_id;

}
