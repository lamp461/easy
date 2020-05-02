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
	 * @param srcPathStr 源地址(class)
	 * @param desPathStr 目标地址
	 */
	public static void copyFile(String srcPathStr, String desPathStr) {

		String t = "/",p = ".";

		String fileSeparatorReg = File.separator.equals(t) ? File.separator : File.separator + File.separator;
		// 特殊字符替换 格式化
		srcPathStr = srcPathStr.replaceAll("\\\\", fileSeparatorReg).replaceAll("/", fileSeparatorReg);
		desPathStr = desPathStr.replaceAll("\\\\", fileSeparatorReg).replaceAll("/", fileSeparatorReg);

		try{
			File file = new File(srcPathStr);
			if(file.exists()){
				//取出文件名 同时获取当前类的内部类
				String fileName = file.getName().substring(0,file.getName().lastIndexOf(p));
				System.out.println(fileName);
				File srcFileFolder = file.getParentFile();
				File[] files = srcFileFolder.listFiles();
				if(files != null){
					for (File f : files) {
						if(f.getName().contains(fileName)){
							FileInputStream fis = new FileInputStream(f);

							//获取目标文件文件名
							String desPathStrTemp = desPathStr.substring(0,desPathStr.lastIndexOf(File.separator)+1) + f.getName();

							File desFile = new File(desPathStrTemp);
							if(!desFile.getParentFile().exists()){
								desFile.getParentFile().mkdirs();
							}
							FileOutputStream fos = new FileOutputStream(desFile);
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
							fos.close();
						}
					}
				}
			}else{
				log.error("{}文件不存在",srcPathStr);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}