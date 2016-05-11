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

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by georged on 5/5/16.
 */
public class App extends Application {
    private static final String CART = "cart";
    private static final String PROFESSION = "profession";
    static private SharedPreferences mSharedPreferences;

    public static class ItemData implements Comparable<ItemData> {
        public final String mName;
        public final int mResourceId;
        public final int mPrice;
        public final String mDescription;
        static final Random mRandom = new Random();

        static {
            mRandom.setSeed(2016);
        }

        ItemData(String name, int resourceId) {
            this.mName = name;
            this.mResourceId = resourceId;
            this.mPrice = mRandom.nextInt(42 - 10 + 1) + 10;
            this.mDescription = String.format(
                    "24dp by 24dp %1s vector icon from material design icon set",
                    mName.replace("_", " "));
        }

        @Override
        public int compareTo(ItemData itemData) {
            return this.mName.compareTo(itemData.mName);
        }
    }

    static private final List<ItemData> mDataset;

    static {
        mDataset = new ArrayList<>();
        mDataset.add(new ItemData("beenhere", R.drawable.ic_beenhere_black_24dp));
        mDataset.add(new ItemData("bike", R.drawable.ic_directions_bike_black_24dp));
        mDataset.add(new ItemData("directions", R.drawable.ic_directions_black_24dp));
        mDataset.add(new ItemData("boat", R.drawable.ic_directions_boat_black_24dp));
        mDataset.add(new ItemData("add_location", R.drawable.ic_add_location_black_24dp));
        mDataset.add(new ItemData("railway", R.drawable.ic_directions_railway_black_24dp));
        mDataset.add(new ItemData("run", R.drawable.ic_directions_run_black_24dp));
        mDataset.add(new ItemData("subway", R.drawable.ic_directions_subway_black_24dp));
        mDataset.add(new ItemData("transit", R.drawable.ic_directions_transit_black_24dp));
        mDataset.add(new ItemData("walk", R.drawable.ic_directions_walk_black_24dp));
        mDataset.add(new ItemData("edit_location", R.drawable.ic_edit_location_black_24dp));
        mDataset.add(new ItemData("ev_station", R.drawable.ic_ev_station_black_24dp));
        mDataset.add(new ItemData("flight", R.drawable.ic_flight_black_24dp));
        mDataset.add(new ItemData("hotel", R.drawable.ic_hotel_black_24dp));
        mDataset.add(new ItemData("layers", R.drawable.ic_layers_black_24dp));
        mDataset.add(new ItemData("layers_clear", R.drawable.ic_layers_clear_black_24dp));
        mDataset.add(new ItemData("activity", R.drawable.ic_local_activity_black_24dp));
        mDataset.add(new ItemData("airport", R.drawable.ic_local_airport_black_24dp));
        mDataset.add(new ItemData("atm", R.drawable.ic_local_atm_black_24dp));
        mDataset.add(new ItemData("bar", R.drawable.ic_local_bar_black_24dp));
        mDataset.add(new ItemData("cafe", R.drawable.ic_local_cafe_black_24dp));
        mDataset.add(new ItemData("car_wash", R.drawable.ic_local_car_wash_black_24dp));
        mDataset.add(new ItemData("convenience_store", R.drawable.ic_local_convenience_store_black_24dp));
        mDataset.add(new ItemData("dining", R.drawable.ic_local_dining_black_24dp));
        mDataset.add(new ItemData("drink", R.drawable.ic_local_drink_black_24dp));
        mDataset.add(new ItemData("florist", R.drawable.ic_local_florist_black_24dp));
        mDataset.add(new ItemData("gas_station", R.drawable.ic_local_gas_station_black_24dp));
        mDataset.add(new ItemData("grocery_store", R.drawable.ic_local_grocery_store_black_24dp));
        mDataset.add(new ItemData("hospital", R.drawable.ic_local_hospital_black_24dp));
        mDataset.add(new ItemData("laundry_service", R.drawable.ic_local_laundry_service_black_24dp));
        mDataset.add(new ItemData("library", R.drawable.ic_local_library_black_24dp));
        mDataset.add(new ItemData("mall", R.drawable.ic_local_mall_black_24dp));
        mDataset.add(new ItemData("movies", R.drawable.ic_local_movies_black_24dp));
        mDataset.add(new ItemData("offer", R.drawable.ic_local_offer_black_24dp));
        mDataset.add(new ItemData("parking", R.drawable.ic_local_parking_black_24dp));
        mDataset.add(new ItemData("pharmacy", R.drawable.ic_local_pharmacy_black_24dp));
        mDataset.add(new ItemData("phone", R.drawable.ic_local_phone_black_24dp));
        mDataset.add(new ItemData("pizza", R.drawable.ic_local_pizza_black_24dp));
        mDataset.add(new ItemData("play", R.drawable.ic_local_play_black_24dp));
        mDataset.add(new ItemData("post_office", R.drawable.ic_local_post_office_black_24dp));
        mDataset.add(new ItemData("printshop", R.drawable.ic_local_printshop_black_24dp));
        mDataset.add(new ItemData("see", R.drawable.ic_local_see_black_24dp));
        mDataset.add(new ItemData("shipping", R.drawable.ic_local_shipping_black_24dp));
        mDataset.add(new ItemData("taxi", R.drawable.ic_local_taxi_black_24dp));
        mDataset.add(new ItemData("map", R.drawable.ic_map_black_24dp));
        mDataset.add(new ItemData("my_location", R.drawable.ic_my_location_black_24dp));
        mDataset.add(new ItemData("navigation", R.drawable.ic_navigation_black_24dp));
        mDataset.add(new ItemData("near_me", R.drawable.ic_near_me_black_24dp));
        mDataset.add(new ItemData("person_pin", R.drawable.ic_person_pin_black_24dp));
        mDataset.add(new ItemData("person_pin_circle", R.drawable.ic_person_pin_circle_black_24dp));
        mDataset.add(new ItemData("pin_drop", R.drawable.ic_pin_drop_black_24dp));
        mDataset.add(new ItemData("place", R.drawable.ic_place_black_24dp));
        mDataset.add(new ItemData("rate_review", R.drawable.ic_rate_review_black_24dp));
        mDataset.add(new ItemData("restaurant", R.drawable.ic_restaurant_black_24dp));
        mDataset.add(new ItemData("restaurant_menu", R.drawable.ic_restaurant_menu_black_24dp));
        mDataset.add(new ItemData("satellite", R.drawable.ic_satellite_black_24dp));
        mDataset.add(new ItemData("store_mall_directory", R.drawable.ic_store_mall_directory_black_24dp));
        mDataset.add(new ItemData("streetview", R.drawable.ic_streetview_black_24dp));
        mDataset.add(new ItemData("subway", R.drawable.ic_subway_black_24dp));
        mDataset.add(new ItemData("terrain", R.drawable.ic_terrain_black_24dp));
        mDataset.add(new ItemData("traffic", R.drawable.ic_traffic_black_24dp));
        mDataset.add(new ItemData("train", R.drawable.ic_train_black_24dp));
        mDataset.add(new ItemData("tram", R.drawable.ic_tram_black_24dp));
        mDataset.add(new ItemData("transfer_within_a_station", R.drawable.ic_transfer_within_a_station_black_24dp));
        mDataset.add(new ItemData("zoom_out_map", R.drawable.ic_zoom_out_map_black_24dp));
        Collections.sort(mDataset);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    static List<ItemData> getDataSet() {
        return mDataset;
    }

    static ItemData getItem(String name) {
        for (ItemData item : mDataset) {
            if (item.mName.equals(name))
                return item;
        }
        return null;
    }

    static public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    static List<ItemData> getCardContent() {
        SharedPreferences preferences = App.getSharedPreferences();
        Set<String> cart = new TreeSet<String>();
        cart = preferences.getStringSet(CART, cart);
        List<ItemData> items = new ArrayList<>();
        if (!cart.isEmpty()) {
            for (ItemData item : getDataSet()) {
                if (cart.contains(item.mName)) items.add(item);
            }
        }
        return items;
    }

    static void addItemToCart(ItemData item) {
        SharedPreferences preferences = App.getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> cart = new TreeSet<>();
        cart = preferences.getStringSet(CART, cart);
        cart.add(item.mName);
        editor.putStringSet(CART, cart);
        editor.commit();
    }

    static void removeItemFromCart(ItemData item) {
        SharedPreferences preferences = App.getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> cart = new TreeSet<>();
        cart = preferences.getStringSet(CART, cart);
        cart.remove(item.mName);
        editor.putStringSet(CART, cart);
        editor.commit();
    }

    @WorkerThread
    static void clearCart() {
        SharedPreferences preferences = App.getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> cart = new TreeSet<>();
        editor.putStringSet(CART, cart);
        editor.commit();
    }

    @WorkerThread
    static void setProfession(String profession) {
        SharedPreferences preferences = App.getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PROFESSION, profession);
        editor.commit();
    }

    @WorkerThread
    static String getProfession() {
        SharedPreferences preferences = App.getSharedPreferences();
        return preferences.getString(PROFESSION, null);
    }
}
