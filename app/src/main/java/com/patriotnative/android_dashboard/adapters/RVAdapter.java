package com.patriotnative.android_dashboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.patriotnative.android_dashboard.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewObject> {

    private Context mContext;
    private List<String> list;

    public RVAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public class CardViewObject extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productPrice;
        CardView productCard;
        ImageView productImage;
        ImageView productOption;

        public CardViewObject(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productCard = itemView.findViewById(R.id.productCard);
            productImage = itemView.findViewById(R.id.productImage);
            productOption = itemView.findViewById(R.id.productOption);
        }
    }

    @NonNull
    @Override
    public CardViewObject onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new CardViewObject(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObject holder, int position) {
        final String product = list.get(position);

        holder.productName.setText(product);

        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Chosen product => productCard " + product, Toast.LENGTH_SHORT).show();
            }
        });

        holder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Chosen product => productImage " + product, Toast.LENGTH_SHORT).show();
            }
        });

        holder.productOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, v);
                popupMenu.getMenuInflater().inflate(R.menu.card_menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deleteProduct) {
                            Toast.makeText(mContext, "Product deleted " + product, Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
