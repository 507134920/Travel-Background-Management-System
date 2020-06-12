package cn.tedu.ttms.shop.service;

import java.util.Map;

public interface OrderService {
	 /**根据条件查询当前页数据*/
	 Map<String,Object>findObjects(
			   Integer states,
			   Integer pageCurrent);
}
