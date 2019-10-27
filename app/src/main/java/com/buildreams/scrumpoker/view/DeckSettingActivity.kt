package com.buildreams.scrumpoker.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.buildreams.scrumpoker.DeckSettingBinding
import com.buildreams.scrumpoker.R
import dagger.android.AndroidInjection



open class DeckSettingActivity : AppCompatActivity() {

    lateinit var binding: DeckSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deck_setting)
        val mp = MediaPlayer.create(this, R.raw.deck_setting)

        val sharedPref = getSharedPreferences(
            getString(R.string.option_selected_deck_setting), Context.MODE_PRIVATE)
        val itemSelected = sharedPref.getInt(getString(R.string.option_selected_deck_setting), -1)

        val array: Array<String> = resources.getStringArray(R.array.deck_setting)
        val adapter = object : ArrayAdapter<String>(this, R.layout.item_deck_setting, array) {
            override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
                val v: TextView = super.getView(pos, convertView, parent) as TextView
                if (pos == itemSelected) {
                    val img = context.resources.getDrawable(R.drawable.ic_check)
                    img.setBounds(0, 0, 60, 60)
                    v.setCompoundDrawables(null, null, img, null)
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

        binding.lvDeckSetting.adapter = adapter

        binding.lvDeckSetting.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                mp.start()
                val itemValue = binding.lvDeckSetting.getItemAtPosition(position) as String
                with (sharedPref.edit()) {
                    putInt(getString(R.string.option_selected_deck_setting), position)
                    commit()
                }
                // Toast the values
                Toast.makeText(
                    applicationContext,
                    "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG
                ).show()
                //TODO pending to do the intent or comeback action
            }
    }
}
