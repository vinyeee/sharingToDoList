package dev.namiga.sharingToDoList.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class To_do_list {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;
    private String details;
    private String check;

    @ManyToOne
    @JoinColumn(name = "challengeId")
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

}
