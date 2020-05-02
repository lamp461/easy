package net.oiyou.temp.svn.model;

/**
 * @author HuangBoo
 */
public class SvnLinkPojo extends SvnAccountPojo{
    /**
     * 库链接路径
     */
    private String repoPath;

    public SvnLinkPojo(String repoPath, String svnAccount, String svnPassword) {
        super(svnAccount, svnPassword);
        this.repoPath = repoPath;
    }

    public SvnLinkPojo(String svnAccount, String svnPassword) {
        super(svnAccount, svnPassword);
    }

    public String getRepoPath() {
        return repoPath;
    }

    public void setRepoPath(String repoPath) {
        this.repoPath = repoPath;
    }

}
