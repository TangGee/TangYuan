import com.sun.tools.javac.tree.JCTree;

/**
 * Created by tlinux on 17-1-20.
 * TODO messager
 */
public class AndroidCallBackStatusHander implements AnnoatationService {

    @Override
    public boolean processAnnoation(JavaNode node, JavaNode.Kind kind, JCTree.JCAnnotation annotation) {

        StringBuilder builder = new StringBuilder();
        for (JCTree.JCExpression e:annotation.args){
            builder.append(e.toString()).append(",");
        }

        return false;
    }
}
