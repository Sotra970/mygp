package com.gp.mygp.Util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class PagerSwitcher {

    private Context context;
    private ViewPager viewPager;

    private Timer timer;
    private int page = 0;
    private int max;

    private boolean run = false;

    public PagerSwitcher(Context context, ViewPager viewPager, int max) {
        this.context = context;
        this.viewPager = viewPager;
        this.max = 2;
    }

    public void startSwitching(){
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, 3000); // delay in milliseconds
    }

    public void setPage(int page) {
        this.page = page;
        this.timer.cancel();
        startSwitching();
    }

    private class RemindTask extends TimerTask {


        @Override
        public void run() {


            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            if(run){
                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                if (page >= max) {
                                    page = 0;
                                    viewPager.setCurrentItem(page, true);
                                }
                                else {
                                    viewPager.setCurrentItem(page++, true);
                                }
                            }
                        }
                );
            }

        }
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
