package me.zgy.routing;

import java.lang.annotation.*;

/**
 * Created by Rayee on 2017/9/22.
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    String value() default "";

}
