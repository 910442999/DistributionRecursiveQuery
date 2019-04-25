package com.yc.distribution.model;

import com.yc.distribution.base.entity.BaseEntity;

public class UserRelation extends BaseEntity {



	private String id;//id


	private String childId;//下级id


	private String userId;//用户id


	private String parentId;//上级id
	public UserRelation(){
	}

	public UserRelation(
		String id
	){
		this.id = id;
	}

	

	public void setId(String id) {
		this.id = id;
	}
	

	public String getId() {
		return this.id;
	}
	

	public void setChildId(String childId) {
		this.childId = childId;
	}
	

	public String getChildId() {
		return this.childId;
	}
	

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getUserId() {
		return this.userId;
	}
	

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

	public String getParentId() {
		return this.parentId;
	}
}
