package com.example.chimkon.customfood;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

@SuppressWarnings("deprecation")
public class CustomPizzaActivity extends TabActivity {
    private static CustomPizzaActivity tabInstance;

    public static CustomPizzaActivity getInstance() {
        return CustomPizzaActivity.tabInstance;
    }

    public CustomPizzaActivity() {
        CustomPizzaActivity.tabInstance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pizza);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        // create tabhost
        TabHost.TabSpec doughtab = tabHost.newTabSpec("Dough");
        TabHost.TabSpec saucetab = tabHost.newTabSpec("Sauce");
        TabHost.TabSpec cheesetab = tabHost.newTabSpec("Cheese");
        TabHost.TabSpec meattab = tabHost.newTabSpec("Meat");
        TabHost.TabSpec veggietab = tabHost.newTabSpec("Veggie");

        // set tab name and activity
//        doughtab.setIndicator("DoughTab");
        doughtab.setIndicator("", getResources().getDrawable(R.drawable.dough));
        doughtab.setContent(new Intent(this, DoughActivity.class));

//        saucetab.setIndicator("SauceTab");
        saucetab.setIndicator("", getResources().getDrawable(R.drawable.sauce));
        saucetab.setContent(new Intent(this, SauceActivity.class));

//        cheesetab.setIndicator("CheeseTab");
        cheesetab.setIndicator("", getResources().getDrawable(R.drawable.cheese));
        cheesetab.setContent(new Intent(this, CheeseActivity.class));

//        meattab.setIndicator("MeatTab");
        meattab.setIndicator("", getResources().getDrawable(R.drawable.meat));
        meattab.setContent(new Intent(this, MeatActivity.class));

//        veggietab.setIndicator("VeggieTab");
        veggietab.setIndicator("", getResources().getDrawable(R.drawable.veggie));
        veggietab.setContent(new Intent(this, VeggieActivity.class));

        // display tabs in tabhost
        tabHost.addTab(doughtab);
        tabHost.addTab(saucetab);
        tabHost.addTab(cheesetab);
        tabHost.addTab(meattab);
        tabHost.addTab(veggietab);

        TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
        tabWidget.setEnabled(false);
    }

    public void setTabInstance(int id) {
        tabInstance.getTabHost().setCurrentTab(id);
    }
}
