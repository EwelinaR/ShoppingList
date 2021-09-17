package com.example.shoppinglist;

public interface Observer {
    void onClickItem(int itemId);
    void add(String text);
    void update(int itemId, String text);
    void delete(int position);
}
