import com.sun.tools.javac.tree.JCTree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tlinux on 17-1-17.
 */
public class JavaNodeGroup extends JavaNode {

    private List<JavaNode> children;


    public JavaNodeGroup(Context context,JCTree node, JavaNode parent, Kind kind, JavaCollectiontor collectiontor) {
        super(context,node, parent, kind, collectiontor);

        //校验类型　没有全写
        if (!TypeUtils.typeIsGroup(kind)){
            throw new IllegalArgumentException("group must is tyep: "+ Kind.CLASS+"  ,"+Kind.NEW_CLASS);
        }
        this.children = new ArrayList<JavaNode>();
    }


    public void traverse(JavacVisitor visitor){
        switch (getKind()){
            case CLASS:
                getCollectiontor().collectorChild(children,this,getKind());
                visitor.traverseChildren(visitor,children);
                break;
            case FIELD:
                visitor.visitField(this, (JCTree.JCVariableDecl) get());
                getCollectiontor().collectorChild(children,this,getKind());
                visitor.traverseChildren(visitor,children);
                break;
            case NEW_CLASS:
                getCollectiontor().collectorChild(children,this,getKind());
                visitor.traverseChildren(visitor,children);
                break;

        }
    }

    public List<JCTree> getDefs(){
        JCTree jcTree = get();
        if (jcTree==null) return Collections.EMPTY_LIST;

        if (jcTree instanceof JCTree.JCVariableDecl){
            if (((JCTree.JCVariableDecl) jcTree).init!=null ){
                List<JCTree> jcTrees = new ArrayList<JCTree>(1);
                jcTrees.add(((JCTree.JCVariableDecl) jcTree).init);
                return jcTrees;
            }else{
                return Collections.EMPTY_LIST;
            }
        }else if (jcTree instanceof JCTree.JCNewClass){
            if (((JCTree.JCNewClass) jcTree).def!=null){
                List<JCTree> jcTrees = new ArrayList<JCTree>(1);
                jcTrees.add(((JCTree.JCNewClass) jcTree).def);
                return jcTrees;
            }else{
                return Collections.EMPTY_LIST;
            }
        } else{
            try {
                Field defs = jcTree.getClass().getDeclaredField("defs");
                defs.setAccessible(true);
                return (List<JCTree>) defs.get(jcTree);
            } catch (Exception e) {
                return Collections.EMPTY_LIST;
            }
        }
    }
}
