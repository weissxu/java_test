package com.weiss.j2se.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface MyAnnotation {
	/*
	 * the author is weiss!!
	 */
	public int  key() default 1;
	public String value() default "jimei university";
	public Constants name() default Constants.MIKE;
}
