package com.example.shoppinglist.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingList {
    private String date;
    private String name;
    private List<Item> items;
}
