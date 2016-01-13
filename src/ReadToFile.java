import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ReadToFile {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Class<?> cl = Class.forName("SimpleClass");
        Object obj = null;
        try {
            obj = cl.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        SimpleClass sc = (SimpleClass) obj;

        //  File file = new File("file.txt");
          /*  try (Scanner scanner = new Scanner(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF-8"))) {*/
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException | NoSuchElementException e) {
            System.out.println("Сначала создайте файл file.txt\nЗапустите класс Main");
        }
    }
}