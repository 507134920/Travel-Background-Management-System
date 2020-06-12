package cn.tedu.ttms.shop.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.shop.service.OrderService;

@Controller
@RequestMapping("/order/")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	//返回项目列表页面
	@RequestMapping("listUI")
	@RequiresRoles(value={"SysManager","SaManager"},logical= Logical.OR)
	public String listUI(){
		return "shop/order_list";
	} 
			
	//根据订单状态分页查询
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(
				Integer states,Integer pageCurrent) {	
		Map<String,Object> map=
				orderService.findObjects(states,pageCurrent);
		return new JsonResult(map);
	}

}
