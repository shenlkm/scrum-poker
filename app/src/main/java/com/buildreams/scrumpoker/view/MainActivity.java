package com.buildreams.scrumpoker.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.buildreams.scrumpoker.R;
import com.buildreams.scrumpoker.databinding.ActivityMainBinding;
import com.buildreams.scrumpoker.viewModel.UserViewModel;
import dagger.android.AndroidInjection;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserViewModel model = ViewModelProviders.of(this, viewModelFactory)
                .get(UserViewModel.class);
        binding.setName(model.getUser());
    }
}
