package com.rb.pojoimpl;

import com.rb.pojo.Item;
import com.rb.pojo.ShoppingListItem;

/**
 * Created by Pervacio on 08-07-2017.
 */

public class ShoppingListItemImpl implements ShoppingListItem {
    private long id;
    private Item item;
    private long shoppingListId;
    private int quantity;
    private double price;
    private String brand;
    private String comments;


    public ShoppingListItemImpl(long id, Item item, long shoppingListId, int quantity, double price, String brand, String comments) {
        this.id = id;
        this.item = item;
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
    public Item getItem() {
        return item;
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
