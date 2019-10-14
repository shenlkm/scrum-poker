package com.buildreams.scrumpoker.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.buildreams.scrumpoker.DashboardBinding
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.domain.entity.Card
import com.buildreams.scrumpoker.view.adapter.DashboardCardAdapter
import com.buildreams.scrumpoker.view.fragment.DashboardCardFragment
import com.buildreams.scrumpoker.view.fragment.SelectedCardFragment
import com.buildreams.scrumpoker.viewModel.CardViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

open class DashboardCardActivity : AppCompatActivity(), DashboardCardAdapter.ItemListener {

    lateinit var binding: DashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CardViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deployment_card)
        binding.viewModel = viewModel
        addFragment(DashboardCardFragment(this))
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_deployment_card, fragment)
            .commit()
    }

    open fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        with(transaction){
            setCustomAnimations(
                R.anim.fragment_fade_enter, R.anim.fragment_close_exit)
            replace(R.id.fl_deployment_card, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onItemSelected(card: Card) {
        viewModel.card.value = card
        replaceFragment(SelectedCardFragment.newInstance())
    }
}
