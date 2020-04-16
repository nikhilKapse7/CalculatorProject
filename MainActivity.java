package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> list = new ArrayList<>();

    Button exponent;
    boolean exponentOn = false;
    LinearLayout layout;
    ArrayList<Button> buttonList = new ArrayList<>();
    TextView text;
    Button decimal;
    Button mode;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    Button enter;
    double answer;
    Boolean typable = true;
    Button clear;
    boolean errorPresent = false;
    boolean undefined = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exponent = findViewById(R.id.exponent);
        exponent.setOnClickListener(this);
        decimal = findViewById(R.id.decimal);
        decimal.setOnClickListener(this);
        layout = findViewById(R.id.layout);
        text = findViewById(R.id.textView);
        text.setOnClickListener(this);
        zero = findViewById(R.id.button0);
        zero.setOnClickListener(this);
        one = findViewById(R.id.button1);
        one.setOnClickListener(this);
        two = findViewById(R.id.button2);
        two.setOnClickListener(this);
        three = findViewById(R.id.button3);
        three.setOnClickListener(this);
        four = findViewById(R.id.button4);
        four.setOnClickListener(this);
        five = findViewById(R.id.button5);
        five.setOnClickListener(this);
        six = findViewById(R.id.button6);
        six.setOnClickListener(this);
        seven = findViewById(R.id.button7);
        seven.setOnClickListener(this);
        eight = findViewById(R.id.button8);
        eight.setOnClickListener(this);
        nine = findViewById(R.id.button9);
        nine.setOnClickListener(this);

        plus = findViewById(R.id.buttonPlus);
        plus.setOnClickListener(this);
        clear = findViewById(R.id.buttonC);
        minus = findViewById(R.id.buttonMinus);
        minus.setOnClickListener(this);
        multiply = findViewById(R.id.buttonMultiply);
        multiply.setOnClickListener(this);
        divide = findViewById(R.id.buttonDivide);
        divide.setOnClickListener(this);
        enter = findViewById(R.id.buttonEnter);

        mode = findViewById(R.id.mode);

        buttonList.add(one);
        buttonList.add(two);
        buttonList.add(three);
        buttonList.add(four);
        buttonList.add(five);
        buttonList.add(six);
        buttonList.add(seven);
        buttonList.add(eight);
        buttonList.add(nine);
        buttonList.add(zero);
        buttonList.add(minus);
        buttonList.add(plus);
        buttonList.add(multiply);
        buttonList.add(divide);
        buttonList.add(enter);
        buttonList.add(mode);
        buttonList.add(decimal);
        buttonList.add(clear);
        buttonList.add(exponent);
        //Html.fromHtml("x<sup>y</sup>On")
        exponent.setText("^ ON");


        text.setTextColor(Color.DKGRAY);
        layout.setBackgroundColor(Color.WHITE);
        for(Button button: buttonList) {
            button.setTextColor(Color.BLACK);
            button.setBackgroundColor(Color.LTGRAY);
        }
        minus.setTextColor(Color.BLUE);
        plus.setTextColor(Color.BLUE);
        divide.setTextColor(Color.BLUE);
        multiply.setTextColor(Color.BLUE);

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode.getText().equals("DARK")) { // DARK MODE
                    text.setTextColor(Color.WHITE);
                    mode.setText("LIGHT");
                    layout.setBackgroundColor(Color.BLACK);

                    for(Button button: buttonList) {
                        button.setTextColor(Color.WHITE);
                        button.setBackgroundColor(Color.DKGRAY);
                    }
                    minus.setTextColor(Color.CYAN);
                    plus.setTextColor(Color.CYAN);
                    divide.setTextColor(Color.CYAN);
                    multiply.setTextColor(Color.CYAN);
                }
                else { //LIGHT MODE
                    mode.setText("DARK");
                    text.setTextColor(Color.DKGRAY);
                    layout.setBackgroundColor(Color.WHITE);
                    for(Button button: buttonList) {
                        button.setTextColor(Color.BLACK);
                        button.setBackgroundColor(Color.LTGRAY);
                    }
                    minus.setTextColor(Color.BLUE);
                    plus.setTextColor(Color.BLUE);
                    divide.setTextColor(Color.BLUE);
                    multiply.setTextColor(Color.BLUE);
                }
            }
        });
        exponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exponent.getText().equals("^ ON")) {
                    exponentOn = true;

                    exponent.setText("^ OFF");

                }
                else { // IF OFF
                    exponentOn = false;
                    exponent.setText("^ ON");

                }
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringTokenizer tokenizer = new StringTokenizer(text.getText()+"", "+-/*", true);

                while(tokenizer.hasMoreTokens()) {
                    list.add(tokenizer.nextToken());
                }
                if(list.isEmpty()) {
                    errorPresent = true;
                }
                if(!errorPresent) {
                    if (list.get(0).equals("+") || //operation errors
                            list.get(0).equals("-") ||
                            list.get(0).equals("*") ||
                            list.get(0).equals("/") ||
                            list.get(list.size() - 1).equals("+") ||
                            list.get(list.size() - 1).equals("-") ||
                            list.get(list.size() - 1).equals("*") ||
                            list.get(list.size() - 1).equals("/")) {
                        errorPresent = true;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("/") && !errorPresent) {
                            if (list.get(i + 1).equals("0")) {
                                undefined = true;
                            }
                        }
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if ((list.get(i).equals("+") || list.get(i).equals("-") || list.get(i).equals("*") || list.get(i).equals("/"))) {
                            if (i < list.size() - 1) {
                                if ((list.get(i + 1).equals("+") || list.get(i + 1).equals("-") || list.get(i + 1).equals("*") || list.get(i + 1).equals("/"))) {
                                    errorPresent = true;
                                }
                            }
                        }
                    }
                }
                if(errorPresent) {
                    text.setText("ERROR");
                    typable = false;

                }
                else if(undefined) {
                    text.setText("UNDEFINED");
                    typable = false;
                }
                else {
                    //exponents
                    int index = 0;
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).contains(Html.fromHtml("<sup>0</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>1</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>2</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>3</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>4</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>5</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>6</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>7</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>8</sup>")) ||
                        list.get(i).contains(Html.fromHtml("<sup>9</sup>"))) { // contains exponent
                            while(!list.get(i).contains(Html.fromHtml("<sup>0</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>1</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>2</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>3</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>4</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>5</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>6</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>7</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>8</sup>")) &&
                                    (!list.get(i).contains(Html.fromHtml("<sup>9</sup>")))))))))))) {

                                if(index < list.get(i).length() - 1) {
                                    index++;
                                }
                            }
                            double base = Double.parseDouble(list.get(i).substring(0, index + 1));
                            if(!list.get(i).substring(index + 1).equals("")) {
                                double powerOf = Double.parseDouble(list.get(i).substring(index + 1));

                                double result = Math.pow(base, powerOf);
                                list.set(i, result + "");
                            }
                        }
                    }
                    // Multiplication and division
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("*")) {
                            double product = Double.parseDouble(list.get(i - 1)) * (Double.parseDouble(list.get(i + 1)));
                            list.set(i - 1, (product + ""));
                            list.remove(i);
                            list.remove(i);
                            i--;
                        } else if (list.get(i).equals("/")) {
                            double quotient = Double.parseDouble(list.get(i - 1)) / (Double.parseDouble(list.get(i + 1)));
                            list.set(i - 1, (quotient + ""));
                            list.remove(i);
                            list.remove(i);
                            i--;
                        }
                    }
                    // Addition and subtraction
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("+")) {
                            double sum = Double.parseDouble(list.get(i - 1)) + (Double.parseDouble(list.get(i + 1)));
                            list.set(i - 1, sum + "");
                            list.remove(i);
                            list.remove(i);
                            i--;
                        } else if (list.get(i).equals("-")) {
                            double difference = Double.parseDouble(list.get(i - 1)) - (Double.parseDouble(list.get(i + 1)));
                            list.set(i - 1, difference + "");
                            list.remove(i);
                            list.remove(i);
                            i--;
                        }
                    }
                    answer = (Double.parseDouble(list.get(0)));
                    text.setText(answer + "");
                    list.clear();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
                list.clear();
                typable = true;
                errorPresent = false;
                undefined = false;
            }
        });

    }
    public void onClick (View v) {
        if(typable) {
            switch (v.getId()) {
                case R.id.button0:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>0</sup>"));
                    }
                    else {
                        text.append("0");
                    }
                    break;
                case R.id.button1:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>1</sup>"));
                    }
                    else {
                        text.append("1");
                    }
                    break;
                case R.id.button2:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>2</sup>"));
                    }
                    else {
                        text.append("2");
                    }
                    break;
                case R.id.button3:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>3</sup>"));
                    }
                    else {
                        text.append("3");
                    }
                    break;
                case R.id.button4:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>4</sup>"));
                    }
                    else {
                        text.append("4");
                    }
                    break;
                case R.id.button5:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>5</sup>"));
                    }
                    else {
                        text.append("5");
                    }
                    break;
                case R.id.button6:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>6</sup>"));
                    }
                    else {
                        text.append("6");
                    }
                    break;
                case R.id.button7:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>7</sup>"));
                    }
                    else {
                        text.append("7");
                    }
                    break;
                case R.id.button8:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>8</sup>"));
                    }
                    else {
                        text.append("8");
                    }
                    break;
                case R.id.button9:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup>9</sup>"));
                    }
                    else {
                        text.append("9");
                    }
                    break;
                case R.id.buttonDivide:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup> / </sup>"));
                    }
                    else {
                        text.append("/");
                    }
                    break;
                case R.id.buttonPlus:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup> + </sup>"));
                    }
                    else {
                        text.append("+");
                    }
                    break;
                case R.id.buttonMinus:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup> - </sup>"));
                    }
                    else {
                        text.append("-");
                    }
                    break;
                case R.id.buttonMultiply:
                    if(exponentOn) {
                        text.append(Html.fromHtml("<sup> * </sup>"));
                    }
                    else {
                        text.append("*");
                    }
                    break;
                case R.id.decimal:
                    text.append(".");
                    break;
            }
        }
    }

}
