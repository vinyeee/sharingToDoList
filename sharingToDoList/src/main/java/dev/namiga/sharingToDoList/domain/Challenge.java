package dev.namiga.sharingToDoList.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengeId;
    private String mate;
    private Timestamp endTime;
    private String quest;
    private Integer prize;
    private Timestamp startTime;
    private Integer time;
    private String status;
    private String permit;
    private String result;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @OneToMany(mappedBy = "challengeId" , cascade = CascadeType.ALL)
    private List<To_do_list> to_do_list;

}
