package com.example.shoppinglist;

public interface ItemObserver extends Observer {
    void isDone(int itemId, boolean isDone);
}
