package com.rb.pojoimpl;

import com.rb.pojo.ShoppingList;
import com.rb.pojo.ShoppingListItem;

import java.util.List;

/**
 * Created by Pervacio on 08-07-2017.
 */

public class ShoppingListImpl implements ShoppingList {

    private long id;
    private long userId;
    private String name;
//    private List<ShoppingListItem> itemsList;


    public ShoppingListImpl(long id, long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

   /* @Override
    public List<ShoppingListItem> getShoppingListItems() {
        return itemsList;
    }

    public void setItemsList(List<ShoppingListItem> itemsList) {
        this.itemsList = itemsList;
    }*/
}
