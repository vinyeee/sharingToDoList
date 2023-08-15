package dev.namiga.sharingToDoList.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class ToDoListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;
    private String details;
    private String complete;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "challengeId",referencedColumnName ="challengeId")
    private ChallengeEntity challengeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName ="userId")
    private UserEntity userId;

}
