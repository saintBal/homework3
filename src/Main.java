import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SimpleClass a = new SimpleClass();
        Class c = SimpleClass.class;
        String name = c.getSimpleName();
        try {
            showAllFields(a);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Map<String, Object> map1 = showAllFields(a);
            Writer fw = new FileWriter("file.txt");
            fw.write(name + "\n");

            Iterator<Map.Entry<String, Object>> iter = map1.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Object> pair = iter.next();
                // System.out.println(pair.getKey() + " = " + pair.getValue());
                fw.write(pair.getKey() + " = " + pair.getValue() + "\n");
                fw.flush();
            }
            fw.close();
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Создан файл file.txt");
    }

    private static Map<String, Object> showAllFields(Object obj) throws IllegalAccessException {
        Class c = obj.getClass();
        Map<String, Object> map = new LinkedHashMap<>();
        for (Field field : c.getDeclaredFields()) {
            int modifiers;
            modifiers = field.getModifiers();
            if ((modifiers & Modifier.PRIVATE) != 0) {
                field.setAccessible(true);
            }
            if ((modifiers & Modifier.PUBLIC) != 0)
                if (modifiers == 0) {
                    field.setAccessible(true);
                }
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
}
