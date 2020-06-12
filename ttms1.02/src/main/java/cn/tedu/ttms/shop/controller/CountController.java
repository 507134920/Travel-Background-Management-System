package cn.tedu.ttms.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.shop.service.CountService;

@Controller

@RequestMapping("/count/")
public class CountController {
	private Logger log =
			Logger.getLogger(CountController.class.getSimpleName());
	@Autowired
	private CountService countService;

	//统计旅游产品的收藏量
	@RequestMapping("toCount")
	@RequiresRoles(value={"SysManager","SaManager"},logical= Logical.OR)
    public String toIndex() {
        //ModelAndView mav = new ModelAndView("index");
        //return "shop/count";
        return "shop/pie";
    }
	//统计已支付订单中产品的购买数量
    @RequestMapping("toCountNum")
    public String toIndexNum() {
        //ModelAndView mav = new ModelAndView("index");
        return "shop/count_num";
    }
 
    @RequestMapping("queryForList")
    @ResponseBody 
    public List<Map<String, Object>> queryForList() {
    	System.out.println("----执行过");
        return countService.queryForList();
    }
    @RequestMapping("queryForCount")
    @ResponseBody 
    public List<Map<String, Object>> queryForCount() {
    	System.out.println("----执行过");
        return countService.queryForCount();
    }
    
    @RequestMapping("queryPie")
    @ResponseBody 
    public Map<String, Object> queryForPie() {
    	List<Map<String, Object>> list = countService.queryForList();
    	List name = new ArrayList();
    	List count = new ArrayList();
    	//log.info("数据wei "+list.get(0));
    	for (int i = 0; i < list.size(); i++) {
			name.add(list.get(i).get("name"));
			count.add(list.get(i).get("count"));
		}
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	jsonMap.put("label",name.toArray());
		jsonMap.put("value",count.toArray());
        return jsonMap;
    }
 
}
