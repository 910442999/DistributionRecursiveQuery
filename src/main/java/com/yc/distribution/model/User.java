package com.yc.distribution.model;

import com.yc.distribution.base.entity.BaseEntity;

import java.math.BigDecimal;

public class User extends BaseEntity {



	private String id;//id


	private String userLevel;//用户等级 0消费者，1会员，2，初级合伙人 3，高级合伙人，4,分公司合伙人


	private String parentId;//上级id


	private BigDecimal extractMoney;//累计提现


	private BigDecimal surplusMoney;//待提现


	private BigDecimal incomeMoney;//累计收入
	public User(){
	}

	public User(
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
	

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	

	public String getUserLevel() {
		return this.userLevel;
	}
	

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

	public String getParentId() {
		return this.parentId;
	}
	

	public void setExtractMoney(BigDecimal extractMoney) {
		this.extractMoney = extractMoney;
	}
	

	public BigDecimal getExtractMoney() {
		return this.extractMoney;
	}
	

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}
	

	public BigDecimal getSurplusMoney() {
		return this.surplusMoney;
	}
	

	public void setIncomeMoney(BigDecimal incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	

	public BigDecimal getIncomeMoney() {
		return this.incomeMoney;
	}
}
