package cn.tedu.ttms.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.tedu.ttms.common.exception.ServiceException;

public class ToPicMsg {
	
	
	public static String toPicMsg1(String picPath) {
		if (picPath==null) {
			throw new ServiceException("图片不能为空！！");
		}
		String newPathString = "img/product/size4/"+UUID.randomUUID().toString()+".jpg";
		String newPath = "C:/Users/50713/eclipse-workspace/ttms1.02/src/main/webapp/dist/"+newPathString;
		File src = new File(newPath);
		
		String newPath1 = "C:/Users/50713/IdeaProjects/trshop/src/main/webapp/"+newPathString;
		File src1 = new File(newPath1);
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
		try {
			OutputStream os = new FileOutputStream(src);
            byte[] imgData = decoder.decodeBuffer(picPath);
            os.write(imgData,0,imgData.length);
            os.flush();
            os.close();
            OutputStream os1 = new FileOutputStream(src1);
            byte[] imgData1 = decoder.decodeBuffer(picPath);
            os1.write(imgData1,0,imgData1.length);
            os1.flush();
            os1.close();
            return newPathString;
        }catch (Exception e) {
        	return null;
        }
	}

	public static String toPicMsg2(String picPath) {
		if (picPath==null) {
			throw new ServiceException("图片不能为空！！");
		}
		String newPathString = "img/product/size2/"+UUID.randomUUID().toString()+".jpg";
		String newPath = "C:/Users/50713/eclipse-workspace/ttms1.02/src/main/webapp/dist/"+newPathString;
		File src = new File(newPath);
		
		String newPath1 = "C:/Users/50713/IdeaProjects/trshop/src/main/webapp/"+newPathString;
		File src1 = new File(newPath1);
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
		try {
			OutputStream os = new FileOutputStream(src);
            byte[] imgData = decoder.decodeBuffer(picPath);
            os.write(imgData,0,imgData.length);
            os.flush();
            os.close();
            OutputStream os1 = new FileOutputStream(src1);
            byte[] imgData1 = decoder.decodeBuffer(picPath);
            os1.write(imgData1,0,imgData1.length);
            os1.flush();
            os1.close();
            return newPathString;
        }catch (Exception e) {
        	return null;
        }
		
	}

	public static String toPicMsg3(String picPath) {
		if (picPath==null) {
			throw new ServiceException("图片不能为空！！");
		}
		String newPathString = "img/product/small/"+UUID.randomUUID().toString()+".jpg";
		String newPath = "C:/Users/50713/eclipse-workspace/ttms1.02/src/main/webapp/dist/"+newPathString;
		File src = new File(newPath);
		
		String newPath1 = "C:/Users/50713/IdeaProjects/trshop/src/main/webapp/"+newPathString;
		File src1 = new File(newPath1);
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
		try {
			OutputStream os = new FileOutputStream(src);
            byte[] imgData = decoder.decodeBuffer(picPath);
            os.write(imgData,0,imgData.length);
            os.flush();
            os.close();
            OutputStream os1 = new FileOutputStream(src1);
            byte[] imgData1 = decoder.decodeBuffer(picPath);
            os1.write(imgData1,0,imgData1.length);
            os1.flush();
            os1.close();
            return newPathString;
        }catch (Exception e) {
        	return null;
        }
		
	}
	
}
