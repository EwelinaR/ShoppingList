package com.example.shoppinglist.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.shoppinglist.Observer;
import com.example.shoppinglist.R;

import org.jetbrains.annotations.NotNull;

public class AddItemDialog extends DialogFragment {

    private EditText itemName;
    private final Observer observer;

    public AddItemDialog(String name, Observer observer) {
        itemName.setText(name);
        this.observer = observer;
    }

    public AddItemDialog(Observer observer) {
        this.observer = observer;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_add_item, null);
        builder.setView(view)
                .setTitle(R.string.add_item_dialog_title)
            .setPositiveButton(R.string.save, (dialog, id) -> save())
            .setNegativeButton(R.string.cancel, (dialog, id) -> { });

        itemName = (EditText) view.findViewById(R.id.name);

        return builder.create();
    }

    private void save() {
        itemName.getText();
        observer.save(itemName.getText().toString());
    }
}
