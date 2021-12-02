package api.board.service;

import api.board.controller.web.PostListResponseDto;
import api.board.controller.web.PostResponseDto;
import api.board.controller.web.PostSaveDto;
import api.board.controller.web.PostUpdateRequestDto;
import api.board.domain.Posts;
import api.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveDto PostSaveDto){
        Posts save = postRepository.save(PostSaveDto.toEntity());
        return save.getId();
    }

    public PostResponseDto findById(Long id){
        Posts posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다 id = " + id));

        return new PostResponseDto(posts);
    }

    public List<PostListResponseDto> findAll(){
        return postRepository.findAll().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Posts findOne = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다 id = " + id));
        findOne.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

}
