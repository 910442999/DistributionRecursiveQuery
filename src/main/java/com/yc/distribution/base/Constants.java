package com.yc.distribution.base;

/**
 * 常量
 */
public class Constants {

    //阿里云oss存储
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    public static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    public static final String accessKeyId = "LTAItxmPLPiMt8no";
    public static final String accessKeySecret = "kFEDSNbXml9bXZ2ds7efAs9qMsuXBe";

    public static final String bucketName = "guzan-image";
    public static final String key = "guzan";


    //======================状态信息=====================
    public static final String SUCCESS = "0"; //操作成功
    public static final String ERROR = "1"; //操作失败

    // 会员等级
    public static final String USER_LEVEL_CONSUMER = "0"; //消费者
    public static final String USER_LEVEL_MEMBER = "1"; //会员
    public static final String USER_LEVEL_PRIMARY_AGENT = "2"; //初级合伙人
    public static final String USER_LEVEL_HIGH_AGENT = "3"; //高级合伙人
    public static final String USER_LEVEL_COMPANY_PARTENER = "4"; //分公司合伙人
    public static final String USER_LEVEL_ADMIN = "8888"; //888管理员

    // 图片类型
    public static final String IMAGE_TYPE_SY = "01"; //首页轮播图
    public static final String IMAGE_TYPE_AL = "02"; //案例展示02
    public static final String IMAGE_TYPE_XM = "03"; //项目展示
    public static final String IMAGE_TYPE_XFZD = "04"; //消费账单
    public static final String IMAGE_TYPE_SFZZM = "05"; //身份证正面
    public static final String USER_LEVEL_SFZFM = "06"; //身份证反面
    public static final String USER_LEVEL_TX = "07"; //头像
    public static final String IMAGE_TYPE_XM_INFO_HOME = "0"; //项目主页图片
    public static final String IMAGE_TYPE_XM_INFO_PAGE= "1"; //项目详情页图片

    //图片状态
    public static final String IMAGE_STATUS_DEL = "1"; //删除图片
    public static final String IMAGE_STATUS_DISPLAY = "0"; //未删除的图片
    // 订单状态
    public static final String ORDER_STATUS_WAITE_REVIEW = "0"; //0待审核
    public static final String ORDER_STATUS_PENDING_REVIEW = "1"; //1审核中
    public static final String ORDER_STATUS_COMPLETED_REVIEW = "2"; //已完成
    public static final String ORDER_STATUS_REJECT = "4"; //驳回

    //初始密码
    public static final String GZ_PWD = "111111"; //驳回



}
