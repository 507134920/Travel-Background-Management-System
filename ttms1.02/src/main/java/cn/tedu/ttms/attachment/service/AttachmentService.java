package cn.tedu.ttms.attachment.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;


public interface AttachmentService {
    /**实现文件上传*/
	public void uploadObject(String title,Integer belongId,
			MultipartFile mFile);
	public Map<String, Object> findObjects();
	public Attachment findObjectById(Integer id);
	
	List<Map<String,Object>> findPrjIdAndNames();
}
