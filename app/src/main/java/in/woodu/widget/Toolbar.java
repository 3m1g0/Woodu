package in.woodu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import in.woodu.R;


/**
 * Created by KT on 29-09-2015.
 */
public class Toolbar extends android.support.v7.widget.Toolbar {

    public Toolbar(Context context) {
        super(context);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitle(CharSequence title) {
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        TextView subtitleText = (TextView) findViewById(R.id.toolbar_subtitle);
        titleText.setText(title);
    }

    @Override
    public void setSubtitle(CharSequence subtitle) {
        TextView subtitleText = (TextView) findViewById(R.id.toolbar_subtitle);
        subtitleText.setText(subtitle);
        if (subtitleText.getText() == null || subtitleText.getText().toString().equals("")) {
            subtitleText.setVisibility(View.GONE);
        } else {
            subtitleText.setVisibility(View.VISIBLE);
        }
    }

    public void enableLogoTitle() {
        ImageView icon = (ImageView) findViewById(R.id.toolbar_woodu_logo_text);
        icon.setVisibility(View.VISIBLE);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setVisibility(View.GONE);
    }

    public void disbleLogoTitle() {
        ImageView icon = (ImageView) findViewById(R.id.toolbar_woodu_logo_text);
        icon.setVisibility(View.GONE);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setVisibility(View.VISIBLE);
    }
}
