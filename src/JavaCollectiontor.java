import com.sun.tools.javac.tree.JCTree;

import java.util.List;

/**
 * Created by tlinux on 17-1-17.
 */
public interface JavaCollectiontor {

    void collectorChild(List<JavaNode> children, JavaNode tree,JavaNode.Kind kind);
}
