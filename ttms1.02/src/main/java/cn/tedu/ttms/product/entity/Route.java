package cn.tedu.ttms.product.entity;

import java.io.Serializable;

public class Route implements Serializable{
	/**
	 * 
	 */
	private static long serialVersionUID = 5869234512774089924L;
	private int rid;//线路id
	private String startState;//起点
	private String endState;//终点
	private int productId;//所属产品id
	private String bigPic;//详情商品大图
	private String smallPic;//详情商品小图
	private int count;//收藏数量
	private String rimage;//缩略图
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getStartState() {
		return startState;
	}
	public void setStartState(String startState) {
		this.startState = startState;
	}
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) {
		this.endState = endState;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getBigPic() {
		return bigPic;
	}
	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getRimage() {
		return rimage;
	}
	public void setRimage(String rimage) {
		this.rimage = rimage;
	}
	@Override
	public String toString() {
		return "Route [rid=" + rid + ", startState=" + startState + ", endState=" + endState + ", productId="
				+ productId + ", bigPic=" + bigPic + ", smallPic=" + smallPic + ", count=" + count + ", rimage="
				+ rimage + "]";
	}

	
}
