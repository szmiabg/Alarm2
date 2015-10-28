package com.appanyz.android.alarm2;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.CheckBox;


public class Checkbox extends CheckBox {

    public Checkbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setButtonDrawable(new StateListDrawable());

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
    }
}