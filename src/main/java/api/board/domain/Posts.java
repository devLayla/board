package api.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
@Entity
public class Posts {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private int views;

    private LocalDateTime writeDateTime;

    private LocalDateTime modifyDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Posts(String title, String content, LocalDateTime writeDateTime){
        this.title = title;
        this.content = content;
        this.writeDateTime = writeDateTime;
    }

    public int addView(){
        this.views++;
        return this.views;
    }

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changePost(LocalDateTime modifyDateTime){
        this.modifyDateTime = modifyDateTime;
    }


}
