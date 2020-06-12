package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Product;
import cn.tedu.ttms.product.service.ProductService;

@Controller
@RequestMapping("/product/")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	private Logger log =
			Logger.getLogger(ProductController.class.getSimpleName());
	//返回项目列表页面
	@RequestMapping("listUI")
	@RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String listUI(){
		return "product/product_list";
	} 
	//返回编辑页面
	@RequestMapping("editUI")
	@RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String editUI(){
		return "product/product_edit";
	} 
	//根据条件分页查询
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer teamId,
									Integer classId,
									Integer states,
									Integer pageCurrent){
		Map<String,Object> map =
				productService.findPageObject(teamId, classId, states, pageCurrent);
	
		return new JsonResult(map);
	}

	/**查询所有启用的团目的id和名字*/
	@RequestMapping("doFindTeamIdAndNames")
	@ResponseBody
	public JsonResult doFindTeamIdAndNames(){
		List<Map<String,Object>>
			list=productService.findTeamIdAndNames();
		return new JsonResult(list);
	}
	/**查询所有分类信息的id和名字*/
	@RequestMapping("doFindClassIdAndNames")
	@ResponseBody
	public JsonResult doFindClassIdAndNames(){
		List<Map<String,Object>>
			list=productService.findClassIdAndNames();
		return new JsonResult(list);
	}
	
	//状态的修改
	@RequestMapping("doStatesById")
	@ResponseBody
	public JsonResult doStatesById(
				String checkedIds,
				Integer states){
		//1.更新状态
		productService.statesById(checkedIds, states); 
		//2.返回状态信息
		return new JsonResult(states==0?"待售OK":((states==1)?"上架OK":"下架OK"));
	}
	
	//添加按钮
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Product entity){
		productService.saveObject(entity);
		return new JsonResult();
	}
	
	/**执行修改操作 @param entity 用于封装页面团信息数据*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Product entity){
		//1.修改产品信息
		productService.updateObject(entity);
		//2.返回响应信息
		return new JsonResult();
	}
	/**根据id执行查找操作
	 * @param id 是页面上传过来的记录的id值*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		log.info("id="+id);
		Product product=
				productService.findObjectById(id);
		
		log.info("product="+product);
		return new JsonResult(product);
		
	}
}
