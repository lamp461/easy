package net.oiyou.service.impl;

import net.oiyou.common.EasyFileType;
import net.oiyou.helper.CookbookHelper;
import net.oiyou.po.EasyFileInfo;
import net.oiyou.po.EasyVersionType;
import net.oiyou.service.EasyService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.lib.Repository;

import java.util.*;

/**
 * git基本操作
 * @author HuangBoo
 */
public class EasyGitServiceImpl implements EasyService {
    private static Git git;
    private String basePath;
    private Map<EasyFileType,String> fileTypeStringMap = new HashMap<EasyFileType,String>(16){{
        put(EasyFileType.source,"");
    }};


    public EasyGitServiceImpl() {

    }

    public EasyGitServiceImpl(String basePath) {
        if(!StringUtils.isBlank(basePath)){
            String end = "/";
            if(!basePath.endsWith(end)){
                basePath += end;
            }
        }
        this.basePath = basePath;
    }

    static{
         init();
    }


    /**
     * 初始化
     */
    private static void init(){
        try{
            Repository repository = CookbookHelper.openGitCookbookRepository();
            git = new Git(repository);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取变化的文件信息
     *
     * @return 查询结果
     */
    @Override
    public List<EasyFileInfo> getEasyFiles() {
        List<EasyFileInfo> result = new ArrayList<>(16);
        try{
            Status status = git.status().call();
            //冲突的
            //Set<String> conflicting = status.getConflicting();
            //添加
            //Set<String> added = status.getAdded();
            //更改
            //Set<String> changed = status.getChanged();
            //missing
            //Set<String> missing = status.getMissing();
            //修改
            Set<String> modified = status.getModified();
            for (String temp : modified) {
                result.add(EasyFileInfo.of(basePath + temp, EasyVersionType.MODIFIED));
            }
            //移除 用于记录删除文件
            Set<String> removed = status.getRemoved();
            for (String temp : removed) {
                result.add(EasyFileInfo.of(basePath + temp, EasyVersionType.REMOVED));
            }
            //未提交
            //Set<String> uncommittedChanges = status.getUncommittedChanges();
            //未添加文件
            Set<String> untracked = status.getUntracked();
            for (String temp : untracked) {
                result.add(EasyFileInfo.of(basePath + temp, EasyVersionType.UNTRACKED));
            }
            //未添加文件夹
            //Set<String> untrackedFolders = status.getUntrackedFolders();

            buildFileType(result);

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
