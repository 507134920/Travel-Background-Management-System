package cn.tedu.ttms.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.system.entity.SysUserRole;

public interface PermisDao {
	
	List<Map<String, Object>> findPageObject(
			@Param("startIndex")int startIndex,
	        @Param("pageSize") int pageSize);
	int getRowCount();
	
	List<Map<String, Object>> findUserIdAndNames();
	List<Map<String, Object>> findRoleIdAndNames();
	
	int insertObject(SysUserRole entity);
	int delById(@Param("ids") String[] ids);
}
