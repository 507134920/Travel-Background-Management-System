package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Route;

public interface RouteService {
	
	Map<String,Object> findPageObjects(
			String startState,String endState,int pageCurrent);	
	//添加
	void saveObject(Route entity);
	//修改
	void updateObject(Route entity);
	Route findObjectById(Integer rid);	
	//查询所有产品的id和名字
	List<Map<String,Object>> findProIdAndNames();
	void deleteObject(String ids);
}
