package api.board.controller;

import api.board.controller.web.PostSaveDto;
import api.board.controller.web.PostUpdateRequestDto;
import api.board.domain.Posts;
import api.board.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//랜덤 포트를 사용한다
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void 포스트_등록_테스트() throws Exception {

        String title = "제목";
        String content = "내용";

        PostSaveDto postSaveDto = PostSaveDto.builder()
                .title(title)
                .content(content)
                //.writeDateTime(LocalDateTime.now())
                .build();

        String url = "http://localhost:" + port + "/api/post";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postSaveDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void 포스트_수정_테스트() throws Exception {
        Posts save = postRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .writeDateTime(LocalDateTime.now())
                .build());

        Long getId = save.getId();
        String changeTitle = "title2";
        String changeContent = "content2";

        PostUpdateRequestDto updateRequestDto = PostUpdateRequestDto.builder()
                .title(changeTitle)
                .content(changeContent)
                .build();

        String url = "http://localhost:" + port + "/api/post/" + getId;
        //요청 혹은 응답에 해당하는 객체를 request로 받아옴
        HttpEntity<PostUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(updateRequestDto);

        //restTemplate를 통해 url, REST타입, entity, 반환받을 타입.class
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> findAll = postRepository.findAll();
        assertThat(findAll.get(0).getTitle()).isEqualTo(changeTitle);
        assertThat(findAll.get(0).getContent()).isEqualTo(changeContent);

    }

}