package com.buildreams.scrumpoker.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.buildreams.scrumpoker.R;
import com.buildreams.scrumpoker.databinding.ActivityDeploymentCardBinding;
import com.buildreams.scrumpoker.domain.entity.Card;
import com.buildreams.scrumpoker.view.adapter.DeploymentCardAdapter;
import com.buildreams.scrumpoker.viewModel.UserViewModel;
import dagger.android.AndroidInjection;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DeploymentCardActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public ActivityDeploymentCardBinding binding;
    @Inject
    public DeploymentCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deployment_card);
        UserViewModel model = ViewModelProviders.of(this, viewModelFactory)
                .get(UserViewModel.class);
        binding.setName(model.getUser());

        RecyclerView recyclerView = binding.rvDeploymentCard;

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<Card> cards = new ArrayList<>();
        byte img = 0;
        Card card1 = new Card(1, "test1", img);
        Card card2 = new Card(2, "test2", img);
        Card card3 = new Card(3, "test3", img);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        adapter.setCards(cards);
        recyclerView.setAdapter(adapter);
    }
}
