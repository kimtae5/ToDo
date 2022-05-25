package com.kt5.todo.dto;

import java.time.LocalDateTime;

import com.kt5.todo.model.ToDoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
	
	private String id;
	private String title;
	private boolean done;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
	//ToDoEntity를 매개변수로 해서 ToDoDTO를 생성하는 생성
	//생성자는 리턴타입이 없고 메서드 이름은 클래스가 동일
	//매개변수
	public ToDoDTO(final ToDoEntity todo) {
		this.id = todo.getId();
		this.title = todo.getTitle();getTitle();
		this.done = todo.isDone();
		this.regDate = todo.getRegDate();
		this.modDate = todo.getModDate();
	}
	
	//DTO를 Entity로 변환해주는 메서드
	public static ToDoEntity toEntity(final ToDoDTO dto) {
		return ToDoEntity.builder().id(dto.getId()).title(dto.getTitle())
				.done(dto.isDone()).build();
	}
	
	

}
