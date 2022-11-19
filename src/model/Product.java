package model;

public class Product {
    private Integer productCode;
    private String name;
    private Integer quantity;
    private Double price;

    public Product(Integer productCode, String name,
                   Integer quantity, Double price) {
        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Product(String[] params) {
        setProductCode(Integer.parseInt(params[0]));
        this.name = params[1];
        this.quantity = Integer.parseInt(params[2]);
        this.price = Double.parseDouble(params[3]);
    }

    public Product() {
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String convertToData(){
        return new StringBuilder().
                append("Product").
                append(this.productCode).append(";").
                append(this.name).append(";").
                append(this.quantity).append(";").
                append(this.price).toString();
    }

}
