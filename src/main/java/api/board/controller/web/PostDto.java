package api.board.controller.web;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {

    private String title;

    private String content;

    private LocalDateTime writeDateTime;

}
