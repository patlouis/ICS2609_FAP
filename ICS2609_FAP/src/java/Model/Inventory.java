
package Model;

import java.util.ArrayList;

public class Inventory {
    
    ArrayList<Product> pList = new ArrayList();
    Product pOffer;
    
    public Inventory() {
        Product p1 = new Product(1, "Crispy Fries - REGULAR", "80.00", "Images/Regular Fries.png", 5);
        Product p2 = new Product(2, "Crispy Fries - BBQ", "84.00", "Images/BBQ Fries.png", 5);
        Product p3 = new Product(3, "Crispy Fries - CHEESE", "84.00", "Images/Cheese Fries.png", 4);
        Product p4 = new Product(4, "Crispy Fries - SOUR CREAM", "84.00", "Images/Sour Cream Fries.png", 3);
        Product p5 = new Product(5, "Crispy Fries - CHILI", "120.00", "Images/Chili Fries.png", 5);
        Product p6 = new Product(6, "Crispy Fries - TRUFFLE", "120.00", "Images/Truffle Fries.png", 4);

        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);
        pList.add(p5);
        pList.add(p6); 

    }
    
    public ArrayList getProductInvetory() {
        return pList;
    }
    public Product getProductOffer() {
        return pOffer;
    }
    
    public void addProductInventory(Product p) {
        pList.add(p);
    }
    public void setProductOffer(Product p) {
        pOffer = p;
    }
}

