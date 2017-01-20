import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by tlinux on 17-1-20.
 *
 * 优先使用上下文加载器
 */
public class ResourceLoader {

    public static URL getResource(String name){
        URL resource = null;

        try{

            if (Thread.currentThread().getContextClassLoader()!=null){
                resource  = Thread.currentThread().getContextClassLoader().getResource(name);
            }
        }catch (SecurityException e){
            //TODO log
        }

        if (resource == null){
            resource = ResourceLoader.class.getResource("/"+name);
        }

        return resource;
    }



    public static InputStream getResourceAsStream(String name){

        URL res = getResource(name);
        try {
            return res!=null?res.openStream():null;
        } catch (IOException e) {
            //TODO Log
        }

        return null;
    }
}
