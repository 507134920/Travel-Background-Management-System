package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Route;

public interface RouteDao {
	/**
	 * 实现旅游路线信息的查询
	 * @param productId   所属产品
	 * @param startIndex
	 * @return
	 */
	List<Map<String, Object>> findPageObject(
				@Param("startState") String startState,
				@Param("endState") String endState,
				@Param("startIndex") int startIndex,
				@Param("pageSize") int pageSize);
	int getRowCount(@Param("startState") String startState,
					@Param("endState") String endState);
	
	//修改按钮
	int updateObject(Route entity);
	//添加按钮
	int insertObject(Route entity);
	Route findObjectById(Integer rid);
	//查询所有启用的产品的id以及名字
	List<Map<String,Object>> findProIdAndNames();
	//根据id删除
	int deleteObject(@Param("rids")String[] rids);
}
