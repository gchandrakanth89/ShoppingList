package com.rb.pojoimpl;

import com.rb.pojo.Item;


/**
 * Created by Pervacio on 02-07-2017.
 */

public class ItemImpl implements Item {
    private int id;
    private String name;
    private String imageUrl;
    private double price;
    private String brand;
    private int quantity;

    @Override
    public int getId() {
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
}
