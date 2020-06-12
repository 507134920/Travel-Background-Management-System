package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Product;


public interface ProductDao {
	
	List<Map<String, Object>> findPageObject(
	    	@Param("teamId")Integer teamId,
	    	@Param("classId")Integer classId,
	    	@Param("states")Integer states,
	    	@Param("startIndex")int startIndex,
	        @Param("pageSize") int pageSize);
	int getRowCount(
		    @Param("teamId")Integer teamId,
		    @Param("classId")Integer classId,
		    @Param("states")Integer states);

	//产品状态的修改
	int statesById(
			@Param("ids")String[] ids,
			@Param("states")Integer states);
	
	//添加按钮
	int insertObject(Product entity);
				
	//修改按钮
	int updateObject(Product entity);
	Product findObjectById(Integer id);	
}
