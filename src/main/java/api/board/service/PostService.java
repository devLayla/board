package api.board.service;

import api.board.domain.Posts;
import api.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Long save(Posts posts){
        Posts save = postRepository.save(posts);
        return save.getId();
    }

    public Posts findOne(Long id){
        return postRepository.findById(id).get();
    }

    public List<Posts> findAll(){
        return postRepository.findAll();
    }


}
