import java.util.List;
import java.util.ArrayList;

interface Box {
    double calculatePrice();
}

abstract class Product implements Box {
    String label;
    double price;
    
    public double calculatePrice() {
        return price;
    }
}

class CompositeBox implements Box {
    List<Box> children;

    //#region Constructors
    public CompositeBox() {
        children = new ArrayList<>();
    }

    public CompositeBox(Box b) {
        children = new ArrayList<>();
        children.add(b);
    }

    public CompositeBox(Box[] boxes) {
        children = new ArrayList<>();
        for (int i = 0; i < boxes.length; i++) {
            children.add(boxes[i]);
        }
    }
    //#endregion

    public void addBoxes(Box[] boxes) {
        for (int i = 0; i < boxes.length; i++) {
            children.add(boxes[i]);
        }
    }

    public double calculatePrice() {
        double totalPrice = 0.0;
        for (Box child : children) {
            totalPrice += child.calculatePrice();
        }
        return totalPrice;
    }
}