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
public class Team implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String tokenType;

    @OneToMany()
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private List<Channel> channels;


}
