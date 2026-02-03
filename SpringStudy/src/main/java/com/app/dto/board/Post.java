package com.app.dto.board;

import java.util.List;

public class Post { //게시글

	List<Comment> commentList; //댓글들...
	int boardId;  //외부 Board 테이블의 PK
}
