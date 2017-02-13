package com.example.mehdi.kashmirobserver.View;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mehdi.kashmirobserver.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DefinitionControl();
        NavigationView();

    }

    private void DefinitionControl() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff1);
    }

    private void NavigationView() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                FragmentTransaction fragmentTransaction;
                switch (menuItem.getItemId()) {

                    case R.id.nav_live:
                        fragmentTransaction = mFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                        break;
                    case R.id.nav_category:
                        Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_more:
                        Toast.makeText(MainActivity.this, "More", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_about:
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();

                        break;
                }

                return false;
            }

        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void Search(View v) {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();

    }
    public void moreMenu(View v) {
        final PopupMenu popup = new PopupMenu(MainActivity.this, v);
        final MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.show();
    }

}