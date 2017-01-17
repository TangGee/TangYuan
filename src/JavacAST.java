import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;

import javax.annotation.processing.Messager;

/**
 * Created by tlinux on 17-1-16.
 */
public class JavacAST  {


    public JavacAST(Messager messager, Context context, JCTree.JCCompilationUnit unit) {

    }

    public boolean isChanged() {
        return true;
    }
}
