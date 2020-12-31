package Annotation.Column;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface ForeignKey {
    public String value();//TableName
}
