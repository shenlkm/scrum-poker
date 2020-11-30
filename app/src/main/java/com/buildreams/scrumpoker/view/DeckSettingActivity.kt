package com.buildreams.scrumpoker.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.buildreams.scrumpoker.DeckSettingBinding
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.ScrumPokerApplication


open class DeckSettingActivity : AppCompatActivity() {

    lateinit var binding: DeckSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as ScrumPokerApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deck_setting)
        val mp = MediaPlayer.create(this, R.raw.deck_setting)

        val sharedPref = getSharedPreferences(
            getString(R.string.option_selected_deck_setting),
            Context.MODE_PRIVATE
        )

        binding.lvDeckSetting.adapter = loadDeckSetting(sharedPref)

        binding.lvDeckSetting.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                mp.start()
                with(sharedPref.edit()) {
                    putInt(getString(R.string.option_selected_deck_setting), position)
                    commit()
                }
                callDashboard()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(this, DashboardCardActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun callDashboard() {
        Intent(this, DashboardCardActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun loadDeckSetting(sharedPref: SharedPreferences): ArrayAdapter<String> {
        val itemSelected = sharedPref.getInt(getString(R.string.option_selected_deck_setting), 0)
        val array: Array<String> = resources.getStringArray(R.array.deck_setting)

        return object : ArrayAdapter<String>(this, R.layout.item_deck_setting, array) {
            override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
                val v: TextView = super.getView(pos, convertView, parent) as TextView
                if (pos == itemSelected) {
                    ContextCompat.getDrawable(context, R.drawable.ic_check).also {
                        it?.setBounds(0, 0, 60, 60)
                        v.setCompoundDrawables(null, null, it, null)
                    }
                }

                when (pos) {
                    1 -> v.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                    else -> {
                        v.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    }
                }
                return v
            }
        }
    }
}
