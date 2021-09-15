package com.example.shoppinglist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private boolean isDone;
}
