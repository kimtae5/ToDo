package com.kt5.todo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt5.todo.dto.ResponseDTO;
import com.kt5.todo.dto.ToDoDTO;
import com.kt5.todo.model.ToDoEntity;
import com.kt5.todo.service.ToDoService;

import lombok.RequiredArgsConstructor;

//데이터를 리턴하기 위한 Controller를 만들기 위한 어노테이션
@RestController
//공통된 URL작성 localhost:포트번호/todo/
@RequestMapping("todo")

public class ToDoController {
	@Autowired
	private  ToDoService toDoService;
	
	//데이터 삽입
	@PostMapping
	public ResponseEntity<?> createToDo(@RequestBody ToDoDTO dto){
		try {
			//회원 정보를 만들 수 없어서 임시로 회원 아이디 설정
			String temporaryUserId = "temporary-user";
			//DTO를 Entity로 변환
			ToDoEntity entity = ToDoDTO.toEntity(dto);
			entity.setId(null);
			entity.setUserId(temporaryUserId);
			//서비스의 삽입을 호출하고 결과를 저장
			List<ToDoEntity> entities = toDoService.create(entity);
			
			//ToDoEntity의 List를 ToDoDTO의 List로 변환
			// entities.stream()는 List 를 stream으로 변환
			//map은 Stream의 모든 요소를 순서대로 매개 변수로 대입된 함수를 
			//적용해서 리턴한 값들을 가지고
			//스트림을 만들어주는 메서드
			//클래스이름
			List<ToDoDTO> list = entities.stream().map(ToDoDTO::new)
					.collect(Collectors.toList());
				//응답객체를 생성
			ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder()
					.data(list).build();
			//정상 응답 객체를 만든 후 본
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			//예외가 발생하면 에러 메세지를 리턴
			String error = e.getMessage();
			ResponseDTO<ToDoDTO> response =ResponseDTO.<ToDoDTO>builder()
					.error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	//데이터조회
	@GetMapping
	public ResponseEntity<?> retrieveToDoList(){
		String temporaryUserId = "temporary-user";
		List<ToDoEntity> entities = toDoService.retrieve(temporaryUserId);
		//ToDoEntity의 List를 ToDoDTO의 List로 변환
		List<ToDoDTO> list = entities.stream().map(ToDoDTO::new)
				.collect(Collectors.toList());
			//응답객체를 생성
		ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder()
				.data(list).build();
		//정상 응답 객체를 만든 후 본
		return ResponseEntity.ok().body(response);
	}
	
	
	//데이터수정
	@PutMapping
	public ResponseEntity<?> updateToDo(@RequestBody ToDoDTO dto){
		String temporaryUserId = "temporary-user";
		
		ToDoEntity entity = ToDoDTO.toEntity(dto);
		entity.setUserId(temporaryUserId);
		
		List<ToDoEntity> entities = toDoService.update(entity);
		//ToDoEntity의 List를 ToDoDTO의 List로 변환
		List<ToDoDTO> list = entities.stream().map(ToDoDTO::new)
				.collect(Collectors.toList());
		//응답객체를 생성
		ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder()
				.data(list).build();
		//정상 응답 객체를 만든 후 본
		return ResponseEntity.ok().body(response);
	}
	
	//데이터 삭제
	@DeleteMapping
	public ResponseEntity<?> deleteToDo(@RequestBody ToDoDTO dto){
		String temporaryUserId = "temporary-user";
		
		ToDoEntity entity = ToDoDTO.toEntity(dto);
		entity.setUserId(temporaryUserId);
		
		List<ToDoEntity> entities = toDoService.delete(entity);
		//ToDoEntity의 List를 ToDoDTO의 List로 변환
		List<ToDoDTO> list = entities.stream().map(ToDoDTO::new)
				.collect(Collectors.toList());
		//응답객체를 생성
		ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder()
				.data(list).build();
		//정상 응답 객체를 만든 후 본
		return ResponseEntity.ok().body(response);
	}

}
