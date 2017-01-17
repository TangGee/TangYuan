/**
 * Created by tlinux on 17-1-17.
 */
public class TypeUtils {

    public static boolean typeIsGroup(JavaNode.Kind type){
        if (type == JavaNode.Kind.CLASS || type == JavaNode.Kind.NEW_CLASS || type == JavaNode.Kind.FIELD){
            return true;
        }

        return false;
    }
}
