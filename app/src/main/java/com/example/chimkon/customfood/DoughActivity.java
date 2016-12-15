package com.example.chimkon.customfood;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DoughActivity extends Activity {

    ObjectAnimator objectanimator1;
    ImageView img1;
    private String Dough;
    TextView textView;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dough);

        // set up animation
        img1 = (ImageView) findViewById(R.id.imgDough);
        img1.setVisibility(View.INVISIBLE);
        objectanimator1 = ObjectAnimator.ofFloat(img1, "y", 750);

        RadioButton radioButton;
        radioGroup = (RadioGroup) findViewById(R.id.pizzadough);
        String[] pizzadough = {"REGULAR DOUGH","THICK DOUGH","GLUTEN-FREE DOUGH"};
        String[] picturedough = {};
        // create button
        String[] detaildough = {"Traditional pizza dough", "Extra dough for thickness", "Dough for gluten allergy"};
        for (int i = 0; i < pizzadough.length; i++)
        {
            radioButton = new RadioButton(this);
            radioButton.setText(pizzadough[i]);
            radioButton.setTextSize(18);
            radioButton.setId(i);
            radioGroup.addView(radioButton);

            textView = new TextView(this);
            textView.setText(detaildough[i]);
            textView.setTextSize(10);
            textView.setPadding(40,0,0,0);
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

    }

    public void gotoSauceActivity (View view) {
//        Intent intent = new Intent(this, SauceActivity.class);
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(1);

        // get DOUGH value
        int checked = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSelected = (RadioButton)findViewById(checked);
        String value = radioButtonSelected.getText().toString();
        PizzaDetails.Dough = value;
    }
}
