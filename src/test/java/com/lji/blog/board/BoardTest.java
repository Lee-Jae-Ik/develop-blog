package com.lji.blog.board;

import com.lji.blog.model.dto.BoardSaveDto;
import com.lji.blog.repository.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BoardTest
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/04/24
 */
@SpringBootTest
@WebAppConfiguration
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName(value = "게시글 생성 테스트")
    public void saveTest() {
        BoardSaveDto defaultBoardSaveDto = BoardSaveDto.builder()
                .userId(1L)
                .title("JUNIT 제목 테스트")
                .contents("JUNIT 내용 테스트")
                .categoryId(1L)
                .boradTagNameList(Arrays.asList("테스트","JUNIT"))
                .build();
        String title = defaultBoardSaveDto.getTitle();
        assertEquals("JUNIT 제목 테스트",title);
    }
}
