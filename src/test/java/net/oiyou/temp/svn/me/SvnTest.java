package net.oiyou.temp.svn.me;

import net.oiyou.temp.svn.service.impl.SvnBaseImpl;
import net.oiyou.temp.svn.tool.StringOutputSteam;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HuangBoo
 */
public class SvnTest {
    public static void main(String[] args) throws SVNException, FileNotFoundException {

        String account = "huangboo";
        String password = "HuangBoo";
        String path = "http://127.0.0.1:9999/svn/first/";

        String targetHead = "D:/test/svn";

        SvnBaseImpl svnBase = new SvnBaseImpl(account,password, true, path);

        // 创建库连接
        SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(path));
        // 身份验证
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(account, password.toCharArray());
        // 创建身份验证管理器
        repository.setAuthenticationManager(authManager);

        /*
         * vn客户操作服务
         */
        SVNClientManager clientManager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true), authManager);


        String[] strs = new String[] { targetHead + "/first" };
        File file = new File(strs[0]);
        SVNDiffClient diffClient = clientManager.getDiffClient();
        diffClient.setIgnoreExternals(false);
        diffClient.setIgnoreExternals(false);
        StringOutputSteam os = new StringOutputSteam(new ArrayList<String>());
        List<String> temp = new ArrayList<>(16);
        //diffClient.doDiff(new File[] { file }, SVNRevision.create(1), SVNRevision.BASE, null, SVNDepth.INFINITY, true, System.out, temp);

        //diffClient.doDiff(file,SVNRevision.create(1),file,SVNRevision.WORKING,SVNDepth.INFINITY,false,System.out,temp);



        List<String> statusList = new ArrayList<>(16);
        SVNStatusClient statusClient = clientManager.getStatusClient();
        statusClient.doStatus(file, SVNRevision.HEAD, SVNDepth.INFINITY, false, false, false, false,status -> {
            System.out.println(status.getFile().getAbsolutePath());
        },statusList);

        for (String t : os.s){
            System.out.println(t);
        }
    }
}
