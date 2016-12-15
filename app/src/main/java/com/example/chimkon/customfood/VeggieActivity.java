package com.example.chimkon.customfood;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class VeggieActivity extends Activity {

    //    Button effect;
    ObjectAnimator objectanimator1;
    ImageView img1;
    RadioGroup radioGroup;
    private String Veggie;
    LinearLayout linearLayout;
    public static ArrayList<String[]> pizzaOrder = new ArrayList<>();
    RelativeLayout relativeLayout;
    List<CheckBox> selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veggie);


//        effect = (Button) findViewById(R.id.button1);
        img1 = (ImageView) findViewById(R.id.imgVeggie);
        objectanimator1 = ObjectAnimator.ofFloat(img1, "y", 750);

        img1.setVisibility(View.INVISIBLE);
        CheckBox checkBox;
//        RadioButton radioButton;
//        radioGroup = (RadioGroup) findViewById(R.id.pizzaveggie);
        String[] pizzaveggie = {"BLACK OLVIVES", "CHERRY TOMATO", "FRESH BASIL",
                "GARLIC", "GREEN BELL PEPPER", "KALAMATA OLIVES", "MUSHROOMS",
                "PINEAPPLE", "RED ONION", "RED BELL PEPPER", "SPINACH", "OREGAMO"};

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_veggie);
        linearLayout = (LinearLayout) findViewById(R.id.layoutveggie);
        selectedItem = new ArrayList<>();


        // check multiple boxes
        for (int i = 0; i < pizzaveggie.length; i++) {
            checkBox = new CheckBox(this);
            checkBox.setText(pizzaveggie[i]);
            checkBox.setTextSize(18);
            final CheckBox finalCheckBox = checkBox;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalCheckBox.isChecked()) {
//                        Toast.makeText(getApplicationContext(), finalCheckBox.getText().toString(), Toast.LENGTH_SHORT).show();
                        selectedItem.add(finalCheckBox);
                        img1.setVisibility(View.VISIBLE);
                        objectanimator1.setDuration(1500);
                        objectanimator1.start();
                        // check TEXT of selected checkbox
//                        for(CheckBox p: selectedItem)
//                        {
//                            System.out.println(p.getText().toString());
//                        }
                    } else {
//                        Toast.makeText(getApplicationContext(), "REMOVED", Toast.LENGTH_SHORT).show();
                        selectedItem.remove(finalCheckBox);
                    }
                }
            });
//            checkBox.setPadding(0, 20, 0, 20);
            linearLayout.addView(checkBox, i);
        }
    }

    public void gotoMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //get checkbox(s)
        for (CheckBox checkBox : selectedItem) {
            PizzaDetails.Veggie.add(checkBox.getText().toString());
        }

        StringBuilder builder = new StringBuilder();
        for (String veg : PizzaDetails.Veggie) {
            builder.append(veg);
            builder.append(", ");
        }

        pizzaOrder.add(new String[]{PizzaDetails.Dough, PizzaDetails.Sauce, PizzaDetails.Cheese, PizzaDetails.Meat,
                builder.toString()});

        System.out.println(pizzaOrder.toString());

        // get VEGGIE value
//        int checked = radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButtonSelected = (RadioButton) findViewById(checked);
//        String value = radioButtonSelected.getText().toString();
//        PizzaDetails.Veggie = value;

//        pizzaOrder.add(new String[]{PizzaDetails.Dough, Sauce,
//                PizzaDetails.Cheese, PizzaDetails.Meat, PizzaDetails.Veggie});
////////////////////////////// ^^^ old code ^^^


    }

    public void backtoMeatActivity(View view) {
        CustomPizzaActivity customPizzaActivity = CustomPizzaActivity.getInstance();
        customPizzaActivity.setTabInstance(3);
    }
}

