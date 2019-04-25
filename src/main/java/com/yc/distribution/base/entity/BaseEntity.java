package com.yc.distribution.base.entity;


import com.yc.distribution.base.utils.GenerateIdUtil;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 基类
 */
public class BaseEntity implements Serializable{

	private String id;// 主键ID.
	private Integer version = 0;// 版本号默认为0
	private String status;// 状态 PublicStatusEnum
	private String creater;// 创建人
	private Timestamp createTime;// 创建时间
	private String editor;// 修改人
	private Timestamp updateTime;// 修改时间
	private String remark;// 描述
	private String shareId;// 分表用主键
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void initId(){
		if(this.id==null || "".equals(this.id)){
//			this.id = StringUtil.get32UUID();
			this.id = GenerateIdUtil.generateId();
		}
	}
	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
}
