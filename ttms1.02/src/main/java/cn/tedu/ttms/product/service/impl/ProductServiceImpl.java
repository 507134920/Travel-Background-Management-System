package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProductDao;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.entity.Product;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private ProductTypeDao productTypeDao;
	
	@Override
	public Map<String,Object> findPageObject(Integer teamId, Integer classId, Integer states, Integer pageCurrent) {
		//1.判定参数数据的有效性
		if(states!=null&&states!=0&&states!=1&&states!=2)
			throw new ServiceException("states 的值无效");
		if(teamId!=null&&teamId<=0)
			throw new ServiceException("团id无效");
		if(classId!=null&&classId<=0)
			throw new ServiceException("分类id无效");
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("当前页码无效");	
		//2.根据pageCurrent计算startIndex
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;	
		//3.执行查询操作获得当前页数据.
		List<Map<String,Object>> list=
				productDao.findPageObject(teamId, classId, states, startIndex, pageSize);
		//4.获得记录数,计算分页相关信息并进行封装
			//4.1根据条件获得记录数
			int rowCount = 
					productDao.getRowCount(teamId, classId, states);
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
	/**
	  *  查询项目id和项目名称,分类的id和名称。
	  *  通过此数据，初始化页面上的select列表
	 */
	@Override
	public List<Map<String, Object>> findTeamIdAndNames() {
		// TODO Auto-generated method stub
		return teamDao.findTeamIdAndNames();
	}
	@Override
	public List<Map<String, Object>> findClassIdAndNames() {
		// TODO Auto-generated method stub
		return productTypeDao.findClassIdAndNames();
	}
	//状态state的修改
	@Override
	public void statesById(String ids, Integer states) {
		//1.验证参数的有效性(无效一般会抛出异常)
		if(ids==null||ids.length()==0)
			throw new ServiceException("至少应该选择一条记录");
		if(states!=0&states!=1&&states!=2)
			throw new ServiceException("valid状态数据无效");
		//2.执行更新操作
		String[] idArray=ids.split(",");//[1,2,3,4]
		int rows=productDao.statesById(idArray, states);
		//3.验证结果(成功以后返回结果应该是>=1)
		if(rows<1)
			throw new ServiceException("修改失败");
	}
	//添加按钮
	@Override
	public void saveObject(Product entity) {
		//1.对参数进行业务验证
		if(entity==null)
			throw new ServiceException("保存的数据不能为空");
		//2.执行保存操作
		int rows=productDao.insertObject(entity);
		//System.out.println("rows=="+rows);
		//3.对结果进行判定
		if(rows<1)
			throw new ServiceException("写入数据失败");
	}
	//修改按钮
	@Override
	public void updateObject(Product entity) {
		if(entity==null)
			throw new ServiceException("修改内容不能为空");
		int rows=productDao.updateObject(entity);
		if(rows<1)
			throw new ServiceException("修改失败");		
	}
	@Override
	public Product findObjectById(Integer id) {
		//1.判定参数的有效性
		if(id==null||id<=0)
			throw new ServiceException("id 的值无效:id="+id);
		//2.执行查找操作
		Product product=productDao.findObjectById(id);
		//3.根据结果进行判定
		if(product==null)
			throw new ServiceException("没找到对应结果");
		//4.返回结果
		return product;
	}

}
