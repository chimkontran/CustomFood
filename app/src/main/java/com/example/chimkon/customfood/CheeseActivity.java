package com.example.chimkon.customfood;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CheeseActivity extends Activity {

    ObjectAnimator objectanimator1;
    ImageView img1;
    private String Cheese;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese);

        // set up animation
        img1 = (ImageView) findViewById(R.id.imgCheese);
        img1.setVisibility(View.INVISIBLE);
        objectanimator1 = ObjectAnimator.ofFloat(img1, "y", 750);

        radioGroup = (RadioGroup)findViewById(R.id.pizzacheese);
        String[] pizzacheese = {"GOAT CHEESE","VEGAN CHEESE","FETA CHEESE","RICOTTA","NO CHEESE"};
        // create button
        RadioButton radioButton;
        for (int i = 0; i < pizzacheese.length; i++)
        {
            radioButton = new RadioButton(this);
            radioButton.setText(pizzacheese[i]);
            radioButton.setTextSize(18);
            radioButton.setId(i);
            radioGroup.addView(radioButton);

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
    }

    public void gotoMeatActivity (View view) {
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(3);

        // get CHEESE value
        int checked = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSelected = (RadioButton)findViewById(checked);
        String value = radioButtonSelected.getText().toString();
        PizzaDetails.Cheese = value;
    }

    public void backtoSauceActivity (View view) {
//        Intent intent = new Intent(this, SauceActivity.class);
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(1);
    }
}
