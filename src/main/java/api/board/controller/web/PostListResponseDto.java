package api.board.controller.web;

import api.board.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime modifyDateTime;

    public PostListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.modifyDateTime = entity.getModifyDateTime();
    }


}
