package net.oiyou.temp.svn.conf;

/**
 * 系统参数设置
 * @see 外部通过<b>SvnConfig.xx</b>来一个<b>SvnConfig</b>类型传入不同的配置
 * @see 外部通过<b>SvnConfig.xx.get</b>来以<b>String</b>类型得到不同的配置
 * @author HuangBoo
 */
public class SvnConfig {
    /**
     * 选择非日志模式
     */
    public static final SvnConfig NORMAL = new SvnConfig("normal");
    /**
     * 选择console日志模式
     */
    public static final SvnConfig LOG = new SvnConfig("log");
    /**
     * 数据存储类型Add
     */
    public static final SvnConfig ADD = new SvnConfig("add");
    /**
     * 数据存储类型Update
     */
    public static final SvnConfig UPDATE = new SvnConfig("update");
    /**
     * 数据存储类型Delete
     */
    public static final SvnConfig DELETE = new SvnConfig("delete");
    private String val;

    public String getVal() {
        return val;
    }

    private SvnConfig() {
    }

    private SvnConfig(String val) {
        this.val = val;

    }
}
