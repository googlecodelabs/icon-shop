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
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter {
    public final List<App.ItemData> mDataSet;
    private final boolean mAllowRemoveFromCart;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIconView;
        private final TextView mTitleView;
        private final TextView mInfoView;
        private final TextView mPriceView;
        private final CardView mCardView;
        private final ImageButton mRemoveFromCardButton;
        public App.ItemData mItem;

        public ViewHolder(View view) {
            super(view);
            mCardView = (CardView) view.findViewById(R.id.card_view);
            mIconView = (ImageView) view.findViewById(R.id.item_icon);
            mTitleView = (TextView) view.findViewById(R.id.title_text);
            mInfoView = (TextView) view.findViewById(R.id.info_text);
            mPriceView = (TextView) view.findViewById(R.id.price);
            mRemoveFromCardButton = (ImageButton) view.findViewById(R.id.remove_from_cart);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    ItemCardActivity.startActivity(context, mItem);
                }
            });

            mRemoveFromCardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItem == null) return;
                    App.removeItemFromCart(mItem);
                    int position = mDataSet.indexOf(mItem);
                    mDataSet.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }

    public ItemsListAdapter(List<App.ItemData> dataSet, boolean allowRemoveFromCart) {
        mDataSet = new ArrayList<>(dataSet);
        mAllowRemoveFromCart = allowRemoveFromCart;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        ViewHolder holder = new ViewHolder(itemCard);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder item = (ViewHolder) holder;
        App.ItemData itemData = mDataSet.get(position);
        String text = itemData.mName;
        item.mTitleView.setText(text);
        item.mInfoView.setText(itemData.mDescription);
        item.mIconView.setImageResource(itemData.mResourceId);
        item.mPriceView.setText(itemData.mPrice + " credits");
        item.mRemoveFromCardButton.setVisibility(mAllowRemoveFromCart ? View.VISIBLE : View.GONE);
        item.mItem = itemData;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
