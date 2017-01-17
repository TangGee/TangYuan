import com.sun.tools.javac.tree.JCTree;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.util.List;

/**
 * Created by tlinux on 17-1-17.
 */
public class MyJavacVisitor implements JavacVisitor{

    private Messager messager;

    public MyJavacVisitor(Messager messager){
        this.messager = messager;
    }

    @Override
    public void visitCompilationUnit(JavaNode node, JCTree.JCCompilationUnit unit) {

    }

    @Override
    public void endVisitCompilationUnit(JavaNode node, JCTree.JCCompilationUnit unit) {

    }

    @Override
    public void visitType(JavaNode node, JCTree.JCClassDecl decl) {

    }

    @Override
    public void traverseChildren(JavacVisitor visitor, List<JavaNode> nodes) {
        if (nodes.isEmpty()) return;
        for (JavaNode node:nodes){
            node.traverse(visitor);
        }
    }

    @Override
    public void endVisitType(JavaNode node, JCTree.JCClassDecl decl) {

    }

    @Override
    public void visitField(JavaNode node, JCTree.JCVariableDecl decl) {

    }

    @Override
    public void endVisitField(JavaNode node, JCTree.JCVariableDecl variableDecl) {

    }

    @Override
    public void visitMethod(JavaNode node, JCTree.JCMethodDecl method) {
        if(method.mods.annotations == null){
            return;
        }
        for (JCTree.JCAnnotation a: method.mods.annotations){
            //TODOa
            messager.printMessage(Diagnostic.Kind.WARNING,a.toString());
        }
    }

    @Override
    public void endVisitMethod(JavaNode node, JCTree.JCMethodDecl decl) {
    }

    @Override
    public void visitInitializer(JavaNode node, JCTree.JCBlock block) {

    }

    @Override
    public void endVisitInitializer(JavaNode node, JCTree.JCBlock block) {

    }

    @Override
    public void visitMethodArgument(JavaNode node, JCTree.JCVariableDecl variable, JCTree.JCMethodDecl parentMethod) {

    }

    @Override
    public void endVisitMethodArgument(JavaNode node, JCTree.JCVariableDecl decl, JCTree.JCMethodDecl parentMethod) {

    }

    @Override
    public void visitLocal(JavaNode node, JCTree.JCVariableDecl decl) {

    }

    @Override
    public void endVisitLocal(JavaNode node, JCTree.JCVariableDecl decl) {

    }

    @Override
    public void visitStatement(JavaNode node, JCTree tree) {

    }

    @Override
    public void endVisitStatement(JavaNode node, JCTree tree) {

    }

    @Override
    public void visitAnnotationOnType(JCTree.JCClassDecl jcClassDecl, JavaNode node, JCTree.JCAnnotation annotation) {

    }

    @Override
    public void visitAnnotationOnField(JCTree.JCVariableDecl decl, JavaNode node, JCTree.JCAnnotation annotation) {

    }

    @Override
    public void visitAnnotationOnMethod(JCTree.JCMethodDecl decl, JavaNode node, JCTree.JCAnnotation annotation) {

    }

    @Override
    public void visitAnnotationOnMethodArgument(JCTree.JCVariableDecl argument, JCTree.JCMethodDecl method, JavaNode node, JCTree.JCAnnotation annotation) {

    }

    @Override
    public void visitAnnotationOnLocal(JCTree.JCVariableDecl variable, JavaNode node, JCTree.JCAnnotation annotation) {

    }
}
