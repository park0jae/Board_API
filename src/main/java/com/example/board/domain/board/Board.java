package com.example.board.domain.board;

import com.example.board.domain.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    private int hits;
    private boolean deleted;

    @Builder
    public Board(String title, String content, String writer, int hits, boolean deleted){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.deleted = deleted;
    }
}
