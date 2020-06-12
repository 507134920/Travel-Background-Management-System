package cn.tedu.ttms.attachment.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.attachment.entity.Attachment;

public interface AttachmentDao {
	int insertObject(Attachment entity);
	/**根据摘要信息获取记录数*/
	int getRowCountByDigest(String fileDisgest);
	/**获得所有上传的文件信息*/
	List<Map<String, Object>> findObjects();
	/**根据id查找某个对象*/
	Attachment findObjectById(Integer id);
	
	/**查询所有项目的id以及名字*/
	List<Map<String,Object>> findPrjIdAndNames();
}
