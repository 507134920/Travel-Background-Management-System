package cn.tedu.ttms.product.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;
@RequestMapping("/type/")
@Controller
public class ProductTypeController {
	@Resource  
	private ProductTypeService productTypeService;
	
	@RequestMapping("listUI")
	@RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String listUI() {
		return "product/type_list";
	}
	@RequestMapping("editUI")
	@RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String edit() {
		return "product/type_edit";
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		
		return new JsonResult(
				productTypeService.findObjects() );
	}
	/**执行删除动作
	 * @param id 为页面上选中的某条记录的id值
	 */
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		productTypeService.deleteObject(id);
		return new JsonResult("删除OK");
		//this.state=1
		//this.message=ok
	}
	@RequestMapping("doFindZtreeNodes")
	@ResponseBody
	public JsonResult doFindZtreeNodes() {
		return new JsonResult(
				productTypeService.findZtreeNodes());
		
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			ProductType entity){
		productTypeService.saveObject(entity);
		return new JsonResult("保存ok！！");
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String,Object> type=
				productTypeService.findMapById(id);
		return new JsonResult(type);
	}
	/**执行更新操作*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			ProductType entity){
		System.out.println("Controller.id="+entity.getId());
		productTypeService.updateObject(entity);
		return new JsonResult();
	}
}

