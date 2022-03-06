package iesluisvives.peluqueriadam.data.entity;

public class ServiceEntity {
    private String id;
    private String name;
    private String desciption;
    private String image;
    private Double price;
    private Integer stock;

    public ServiceEntity(String id, String name, String desciption, String image, Double price, Integer stock) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
