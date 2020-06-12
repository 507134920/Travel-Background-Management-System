package cn.tedu.ttms.system.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysUserRole;
import cn.tedu.ttms.system.service.PermisService;

@Controller
@RequestMapping("/permis/")
public class SysPermisController {
	private Logger log =
			Logger.getLogger(SysPermisController.class.getSimpleName());
	@Autowired
	private PermisService permisService;
	
	@RequestMapping("permisUI")
	@RequiresRoles(value={"SysManager"},logical= Logical.OR)
	public String toPermisUI(){
		return "system/permis_list";
	}
	
	//根据条件分页查询
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer pageCurrent){
		//log.info("页面传来的参数为"+pageCurrent);
		Map<String,Object> map =
				permisService.findPageObject(pageCurrent);
		//log.info("返回数据为"+map);
		return new JsonResult(map);
	}

	//下拉框 动态显示 用户和角色
	/**查询所有启用的用户的id和名字*/
	@RequestMapping("doFindUserIdAndNames")
	@ResponseBody
	public JsonResult doFindUserIdAndNames(){
		List<Map<String,Object>>
			list = permisService.findUserIdAndNames();
		return new JsonResult(list);
	}
	/**查询所有角色的id和名字*/
	@RequestMapping("doFindRoleIdAndNames")
	@ResponseBody
	public JsonResult doFindRoleIdAndNames(){
		List<Map<String,Object>>
			list = permisService.findRoleIdAndNames();
		return new JsonResult(list);
	}
	//分配权限
	@RequestMapping("doAddObjects")
	@ResponseBody
	public JsonResult doAddObjects(SysUserRole entity) {
		//log.info("userId"+entity.getUserId());
		//log.info("roleId"+entity.getRoleId());
		permisService.saveObject(entity);
		return new JsonResult();
	}
	
	@RequestMapping("doDelObjects")
	@ResponseBody
	public JsonResult doDelObjects(String checkedIds) {
		permisService.delById(checkedIds);
		return new JsonResult();
	}

}
