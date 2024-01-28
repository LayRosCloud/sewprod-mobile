package com.betrayal.atcutter.scripts;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TableControllerAdder {
    private final TableLayout tableLayout;
    public TableControllerAdder(TableLayout tableLayout){
        this.tableLayout = tableLayout;
    }

    public void putInRowComponents(TableRow row, int indexOfInsertRow, TableRow.LayoutParams params, View... views){
        for (View view:
             views) {
           row.addView(view, params);
        }

        tableLayout.addView(row, indexOfInsertRow);
    }

    public void putInRowComponents(TableRow row, View... views){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.weight = 1;
        putInRowComponents(row, tableLayout.getChildCount() - 1, params, views);
    }
}
