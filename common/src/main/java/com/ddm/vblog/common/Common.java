package com.ddm.vblog.common;

/**
 * @Description
 * @Date:2019/2/14 16:12
 * @Author ddm
 **/
public class Common {

    public static final Long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7L;
    public static final Long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 24L;

    public static final String ACCESS_TOKEN_NAME = "accessToken:";
    public static final String REFRE_TOKEN_NAME = "refreshToken:";

    //验证码存储时间
    public static final Long VERIFICATION_TIME = 60L;

    public static final String DEFAULT_IMAGE = "http://heshaohua.oss-cn-beijing.aliyuncs.com/u%3D1205884360%2C3885573115%26fm%3D26%26gp%3D0.jpg";

    //模块英文(首字母大写)
    public static final String ARTICLE = "Article";

    //redis模块前缀
    public static final String COMMENT_REDIS_PREFIX = "Comment::";

    public static final String ARTICLE_REDIS_PREFIX = "Article::";

}