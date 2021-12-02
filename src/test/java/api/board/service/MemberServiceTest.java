package api.board.service;

import api.board.domain.Member;
import api.board.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebAppConfiguration
@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입_테스트() throws Exception {
        Member member = Member.builder()
                .userid("asdf12")
                .password("aaaa")
                .name("김철수")
                .build();

        Long save = memberService.join(member);

        //findById만 할 경우 Optional로 반환하기 때문에 get으로 받아줘야 JPA의 동일성 보장이 처리됨.
        assertEquals(member,memberRepository.findById(save).get());

    }

    @Test
    public void 중복_회원_가입() throws Exception {
        Member member1 = Member.builder()
                .userid("asdf")
                .build();

        Member member2 = Member.builder()
                .userid("asdf")
                .build();

        memberService.join(member1);

        //junit4 -> 5변경점
        //assertThrows를 통해 어떤 코드를 실행시 어떤 exception이 실행되는지 받아온다
        //해당 exception에서 메세지를 받아와 받아온 메세지와 입력한 메세지가 동일한지 확인한다.
        IllegalStateException thrown = assertThrows(IllegalStateException.class,()-> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());

    }



}