package api.board.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "interest")
@Entity
public class Interested {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private INTEREST isInterest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Interested(){
        isInterest = INTEREST.NONE;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public int addInterest(INTEREST isInterest){
        if(this.isInterest == INTEREST.NONE){
            this.isInterest = INTEREST.INTEREST;
            return 1;
        }else if(this.isInterest == INTEREST.BORING){
            return 0;
        }
        this.isInterest = INTEREST.NONE;
        return -1;
    }

    public int addBoring(INTEREST isInterest){
        if(this.isInterest == INTEREST.NONE){
            this.isInterest = INTEREST.BORING;
            return 1;
        }else if(this.isInterest == INTEREST.INTEREST){
            return 0;
        }
        this.isInterest = INTEREST.NONE;
        return -1;
    }

    public void deleteCheck(){
        isInterest = INTEREST.NONE;
    }

}
