package com.buildreams.scrumpoker.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.buildreams.scrumpoker.BR;
import com.buildreams.scrumpoker.R;
import com.buildreams.scrumpoker.domain.entity.Card;

import javax.inject.Inject;
import java.util.List;

public class DeploymentCardAdapter extends RecyclerView.Adapter<DeploymentCardAdapter.CardViewHolder> {

    public List<Card> cards;

    @Inject
    public DeploymentCardAdapter() {
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_card,
                viewGroup, false);

        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int i) {
        Card item = cards.get(i);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (cards == null) {
            return 0;
        }
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public CardViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Card card) {
            binding.setVariable(BR.card, card);
            binding.executePendingBindings();
        }
    }
}
