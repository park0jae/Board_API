package com.example.board.domain.board;

import com.example.board.factory.board.BoardFactory;
import com.example.board.repository.board.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static com.example.board.factory.board.BoardFactory.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void before(){
        boardRepository.deleteAllInBatch();
    }

    @Test
    void save(){
        //given
        Board board = createBoard();
        //when
        Board saveBoard = boardRepository.save(board);
        //then
        assertThat(saveBoard.getTitle()).isEqualTo("title 입니다.");
        assertThat(saveBoard.getContent()).isEqualTo("content 입니다.");
    }

    @Test
    void findAll(){
        // given
        Board board1 = createBoard();
        Board board2 = createBoard();
        // when
        boardRepository.save(board1);
        boardRepository.save(board2);
        // then
        List<Board> findBoards = boardRepository.findAll();
        assertThat(findBoards.size()).isEqualTo(2);
    }

    @Test
    void delete(){
        // given
        Board board = createBoard();
        boardRepository.save(board);
        // when
        boardRepository.delete(board);
        // then
        List<Board> findBoards = boardRepository.findAll();
        assertThat(findBoards.size()).isEqualTo(0);
    }
}