package dev.namiga.sharingToDoList.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String token;
    private String bank;
    private String account;
    private String introduce;
    private String platform;
    private String nickName;

    @OneToMany(mappedBy = "userId")
    private List<ChallengeEntity> challenge;

    @OneToMany(mappedBy = "userId")
    private List<ToDoListEntity> to_do_list;

    @OneToMany(mappedBy = "userId")
    private List<AnalysisEntity> analysis;

    @OneToMany(mappedBy = "userId")
    private List<MateEntity> mate;

}
