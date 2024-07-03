package io.thesun4sky.todoapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponseDto<T> {
	private Integer statusCode;
	private String msg;
	private T data;
}
