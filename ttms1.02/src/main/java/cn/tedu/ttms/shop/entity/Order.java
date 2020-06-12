package cn.tedu.ttms.shop.entity;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tedu.ttms.common.web.JsonDateTypeConvert;

public class Order implements Serializable{
	private static final long serialVersionUID = 3059943391866023434L;
	private Integer id;
	private String code;//订单编号
	private Integer productId;//所购买的产品
	private Integer num;//数量
	private float money;//总金额
	private Integer states;//订单状态  0:未支付 1已支付
	private Date createTime;//创建时间
	private Integer uid;//创建人
	private Integer rid;//线路
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", code=" + code + ", productId=" + productId + ", num=" + num + ", money=" + money
				+ ", states=" + states + ", createTime=" + createTime + ", uid=" + uid + ", rid=" + rid + "]";
	}

}
