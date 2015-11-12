package in.wooduUnity;

import android.app.NativeActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.unity3d.player.UnityPlayer;

public class UnityPlayerNativeActivity extends NativeActivity {
    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

    // Setup activity layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().takeSurface(null);
        getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

        mUnityPlayer = new UnityPlayer(this);
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();
    }

    // Quit Unity
    @Override
    protected void onDestroy() {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    public void fetchUrl() {
        String uri = getIntent().getStringExtra("uri");

        UnityPlayer.UnitySendMessage("SOFAS", "GetUrl", getDefaultModel(UnityPlayerNativeActivity.this, "SOFAS"));
        Log.d("SOFAS", getDefaultModel(UnityPlayerNativeActivity.this, "SOFAS"));
        UnityPlayer.UnitySendMessage("SEATING", "GetUrl", getDefaultModel(UnityPlayerNativeActivity.this, "SEATING"));
        Log.d("SEATING", getDefaultModel(UnityPlayerNativeActivity.this, "SEATING"));
        UnityPlayer.UnitySendMessage("BEDS", "GetUrl", getDefaultModel(UnityPlayerNativeActivity.this, "BEDS"));
        UnityPlayer.UnitySendMessage("DINING", "GetUrl", getDefaultModel(UnityPlayerNativeActivity.this, "DINING"));
        UnityPlayer.UnitySendMessage("SHELVES", "GetUrl", getDefaultModel(UnityPlayerNativeActivity.this, "SHELVES"));
    }

    public String getDefaultModel(Context mContext, String category) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("default_model_pref", Context.MODE_PRIVATE);
        return sharedPreferences.getString(category, null);
    }

    // Pause Unity
    @Override
    protected void onPause() {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override
    protected void onResume() {
        super.onResume();
        mUnityPlayer.resume();
    }

    public void closeApp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUnityPlayer.quit();
                UnityPlayer.currentActivity.finish();
            }
        });
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /*API12*/
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }
}
