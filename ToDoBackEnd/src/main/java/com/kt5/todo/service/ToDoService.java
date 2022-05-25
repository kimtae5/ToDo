package com.kt5.todo.service;

import java.util.List;

import com.kt5.todo.model.ToDoEntity;

public interface ToDoService {
	//데이터 삽입
	public List<ToDoEntity> create(final ToDoEntity entity);
	//userId에 해당되는 데이터 조회
	public List<ToDoEntity> retrieve(final String userId);
	//id에 해당되는 데이터 조회
	public ToDoEntity detail(final String id);
	//데이터 수정
	public List<ToDoEntity> update(final ToDoEntity entity);
	//데이터 삭제
	public List<ToDoEntity> delete(final ToDoEntity entity);

}
//지난번에는 DTO와 Entity의 변환을 Service에서 함
//장점은 DTO와 Entity변환 작업을 Service에서 호출하기 때문에 자기 한테 만들어져 있으면 
//코드를 읽기가 편함
//단점은 Business Logic 과 스그렇지 않은 로직이 한곳에 있어서 역할의 경걔가 애매해지고 유지보수
// 어려움