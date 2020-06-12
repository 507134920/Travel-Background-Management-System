package cn.tedu.ttms.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.shop.dao.OrderDao;
import cn.tedu.ttms.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao OrderDao;
	
	@Override
	public Map<String, Object> findObjects(Integer states, Integer pageCurrent) {
		
		//1.判定参数数据的有效性
				if(states!=null&&states!=0&&states!=1)
					throw new ServiceException("states 的值无效");
				if(pageCurrent==null||pageCurrent<=0)
					throw new ServiceException("当前页码无效");
				//2.根据pageCurrent计算startIndex
				int pageSize=4;
				int startIndex=(pageCurrent-1)*pageSize;
				//3.执行查询操作获得当前页数据.
				List<Map<String,Object>> list=
						OrderDao.findObjects(states, startIndex, pageSize);
				//4.获得记录数,计算分页相关信息并进行封装
				//4.1根据条件获得记录数
				int rowCount=OrderDao.getRowCount(states);
				//4.2将分页信息封装到PageObject
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

}
