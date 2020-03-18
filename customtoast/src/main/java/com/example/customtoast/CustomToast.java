package com.example.customtoast;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomToast {


    private volatile static CustomToast globalCustomToast = null;

    private Toast internalToast;

    private CustomToast(Toast toast)
    {
        // null check
        if (toast == null)
        {
            throw new NullPointerException("CustomToast.CustomToast(Toast) requires a non-null parameter.");
        }
        internalToast = toast;
    }

    public static CustomToast makeText(@NonNull Context context, CharSequence text, int duration) throws Resources.NotFoundException,NullPointerException
    {
        return new CustomToast(Toast.makeText(context, text, duration));
    }

    public static CustomToast makeText(@NonNull Context context, int resId, int duration) throws Resources.NotFoundException,NullPointerException
    {
        return new CustomToast(Toast.makeText(context, resId, duration));
    }

    public static CustomToast makeText(@NonNull Context context, CharSequence text) throws Resources.NotFoundException,NullPointerException
    {
        return new CustomToast(Toast.makeText(context, text, Toast.LENGTH_SHORT));
    }

    public static CustomToast makeText(@NonNull Context context, int resId) throws Resources.NotFoundException,NullPointerException
    {
        return new CustomToast(Toast.makeText(context, resId, Toast.LENGTH_SHORT));
    }

    public static void showText(Context context, CharSequence text, int duration) throws Resources.NotFoundException
    {
        CustomToast.makeText(context, text, duration).preview();
    }

    public static void showText(Context context, int resId, int duration) throws Resources.NotFoundException
    {
        CustomToast.makeText(context, resId, duration).preview();
    }

    public static void showText(Context context, CharSequence text) throws Resources.NotFoundException
    {
        CustomToast.makeText(context, "" + text, Toast.LENGTH_SHORT).preview();
    }



    public static void showText(Context context, int resId) throws Resources.NotFoundException
    {
        CustomToast.makeText(context, resId, Toast.LENGTH_SHORT).preview();
    }

    public void cancel()
    {
        internalToast.cancel();
    }

    public void preview()
    {
        show(true);
    }

    public void show(boolean cancelCurrent)
    {
        // cancel current
        if (cancelCurrent && (globalCustomToast != null))
        {
            globalCustomToast.cancel();
        }

        // save an instance of this current notification
        globalCustomToast = this;
        //internalToast.setGravity(Gravity.CENTER, 0, 0);
        internalToast.show();
    }

}
