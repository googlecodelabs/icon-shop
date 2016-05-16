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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ItemCardActivity extends AppCompatActivity {
    public static final String INTENT_EXTRA_ITEM = "item";
    private App.ItemData mItem;
    private TextView mItemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mItemDescription = (TextView) findViewById(R.id.item_description);

        mItem = App.getItem(getIntent().getStringExtra(INTENT_EXTRA_ITEM));

        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItem == null) return;
                App.addItemToCart(mItem);
                Snackbar ok = Snackbar.make(view, "Icon \"" + mItem.mName + "\" added to cart", Snackbar.LENGTH_LONG)
                        .setAction("Home", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                NavUtils.navigateUpFromSameTask(ItemCardActivity.this);
                            }
                        });
                ok.setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        NavUtils.navigateUpFromSameTask(ItemCardActivity.this);
                    }
                });
                ok.show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mItem != null) {
            setTitle(mItem.mName + " icon");
            mItemDescription.setText(mItem.mDescription);
        }
    }

    public static void startActivity(Context context, App.ItemData item) {
        Intent intent = new Intent(context, ItemCardActivity.class);
        intent.putExtra(INTENT_EXTRA_ITEM, item.mName);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                NavUtils.navigateUpFromSameTask(this);
                break;
            }
            case R.id.nav_cart: {
                CartActivity.startActivity(this);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
