package api.board.controller;

import api.board.controller.web.PostResponseDto;
import api.board.controller.web.PostSaveDto;
import api.board.controller.web.PostUpdateRequestDto;
import api.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/post")
    public Long save(@RequestBody PostSaveDto postSaveDto){
        return postService.save(postSaveDto);
    }

    @PutMapping("/api/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto updateDto){
        return postService.update(id,updateDto);
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }

}
