package com.example.baeza.fatherhome.ui.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.helper.TabsAdapter;
import com.example.baeza.fatherhome.ui.view.about.AboutActivity;
import com.example.baeza.fatherhome.ui.view.audioSpeech.AudioSpeechListActivity;
import com.example.baeza.fatherhome.ui.view.bible.BibleFragment;
import com.example.baeza.fatherhome.ui.view.churchMap.ChurchMapActivity;
import com.example.baeza.fatherhome.ui.view.dailyBread.DailyBreadFragment;
import com.example.baeza.fatherhome.ui.view.favorite.FavoriteActivity;
import com.example.baeza.fatherhome.ui.view.licences.LicenceActivity;
import com.example.baeza.fatherhome.ui.view.onlineTransmission.OnlineTransmissionActivity;
import com.example.baeza.fatherhome.ui.view.ourPastor.PastorListActivity;
import com.example.baeza.fatherhome.ui.view.songs.SongListActivity;
import com.example.baeza.fatherhome.ui.view.sos.SosTabBibleFragment;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());
        ButterKnife.bind(this);


        if(savedInstanceState ==  null) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        initToolbar();

        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);

        setActionBar();
        setNavigationDrawer();
    }

    private void setImageInHeader(){
        View hView = mNavigationView.getHeaderView(0);
        ImageView imageViewHeader = hView.findViewById(R.id.imageViewHeader);
        imageViewHeader.setImageResource(R.drawable.logofatherhome);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(new SosTabBibleFragment(), getString(R.string.sos_fragment_title));
        adapter.addFragment(new DailyBreadFragment(), getString(R.string.daily_bread_title));
        adapter.addFragment(new BibleFragment(), getString(R.string.bible_fragment));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
            if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        String savedLanguage = "";
        switch (id){
            case R.id.russian_lang:{
                saveLanguageToPrefs(Constants.RUSSIAN_LANGUAGE);
                savedLanguage = getString(R.string.selected_rus_language);
                showSnackBarBySetLanguage(savedLanguage);
                break;
            }
            case R.id.english_lang:{
                saveLanguageToPrefs(Constants.ENGLISH_LANGUAGE);
                savedLanguage = getString(R.string.selected_eng_language);
                showSnackBarBySetLanguage(savedLanguage);
                break;
            }
            case R.id.spanish_lang:{
                saveLanguageToPrefs(Constants.SPANISH_LANGUAGE);
                savedLanguage = getString(R.string.selected_es_language);
                showSnackBarBySetLanguage(savedLanguage);
                break;
            }
            case R.id.portuguese_lang:{
                saveLanguageToPrefs(Constants.PORTUGUESE_LANGUAGE);
                savedLanguage = getString(R.string.selected_pt_language);
                showSnackBarBySetLanguage(savedLanguage);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSnackBarBySetLanguage(String savedLanguage){
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, savedLanguage, Snackbar.LENGTH_SHORT);
        snackbar.show();
        setupViewPager(viewPager);
    }

    private void setNavigationDrawer(){
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        switch (menuItem.getItemId()){
                            case R.id.nav_speech:{
                                intent = new Intent(MainActivity.this, AudioSpeechListActivity.class);break; }
                            case R.id.nav_songs:{
                                intent = new Intent(MainActivity.this, SongListActivity.class);break; }
                            case R.id.nav_our_pastors:{
                                intent = new Intent(MainActivity.this,PastorListActivity.class);break; }
                            case R.id.nav_find_local_church:{
                                intent = new Intent(MainActivity.this, ChurchMapActivity.class);break; }
                            case R.id.nav_church_about:{
                                intent = new Intent(MainActivity.this, AboutActivity.class);break; }
                            case R.id.nav_online_transmission:{
                                intent = new Intent(MainActivity.this, OnlineTransmissionActivity.class);break; }
                            case R.id.nav_licenses:{
                                intent = new Intent(MainActivity.this, LicenceActivity.class);break; }
                            case R.id.nav_favorite:{
                                intent = new Intent(MainActivity.this, FavoriteActivity.class);break; }
                        }
                        startActivity(intent);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        setImageInHeader();
    }

    private void setActionBar(){
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), android.R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        }
    }

    private void saveLanguageToPrefs(String stringLanguage){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_LANG, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.SHARED_PREF_LANG, stringLanguage);
        editor.commit();
    }
}
