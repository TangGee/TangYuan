import com.sun.tools.javac.processing.JavacProcessingEnvironment;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;

/**
 * Created by tlinux on 17-1-20.
 * 希望在上下文对象里处理　Java 和 eclipse 编译器的不同,提供一致性接口，不过我还没看eclipse　jdt的实现
 * 希望如我所愿
 */
public class Context {

    private ProcessingEnvironment processingEnv;
    private Messager messager;

    public Context(JavacProcessingEnvironment processingEnv){
        this.processingEnv = processingEnv;
        this.messager = processingEnv.getMessager();
    }

    public Messager getMessager() {
        return messager;
    }
}
