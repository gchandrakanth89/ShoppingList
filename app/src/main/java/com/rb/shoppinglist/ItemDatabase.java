package com.rb.shoppinglist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pervacio on 27-06-2017.
 */

public class ItemDatabase {

    public static List<Item> getShoppingList() {
        ArrayList<Item> arrayList = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setName("Item " + i);
            item.setBrand("Brand " + i);
            item.setQuantity(10);

            arrayList.add(item);
        }

        return arrayList;
    }
}
