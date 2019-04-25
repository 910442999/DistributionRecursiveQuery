package com.yc.distribution.base.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionAnnotation {
	
	public String code();
	
	public String msg();
	/**
	 * 日志级别
	 * @return
	 */
	public String level() default LogUtil.ERROR;
	
	public boolean isThrow() default true;

}
