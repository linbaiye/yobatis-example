package org.nalby.yobatis.book.response;

public class Response<T> {
	
	private int code;

	private String description;

	private T data;

	public Response(int code, String desc, T data) {
		this.data = data;
		this.code = code;
		this.description = desc;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}

	public T getData() {
		return data;
	}
	

	public void setData(T data) {
		this.data = data;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public static <T> Response<T> ok(T data) {

		return new Response<>(200, "Ok", data);
	}

	public static <T> Response<T> error(T data) {
		return new Response<>(500, "error", data);
	}

}
