package com.idankorenisraeli.soccerbattle.common;

import android.app.Activity;
import android.content.DialogInterface;

// This class is used to create dialogs in a more convenient way
// We just need to create an object and pass it to the function that shows the dialog
// which is CommonUtils.showMaterialAlertDialog()
public class MaterialDialogProperties {
    private Activity activity;
    private String titleLabel, yesLabel, noLabel, message;
    private DialogInterface.OnClickListener onPositive, onNegative;

    public MaterialDialogProperties(Activity activity, String titleLabel,
                                    String yesLabel, String noLabel, String message, DialogInterface.OnClickListener onPositive,
                                    DialogInterface.OnClickListener onNegative) {
        this.activity = activity;
        this.titleLabel = titleLabel;
        this.yesLabel = yesLabel;
        this.noLabel = noLabel;
        this.message = message;
        this.onPositive = onPositive;
        this.onNegative = onNegative;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel = titleLabel;
    }

    public String getYesLabel() {
        return yesLabel;
    }

    public void setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
    }

    public String getNoLabel() {
        return noLabel;
    }

    public void setNoLabel(String noLabel) {
        this.noLabel = noLabel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DialogInterface.OnClickListener getOnPositive() {
        return onPositive;
    }

    public void setOnPositive(DialogInterface.OnClickListener onPositive) {
        this.onPositive = onPositive;
    }

    public DialogInterface.OnClickListener getOnNegative() {
        return onNegative;
    }

    public void setOnNegative(DialogInterface.OnClickListener onNegative) {
        this.onNegative = onNegative;
    }
}
