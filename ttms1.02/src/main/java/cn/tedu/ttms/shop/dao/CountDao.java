package cn.tedu.ttms.shop.dao;

import java.util.List;
import java.util.Map;

public interface CountDao {

	//统计旅游产品的收藏量
	List<Map<String, Object>> queryForList();
		
	//统计已支付订单中产品的购买数量
	List<Map<String, Object>> queryForCount();
}
