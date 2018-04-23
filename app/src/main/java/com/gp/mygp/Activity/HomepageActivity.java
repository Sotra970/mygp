package com.gp.mygp.Activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gp.mygp.AppController;
import com.gp.mygp.Fragment.AboutFragment;
import com.gp.mygp.Fragment.ApplicationsFragment;
import com.gp.mygp.Fragment.ContactFragment;
import com.gp.mygp.Fragment.HomepageFragment;
import com.gp.mygp.Fragment.NotificationsFragment;
import com.gp.mygp.Fragment.ProfileFragment;
import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class HomepageActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_layout)
    View search_layout;
    @BindView(R.id.search)
    android.support.v7.widget.SearchView searchView;

    private Fragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        FirebaseMessaging.getInstance().subscribeToTopic(AppController.getInstance().getPrefManager().getUser().getId()+"");
        initNavDrawer();
        initSearch();
        showHomepage();
    }

    @OnClick(R.id.search_icon)
    void search_icon_click(){
        String query = searchView.getQuery().toString();
        if(query == null || TextUtils.isEmpty(query))
            clearSearch();
        else
            doSearch(query);

        searchView.clearFocus();
    }

    private void initSearch() {
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.searc_hint));
        ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
        closeButton.setColorFilter(
                ResourcesCompat.getColor(
                        getResources(),
                        R.color.grey_900,
                        null
                ),
                PorterDuff.Mode.SRC_IN
        );
        final EditText et = searchView.findViewById(R.id.search_src_text);
        et.setBackground(null);
        et.setTextColor(
                ResourcesCompat.getColor(
                        getResources(),
                        R.color.grey_900,
                        null
                )
        );
        et.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        closeButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //Clear the text from EditText view
                        et.setText("");

                        //Clear query
                        searchView.setQuery("", false);

                        clearSearch();
                    }
                });
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query == null || query.isEmpty())
                    clearSearch();
                else
                    doSearch(query);

                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    private void clearSearch() {
        try {
            ((HomepageFragment) currentFragment).clearSearch();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doSearch(String query) {
        try {
            ((HomepageFragment) currentFragment).doSearch(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initNavDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    public void showFragment(Fragment fragment){
        currentFragment = fragment;
        if(fragment != null){
            switchSearch(fragment instanceof HomepageFragment);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }



    private void switchSearch(boolean b) {
        search_layout.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    private void showHomepage(){
        Fragment home = HomepageFragment.getInstance();
        showFragment(home );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id){
            case R.id.nav_notifications:
                fragment = NotificationsFragment.getInstance();
                break;
            case R.id.nav_about:
                fragment = AboutFragment.getInstance();
                break;
            case R.id.nav_contact:
                fragment = ContactFragment.getInstance();
                break;
            case R.id.nav_applications:
                fragment = ApplicationsFragment.getInstance();
                break;
    /*        case R.id.nav_profile:
                fragment = ProfileFragment.getInstance();*/
//                break;
            case R.id.nav_home:
                fragment = HomepageFragment.getInstance();
                break;
            case R.id.nav_sign:
                AppController.getInstance().getPrefManager().clear(true);
                break;
        }

        if(fragment != null){
            showFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

