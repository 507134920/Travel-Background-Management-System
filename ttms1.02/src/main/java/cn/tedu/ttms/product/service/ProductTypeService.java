package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	List<Map<String, Object>> findObjects();
	/**删除指定的分类信息
	 * @param id 为分类id
	 */
	void deleteObject(Integer id);
	/**查询分类节点信息,在client端以zTree形式展示*/
	List<Node>findZtreeNodes();
	/**保存产品类型信息*/
	void saveObject(ProductType entity);
	
	/**根据id执行更新 操作*/
	Map<String,Object> findMapById(Integer id);
	void updateObject(ProductType entity);
}






