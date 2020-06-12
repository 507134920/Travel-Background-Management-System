package cn.tedu.ttms.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.shop.dao.CountDao;
import cn.tedu.ttms.shop.service.CountService;

@Service
public class CountServiceImpl implements CountService{
	@Autowired
	private CountDao countDao;
	
	@Override
	public List<Map<String, Object>> queryForList() {
        return countDao.queryForList();
    }

	@Override
	public List<Map<String, Object>> queryForCount() {
		// TODO Auto-generated method stub
		return countDao.queryForCount();
	}

}
