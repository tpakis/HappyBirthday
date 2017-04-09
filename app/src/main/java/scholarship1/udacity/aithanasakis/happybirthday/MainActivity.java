package scholarship1.udacity.aithanasakis.happybirthday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import com.plattysoft.leonids.ParticleSystem;


public class MainActivity extends AppCompatActivity {


    // create timer
    long startTime = 0;
    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        timerHandler.removeCallbacks(timerRunnable);

        ImageView b = (ImageView) findViewById(R.id.imageView1);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showfireworks(v);
            }
        });
    }

    public void showfireworks(View v){
        ParticleSystem ps = new ParticleSystem(this, 100, R.drawable.star_white, 3000);
        ps.setScaleRange(0.7f, 1.3f);
        ps.setSpeedRange(0.1f, 0.25f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.oneShot(v, 70);
    }


    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }
}
