package in.woodu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import in.woodu.R;
import in.woodu.utils.Utils;


/**
 * Created by KT on 10/2/15.
 */
public class TextView extends android.widget.TextView {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyFontView);
            String fontName = a.getString(R.styleable.MyFontView_fontName);
            if (fontName == null) {
                fontName = "Oswald";
            }
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", 0);
            setTypeface(Utils.selectTypeface(getContext(), fontName, textStyle));
            a.recycle();
//            processAndroidAttributes(getContext(), attrs);
        }
    }

    private static final int[] colorAttrs = new int[]{android.R.attr.textColor, android.R.attr.background, android.R.attr.textStyle};

    private void processAndroidAttributes(final Context context, final AttributeSet attrs) {
        final TypedArray colorA = context.obtainStyledAttributes(attrs, colorAttrs);
        setTextColor(colorA.getColor(0, Color.BLACK));
        colorA.recycle();
    }
}

