package dev.namiga.sharingToDoList.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class To_do_list {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;
    private String details;
    private String complete;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "challengeId",referencedColumnName ="challengeId")
    private Challenge challengeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName ="userId")
    private User userId;

}
