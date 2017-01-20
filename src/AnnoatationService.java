import com.sun.tools.javac.tree.JCTree;

/**
 * Created by tlinux on 17-1-20.
 */
public interface AnnoatationService {

    //返回是否改变了语法树
    boolean processAnnoation(JavaNode node, JavaNode.Kind kind, JCTree.JCAnnotation annotation);
}
