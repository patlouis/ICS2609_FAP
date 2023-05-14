package Model;

public class Product {
    
    private int id;
    private String name;
    private String price;
    private String image;
    private int rating;
    
    public Product() { }
    
    public Product(int id, String name, String price, String image, int rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.rating = rating;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getImage() {
        return image;
    }
    public int getRating() {
        return rating;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "[Id:" + id + "_Name:" + name + "_Price:" + price + ".]";
    }
    
}