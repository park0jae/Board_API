package com.example.board.factory.board;

import com.example.board.domain.board.Board;

public class BoardFactory {

    public static Board createBoard(){
        return Board.builder()
                .title("title 입니다.")
                .content("content 입니다.")
                .writer("작성자1")
                .hits(0)
                .deleted(false).build();
    }
}
