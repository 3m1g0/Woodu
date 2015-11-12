package in.woodu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Patterns;

import in.woodu.R;
import in.woodu.utils.Utils;


/**
 * Created by KT on 10/2/15.
 */
public class EditText extends android.widget.EditText {

    public static final String ALPHA_NUMERIC = "[a-zA-Z0-9 \\./-]*";

    public static final String ALPHABETS = "[a-zA-Z \\./-]*";

    public static final String NAME = "[a-zA-Z \\./-]*";

    public static final String EMAIL = Patterns.EMAIL_ADDRESS.pattern();

    public static final String PHONE = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";

    public static final String USERNAME = "^[a-zA-Z0-9_.@-]{6,25}$";

    public static final String PASSWORD = "^[a-z0-9_-]{6,25}$";

    public static final String EMPTY = "";

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    private static final int[] colorAttrs = new int[]{android.R.attr.textColor, android.R.attr.background};
    private Context mContext;

    public EditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EditText(Context context) {
        super(context);
        init(null, null);
    }

    private void init(Context mContext, AttributeSet attrs) {
        this.mContext = mContext;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyFontView);
            String fontName = a.getString(R.styleable.MyFontView_fontName);
            if (fontName == null) {
                fontName = "Oswald";
            }
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", 0);
            setTypeface(Utils.selectTypeface(getContext(), fontName, textStyle));
            a.recycle();
        }
        processAndroidAttributes(getContext(), attrs);
        setTextSize(14);
        setCompoundDrawablePadding(16);
        setSingleLine();
        setInputType(getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

    private void processAndroidAttributes(final Context context, final AttributeSet attrs) {
        final TypedArray colorA = context.obtainStyledAttributes(attrs, colorAttrs);
        setTextColor(colorA.getColor(0, Color.BLACK));
        colorA.recycle();
    }
}
