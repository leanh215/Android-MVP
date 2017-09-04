package vn.nano.androidmvp.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.nano.androidmvp.R;
import vn.nano.androidmvp.data.Callback;
import vn.nano.androidmvp.data.model.User;
import vn.nano.androidmvp.databinding.ItemUserBinding;

/**
 * Created by alex on 9/4/17.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    List<User> users;
    Callback<User> onClickUser;

    public UserAdapter(Callback<User> onClickUser) {
        users = new ArrayList<>();
        this.onClickUser = onClickUser;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_user, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemUserBinding binding = ((UserViewHolder)holder).binding;
        User user = users.get(position);
        binding.setUser(user);
        binding.imgAvatar.setImageURI(user.getAvatarUrl());
        binding.getRoot().setTag(user);
        binding.getRoot().setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onClick(View view) {
        User user = (User) view.getTag();
        onClickUser.onCallback(user);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding binding;

        public UserViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
