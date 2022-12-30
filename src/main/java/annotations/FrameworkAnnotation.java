package annotations;

import report.ExtentLogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;


@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface FrameworkAnnotation {


    String [] assignAuthor() default "default authors";

    String [] assignCategory() default  "default category";




}
