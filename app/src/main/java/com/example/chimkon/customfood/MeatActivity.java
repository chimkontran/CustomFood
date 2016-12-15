package com.example.chimkon.customfood;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MeatActivity extends Activity {

    //    Button effect;
    ObjectAnimator objectanimator1;
    ImageView img1;
    private String Meat;
    RadioGroup radioGroup;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat);

//        effect = (Button) findViewById(R.id.button1);
        img1 = (ImageView) findViewById(R.id.imgMeat);
        img1.setVisibility(View.INVISIBLE);
        objectanimator1 = ObjectAnimator.ofFloat(img1, "y", 750);
        RadioButton radioButton;
        radioGroup = (RadioGroup)findViewById(R.id.pizzameat);

        String[] pizzameat = {"PEPPERONI","GRILLED CHICKEN","SMOKED HAM","ITALIAN SAUSAGE",
                "CRUMBLED MEATBALL","BACON STRIPS","GROUND BEEF"};

        int[] image = {R.drawable.pepperoni, R.drawable.grilled_chicken, R.drawable.smoked_ham,
                R.drawable.italian_sausage, R.drawable.crumbled_meatball, R.drawable.bacon_strips,
                R.drawable.ground_beef};

        // create button
        for (int i = 0; i < pizzameat.length; i++)
        {
            radioButton = new RadioButton(this);
            radioButton.setText(pizzameat[i]);
            radioButton.setTextSize(18);
            radioButton.setId(i);
            radioGroup.addView(radioButton);

            imageView = new ImageView(this);
            imageView.setMaxWidth(30);
            imageView.setMaxHeight(30);
            imageView.setImageResource(image[i]);
            radioGroup.addView(imageView);

            // run animation on click
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

    public void gotoVeggieActivity(View view) {
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(4);

        // get MEAT value
        int checked = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSelected = (RadioButton)findViewById(checked);
        String value = radioButtonSelected.getText().toString();
        PizzaDetails.Meat = value;
    }

    public void backtoCheeseActivity (View view) {
//        Intent intent = new Intent(this, CheeseActivity.class);
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(2);
    }

}
