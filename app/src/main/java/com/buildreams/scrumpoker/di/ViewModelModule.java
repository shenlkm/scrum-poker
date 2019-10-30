package com.buildreams.scrumpoker.di;

import androidx.lifecycle.ViewModelProvider;

import com.buildreams.scrumpoker.viewModel.AboutViewModel;
import com.buildreams.scrumpoker.viewModel.CardViewModel;
import com.buildreams.scrumpoker.viewModel.MasterViewModelFactory;
import com.buildreams.scrumpoker.viewModel.UserViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

@Module(subcomponents = {ViewModelModule.ViewModelSubComponent.class})
public class ViewModelModule {

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new MasterViewModelFactory(viewModelSubComponent.build());
    }

    @Subcomponent
    public interface ViewModelSubComponent {
        UserViewModel userViewModel();
        CardViewModel cardViewModel();
        AboutViewModel aboutViewModel();

        @Subcomponent.Builder
        interface Builder {
            com.buildreams.scrumpoker.di.ViewModelModule.ViewModelSubComponent build();
        }
    }
}
