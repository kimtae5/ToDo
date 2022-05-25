package com.kt5.todo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.todo.model.ToDoEntity;

public interface ToDoRepository extends JpaRepository<ToDoEntity, String> {
	//UserId에 해당하는 ToDoEntity를 가져오는 메서드
	public List<ToDoEntity> findByUserId(String userId);

}
