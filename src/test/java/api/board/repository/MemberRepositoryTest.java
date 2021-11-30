package api.board.repository;

import api.board.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입테스트() throws Exception {
        Member member = Member.builder()
                .user_id("asdf12")
                .password("asdf")
                .name("김철수")
                .build();

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        assertThat(member.getName()).isEqualTo(result.getName());
        assertThat(member.getPassword()).isEqualTo(result.getPassword());

    }

}