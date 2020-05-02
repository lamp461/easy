package net.oiyou.temp.svn.service;

import net.oiyou.temp.svn.conf.ErrorVal;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author HuangBoo
 */
public class SvnServiceImpl extends SvnCommonImpl implements ISvnService{
    /**
     * svn账号
     */
    protected String svnAccount;
    /**
     * svn密码
     */
    protected String svnPassword;
    /**
     * svn版本库根目录
     */
    protected String svnRepoPath;
    /**
     * 日志状态
     */
    protected boolean logStatus = false;
    /**
     * 版本库服务
     */
    protected SVNRepository repository = null;
    /**
     * 身份验证器
     */
    protected ISVNAuthenticationManager authManager;
    /**
     * vn客户操作服务
     */
    protected SVNClientManager clientManager;

    /**
     *
     * @param account
     *            账号
     * @param password
     *            密码
     * @param logStatus
     *            是否开启日志状态(默认false)
     * @param repoPath
     *            svn库根目录
     *
     */
    public SvnServiceImpl(String account, String password, boolean logStatus, String repoPath) {
        this.svnAccount = account;
        this.svnPassword = password;
        this.svnRepoPath = repoPath;
        super.log = logStatus;
    }

    @Override
    public void createSvnRepository() {
        try {
            if (repository != null){
                throw new Exception(ErrorVal.SVNRepository_is_having);
            }
            // 创建库连接
            SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(this.svnRepoPath));
            super.log("创建版本库连接");
            // 身份验证
            this.authManager = SVNWCUtil.createDefaultAuthenticationManager(this.svnAccount, this.svnPassword.toCharArray());
            super.log("创建身份验证");
            // 创建身份验证管理器
            repository.setAuthenticationManager(authManager);
            this.repository = repository;
            super.log("设置版本库身份验证");
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeRepo() {
        if (repository == null){
            try {
                throw new NullPointerException(ErrorVal.SVNRepository_is_null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            repository.closeSession();
            repository = null;
            super.log("关闭版本库");
        }
    }

    @Override
    public void createSvnClientManager() {
        if (authManager == null){
            try {
                throw new NullPointerException(ErrorVal.ISVNAuthenticationManager_is_null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        clientManager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true), authManager);
        super.log("创建svn客户操作服务");
    }
}
