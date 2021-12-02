package api.board.service;

import api.board.controller.web.PostSaveDto;
import api.board.domain.*;
import api.board.repository.InterestRepository;
import api.board.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private ReplyService replyService;

    @Test
    public void 댓글작성_테스트() throws Exception {
        Member member = Member.builder()
                .userid("asdf1234")
                .password("asdf")
                .name("김철수")
                .build();
        memberRepository.save(member);

        PostSaveDto posts = PostSaveDto.builder()
                .title("글 제목 입니다")
                .content("글 내용 입니다")
                .writeDateTime(LocalDateTime.now())
                .build();
        postService.save(posts);

        Interested interested = new Interested();

        Reply reply = Reply.builder()
                .content("댓글입력테스트")
                .member(member)
                .writeDateTime(LocalDateTime.now())
                .post(posts.toEntity())
                .interested(interested)
                .build();

        Long findOneId = replyService.save(reply);

        Reply findReply = replyService.findOne(findOneId);

        Assertions.assertThat(findReply.getId()).isEqualTo(reply.getId());
        Assertions.assertThat(findReply.getContent()).isEqualTo(reply.getContent());

    }

    @Test
    public void 댓글추천_테스트() throws Exception {
        Member member = Member.builder()
                .userid("asdf1234")
                .password("asdf")
                .name("김철수")
                .build();
        memberRepository.save(member);

        PostSaveDto posts = PostSaveDto.builder()
                .title("글 제목 입니다")
                .content("글 내용 입니다")
                .writeDateTime(LocalDateTime.now())
                .build();
        postService.save(posts);

        Interested interested = new Interested();

        Reply reply = Reply.builder()
                .content("댓글입력테스트")
                .member(member)
                .writeDateTime(LocalDateTime.now())
                .post(posts.toEntity())
                .interested(interested)
                .build();

        Long findOneId = replyService.save(reply);
        Reply findReply = replyService.findOne(findOneId);

        replyService.changeInterest(findOneId, INTEREST.INTEREST);

    }


}