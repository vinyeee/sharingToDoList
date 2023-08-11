package dev.namiga.sharingToDoList.domain;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long analysisId;  //혹시 몰라서 생성함
    private Integer number;
    private Integer winNum;
    private Double percent;
    private Long totalPrize;

    @ManyToOne
    @JoinColumn(name = "mateNickname")
    private Mate mateNickname;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

}
