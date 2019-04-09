package com.netmanagerdemo.net;

/**
 * Created by hluo on 2019/4/9.
 */
public class ErrorCode {

    public static final int UNAUTHORIZED = 401;//请求用户进行身份验证
    public static final int UNREQUEST=403;//服务器理解请求客户端的请求，但是拒绝执行此请求
    /**
     * 服务器无法根据客户端的请求找到资源
     */
    public static final int UNFINDSOURCE=404;//服务器无法根据客户端的请求找到资源
    /**
     * 服务器内部错误，无法完成请求
     */
    public static final int SEVERERROR=500;//服务器内部错误，无法完成请求。
    /**
     * token失效
     */
    public static final int TOKEN_EXPRIED=4105;//token失效。
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1005;
}
