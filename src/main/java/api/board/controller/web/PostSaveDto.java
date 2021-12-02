package api.board.controller.web;

import api.board.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostSaveDto {

    private String title;

    private String content;

    private LocalDateTime writeDateTime;

    @Builder
    public PostSaveDto(String title, String content, LocalDateTime writeDateTime){
        this.title = title;
        this.content = content;
        this.writeDateTime = writeDateTime;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .writeDateTime(writeDateTime)
                .build();
    }


}
