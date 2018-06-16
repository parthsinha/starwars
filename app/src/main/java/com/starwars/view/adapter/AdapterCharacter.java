package com.starwars.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starwars.R;
import com.starwars.databinding.ItemCharacterBinding;
import com.starwars.models.response.ResultResponseModel;

import java.util.List;

public class AdapterCharacter extends RecyclerView.Adapter<AdapterCharacter.MyViewHolder> {

    private CharacterOnClick listener;
    private ItemCharacterBinding binding;

    private List<? extends ResultResponseModel> modelList;


    public AdapterCharacter(CharacterOnClick listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_character, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final ResultResponseModel event = modelList.get(position);
        holder.binding.setRepo(event);

        holder.binding.llParent.setOnClickListener(v -> listener.onClick(modelList.get(position), v));
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public void setCharacterList(final List<ResultResponseModel> modelList) {
        if (this.modelList == null) {
            this.modelList = modelList;
            notifyItemRangeInserted(0, modelList.size());
        } else {
            this.modelList = modelList;
            notifyDataSetChanged();

        }

    }

    public interface CharacterOnClick {
        void onClick(ResultResponseModel model, View view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        private MyViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
