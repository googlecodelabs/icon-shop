// Copyright 2016 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//      http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.googlecodelabs.icon_shop;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView.LayoutManager mItemsLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Button mSelectProfessionButton;

    private RecyclerView getItemsRecycleView() {
        return (RecyclerView) findViewById(R.id.items_list);
    }

    private NavigationView getNavigationView() {
        return (NavigationView) findViewById(R.id.nav_view);
    }

    private FloatingActionButton getFloatingActonButton() {
        return (FloatingActionButton) findViewById(R.id.fab);
    }

    private Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    private DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSelectProfessionButton = (Button) findViewById(R.id.select_profession_button);


        Toolbar toolbar = getToolbar();
        setSupportActionBar(toolbar);

        DrawerLayout drawer = getDrawerLayout();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = getNavigationView();
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView searchResult = getItemsRecycleView();
        searchResult.setHasFixedSize(true);
        mItemsLayoutManager = new LinearLayoutManager(this);
        searchResult.setLayoutManager(mItemsLayoutManager);
        mAdapter = new ItemsListAdapter(App.getDataSet(), false);
        searchResult.setAdapter(mAdapter);

        mSelectProfessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_profession, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onProfessionSelected(item);
                        return true;
                    }
                });
                popup.show();
            }
        });
        mSelectProfessionButton.setVisibility(View.GONE);
        AsyncTask<Void, Void, Void> checkProfessionSelected = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String profession = App.getProfession();
                if (profession == null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSelectProfessionButton.setVisibility(View.VISIBLE);
                        }
                    });
                }
                return null;
            }
        }.execute();
    }

    private void onProfessionSelected(MenuItem item) {
        final String profession = item.getTitle().toString();
        AsyncTask<Void, Void, Void> save = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                App.setProfession(profession);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSelectProfessionButton.setVisibility(View.GONE);
                    }
                });
                return null;
            }
        }.execute();
    }

    public void showSnackbar(String text) {
        Snackbar.make(getToolbar(), text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = getDrawerLayout();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings: {
                showSnackbar("Settings");
                return true;
            }
            case R.id.nav_cart: {
                CartActivity.startActivity(this);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_cart: {
                startActivity(new Intent(this, CartActivity.class));
                break;
            }
            case R.id.nav_manage: {
                showSnackbar("Tools");
                break;
            }
        }

        DrawerLayout drawer = getDrawerLayout();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
