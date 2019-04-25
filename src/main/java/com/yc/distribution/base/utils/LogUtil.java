package com.yc.distribution.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LogUtil {

    public static final String DEBUG = "DEBUG";
    public static final String INFO = "INFO";
    public static final String WARN = "WARN";
    public static final String ERROR = "ERROR";
    public static final String EMPTY = "EMPTY";

    /**
     * 初始化日志
     *
     * @return
     */
    public static Logger getLogger() {
        return LoggerFactory.getLogger(LogUtil.getLogClass());
    }

    /*
     * 不抛异常的错误码，notErrorMap里面的错误码,都是只抛自定义的异常级别
     */
    public static Map<String, String> notErrorMap = new HashMap<String, String>();

    public static Map<String, String> getNotErrorMap() {
        //notErrorMap.put(ErrorCode.WERROR_REAL_NAME, LogUtil.WARN); //实名认证未通过
        return notErrorMap;
    }

    @Around(value = "@annotation(logAnn)")
    public Object exceptionHander(ProceedingJoinPoint p, ExceptionAnnotation logAnn) throws TranFailException {
        Object o = null;
        String errorCode = logAnn.code();
        String errorMsg = logAnn.msg();
        String level = logAnn.level();
        boolean throw1 = logAnn.isThrow();
        try {
            o = p.proceed();
        } catch (Throwable e) {

            TranFailException handerEx = handerEx(p.getTarget().getClass().getSimpleName(), p.getSignature().getName(), errorCode, errorMsg, level, e);
            if (throw1) {
                throw handerEx;
            }
        }
        return o;
    }

//	public static void writeLog(Object msg, String level) {
//		Logger logger = LogUtil.getLogger();
//
//		if (DEBUG.equals(level)) {
//			logger.debug(LogUtil.getMsg(msg));
//		} else if (INFO.equals(level)) {
//			logger.info(LogUtil.getMsg(msg));
//		} else if (WARN.equals(level)) {
//			logger.warn(LogUtil.getMsg(msg));
//		} else if (ERROR.equals(level)) {
//			logger.error(LogUtil.getMsg(msg));
//		} else {
//			logger.error("");
//		}
//	}

    public static void info(Object msg) {
        Logger logger = LogUtil.getLogger();
        logger.info(LogUtil.getMsg(msg));
    }

    public static void info(Object msg, Object o1) {
        Logger logger = LogUtil.getLogger();
        logger.info(LogUtil.getMsg(msg), o1);
    }

    public static void info(Object msg, Object o1, Object o2) {
        Logger logger = LogUtil.getLogger();
        logger.info(LogUtil.getMsg(msg), o1, o2);
    }

    public static void info(Object msg, Object[] obj) {
        Logger logger = LogUtil.getLogger();
        logger.info(LogUtil.getMsg(msg), obj);
    }

    public static void debug(Object msg) {
        Logger logger = LogUtil.getLogger();
        logger.debug(LogUtil.getMsg(msg));
    }

    public static void debug(Object msg, Object o) {
        Logger logger = LogUtil.getLogger();
        logger.debug(LogUtil.getMsg(msg), o);
    }

    public static void debug(Object msg, Object o1, Object o2) {
        Logger logger = LogUtil.getLogger();
        logger.debug(LogUtil.getMsg(msg), o1, o2);
    }

    public static void debug(Object msg, Object[] obj) {
        Logger logger = LogUtil.getLogger();
        logger.debug(LogUtil.getMsg(msg), obj);
    }

    public static void warn(Object msg) {
        Logger logger = LogUtil.getLogger();
        logger.warn(LogUtil.getMsg(msg));
    }

    public static void warn(Object msg, Object o) {
        Logger logger = LogUtil.getLogger();
        logger.warn(LogUtil.getMsg(msg), o);
    }

    public static void warn(Object msg, Object o1, Object o2) {
        Logger logger = LogUtil.getLogger();
        logger.warn(LogUtil.getMsg(msg), o1, o2);
    }

    public static void warn(Object msg, Object[] obj) {
        Logger logger = LogUtil.getLogger();
        logger.warn(LogUtil.getMsg(msg), obj);
    }

    public static void error(Object msg) {
        Logger logger = LogUtil.getLogger();
        logger.error(LogUtil.getMsg(msg));// 并追加方法名称
    }

    public static void error(Object msg, Object o) {
        Logger logger = LogUtil.getLogger();
        logger.error(LogUtil.getMsg(msg), o);
    }

    public static void error(Object msg, Object o1, Object o2) {
        Logger logger = LogUtil.getLogger();
        logger.error(LogUtil.getMsg(msg), o1, o2);
    }

    public static void error(Object msg, Object[] obj) {
        Logger logger = LogUtil.getLogger();
        logger.error(LogUtil.getMsg(msg), obj);
    }

    public static void error(Object msg, Throwable ex) {
        String control_name = MDC.get("control_name");
        if (ex instanceof TranFailException) {
            TranFailException tfe = (TranFailException) ex;
            String ret_code = handerErrorCode(tfe.getErrorCode());
            MDC.put("control_name", ret_code);
        }
        Logger logger = LogUtil.getLogger();
        logger.error(LogUtil.getMsg(msg), ex);
        MDC.put("control_name", control_name);
    }

    public static String getMsg(Object msg, Throwable ex) {
        String str = "";

        if (msg != null) {
            str = LogUtil.getLogMethod() + "[" + msg.toString() + "]";
        } else {
            str = LogUtil.getLogMethod() + "[null]";
        }
        if (ex != null) {
            str += "[" + ex.getMessage() + "]";
        }

        return str;
    }

    public static String getMsg(Object msg) {
        return LogUtil.getMsg(msg, null);
    }

    /**
     * 得到调用类名称
     *
     * @return
     */
    private static String getLogClass() {
        String str = "";

        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > 3) {
            StackTraceElement ste = stack[3];
            str = ste.getClassName();// 类名称
        }

        return str;
    }

    /**
     * 得到调用方法名称
     *
     * @return
     */
    private static String getLogMethod() {
        String str = "";

        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > 4) {
            StackTraceElement ste = stack[4];
            str = "Method[" + ste.getMethodName() + ":" + ste.getLineNumber() + "]";// 方法名称
        }

        return str;
    }

    public static void error(Throwable e, String errorCode, String errorMsg, boolean ifLog) throws TranFailException {

        TranFailException tfe = null;
        String errorCode_ = "";
        String errorMsg_ = "";
        if (e instanceof TranFailException) {
            tfe = (TranFailException) e;
            errorCode_ = tfe.errorCode;
            errorMsg_ = tfe.errorMsg;
        } else {
            errorCode_ = errorCode;
            errorMsg_ = errorMsg;
            tfe = new TranFailException(errorCode, errorMsg);
        }
        String log = "错误码:" + errorCode_ + ",错误信息:" + errorMsg_;
        if (ifLog) {
            Logger logger = LogUtil.getLogger();
            logger.error(LogUtil.getMsg(log), e);
        }
        throw tfe;
    }

    public static TranFailException handerEx(String code, String msg) {

        return handerEx(code, msg, EMPTY, null);

    }

    public static TranFailException handerEx(String code, String msg, Throwable e) {

        return handerEx(code, msg, EMPTY, e);

    }

    public static TranFailException handerEx(String code, String msg, String level) {

        return handerEx(code, msg, level, null);

    }

    public static TranFailException handerEx(String code, String msg, String level, Throwable e) {


        return handerEx(getLogClass(), getLogMethod(), code, msg, level, e);

    }


    public static TranFailException handerEx(String code, String msg, String level, Map<String, Object> codeMap, Throwable e) {

        return handerEx(getLogClass(), getLogMethod(), code, msg, level, e);

    }

    /**
     * @param name   log的类名
     * @param method log的方法名
     * @param code   错误码
     * @param msg    错误信息
     * @param level  日志级别
     * @param e
     * @return
     */
    public static TranFailException handerEx(String name, String method, String code, String msg, String level, Throwable e) {

        return handerEx(name, method, code, msg, level, null, LogUtil.getNotErrorMap(), e);
    }

    private static void printLog(String name, String level, String log_, TranFailException tfe) {

        Logger logger = LoggerFactory.getLogger(name);
        String control_name = MDC.get("control_name");
        if (tfe!=null) {
            String ret_code = handerErrorCode(tfe.getErrorCode());
            MDC.put("control_name", ret_code);
        }
        if (DEBUG.equals(level)) {
            logger.debug(log_, tfe);
        } else if (INFO.equals(level)) {
            logger.info(log_, tfe);
        } else if (WARN.equals(level)) {
            logger.warn(log_, tfe);
        } else if (ERROR.equals(level)) {
            logger.error(log_, tfe);
        } else if (EMPTY.equals(level)) {
            logger.info(log_, tfe);
        } else {
            logger.info(log_, tfe);
        }
        MDC.put("control_name", control_name);
    }

    /**
     * @param name    log的类名
     * @param method  log的方法名
     * @param code    错误码
     * @param msg     错误信息
     * @param codeMap 在map中的错误码日志根据级别显示
     * @param level   日志级别
     * @param e
     * @return
     */
    public static TranFailException handerEx(String name, String method, String code, String msg, String level, Map<String, Object> codeMap, Map<String, String> notErrorMap, Throwable e) {
        HanderExVo handerExVo = new HanderExVo(method, code, msg, e).invoke();
        String errorCode_ = handerExVo.getErrorCode_();
        String log_ = handerExVo.getLog_();
        TranFailException tfe = handerExVo.getTfe();
        /*
		 * 如果错误码在定义map中，根据map中的错误码对应的值打印日志级别
		 */
        if (codeMap != null) {
            String logLevel = codeMap.get(errorCode_) == null ? "" : codeMap.get(errorCode_).toString();
            level = logLevel;
        }

		/*如果错误码维护进notErrorMap中，错误级别由notErrorMap里面的value定【错误码作为key，value作为日志级别】
		 *
		 */
        if (notErrorMap != null) {
            if (!(notErrorMap.get(errorCode_) == null || "".equals(notErrorMap.get(errorCode_)))) {
                level = notErrorMap.get(errorCode_);
            }
        }

        printLog(name, level, log_, tfe);


        return tfe;
    }

    /**
     * @param name
     * @param method
     * @param code
     * @param msg
     * @param level
     * @param codeMap
     * @param e
     * @return
     */
    public static TranFailException handerEx(String name, String method, String code, String msg, String level, Map<String, Object> codeMap, Throwable e) {

        return handerEx(name, method, code, msg, level, codeMap, null, e);
    }

    public static String handerErrorCode(String code) {
        if (StringUtils.isBlank(code)) {
            return code;
        }
        return code.replaceAll("错误码:", "");
    }

    private static class HanderExVo {
        private String method;
        private String code;
        private String msg;
        private Throwable e;
        private TranFailException tfe;
        private String errorCode_;
        private String log_;

        public HanderExVo(String method, String code, String msg, Throwable e) {
            this.method = method;
            this.code = code;
            this.msg = msg;
            this.e = e;
        }

        public TranFailException getTfe() {
            return tfe;
        }

        public String getErrorCode_() {
            return errorCode_;
        }

        public String getLog_() {
            return log_;
        }

        public HanderExVo invoke() {
            tfe = null;
            errorCode_ = "";
            String errorMsg_ = "";
            if (e == null) {
                errorCode_ = code;
                errorMsg_ = msg;
                tfe = new TranFailException(errorCode_, errorMsg_);
            } else if (e instanceof TranFailException) {
                tfe = (TranFailException) e;
                errorCode_ = tfe.errorCode;
                errorMsg_ = msg + "-->" + tfe.errorMsg + "-->" + tfe.getMessage();
            } else {
                errorCode_ = code;
                errorMsg_ = msg + "-->" + e.getMessage();
                tfe = new TranFailException(errorCode_, errorMsg_, e);
            }

            log_ = "Method[" + method + "]" + "错误码:" + errorCode_ + ",错误信息:" + errorMsg_;
            return this;
        }
    }
}
