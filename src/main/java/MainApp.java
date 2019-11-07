import GenericAssignement.Apple;
import GenericAssignement.Box;
import GenericAssignement.Orange;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Program start");

        Box<Apple> boxA1 = new Box<>();
        Box<Orange> boxO1 = new Box<>();
        Box<Apple> boxA2 = new Box<>();

        boxA1.addFruit(new Apple());
        boxA1.addFruit(new Apple());
        boxA1.addFruit(new Apple());
        boxA1.addFruit(new Apple());

        boxA2.addFruit(new Apple());

        boxO1.addFruit(new Orange());

        System.out.println(boxA1);
        System.out.println(boxA2);
        System.out.println(boxO1);

        System.out.println("Box A1 is bigger then Box A2: " + boxA1.compare(boxA2));
        System.out.println("Box A1 is bigger then Box O1: " + boxA1.compare(boxO1));

        boxA1.moveTo(boxA2);
        System.out.println(boxA1);
        System.out.println(boxA2);



        TaskService tracker = new TaskService();
        tracker.test();
    }
}
