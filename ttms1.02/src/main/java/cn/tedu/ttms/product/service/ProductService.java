package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Product;

public interface ProductService {
	//根据条件分页查询
	Map<String,Object> findPageObject(
	    	Integer teamId,
	    	Integer classId,
	    	Integer states,
	    	Integer pageCurrent);
	//查询所有启用的团的id和名字
	List<Map<String,Object>> findTeamIdAndNames();
	//查询所有分类信息的id和名字
	List<Map<String,Object>> findClassIdAndNames();
	 
	//状态state的修改
	void statesById(String ids,Integer states);
	
	/**保存产品信息*/
	void saveObject(Product entity);
	/**修改产品信息*/
	void updateObject(Product entity);
	/**根据id查找产品信息*/
	Product findObjectById(Integer id);
}
