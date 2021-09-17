package com.example.shoppinglist;

public interface Observer {
    void onClickItem(int itemId);
    void save(String text);
    void delete(int position);
}
