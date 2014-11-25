package edu.neumont.csc280.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for linking a Controller method to its URI.
 * 
 * This framework currently only supports regular expressions.
 * 
 * @author jcummings
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UrlMapping {
	String value();
	String method() default "GET";
}
