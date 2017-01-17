import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by tlinux on 17-1-16.
 */
public class AnnotationHandler{

    private JavacProcessingEnvironment processingEnv;
    private Trees trees;
    private Messager messager;
    private java.util.List<JCTree.JCCompilationUnit> roots = new ArrayList<JCTree.JCCompilationUnit>();

    public AnnotationHandler(ProcessingEnvironment procEnv){
        processingEnv = (JavacProcessingEnvironment) procEnv;
        trees = Trees.instance(procEnv);
        messager = procEnv.getMessager();

    }


    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        for (Element elemen:roundEnv.getRootElements()) {
            TreePath path = trees.getPath(elemen);
            if (path!=null){
                JCTree.JCCompilationUnit unit = getUnitFromElement(elemen);
                if (unit ==null ) continue;
                if (!roots.contains(unit)) {
                    roots.add(unit);
                }
            }

        }
        if (roots.isEmpty()){
            return false;
        }

        List<List<JavaNode>> cleass = new ArrayList<List<JavaNode>>();

        for (JCTree.JCCompilationUnit unit : roots) {
            List<JavaNode> childNodes = new ArrayList();
            for (JCTree s : unit.defs) {
                if (s instanceof JCTree.JCClassDecl) {
                    if (s!=null){
                        childNodes.add(buildJavaClassNode(s));
                    }
                }
            }
            cleass.add(childNodes);
        }

        for (List<JavaNode> nodes:cleass){

            for (JavaNode n: nodes){
                n.traverse(new MyJavacVisitor(messager));
            }
        }

        return false;
    }


    private JavaNode buildJavaClassNode(JCTree jcTree){
        return new JavaNodeGroup(jcTree,null, JavaNode.Kind.CLASS,new ClassAndMethodCollection());
    }

    private JCTree.JCCompilationUnit getUnitFromElement(Element element){
        if (element == null) return null;
        TreePath path = trees.getPath(element);
        if(path!=null){
            return (JCTree.JCCompilationUnit) path.getCompilationUnit();
        }

        return null;
    }
}
