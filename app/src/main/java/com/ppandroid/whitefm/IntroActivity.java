package com.ppandroid.whitefm;


import com.antonioleiva.mvpexample.app.R;
import com.chyrta.onboarder.OnboarderActivity;
import com.chyrta.onboarder.OnboarderPage;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;

/**
 * 欢迎页面
 * Created by yeqinfu on 2016/4/7.
 */
public class IntroActivity extends OnboarderActivity {

    List<OnboarderPage> onboarderPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboarderPages = new ArrayList<>();

        // Create your first page
        OnboarderPage onboarderPage1 = new OnboarderPage("Title 1", "Description 1");
        OnboarderPage onboarderPage2 = new OnboarderPage(R.string.app_title, R.string.app_description, R.drawable.ic_launcher);

        // You can define title and description colors (by default white)
        onboarderPage1.setTitleColor(R.color.colorPrimary);
        onboarderPage1.setDescriptionColor(R.color.white);

        // Don't forget to set background color for your page
        onboarderPage1.setBackgroundColor(R.color.colorPrimaryDark);

        // Add your pages to the list
        onboarderPages.add(onboarderPage1);
        onboarderPages.add(onboarderPage2);

        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);

    }

    @Override
    public void onSkipButtonPressed() {
        // Define your actions when the user press 'Skip' button
        Intent intent=new Intent();
        intent.setClass(IntroActivity.this,AC_Main.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFinishButtonPressed(){
        // Define your actions when the user press 'Finish' button
        Intent intent=new Intent();
        intent.setClass(IntroActivity.this,AC_Main.class);
        startActivity(intent);
        finish();
    }
}

