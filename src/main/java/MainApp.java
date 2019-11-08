import com.dennn66.phonebook.Phonebook;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Program start");
        Phonebook pb = new Phonebook();
        pb.add("Den", "+79999999999");
        pb.add("Den", "+79999999998");
        pb.add("Pit", "+79999999997");

        System.out.println(pb);
        System.out.println(pb.get("Den"));
        System.out.println(pb.get("Pit"));

        ArrayList<String> words = new ArrayList<>(Arrays.asList(
                "Создать", "массив", "c", "набором", "слов",
                "10-20",  "слов", "должны", "встречаться", "повторяющиеся",
                "Найти", "и",  "вывести", "список",  "уникальных", "слов",
                "из", "которых", "состоит", "массив", "Посчитать", "сколько",
                "раз", "встречается", "каждое", "слово"
                ));

        //TaskService tracker = new TaskService();
        //tracker.test();
    }
}
