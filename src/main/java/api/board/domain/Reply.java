package api.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply")
@Entity
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private String content;

    private int totalInterest;

    private int totalBoring;

    private LocalDateTime writeDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Posts post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="interest_id")
    private Interested interested;

    @Builder
    public Reply(String content, LocalDateTime writeDateTime, Posts post, Member member, Interested interested){
        this.content = content;
        this.writeDateTime = writeDateTime;
        this.post = post;
        this.member = member;
        this.interested = interested;
    }

    public int addInterest(INTEREST interest){
        totalInterest += interested.addInterest(interest);
        return totalInterest;
    }

    public int addBoring(INTEREST interest){
        totalBoring += interested.addBoring(interest);
        return totalBoring;
    }

}
