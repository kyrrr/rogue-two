package com.kyrrr.Model.Status;

import com.kyrrr.Interface.InventoryInterface;
import com.kyrrr.Model.Items.Consumable;
import com.kyrrr.Model.Items.Item;
import com.kyrrr.Model.Items.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrrebugge on 07.08.2017.
 */
public class Inventory implements InventoryInterface {

    private List<Item> contents = new ArrayList<>();
    private List<Consumable> consumables = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private int size;

    public Inventory(){

    }

    public Inventory(int size){
        this.size = size;
    }

    @Override
    public List<Item> getContents() {
        return contents;
    }

    @Override
    public Item getFirst() {
        try{
            return contents.get(0);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public Item get(int index) {
        try{
            return contents.get(index);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public Item getLast() {
        return get(getAmount() - 1);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void add(Item item) {
        if(contents.size() <= getSize()){
           /* if(item instanceof Consumable){
                consumables.add((Consumable)item);
            }else if(item instanceof Weapon){
                weapons.add((Weapon)item);
            }*/
            contents.add(item);
        }
    }


    @Override
    public void add(Inventory inventory) {
        //while (getContents().size() <= getSize()){
        if(getContents().size() >= getSize()){
            System.out.println("Inventory is full");
        } else {
            while (contents.size() <= getSize()){
                //inventory
                inventory.getContents().forEach(contents::add);
            }
        }
    }

    @Override
    public void remove(Item item) {
        contents.remove(item);
    }

    @Override
    public void remove(int index) {
        contents.remove(index);
    }

    @Override
    public int getAmount() {
        return contents.size();
    }

    @Override
    public int getFreeSpace() {
        return getSize() - getAmount();
    }

    public boolean hasItem(Class c){
        for (Item i : getContents()){
            if(c == i.getClass()){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean containsInstanceOf(Item i) {
        for (Item mi : getContents()){
            if(mi.getClass() == i.getClass()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Item getFirstInstanceOf(Item i) {
        for (Item mi : getContents()){
            if(mi.getClass() == i.getClass()){
                return mi;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return contents.isEmpty();
    }

    public int size(){
        return contents.size();
    }

}
