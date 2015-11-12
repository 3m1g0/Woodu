package in.woodu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    protected int _splashTime = 1000;

    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_splash);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(_splashTime);
                    }

                } catch (Exception e) {
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();
                        }
                    });
                }
            }
        }

        ;

        splashTread.start();

    }

}
