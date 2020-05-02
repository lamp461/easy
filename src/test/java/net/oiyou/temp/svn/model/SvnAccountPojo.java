package net.oiyou.temp.svn.model;

import java.io.Serializable;

/**
 * @author HuangBoo
 */
public class SvnAccountPojo implements Serializable {
    /**
     * svn账号
     */
    private String svnAccount;
    /**
     * svn密码
     */
    private String svnPassword;

    protected SvnAccountPojo() {
    }

    public SvnAccountPojo(String svnAccount, String svnPassword) {
        this.svnAccount = svnAccount;
        this.svnPassword = svnPassword;
    }

    public String getSvnAccount() {
        return svnAccount;
    }

    public void setSvnAccount(String svnAccount) {
        this.svnAccount = svnAccount;
    }

    public String getSvnPassword() {
        return svnPassword;
    }

    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }
}
