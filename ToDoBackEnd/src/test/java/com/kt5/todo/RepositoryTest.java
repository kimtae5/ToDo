package com.kt5.todo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kt5.todo.model.ToDoEntity;
import com.kt5.todo.persistence.ToDoRepository;


@SpringBootTest
public class RepositoryTest {
	@Autowired
	private ToDoRepository toDoRepository;
	
	//삽입확인
	//@Test
	public void testInsert() {
		ToDoEntity toDoEntity = ToDoEntity.builder().userId("kt1").title("테스트용 데이터5").build();
		toDoRepository.save(toDoEntity);
	}

	//수정확인
	//@Test
	public void testUpdate() {
		ToDoEntity toDoEntity = ToDoEntity.builder().id("40288a1f80f443c40180f443ce8e0000")
				.userId("kt9").title("수정용 데이터3").build();
		toDoRepository.save(toDoEntity);
	}
	
	//하나의 데이터 가져오는 메서드 확인
	//@Test
	public void testDetail() {
		
		//기본키를 가지고 데이터 조회
		Optional<ToDoEntity> result = toDoRepository.findById("40288a1f80f443c40180f443ce8e0010");
		
		//데이터가 존재할때
		if(result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("데이터가 존재하지 않음");
		}
	}
	
	//기본키가 아닌 것을 가지고 조회
	//@Test
	public void testList() {
		
		//기본키를 가지고 데이터 조회
		List<ToDoEntity> result = toDoRepository.findByUserId("kt6");
		
		//데이터가 존재할때
		if(result.size() > 0) {
			for(ToDoEntity todo : result) {
				System.out.println(todo);
				
			}
		}else {
			System.out.println("데이터가 존재하지 않음");
		}
	}
	
	//삭제는 기본키를 가지고 지우는 것과 Entity를 이용해서 지우는 2가지를 제공
	//@Test
	public void testDelete() {
		toDoRepository.deleteById("40288a1f80f443c40180f443ce8e0000");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
