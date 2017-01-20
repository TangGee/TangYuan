import com.sun.source.tree.Tree;
import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tlinux on 17-1-17.
 * TODO add messager
 */
public class JavaNode {

    protected boolean change;

    public enum Kind {
        COMPILATION_UNIT,NEW_CLASS,CLASS, TYPE, FIELD, INITIALIZER, METHOD, ANNOTATION, ARGUMENT, LOCAL, STATEMENT;
    }


    private JCTree tree;
    private Kind kind;
    private JavaNode parent;
    protected Context context;

    //收集当前节点下子的关心的节点
    private JavaCollectiontor collectiontor;

    public JavaCollectiontor getCollectiontor() {
        return collectiontor;
    }

    public JavaNode(Context context,JCTree node, JavaNode parent, Kind kind, JavaCollectiontor collectiontor) {
        this.tree = node;
        this.kind = kind;
        this.parent = parent;
        this.collectiontor = collectiontor;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public JCTree get() {
        return tree;
    }

    public Kind getKind() {
        return kind;
    }

    public JavaNode getParent() {
        return parent;
    }

    public void traverse(JavacVisitor visitor){
        switch (getKind()){
            case METHOD:
                visitor.visitMethod(this, (JCTree.JCMethodDecl) get());
                break;
        }
    }

//    public void traverse(JavacVisitor visitor) {
//
//
//        switch (this.getKind()) {
//            case Kind.COMPILATION_UNIT:
//                visitor.visitCompilationUnit(this, (JCTree.JCCompilationUnit) get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitCompilationUnit(this, (JCTree.JCCompilationUnit) get());
//                break;
//            case Kind.TYPE:
//                visitor.visitType(this, (JCTree.JCClassDecl) get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitType(this, (JCTree.JCClassDecl) get());
//                break;
//            case Kind.FIELD:
//                visitor.visitField(this, (JCTree.JCVariableDecl) get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitField(this, (JCTree.JCVariableDecl) get());
//                break;
//            case Kind.METHOD:
//                visitor.visitMethod(this, (JCTree.JCMethodDecl) get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitMethod(this, (JCTree.JCMethodDecl) get());
//                break;
//            case Kind.INITIALIZER:
//                visitor.visitInitializer(this, (JCTree.JCBlock) get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitInitializer(this, (JCTree.JCBlock) get());
//                break;
//            case Kind.ARGUMENT:
//                JCTree.JCMethodDecl parentMethod = (JCTree.JCMethodDecl) up().get();
//                visitor.visitMethodArgument(this, (JCTree.JCVariableDecl) get(), parentMethod);
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitMethodArgument(this, (JCTree.JCVariableDecl) get(), parentMethod);
//                break;
//            case Kind.LOCAL:
//                visitor.visitLocal(this, (JCTree.JCVariableDecl) get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitLocal(this, (JCTree.JCVariableDecl) get());
//                break;
//            case Kind.STATEMENT:
//                visitor.visitStatement(this, get());
//                visitor.traverseChildren(visitor, this);
//                visitor.endVisitStatement(this, get());
//                break;
//            case ANNOTATION:
//                switch (up().getKind()) {
//                    case Kind.TYPE:
//                        visitor.visitAnnotationOnType((JCTree.JCClassDecl) up().get(), this, (JCTree.JCAnnotation) get());
//                        break;
//                    case Kind.FIELD:
//                        visitor.visitAnnotationOnField((JCTree.JCVariableDecl) up().get(), this, (JCTree.JCAnnotation) get());
//                        break;
//                    case Kind.METHOD:
//                        visitor.visitAnnotationOnMethod((JCTree.JCMethodDecl) up().get(), this, (JCAnnotation) get());
//                        break;
//                    case Kind.ARGUMENT:
//                        JCTree.JCVariableDecl argument = (JCTree.JCVariableDecl) up().get();
//                        JCTree.JCMethodDecl method = (JCTree.JCMethodDecl) up().up().get();
//                        visitor.visitAnnotationOnMethodArgument(argument, method, this, (JCAnnotation) get());
//                        break;
//                    case Kind.LOCAL:
//                        visitor.visitAnnotationOnLocal((JCTree.JCVariableDecl) up().get(), this, (JCAnnotation) get());
//                        break;
//                    default:
//                        throw new AssertionError("Annotion not expected as child of a " + up().getKind());
//                }
//                break;
//            default:
//                throw new AssertionError("Unexpected kind during node traversal: " + getKind());
//        }
//    }
}
