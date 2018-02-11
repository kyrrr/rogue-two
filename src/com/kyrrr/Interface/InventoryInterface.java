package com.kyrrr.Interface;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Inventory;
import com.kyrrr.Model.Item;

import java.util.List;

/**
 * Created by kyrrebugge on 20.07.2017.
 */
public interface InventoryInterface {
    //actor has inventory
    //inventory has items
    //items have effects
    List<Item> getContents();
    Item getFirst();
    Item get(int index);
    Item getLast();
    int getSize();
    void setSize(int size);
    void add(Item item);
    void add(Inventory inventory);
    void remove(Item item);
    void remove(int index);
    int getAmount();
    int getFreeSpace();
}
