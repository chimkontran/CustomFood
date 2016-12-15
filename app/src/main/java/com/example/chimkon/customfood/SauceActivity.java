package com.example.chimkon.customfood;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SauceActivity extends Activity {

    ObjectAnimator objectanimator1;
    ImageView img1;
    TextView textView;
    private String Sauce;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauce);

        // set up animation
        img1 = (ImageView) findViewById(R.id.imgSauce);
        img1.setVisibility(View.INVISIBLE);
        objectanimator1 = ObjectAnimator.ofFloat(img1, "y", 750);

        RadioButton radioButton;
        radioGroup = (RadioGroup) findViewById(R.id.pizzasauce);

        String[] pizzasauce = {"TOMATO SAUCE", "CHILI SAUCE", "WHITE CREAM SAUCE",
                "GARLIC PESTO CREAM", "NO SAUCE"};

        String[] detailsauce = {"Sauce seasoned tomato with salt, onion, garlic and herbs",
                "Spicy sauce made with fresh chilies",
                "Made from butter, salt, milk, flour and cheese",
                "A blend of basil, nuts, garlic and cheese", ""};
        // create button
        for (int i = 0; i < pizzasauce.length; i++) {
            radioButton = new RadioButton(this);
            radioButton.setText(pizzasauce[i]);
            radioButton.setTextSize(18);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
            textView = new TextView(this);
            textView.setText(detailsauce[i]);
            textView.setTextSize(10);
            textView.setPadding(40, 0, 0, 0);
            radioGroup.addView(textView);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img1.setVisibility(View.VISIBLE);
                    objectanimator1.setDuration(1500);
                    objectanimator1.start();
                }
            });
        }
        radioGroup.check(0);

//        int checked = radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButtonSelected = (RadioButton)findViewById(checked);
//        String values = radioButtonSelected.getText().toString();


    }

    public void gotoCheeseActivity(View view) {
//        Intent intent = new Intent(this, CheeseActivity.class);
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(2);

        int checked = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSelected = (RadioButton) findViewById(checked);
        String value = radioButtonSelected.getText().toString();
        PizzaDetails.Sauce = value;
    }

    public void backtoDoughActivity(View view) {
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(0);
    }
}
