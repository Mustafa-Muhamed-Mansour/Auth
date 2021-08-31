package com.authentication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.authentication.R;
import com.authentication.model.UserModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{

    ArrayList<UserModel> userModels;

    public UserAdapter(ArrayList<UserModel> userModels)
    {
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position)
    {

       UserModel model = userModels.get(position);
       holder.textUserName.setText(model.getUserName());
       holder.textUserEmail.setText(model.getUserEmail());
       holder.textUserPhone.setText(model.getUserPHone());
       Glide
               .with(holder.itemView.getContext())
               .load(model.getUserImage())
               .into(holder.userImage);

       holder.itemView.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {
               Toast.makeText(holder.itemView.getContext(), "user name = " + model.getUserName(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    @Override
    public int getItemCount()
    {
        return userModels.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {

        private CircleImageView userImage;
        private TextView textUserName, textUserEmail, textUserPhone;

        public UserViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userImage = itemView.findViewById(R.id.circle_item_user_img);
            textUserName = itemView.findViewById(R.id.txt_item_user_name);
            textUserEmail = itemView.findViewById(R.id.txt_item_user_email);
            textUserPhone = itemView.findViewById(R.id.txt_item_user_phone);

        }
    }
}
