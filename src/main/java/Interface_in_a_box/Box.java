package Interface_in_a_box;

import java.util.ArrayList;

public class Box implements Packable{
    private ArrayList<Packable> box;
    private double maxCapacity;

    public Box(double capacity) {
        this.box = new ArrayList<>();
        this.maxCapacity = capacity;
    }

    public void add(Packable item) {
        if (item.weight() + weight() > maxCapacity) {
            return;
        }
        box.add(item);
    }

    public String toString() {
        return "Box: " + box.size() + " items, total weight " + weight() + " kg";
    }

    public double weight() {
        double weight = 0;
        
        for (Packable item: box) {
            weight += item.weight();
        }

        return weight;
    }

}
