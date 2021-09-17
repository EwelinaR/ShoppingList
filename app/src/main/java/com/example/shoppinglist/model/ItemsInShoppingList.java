package com.example.shoppinglist.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsInShoppingList {
    @Embedded
    private ShoppingList shoppingList;
    @Relation(
            parentColumn = "id",
            entityColumn = "shoppingListId"
    )
    private List<Item> items;
}
