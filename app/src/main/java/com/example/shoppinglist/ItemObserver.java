package com.example.shoppinglist;

public interface ItemObserver extends Observer {
    void isChecked(int itemId, boolean isChecked);
}
