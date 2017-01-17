import com.sun.tools.javac.tree.JCTree;

import java.util.List;

/**
 * Created by tlinux on 17-1-17.
 */
public interface JavacVisitor {


    void visitCompilationUnit(JavaNode node, JCTree.JCCompilationUnit unit);
    void endVisitCompilationUnit(JavaNode node,JCTree.JCCompilationUnit unit);
    void visitType(JavaNode node, JCTree.JCClassDecl decl);
    void traverseChildren(JavacVisitor visitor, List<JavaNode> nodes);
    void endVisitType(JavaNode node, JCTree.JCClassDecl decl);
    void visitField(JavaNode node, JCTree.JCVariableDecl decl);
    void endVisitField(JavaNode node, JCTree.JCVariableDecl variableDecl);
    void visitMethod(JavaNode node,JCTree.JCMethodDecl method);
    void endVisitMethod(JavaNode node, JCTree.JCMethodDecl decl);
    void visitInitializer(JavaNode node, JCTree.JCBlock block);
    void endVisitInitializer(JavaNode node, JCTree.JCBlock block);
    void visitMethodArgument(JavaNode node, JCTree.JCVariableDecl variable, JCTree.JCMethodDecl parentMethod);
    void endVisitMethodArgument(JavaNode node, JCTree.JCVariableDecl decl, JCTree.JCMethodDecl parentMethod);
    void visitLocal(JavaNode node, JCTree.JCVariableDecl decl);
    void endVisitLocal(JavaNode node, JCTree.JCVariableDecl decl);
    void visitStatement(JavaNode node, JCTree tree);
    void endVisitStatement(JavaNode node, JCTree tree);
    void visitAnnotationOnType(JCTree.JCClassDecl jcClassDecl, JavaNode node, JCTree.JCAnnotation annotation);
    void visitAnnotationOnField(JCTree.JCVariableDecl decl, JavaNode node, JCTree.JCAnnotation annotation);
    void visitAnnotationOnMethod(JCTree.JCMethodDecl decl, JavaNode node, JCTree.JCAnnotation annotation);
    void visitAnnotationOnMethodArgument(JCTree.JCVariableDecl argument, JCTree.JCMethodDecl method, JavaNode node, JCTree.JCAnnotation annotation);
    void visitAnnotationOnLocal(JCTree.JCVariableDecl variable, JavaNode node,JCTree.JCAnnotation  annotation);
}
