package ru.macdroid.randomizer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMinNumber, etMaxNumber;
    TextView tvResult, tvMinNumber, tvMaxNumber;
    Button buttonGenerate;
    Random r;
    CheckBox even, odd, allNum;
    int min, max, output;
    InputMethodManager imm;
    Animation tvResAnim, etMinAnim, etMaxAnim, tvMinAnim, tvMaxAnim, chAllAnim, chOddAnim, chEvenAnim, buttonAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tvMinAnim = AnimationUtils.makeInAnimation(this, true);
        /*tvMaxAnim = AnimationUtils.makeInAnimation(this, false);
        etMinAnim = AnimationUtils.makeInAnimation(this, true);
        etMaxAnim = AnimationUtils.makeInAnimation(this, false);
        chAllAnim = AnimationUtils.makeInAnimation(this, true);
        chOddAnim = AnimationUtils.makeInAnimation(this, false);*/
        //tvMinNumber.startAnimation(tvMinAnim);
       /* tvMaxNumber.startAnimation(tvMaxAnim);
        etMinNumber.startAnimation(etMinAnim);
        etMaxNumber.startAnimation(etMaxAnim);*/
        //allNum.startAnimation(chAllAnim);
        //odd.startAnimation(chOddAnim);

        //chEvenAnim = AnimationUtils.makeInAnimation(this, true);
        //buttonAnim = AnimationUtils.makeInAnimation(this, true);

        r = new Random();

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        tvMinNumber = (TextView) findViewById(R.id.tvMinNumber);
        tvMaxNumber = (TextView) findViewById(R.id.tvMaxNumber);
        etMinNumber = (EditText) findViewById(R.id.etMinNumber);
        etMaxNumber = (EditText) findViewById(R.id.etMaxNumber);
        buttonGenerate = (Button) findViewById(R.id.buttonGenerate);
        tvResult = (TextView) findViewById(R.id.tvResult);
        even = (CheckBox) findViewById(R.id.even);
        odd = (CheckBox) findViewById(R.id.odd);
        allNum = (CheckBox) findViewById(R.id.allNum);

        buttonGenerate.setOnClickListener(this);
        even.setOnClickListener(this);
        odd.setOnClickListener(this);
        allNum.setOnClickListener(this);
    }

    private void kbHide() {
        imm.hideSoftInputFromWindow(even.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void allNumber() {
        if (!etMinNumber.getText().toString().equals("") && (!etMaxNumber.getText().toString().equals(""))) {
            min = Integer.parseInt(etMinNumber.getText().toString());
            max = Integer.parseInt(etMaxNumber.getText().toString());
            if (max > min) {
                output = r.nextInt((max - min) + 1) + min;
                tvResult.setText("" + output);
            }
        }
    }

    private void oddNumber() {
        if (!etMinNumber.getText().toString().equals("") && (!etMaxNumber.getText().toString().equals(""))) {
            min = Integer.parseInt(etMinNumber.getText().toString());
            max = Integer.parseInt(etMaxNumber.getText().toString());
            if (max > min) {
                output = r.nextInt((max - min) + 1) + min;
                if (output % 2 != 0) {
                    tvResult.setText("" + output);
                } else {
                    buttonGenerate.callOnClick();
                }
            }
        }
    }

    private void evenNumber() {
        if (!etMinNumber.getText().toString().equals("") && (!etMaxNumber.getText().toString().equals(""))) {
            min = Integer.parseInt(etMinNumber.getText().toString());
            max = Integer.parseInt(etMaxNumber.getText().toString());
            if (max > min) {
                output = r.nextInt((max - min) + 1) + min;
                if (output % 2 == 0) {
                    tvResult.setText("" + output);
                } else {
                    buttonGenerate.callOnClick();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

        tvResAnim = AnimationUtils.makeInAnimation(this, true);
        tvResult.startAnimation(tvResAnim);

        switch (view.getId()) {

            case R.id.allNum:
                tvResult.setText("");
                even.setChecked(false);
                odd.setChecked(false);
                kbHide();
                break;

            case R.id.even:
                tvResult.setText("");
                allNum.setChecked(false);
                odd.setChecked(false);
                kbHide();
                break;

            case R.id.odd:
                tvResult.setText("");
                even.setChecked(false);
                allNum.setChecked(false);
                kbHide();
                break;

            case R.id.buttonGenerate:
                if (even.isChecked() && !odd.isChecked() && !allNum.isChecked()) { evenNumber(); }
                if (odd.isChecked() && !even.isChecked() && !allNum.isChecked()) { oddNumber(); }
                if (!odd.isChecked() && !even.isChecked() && allNum.isChecked()) { allNumber(); }
                if (!odd.isChecked() && !even.isChecked() && !allNum.isChecked()) {
                    Toast.makeText(this, "Выберите какие числа отображать", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
