package com.nta.slack.database.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Channel implements Serializable {

    @Id
    public String id;

    @Column(nullable = false)
    public String name;

    @Column
    private boolean is_member;

    @Column
    private boolean is_channel;

    @Column
    private boolean is_private;

    @Column
    private boolean is_group;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToMany
    private List<Event> eventList;

}
