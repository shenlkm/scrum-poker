package com.buildreams.scrumpoker.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.databinding.ActivityDeploymentCardBinding
import com.buildreams.scrumpoker.view.fragment.DeploymentCardFragment
import com.buildreams.scrumpoker.viewModel.UserViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class DeploymentCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDeploymentCardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val model: UserViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deployment_card)

        addFragment(DeploymentCardFragment(this))
    }

    private fun addFragment(fragment: DeploymentCardFragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_deployment_card, fragment).commit()
    }
}
