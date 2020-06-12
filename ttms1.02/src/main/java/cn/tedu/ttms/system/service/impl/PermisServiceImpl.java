package cn.tedu.ttms.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.dao.PermisDao;
import cn.tedu.ttms.system.entity.SysUserRole;
import cn.tedu.ttms.system.service.PermisService;
import sun.net.www.content.text.plain;

@Service
public class PermisServiceImpl implements PermisService {
	@Autowired
	private PermisDao permisDao;
	@Override
	public Map<String, Object> findPageObject(Integer pageCurrent) {
		int pageSize=4;
		int startIndex=(pageCurrent-1)*pageSize;
		
		int rowCount = permisDao.getRowCount();
		List<Map<String,Object>> list = 
				permisDao.findPageObject(startIndex,pageSize);
		PageObject pageObject=new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//5.封装数据(当前页记录,分页PageObject)
		Map<String,Object> map=
				new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);
		return map;
	}

	@Override
	public List<Map<String, Object>> findUserIdAndNames() {
		return permisDao.findUserIdAndNames();
	}

	@Override
	public List<Map<String, Object>> findRoleIdAndNames() {
		return permisDao.findRoleIdAndNames();
	}

	@Override
	public void saveObject(SysUserRole entity) {
		//1.对参数进行业务验证
		if(entity.getRoleId()==null )
			throw new ServiceException("请选择用户要分配的角色");
		if(entity.getUserId()==null)
			throw new ServiceException("请选择要分配角色的用户");
		//2
		int rows = permisDao.insertObject(entity);
		//3.对结果进行判定
		if(rows<1)
			throw new ServiceException("写入数据失败");
	}

	@Override
	public void delById(String ids) {
		//1.验证参数的有效性(无效一般会抛出异常)
		if(ids==null||ids.length()==0)
			throw new ServiceException("至少应该选择一条记录");
		//2.执行更新操作
		String[] idArray=ids.split(",");//[1,2,3,4]
		int rows = permisDao.delById(idArray);
		//3.验证结果(成功以后返回结果应该是>=1)
		if(rows<1)
			throw new ServiceException("修改失败");		
	}

}
