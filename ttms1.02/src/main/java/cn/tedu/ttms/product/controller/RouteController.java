package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.util.ToPicMsg;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Route;
import cn.tedu.ttms.product.service.RouteService;

@Controller
@RequestMapping("/route/")
public class RouteController {
	@Autowired
	private RouteService routeService;
	
	//返回路线列表页面
	@RequestMapping("listUI")
	@RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String listUI(){
		return "product/route_list";
	}
	@RequestMapping("editUI")
	@RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String editUI(){
		return "product/route_edit";
	}
	//根据路线名称分页查询
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String startState,String endState,Integer pageCurrent) {
		return new JsonResult(
				routeService.findPageObjects(startState, endState, pageCurrent) );		
	}
	
	//添加
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Route entity,HttpServletRequest request) {
		String a= ToPicMsg.toPicMsg1(entity.getBigPic());
		String b= ToPicMsg.toPicMsg2(entity.getSmallPic());
		String c= ToPicMsg.toPicMsg3(entity.getRimage());
		if (StringUtils.isEmpty(a)||StringUtils.isEmpty(b)||StringUtils.isEmpty(c)) {
			return new JsonResult("图片上传失败");
		}
		entity.setBigPic(a);
		entity.setSmallPic(b);
		entity.setRimage(c);
		routeService.saveObject(entity);
		return new JsonResult("Insert ok !");	
	}
	
	//根据id修改
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer rid) {
		Route route = 
			routeService.findObjectById(rid);
		return new JsonResult(route);	
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Route entity,HttpServletRequest request) {
		String a= ToPicMsg.toPicMsg1(entity.getBigPic());
		String b= ToPicMsg.toPicMsg2(entity.getSmallPic());
		String c= ToPicMsg.toPicMsg3(entity.getRimage());
		if (StringUtils.isEmpty(a)||StringUtils.isEmpty(b)||StringUtils.isEmpty(c)) {
			return new JsonResult("图片更新失败");
		}
		entity.setBigPic(a);
		entity.setSmallPic(b);
		entity.setRimage(c);
		routeService.updateObject(entity);
		return new JsonResult("更新OK");			
	}
	

	/**查询所有产品的id和名字*/
	@RequestMapping("doFindProIdAndNames")
	@ResponseBody
	public JsonResult doFindTeamIdAndNames(){
		List<Map<String,Object>>
			list = routeService.findProIdAndNames();
		return new JsonResult(list);
	}
	//删除
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(String rid) {
		routeService.deleteObject(rid);
		return new JsonResult("删除OK");			
	}
}
