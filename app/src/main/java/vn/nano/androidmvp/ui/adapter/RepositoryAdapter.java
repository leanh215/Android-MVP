package vn.nano.androidmvp.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.nano.androidmvp.R;
import vn.nano.androidmvp.data.model.Repository;
import vn.nano.androidmvp.databinding.ItemRepositoryBinding;

/**
 * Created by alex on 9/4/17.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Repository> repositories;

    public RepositoryAdapter() {
        repositories = new ArrayList<>();
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepositoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        ((RepositoryViewHolder)holder).binding.setRepository(repository);
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        ItemRepositoryBinding binding;

        public RepositoryViewHolder(ItemRepositoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
