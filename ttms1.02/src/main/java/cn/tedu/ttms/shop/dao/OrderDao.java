package cn.tedu.ttms.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderDao {
	 List<Map<String,Object>> findObjects(
				@Param("states")Integer states,
		    	@Param("startIndex")int startIndex,
		        @Param("pageSize") int pageSize);
	 
	 int getRowCount(@Param("states")Integer states);
		 
}
