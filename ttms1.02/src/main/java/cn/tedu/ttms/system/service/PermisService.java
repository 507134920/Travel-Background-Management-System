package cn.tedu.ttms.system.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.system.entity.SysUserRole;

public interface PermisService {
	
	//根据条件分页查询
	Map<String,Object> findPageObject(Integer pageCurrent);
	
	//查询所有启用的用户的id和名字
	List<Map<String,Object>> findUserIdAndNames();
	//查询所有角色的id和名字
	List<Map<String,Object>> findRoleIdAndNames();	
	
	void saveObject(SysUserRole entity);
	void delById(String ids);
}
