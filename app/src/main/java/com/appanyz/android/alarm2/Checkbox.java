package com.appanyz.android.alarm2;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Miki on 28/10/15.
 */
public class Checkbox extends CheckBox {

   //private CheckBox checkbox;

    public Checkbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setButtonDrawable(new StateListDrawable());

        /*//Manage attributes
        int[] attributeSet = {
                android.R.attr.checked
        };

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, attributeSet, 0, 0);

        try {
            checkbox.setChecked(a.getBoolean(1, false));
        } finally {
            a.recycle();
        }*/
    }
    @Override
    public void setChecked(boolean checked){
        if(checked)
        {
            this.setBackgroundResource(R.drawable.select);
        }
        else
        {
            this.setBackgroundResource(R.drawable.deselect);
        }
        super.setChecked(checked);
        //checkbox.setChecked(checked);
    }

    /*public boolean isChecked() {
        return checkbox.isChecked();
    }*/
}