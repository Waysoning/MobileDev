package edu.neu.madcourse.numad21fa_sihengwei;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

/*
  onClick Event of the btn_convert
  public void convertCurrency(View view) {
    EditText dollarText = findViewById(R.id.dollarText);
    TextView textView = findViewById(R.id.textView);
    if (!dollarText.getText().toString().equals("")) {
      float dollarValue = Float.parseFloat(dollarText.getText().toString());
      float euroValue = dollarValue * 0.85F;
      textView.setText(String.format(Locale.ENGLISH, "%f", euroValue));
    } else {
      textView.setText(R.string.no_value_string);
    }
  }
  */

  @SuppressLint("NonConstantResourceId")
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_about:
        Toast.makeText(
            getApplicationContext(), "Siheng Wei\nsiheng.w@northeastern.edu", Toast.LENGTH_SHORT).show();
        break;
      case R.id.btn_clikyCliky:
        startActivity(new Intent(this, ClickyClickyActivity.class));
        break;
      case R.id.btn_linkCollector:
        startActivity(new Intent(this, RecyclerViewActivity.class));
        break;
      case R.id.btn_locator:
        startActivity(new Intent(this, LocatorActivity.class));
        break;
    }
  }
}
