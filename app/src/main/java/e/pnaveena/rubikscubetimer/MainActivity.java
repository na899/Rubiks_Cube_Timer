package e.pnaveena.rubikscubetimer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView inspecTime;
    TextView solveTime;
    long milliSec, startTime, timeBuff = 0L, Time = 0L;
    Handler handler;
    int sec, min, mSec, f = 0,time=15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inspecTime = findViewById(R.id.inspecTimer);
        solveTime = findViewById(R.id.solveTimer);

        handler = new Handler();

    }

    @SuppressLint("ResourceAsColor")
    public void screenTouch(View view) {
        if (f == 0) {
            handler.postDelayed(runnable1,1000);

        }
            else if (f == 1) {
            handler.removeCallbacks(runnable1);
            inspecTime.setTextColor(Color.parseColor("#FF0000"));
            TextView a=findViewById(R.id.inspecCaption);
            a.setTextColor(Color.parseColor("#FF0000"));

            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);

        } else if (f == 2) {
            timeBuff += milliSec;
            handler.removeCallbacks(runnable);
            solveTime.setTextColor(Color.parseColor("#32cd32"));
            TextView a=findViewById(R.id.solveCaption);
            a.setTextColor(Color.parseColor("#32cd32"));

        }
        f++;
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            milliSec = SystemClock.uptimeMillis() - startTime;

            Time = timeBuff + milliSec;

            sec = (int) (Time / 1000);

            min = sec / 60;

            sec = sec % 60;

            mSec = (int) (Time % 1000);

            solveTime.setText("" + min + ":"
                    + String.format("%02d", sec) + ":"
                    + String.format("%03d", mSec));

            handler.postDelayed(this, 0);
        }


    };
   public  Runnable runnable1=new Runnable() {
        @Override
        public void run() {
            if (time > 0) {
                if(time==4)
                {
                    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                    toneGen1.startTone(ToneGenerator.TONE_CDMA_HIGH_SS,500);
                }
                time -= 1;
                handler.postDelayed(this, 1000);
                inspecTime.setText(time+"");

            }
            else
            {f++;
                startTime = SystemClock.uptimeMillis();
             handler.postDelayed(runnable,0);
             handler.removeCallbacks(runnable1);
                inspecTime.setTextColor(Color.parseColor("#FF0000"));
                TextView a=findViewById(R.id.inspecCaption);
                a.setTextColor(Color.parseColor("#FF0000"));
        }}
    };
}