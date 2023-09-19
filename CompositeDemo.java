import java.util.List;
import java.util.ArrayList;

public class CompositeDemo {

    public static void main(String[] args) {

        // Creating the products
        Box myBook = new Book("El Principito", "...", 29.99);
        Box myBook2 = new Book("Lord of the Rings", "J.R.T.", 69.69);
        Box myComputer = new Computer("16 GB @ 2600 Hz", "Ryzen 7", 2599.99);
        Box myComputer2 = new Computer("2 GB @ 1200 Hz", "Pentium duo", 499.99);
        Box myComputer3 = new Computer("16 GB", "M2", 2999.99);

        //putting them in composite boxes
        CompositeBox alice_books = new CompositeBox(new Box[] { myBook, myBook2 });
        CompositeBox alice_PC = new CompositeBox(myComputer);
        CompositeBox bob_PC = new CompositeBox(myComputer2);
        CompositeBox livingroom_PC = new CompositeBox(myComputer3);
        CompositeBox alice_stuff = new CompositeBox(new Box[] { alice_books, alice_PC });
        CompositeBox bob_stuff = new CompositeBox(bob_PC);
        CompositeBox shopping_cart = new CompositeBox(new Box[] { alice_stuff, bob_stuff, livingroom_PC });

        System.out.println("Total Price: $" + shopping_cart.calculatePrice()); // Total Price: $6199.65
    }
}

interface Box {
    double calculatePrice();
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

abstract class Product implements Box {
    String label;
    double price;

    public double calculatePrice() {
        return price;
    }
}

class Book extends Product {
    String title, author;

    public Book(String title, 
                String author, 
                double price) {
        this.label = title;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}

class Computer extends Product {
    String ram, processor;

    public Computer(String ram, 
                    String processor, 
                    double price) {
        this.label = processor;
        this.ram = ram;
        this.processor = processor;
        this.price = price;
    }
}
