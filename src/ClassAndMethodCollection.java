
import com.sun.tools.javac.tree.JCTree;

import java.util.List;

/**
 * Created by tlinux on 17-1-17.
 */
public class ClassAndMethodCollection implements JavaCollectiontor {

    @Override
    public void collectorChild(List<JavaNode> children, JavaNode tree, JavaNode.Kind kind) {

        if (tree instanceof JavaNodeGroup){
            JavaNodeGroup c = (JavaNodeGroup) tree;

            for (JCTree t : c.getDefs()){
                if (t instanceof JCTree.JCMethodDecl){
                    children.add(buildNode(tree,t, JavaNode.Kind.METHOD));
                }else if (t instanceof JCTree.JCClassDecl){
                    children.add(buildNode(tree,t, JavaNode.Kind.CLASS));
                }else if (t instanceof JCTree.JCVariableDecl){
                    children.add(buildNode(tree,t,JavaNode.Kind.FIELD));
                }else if (t instanceof JCTree.JCNewClass){
                    children.add(buildNode(tree,t,JavaNode.Kind.NEW_CLASS));
                }
            }
        }


    }


    private JavaNode buildNode(JavaNode parent, JCTree node, JavaNode.Kind kind){
        if (TypeUtils.typeIsGroup(kind)){
            return new JavaNodeGroup(parent.getContext(),node,parent, kind,this);
        }
        return new JavaNode(parent.getContext(),node,parent, kind,this);
    }
}
