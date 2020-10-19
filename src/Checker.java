import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Checker {
    private ArrayList<File> files = new ArrayList<>();
    private String separator = "\\|";


    void addToFiles(String path) {
        File file = new File(path);
        files.add(file);
    }

    void start() {
        System.out.println("English checker 1.0 by Pavel Novikov");
        Map<String, String> tasks = new HashMap<>();
        for (File file : files) {
            try {
                tasks.putAll(getCombinations(file));
            } catch (Exception e) {
                System.out.println("Ошибка чтения файла: " + file.getPath());
            }
        }
        int errors = 0;
        Scanner scanner = new Scanner(System.in);
        for (String task : tasks.keySet()) {
            System.out.println(task);
            String answer = scanner.nextLine();
            answer = answer.replaceAll(" ", "");
            if (answer.equals(tasks.get(task))) {
                System.out.println("Верно!");
            } else {
                errors++;
                System.out.println("Ваш ответ: " + answer);
                System.out.println("Правильный ответ: " + tasks.get(task));
            }
        }

        System.out.println("Спасибо! Результат, количество ошибок: " + errors);
    }


    private Map<String, String> getCombinations(File file) throws Exception {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String string = scanner.nextLine();
            String[] text = string.split(separator);
            map.put(text[0], text[1]);
        }
        return map;
    }


}
