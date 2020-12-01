package com.example.bookland.Recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookland.Activity.InsertToFirebase;
import com.example.bookland.Book;
import com.example.bookland.Activity.BookDetail;
import com.example.bookland.R;
import com.example.bookland.TabLayout.TopFragment;
import com.example.bookland.User;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyView> {
    ArrayList<Book> mData;
    Context context;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TopFragment topFragment = new TopFragment();
    private boolean check = false;

    public RecyclerViewAdapter(Context context, ArrayList<Book> mData){
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_top, parent, false);
        return new MyView(view);    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {
        holder.name.setText(mData.get(position).getName());
        holder.price.setText(mData.get(position).getPrice());
        holder.rating.setText(mData.get(position).getRating());
        Glide.with(context).load(mData.get(position).getImage()).into(holder.image);

        if(holder.saved.getDrawable().getConstantState() == context.getDrawable(R.drawable.bookmark).getConstantState()){
           check = false;
        }else{
            check = true;
        }
        holder.saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==false){
                    check = true;
                    holder.saved.setImageResource(R.drawable.ic_baseline_bookmark_24);
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Book_Saved");
                    /*
                    String name = mData.get(position).getName();
                    String author = mData.get(position).getAuthor();
                    String price = mData.get(position).getPrice();
                    String category = mData.get(position).getCagegory();
                    String rating = mData.get(position).getRating();
                    String description = mData.get(position).getDescription();
                    String image = mData.get(position).getImageUrl();
                    User user = new User(name, author, price,category, rating, description, image);

                     */
                    reference.child(mData.get(position).getName()).setValue(mData.get(position));
                }else{
                    holder.saved.setImageResource(R.drawable.bookmark);
                    reference = FirebaseDatabase.getInstance().getReference("Book_Saved").child(mData.get(position).getName());
                    reference.removeValue();
                    check = false;
                }

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("name", mData.get(position).getName());
                intent.putExtra("price", mData.get(position).getPrice());
                intent.putExtra("image", mData.get(position).getImage());
                intent.putExtra("rating", mData.get(position).getRating());
                intent.putExtra("description", mData.get(position).getDescription());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class MyView extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, price, rating;
        ImageView image, saved;
        @SuppressLint("UseCompatLoadingForDrawables")
        public MyView(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.gridlayout);
            name = itemView.findViewById(R.id.name_id);
            price = itemView.findViewById(R.id.fee_id);
            image = itemView.findViewById(R.id.img_grid);
            rating = itemView.findViewById(R.id.rating_id);
            saved = itemView.findViewById(R.id.saved_id);

        }
    }
}
