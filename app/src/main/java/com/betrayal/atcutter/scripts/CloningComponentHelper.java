package com.betrayal.atcutter.scripts;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;

public class CloningComponentHelper {
    private final Context context;
    public CloningComponentHelper(Context context){
        this.context = context;
    }

    public EditText clone(EditText template){
        EditText newEditText = new EditText(context);
        newEditText.setText(template.getText());
        newEditText.setWidth(template.getWidth());
        newEditText.setHeight(template.getHeight());
        newEditText.setBackground(template.getBackground());
        newEditText.setHint(template.getHint());
        newEditText.setInputType(template.getInputType());
        return newEditText;
    }

    public Spinner clone(Spinner template){
        Spinner spinner = new Spinner(context);
        spinner.setAdapter(template.getAdapter());
        spinner.setBackground(template.getBackground());
        return spinner;
    }

    public TableRow clone(TableRow template){
        TableRow tableRow = new TableRow(context);
        tableRow.setPadding(
                template.getPaddingLeft(),
                template.getPaddingTop(),
                template.getPaddingRight(),
                template.getPaddingBottom()
        );
        tableRow.setBackground(tableRow.getBackground());
        return tableRow;
    }
}
