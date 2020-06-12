package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tedu.ttms.common.web.JsonDateTypeConvert;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3684210260897012467L;
	
	private Integer id;
	private String code;//产品编号
	private String name;//产品名称
	private Integer teamId;//团号Id
	private String exText;//特殊提示
	private Date onlineDate;//上架时间
	private Date offlineDate;//下架时间
	private Integer quantity ;//预售数量
	private Integer minQty;//最低数量
	private Integer soldQty;//已售数量
	private float price;//价格
	private Integer classId;//产品分类编号
	private Integer nights;//晚数
	private Integer states;//产品状态  0：待售  1：上架   2：下架,
	private String note;//备注
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getExText() {
		return exText;
	}
	public void setExText(String exText) {
		this.exText = exText;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getOfflineDate() {
		return offlineDate;
	}
	
	public void setOfflineDate(Date offlineDate) {
		this.offlineDate = offlineDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getMinQty() {
		return minQty;
	}
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	public Integer getSoldQty() {
		return soldQty;
	}
	public void setSoldQty(Integer soldQty) {
		this.soldQty = soldQty;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getNights() {
		return nights;
	}
	public void setNights(Integer nights) {
		this.nights = nights;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", teamId=" + teamId + ", exText=" + exText
				+ ", onlineDate=" + onlineDate + ", offlineDate=" + offlineDate + ", quantity=" + quantity + ", minQty="
				+ minQty + ", soldQty=" + soldQty + ", price=" + price + ", classId=" + classId + ", nights=" + nights
				+ ", states=" + states + ", note=" + note + ", createdUser=" + createdUser + ", createdTime="
				+ createdTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
	

}
