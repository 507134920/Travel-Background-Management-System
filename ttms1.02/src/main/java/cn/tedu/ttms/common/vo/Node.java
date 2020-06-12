package cn.tedu.ttms.common.vo;

import java.io.Serializable;
/**
 * Node
 *   为一个值对象（Value Object）
 *   用于封装树节点相关信息
 * @author 50713
 *
 */
public class Node implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8051142729366488961L;
	private Integer id;
	private Integer parentId;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
