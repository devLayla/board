package api.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="member")
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String user_id;

    private String password;

    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @Builder
    public Member(String user_id, String password, String name){
        this.user_id = user_id;
        this.password = password;
        this.name = name;
    }


}
