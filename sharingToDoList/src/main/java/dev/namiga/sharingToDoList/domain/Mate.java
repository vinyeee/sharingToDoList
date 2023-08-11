package dev.namiga.sharingToDoList.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Mate {
    @Id
    private String mateNickname;
    private String together;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

}
