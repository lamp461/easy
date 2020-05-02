package net.oiyou.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 用于文件操作
 * @author HuangBoo
 */
public class FileUtil {

	private final static Logger log = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 实现文件的拷贝
	 * @param srcPathStr 源地址
	 * @param desPathStr 目标地址
	 */
	public static void copyFile(String srcPathStr, String desPathStr) {
		try{
			File file = new File(srcPathStr);
			if(file.exists()){
				FileInputStream fis = new FileInputStream(srcPathStr);
				File desFile = new File(desPathStr);

				if(!desFile.getParentFile().exists()){
					desFile.getParentFile().mkdirs();
				}

				FileOutputStream fos = new FileOutputStream(desPathStr);
				//创建搬运工具
				byte[] data = new byte[1024*8];
				//创建长度
				int len = 0;
				//循环读取数据
				while((len = fis.read(data))!=-1){
					fos.write(data,0,len);
				}
				//3.释放资源
				fis.close();
				fis.close();
			}else{
				log.error("{}文件不存在",srcPathStr);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}