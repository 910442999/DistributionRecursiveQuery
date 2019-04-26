package com.yc.distribution.base.entity;


import com.yc.distribution.base.utils.GenerateIdUtil;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 基类
 */
public class BaseEntity implements Serializable{

	private String id;// 主键ID.
	private String creater;// 创建人
	private Timestamp createTime;// 创建时间
	private String editor;// 修改人
	private Timestamp updateTime;// 修改时间


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}


	public void initId(){
		if(this.id==null || "".equals(this.id)){
//			this.id = StringUtil.get32UUID();
			this.id = GenerateIdUtil.generateId();
		}
	}
}
