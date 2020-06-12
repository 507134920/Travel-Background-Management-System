package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;
/**产品分类的持久层对象*/
public interface ProductTypeDao 
   extends BaseDao<ProductType>{
	/**查询分类信息*/
	List<Map<String,Object>>
	findObjects();
	/**添加删除的方法*/
	int deleteObject(Integer id);
	/**判定分类下是否还有子分类*/
	int hasChilds(Integer id);	
	
	//查询分类信息中的id、parentId、name
	List<Node>findZtreeNodes();
	
	Map<String,Object> findMapById(Integer id);
	
	//获得分类的id和name 在产品信息中使用
	List<Map<String,Object>> findClassIdAndNames();
}

