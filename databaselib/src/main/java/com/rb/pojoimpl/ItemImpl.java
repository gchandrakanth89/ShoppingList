package com.rb.pojoimpl;

import com.rb.pojo.Item;


/**
 * Created by Pervacio on 02-07-2017.
 */

public class ItemImpl implements Item {
    private long id;
    private String name;
    private String imageUrl;
    private double price;
    private String brand;
    private int quantity;
    private long userId;

    public ItemImpl(long id, String name, String imageUrl, double price, String brand, int quantity, long userId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public long getUserId() {
        return userId;
    }
}
