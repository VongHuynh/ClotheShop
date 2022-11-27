package com.pojo;

public class Cart {
    private Integer idProduct;
    private String nameProduct;
    private double priceProduct;
    private String photoColor;
    private int quantity;
    private Integer idColor;
    private String nameColor;
    private Integer idSize;
    private String nameSize;
    private Integer idProductsColors;
    private double amount;


    public Cart() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getIdProductsColors() {
        return idProductsColors;
    }

    public void setIdProductsColors(Integer idProductsColors) {
        this.idProductsColors = idProductsColors;
    }

    public Integer getIdSize() {
        return idSize;
    }

    public void setIdSize(Integer idSize) {
        this.idSize = idSize;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getPhotoColor() {
        return photoColor;
    }

    public void setPhotoColor(String photoColor) {
        this.photoColor = photoColor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
}
