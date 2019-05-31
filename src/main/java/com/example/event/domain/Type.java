package com.example.event.domain;

import javax.validation.constraints.Size;

public class Type {
	public static final int ADMIN = 2; //管理ユーザーのID
	private Integer typeId;
	@Size(max=45)
	private String typeName;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
