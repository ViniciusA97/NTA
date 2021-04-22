package com.nta.slack.database.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Channel> channels;

    @Column
    private String name;

    @OneToMany
    private List<Message> messages;


}
