package annotation;

import java.lang.annotation.*;

/**
 * Created by tlinux on 17-1-17.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
@Inherited
public @interface CheckState {

    enum Ret{
        FINISH,
        BACK,
        RETURN
    }

    enum OKState{
        RESUMED,
        NOT_FINISH
    }
}
