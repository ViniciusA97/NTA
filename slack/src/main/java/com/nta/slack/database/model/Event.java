package com.nta.slack.database.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private List<Channel> channels;

    @Column
    private String name;

    @OneToMany
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private List<Message> messages;


}
