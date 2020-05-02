package net.oiyou.temp.svn.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuangBoo
 */
public class SvnRepoPojo implements Serializable {

    /**
     * 提交信息
     */
    private String commitMessage;
    /**
     * 提交日期
     */
    private Date date;
    /**
     * 提交方式 dir目录 file文件 none空 unknown 未知
     */
    private String kind;
    /**
     * 目录名
     */
    private String name;
    /**
     * 资源库路径
     */
    private String repositoryRoot;
    /**
     * 提交的svn版本号
     */
    private long revision;
    /**
     * 提交的文件数
     */
    private long size;
    /**
     * 更变的目录地址
     */
    private String url;
    /**
     * 作者
     */
    private String author;
    /**
     * 状态
     */
    private String state;


    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepositoryRoot() {
        return repositoryRoot;
    }

    public void setRepositoryRoot(String repositoryRoot) {
        this.repositoryRoot = repositoryRoot;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
