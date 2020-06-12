package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.RouteDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.entity.Route;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService{
	@Autowired
	private RouteDao routeDao;
	
	@Override
	public Map<String, Object> findPageObjects(String startState, String endState, int pageCurrent) {
		int pageSize=2;
		int startIndex = (pageCurrent-1)*pageSize;
		//获取当页数据
		List<Map<String,Object>> list =
				routeDao.findPageObject(startState, endState, startIndex, pageSize);		
		//获取总记录数并封装分页信息	
		PageObject pageObject = new PageObject();
		int rowCount = routeDao.getRowCount(startState, endState);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		//3、封装当前页数据和分页信息
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);
		return map;
	}

	@Override
	public void saveObject(Route entity) {
		//传来的数据不能为空
		if(entity ==null)
			throw new ServiceException("保存的对象不能为空");
		int rows = routeDao.insertObject(entity);
		if(rows <=0) {
			throw new ServiceException("insert error");
		}
	}

	@Override
	public void updateObject(Route entity) {
		if(entity == null)
			throw new ServiceException("更新的数据不能为空");
		int rows = routeDao.updateObject(entity);
		if(rows <=0) {
			throw new ServiceException("update error");
		}
	}

	@Override
	public Route findObjectById(Integer rid) {
		if(rid==null)
			throw new ServiceException("");
		Route route = routeDao.findObjectById(rid);
		if(route == null) {
			throw new ServiceException("对象不存在");
		}
		return route;
	}

	@Override
	public List<Map<String, Object>> findProIdAndNames() {
		return routeDao.findProIdAndNames();
	}

	@Override
	public void deleteObject(String rids) {
		if(rids==null||rids.length()==0)
			throw new ServiceException("至少应该选择一条记录");
		String[] idArray=rids.split(",");//[1,2,3,4]
		int rows = routeDao.deleteObject(idArray);
		if(rows <=0) {
			throw new ServiceException("delete error");
		}
		
	}

}
