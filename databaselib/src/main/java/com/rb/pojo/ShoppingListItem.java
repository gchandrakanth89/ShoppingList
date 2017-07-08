package com.rb.pojo;

/**
 * Created by Pervacio on 08-07-2017.
 */

public interface ShoppingListItem {
    long getId();

    long getItemId();

    long getShoppingListId();

    int getQuantity();

    double getPrice();

    String getBrand();

    String getComments();
}
