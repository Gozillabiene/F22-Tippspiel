package net.gozillabiene.android.f22tippspiel;


import android.content.Context;
import android.graphics.Typeface;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableGenerator {
    private final Context mContext;
    private TableLayout mTable;

    private TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams();
    private TableRow.LayoutParams colParams = new TableRow.LayoutParams();

    public TableGenerator(Context context) {
        mContext = context;
        mTable = new TableLayout(context);
        rowParams.setMargins(0, 0, 0, 1);
        colParams.setMargins(0, 0, 1, 0);

        TableLayout.LayoutParams lptable = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mTable.setLayoutParams(lptable);

        mTable.setStretchAllColumns(true);
        mTable.setBackgroundColor(mContext.getResources().getColor(R.color.table_background));
    }

    public void addRow(String[] data, Integer farbe) {
        TableRow tr = new TableRow(mContext);
        tr.setBackgroundColor(mContext.getResources().getColor(R.color.table_background));

        tr.setLayoutParams(rowParams);

        Integer col0 = mContext.getResources().getColor(R.color.row_background0);
        Integer col1 = mContext.getResources().getColor(R.color.row_background1);
        Integer col2 = mContext.getResources().getColor(R.color.row_background2);
        Integer col3 = mContext.getResources().getColor(R.color.row_background3);
        Integer rowBackground;

        for (int iCol = 0; iCol < data.length; iCol++) {
            TextView tvCol = new TextView(mContext);

            String test=data[iCol];
            String check=test.replaceAll("[^**]", "");

            int farbeRes;
            farbeRes=farbe;
            String[] result=null;
            if(check.length()==2){
                tvCol.setTypeface(null, Typeface.BOLD);
                result=data[iCol].split("\\*\\*");
                data[iCol]=result[0];
                farbe=3;
            }else{

            }

            tvCol.setText(data[iCol]);
            tvCol.setGravity(Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setTextColor(mContext.getResources().getColor(R.color.text_black));
            tvCol.setLayoutParams(colParams);

            if (farbe == 1) {
                rowBackground = col1;
            } else if (farbe == 2) {
                rowBackground = col2;
            } else if (farbe == 3) {
                rowBackground = col3;
            } else {
                rowBackground = col0;
            }

            tvCol.setBackgroundColor(rowBackground);
            farbe=farbeRes;
            tr.addView(tvCol);
        }

        mTable.addView(tr);
    }

    public TableLayout getTable() {
        return mTable;
    }
}