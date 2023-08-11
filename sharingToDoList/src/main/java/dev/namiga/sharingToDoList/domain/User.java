package dev.namiga.sharingToDoList.domain;

import dev.namiga.sharingToDoList.domain.To_do_list;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    private long userId;
    private String token;
    private String bank;
    private String account;
    private String introduce;
    private String platform;
    private String nickName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Challenge> challenge;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<To_do_list> to_do_list;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Analysis> analysis;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Mate> mate;

}
