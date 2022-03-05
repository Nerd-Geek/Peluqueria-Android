package iesluisvives.peluqueriadam.data.entity;

public class ServiceEntity {
    private String id;
    private String name;
    private String desciption;
    private String image;
    private double price;
    private int stock;

    public ServiceEntity() {
    }

    public ServiceEntity(String id, String name, String desciption, String image, double price, int stock) {
        this.id = id;
        this.name = name;
        this.desciption = desciption;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
