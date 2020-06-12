package cn.tedu.ttms.shop.service;

import java.util.List;
import java.util.Map;

public interface CountService {
	List<Map<String, Object>> queryForList();
	List<Map<String, Object>> queryForCount();
}
