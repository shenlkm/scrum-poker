package com.buildreams.scrumpoker.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.buildreams.scrumpoker.DashboardBinding
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.ScrumPokerApplication
import com.buildreams.scrumpoker.domain.entity.Card
import com.buildreams.scrumpoker.view.adapter.DashboardCardAdapter
import com.buildreams.scrumpoker.view.fragment.AboutFragment
import com.buildreams.scrumpoker.view.fragment.DashboardCardFragment
import com.buildreams.scrumpoker.view.fragment.SelectedCardFragment
import com.buildreams.scrumpoker.viewModel.CardViewModel
import com.buildreams.scrumpoker.viewModel.ViewModelFactory
import java.util.*
import javax.inject.Inject


open class DashboardCardActivity : AppCompatActivity(), DashboardCardAdapter.ItemListener {

    lateinit var binding: DashboardBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CardViewModel>
    lateinit var viewModel: CardViewModel

    private var sounds = intArrayOf(
        R.raw.dashboard1, R.raw.dashboard2, R.raw.dashboard3, R.raw.dashboard4,
        R.raw.dashboard5, R.raw.dashboard6, R.raw.dashboard7
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as ScrumPokerApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CardViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_card)
        binding.viewModel = viewModel

        binding.navView.setNavigationItemSelectedListener { item -> onNavViewItemSelected(item) }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        addFragment(DashboardCardFragment(this))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, DeckSettingActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_deployment_card, fragment)
            .commit()
    }

    override fun onItemSelected(card: Card) {
        val id = sounds[Random().nextInt(sounds.size)]
        val mp = MediaPlayer.create(this, id)
        mp.start()
        viewModel.card.value = card
        replaceFragment(SelectedCardFragment.newInstance())
    }

    private fun onNavViewItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                supportFragmentManager.popBackStack()
                replaceFragment(DashboardCardFragment(this))
            }
            R.id.nav_about -> replaceFragment(AboutFragment())
        }
        item.isChecked = true
        binding.drawerLayout.closeDrawers()
        return true
    }

    open fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        with(transaction) {
            setCustomAnimations(
                R.anim.fragment_fade_enter, R.anim.fragment_close_exit
            )
            replace(R.id.fl_deployment_card, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
