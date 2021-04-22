package com.nta.slack.database.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("is_member")
    private boolean is_member;

    @Column
    @JsonProperty("is_channel")
    private boolean is_channel;

    @Column
    @JsonProperty("is_private")
    private boolean is_private;

    @Column
    @JsonProperty("is_group")
    private boolean is_group;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @JsonBackReference
    private Team team;

    @ManyToMany
    @JsonBackReference
    private List<Event> eventList;

}
