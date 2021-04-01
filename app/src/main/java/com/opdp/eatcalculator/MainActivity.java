package com.opdp.eatcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Fragment previousFragment;
    Fragment mainFragment;

    TextSwitcher title;
    String mainActivityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        setSupportActionBar(toolbar);
        //Костыль, дабдабя
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationOnClickListener(this::backFragment);

        title = findViewById(R.id.ToolbarTitle);

        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currentHour < 6) {
            mainActivityText = "Доброй ночи";
        } else if (currentHour < 13) {
            mainActivityText = "Доброе утро";
        } else if (currentHour < 18) {
            mainActivityText = "Добрый день";
        } else {
            mainActivityText = "Добрый вечер";
        }
        title.setCurrentText(mainActivityText);

        mainFragment = MainFragment.newInstance("", "");

        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,
                mainFragment).commit();
    }

    public void onClick(View view) {
        System.out.println("1");
        getClickFromFragment(view.getId());
    }

    public void getClickFromFragment(int idButton) {
        int typeOfCategory = -1;
        String nameOfFragment = "NULL";
        switch (idButton) {
            case (R.id.recipe_card):
                typeOfCategory = CategoryFragment.CATEGORY_OF_RECIPES;
                nameOfFragment = getString(R.string.recipe);
                break;
            case (R.id.products_card):
                typeOfCategory = CategoryFragment.CATEGORY_OF_PRODUCTS;
                nameOfFragment = getString(R.string.products);
                break;
            case (R.id.favorite_card):
                typeOfCategory = CategoryFragment.FAVORITE;
                nameOfFragment = getString(R.string.favorite);
        }
        changeFragment(CategoryFragment.newInstance(typeOfCategory, ""), nameOfFragment, true);
    }

    //true - next, false - back
    private void changeFragment(Fragment fragment, String arg, boolean nextOrBack) {
        if (previousFragment != null && previousFragment.equals(mainFragment) && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            previousFragment = null;
        }
        else if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            previousFragment = getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, fragment).commit();
        changeText(arg, nextOrBack);
    }

    void changeText(String text, boolean nextOrBack) {
        if (nextOrBack) {
            title.setInAnimation(this, R.anim.slide_up_in);
            title.setOutAnimation(this, R.anim.slide_up_out);
        } else {
            title.setInAnimation(this, R.anim.slide_down_in);
            title.setOutAnimation(this, R.anim.slide_down_out);
        }
        title.setText(text);
    }
    //TODO: выпилить/переписать ненужную функцию
    public void backFragment(View view) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, previousFragment).commit();
        changeFragment(previousFragment, mainActivityText, false);

    }

    @Override
    public void onBackPressed() {
        if (previousFragment == null)
            super.onBackPressed();
        else
            backFragment(null);
    }
}