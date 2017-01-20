import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by tlinux on 17-1-20.
 */
public class Configurator {


    private static final Properties CONFIG = new Properties();

    static {
        try{
            CONFIG.load(ResourceLoader.getResourceAsStream("service.properties"));
        }catch (Exception e){
            //TODO log
        }
    }


    private Configurator(){}

    public static String getProperty(final String key){
        String property = CONFIG.getProperty(key);
        if (property == null){
            property = System.getProperty(key);
        }

        return property;
    }

    public static Map<String,String> listProperty(){
        Map<String,String> values = new LinkedHashMap<String, String>();
            for(Map.Entry<Object,Object> e:CONFIG.entrySet()){
                values.put(String.valueOf(e.getKey()),String.valueOf(e.getValue()));
            }
        return values;
    }
}
