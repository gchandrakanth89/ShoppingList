package com.rb.pojoimpl;

import com.rb.pojo.ShoppingListItem;

/**
 * Created by Pervacio on 08-07-2017.
 */

public class ShoppingListItemImpl implements ShoppingListItem {
    private long id;
    private long itemId;
    private long shoppingListId;
    private int quantity;
    private double price;
    private String brand;
    private String comments;


    public ShoppingListItemImpl(long id, long itemId, long shoppingListId, int quantity, double price, String brand, String comments) {
        this.id = id;
        this.itemId = itemId;
        this.shoppingListId = shoppingListId;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.comments = comments;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getItemId() {
        return itemId;
    }

    @Override
    public long getShoppingListId() {
        return shoppingListId;
    }

    @Override
    public int getQuantity() {
        return quantity;
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
    public String getComments() {
        return comments;
    }
}
