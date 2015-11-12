package in.woodu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import in.woodu.R;
import in.woodu.utils.Utils;

/**
 * Created by KT on 10/2/15.
 */
public class Button extends android.widget.Button {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Button(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            setAllCaps(true);
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyFontView);
            String fontName = a.getString(R.styleable.MyFontView_fontName);
            if (fontName == null) {
                fontName = "Oswald";
            }
            setTextColor(getResources().getColor(android.R.color.black));
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", 0);
            setTypeface(Utils.selectTypeface(getContext(), fontName, textStyle));
            setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//            setBackgroundResource(R.drawable.bg_woodu);
            a.recycle();
        }
    }
}