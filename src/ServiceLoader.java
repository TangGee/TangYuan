import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tlinux on 17-1-20.
 * 名没有想好是将注解信息向下传递还是根据类型区分处理器，先采用向下传递的方式
 * 如果将来有什么问题再设计成　根据注解类型找到特定的处理器处理，这样处理器的开发者需要注意
 *
 * 要确认注解是自己想要处理的！　当然在区分了注解类型的框架中，谨慎的开发者也会确认注解是否是自己想要的！
 *
 * 当然我们也偷了点懒，可以简单的处理注解处理器的信息（呵呵）
 */
public class ServiceLoader {

    private static ClassLoader sClassLoader;

    public static final Map<String,AnnoatationService> loadService()  {
        Map<String,String> mapVal = Configurator.listProperty();

        Map<String,AnnoatationService> serviceMap = new LinkedHashMap<String, AnnoatationService>();

        for (Map.Entry<String,String> entry: mapVal.entrySet()){
            if (TextUtils.isTextEmpty(entry.getKey())){
                continue;
            }

            if (!TextUtils.isTextEmpty(entry.getValue())){
                Class clazz = loadServiceClass(entry.getValue());
                if (clazz == null) continue;

                int modifiers =clazz.getModifiers();
                if( Modifier.isStatic(modifiers) || !clazz.isMemberClass() ) {
                    try{
                        AnnoatationService service = (AnnoatationService) clazz.newInstance();
                        serviceMap.put(entry.getKey(),service);
                    }catch (Exception e){
                        //TODO log
                    }
                }

            }
        }
        return serviceMap;
    }


    private static Class loadServiceClass(String className){

        ClassLoader loader = getClassLoaderByThread();

        try {
            Class clazz = loader.loadClass(className);
            if (AnnoatationService.class.isAssignableFrom(clazz))
                return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public synchronized static ClassLoader getClassLoaderByThread(){

        if (sClassLoader ==null){
            sClassLoader =  Thread.currentThread().getContextClassLoader()!=null?
                    Thread.currentThread().getContextClassLoader():ServiceLoader.class.getClassLoader();
        }
        return sClassLoader;
    }
}
