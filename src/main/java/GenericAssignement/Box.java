package GenericAssignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Box <T extends Fruit> {
    private ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }
    public float getWeight(){
        if(fruits.size() > 0){
            return fruits.get(0).getWeight() * fruits.size();
        }
        return 0.0f;
    }

    public void addFruit(T fruit){
        fruits.add(fruit);
    }

    public boolean compare( Box<?> box) {
        return Math.abs(getWeight() - box.getWeight()) < 0.0001f;
    }

    public void moveTo(Box<T> box){
        Iterator<T> iter = fruits.iterator();
        while (iter.hasNext()){
            box.addFruit(iter.next());
        }
        fruits.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box<?> box = (Box<?>) o;
        return Objects.equals(fruits, box.fruits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fruits);
    }

    @Override
    public String toString() {
        return "Box{" +
                "weight=" + getWeight() +
                ", quantity=" + fruits.size() +
                ", fruits=" + fruits +
                '}';
    }
}
