package net.oiyou.service;

import net.oiyou.common.EasyFileType;
import net.oiyou.helper.FileUtil;
import net.oiyou.po.EasyFileInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * svn+git基本操作
 * @author HuangBoo
 */
public interface EasyService {

    /**默认使用*/
    Map<String, EasyFileType> FOLDER = new HashMap<String,EasyFileType>(){{
        put("java",EasyFileType.source);
        put("resources",EasyFileType.resources);
        put("webapp",EasyFileType.webResource);
        put("other",EasyFileType.other);
    }};

    Map<EasyFileType,String> EXPLODEDTEMPPATH = new HashMap<EasyFileType,String>(){{
        put(EasyFileType.source,"WEB-INF\\classes");
        put(EasyFileType.resources,"WEB-INF\\classes");
        put(EasyFileType.webResource,"WEB-INF\\views");
        put(EasyFileType.webStatic,"");
    }};

    /**
     * 获取变化的文件信息
     * @return 查询结果
     */
    List<EasyFileInfo> getEasyFiles();

    /**
     * 构建文件类型
     * @param fileInfos 文件信息
     */
    default void buildFileType(List<EasyFileInfo> fileInfos){

        String startPath = "src/main/";

        for (EasyFileInfo file :fileInfos) {
            if(file.getPath().startsWith(startPath)){
                //将前面路径截取 替换
                String temp = file.getPath().replace(startPath,"");
                for (Map.Entry<String,EasyFileType> entry : FOLDER.entrySet()){
                    if(temp.startsWith(entry.getKey())){
                        String javaPath = file.getPath().replace(startPath + entry.getKey(),"");
                        //对Java文件做单独处理
                        if(javaPath.endsWith(entry.getKey())){
                            String classPath = javaPath.replace(entry.getKey(),"class");
                            file.setPath(classPath);
                        }else{
                            //String classPath = javaPath.replace(entry.getKey(),"");
                            file.setPath(javaPath);
                        }
                        file.setFileType(entry.getValue());
                    }
                }
            }else{
                //其他文件
                file.setFileType(EasyFileType.other);
            }
        }
    }

    /**
     * 将修改文件打包
     * @param fileInfos 处理之后的文件信息
     * @param explodedPath 项目运行路径
     * @param endPath 拷贝文件至此路径
     * @return 其他文件未处理
     */
    default List<EasyFileInfo> packageChanged(List<EasyFileInfo> fileInfos,String explodedPath,String endPath){
        List<EasyFileInfo> others = new ArrayList<>(16);
        fileInfos.stream().filter(o->{
            if(EasyFileType.other == o.getFileType()){
                others.add(o);
                return false;
            }else{
                return true;
            }
        }).forEach(file -> {
            String srcPath = "";
            String descPath = "";
            if(file.getPath().contains("static")){
                file.setFileType(EasyFileType.webStatic);
                srcPath = explodedPath + EXPLODEDTEMPPATH.get(EasyFileType.webStatic) + file.getPath();
                descPath = endPath + EXPLODEDTEMPPATH.get(EasyFileType.webStatic) + file.getPath();
            }else{
                srcPath = explodedPath + EXPLODEDTEMPPATH.get(file.getFileType()) + file.getPath();
                descPath = endPath + EXPLODEDTEMPPATH.get(file.getFileType()) + file.getPath();
            }
            FileUtil.copyFile(srcPath,descPath);
        });

        return others;
    }
}
