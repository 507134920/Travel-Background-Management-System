package cn.tedu.ttms.attachment.controller;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.controller.ProjectController;

@Controller
@RequestMapping("/attachment/")
public class AttachmentController {
	private Logger log =
			Logger.getLogger(AttachmentController.class.getSimpleName());
	@Resource
	private AttachmentService attachmentService;
	
    @RequestMapping("attachmentUI")
    @RequiresRoles(value={"SysManager","ProManager"},logical= Logical.OR)
	public String attachmentUI(){
		return "attachment/attachment";
	}
    /**
     * @param title 为附件标题
     * @param mFile 用于接收上传的附件的对象
     * */
    @RequestMapping("doUpload")
    @ResponseBody
    public JsonResult doUpload(
    		String title,Integer searchBelongId,
    		MultipartFile mFile){
    	log.info("title"+title);
    	log.info("searchBelongId="+searchBelongId);
    	//原有内容是练习上传,业务要写到service
    	attachmentService.uploadObject(title,searchBelongId,mFile);
    	return new JsonResult();
    }
    @RequestMapping("doDownload")
    @ResponseBody
    public byte[] doDownload(Integer id,HttpServletResponse response)
    		throws IOException{
    	//1.根据id执行查找操作
    	Attachment a=attachmentService.findObjectById(id);
    	//2.设置下载内容类型以及响应头(固定格式)
    	response.setContentType("appliction/octet-stream");
    	String fileName=URLEncoder.encode(a.getFileName(),"utf-8");
		response.setHeader("Content-disposition",
				"attachment;filename="+fileName);
		//3.获得指定文件的路径对象(java.nio.Path)
        Path path=Paths.get(a.getFilePath());
        //4.读取path路径对应的文件,并返回字节数组
    	return Files.readAllBytes(path);
    }
    
    /**获得所有的附件信息*/
    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
    	return new JsonResult(attachmentService.findObjects());
    } 
    /**查询所有的产品的id和名字*/
	@RequestMapping("doFindPrjIdAndNames")
	@ResponseBody
	public JsonResult doFindPrjIdAndNames(){
		List<Map<String,Object>>
			list=attachmentService.findPrjIdAndNames();
		return new JsonResult(list);
	}
}
