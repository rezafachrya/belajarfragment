package com.example.belajarfragment.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.belajarfragment.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogHelper {
    public static SweetAlertDialog showConfirmgWithCallback(final Context c, String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener listener) {
        final SweetAlertDialog sweetDialog = new SweetAlertDialog(c, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(title)
                .setCustomView(createCustomText(c, content))
                .setConfirmText(confirmText)
                .setCancelText(cancelText)
                .showCancelButton(true)
                .setCustomView(createCustomText(c, content))
                .setConfirmClickListener(listener);
        sweetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                sweetDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(c.getResources().getDrawable(R.drawable.border_orange_dark_rounded_big_fill));
                sweetDialog.getButton(SweetAlertDialog.BUTTON_CANCEL).setBackground(c.getResources().getDrawable(R.drawable.border_orange_dark_rounded_big_fill));
                sweetDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setTextSize(TypedValue.COMPLEX_UNIT_PX, c.getResources().getDimension(R.dimen._8ssp));
                sweetDialog.getButton(SweetAlertDialog.BUTTON_CANCEL).setTextSize(TypedValue.COMPLEX_UNIT_PX, c.getResources().getDimension(R.dimen._8ssp));
            }
        });

        return sweetDialog;
    }

    public static TextView createCustomText(Context c, String content) {
        final TextView text = new TextView(c);
        text.setGravity(Gravity.CENTER);

        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(params);
        text.setText(content.replace("500 - ", "").replace("403 - ", "") + "\n");

        return text;
    }
}
