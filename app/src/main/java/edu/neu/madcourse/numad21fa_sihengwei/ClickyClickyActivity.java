package edu.neu.madcourse.numad21fa_sihengwei;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ClickyClickyActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);

        //setOnTouchListener for the bottons
        findViewById(R.id.btn_A).setOnTouchListener(this);
        findViewById(R.id.btn_B).setOnTouchListener(this);
        findViewById(R.id.btn_C).setOnTouchListener(this);
        findViewById(R.id.btn_D).setOnTouchListener(this);
        findViewById(R.id.btn_E).setOnTouchListener(this);
        findViewById(R.id.btn_F).setOnTouchListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility"})
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView tv_showPressed = findViewById(R.id.tv_showPressed);
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                switch (view.getId()) {
                    case R.id.btn_A:
                        tv_showPressed.setText(R.string.pressed_a);
                        break;
                    case R.id.btn_B:
                        tv_showPressed.setText(R.string.pressed_b);
                        break;
                    case R.id.btn_C:
                        tv_showPressed.setText(R.string.pressed_c);
                        break;
                    case R.id.btn_D:
                        tv_showPressed.setText(R.string.pressed_d);
                        break;
                    case R.id.btn_E:
                        tv_showPressed.setText(R.string.pressed_e);
                        break;
                    case R.id.btn_F:
                        tv_showPressed.setText(R.string.pressed_f);
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                tv_showPressed.setText(R.string.pressed);
                break;
        }
        return true;
    }
}