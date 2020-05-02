package net.oiyou.po;

import net.oiyou.common.EasyFileType;

/**
 * 修改文件信息
 * @author HuangBoo
 */
public class EasyFileInfo {

    /**
     * 路径
     */
    private String path;
    /**
     * 版本控制类型
     */
    private EasyVersionType versionType;

    private EasyFileType fileType;

    public EasyFileInfo() {
    }

    public EasyFileInfo(String path, EasyVersionType versionType) {
        this.path = path;
        this.versionType = versionType;
    }

    public EasyFileInfo(String path, EasyVersionType versionType, EasyFileType fileType) {
        this.path = path;
        this.versionType = versionType;
        this.fileType = fileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public EasyVersionType getVersionType() {
        return versionType;
    }

    public void setVersionType(EasyVersionType versionType) {
        this.versionType = versionType;
    }

    public EasyFileType getFileType() {
        return fileType;
    }

    public void setFileType(EasyFileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static EasyFileInfo of(String path,EasyVersionType versionType){
        return new EasyFileInfo(path,versionType);
    }

    public static EasyFileInfo of(String path,EasyVersionType versionType,EasyFileType fileType){
        return new EasyFileInfo(path,versionType,fileType);
    }
}
