package com.nta.slack.database.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String message;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Column
    private StatusMessage statusMessage;

    private enum StatusMessage{
        enviada,
        erro
    }

}
