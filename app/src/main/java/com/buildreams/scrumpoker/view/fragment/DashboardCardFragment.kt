package com.buildreams.scrumpoker.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.ScrumPokerApplication
import com.buildreams.scrumpoker.databinding.FragmentDashboardCardBinding
import com.buildreams.scrumpoker.domain.entity.Card
import com.buildreams.scrumpoker.view.DashboardCardActivity
import com.buildreams.scrumpoker.view.adapter.DashboardCardAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject


class DashboardCardFragment(private var activity: DashboardCardActivity) : Fragment() {

    @Inject
    lateinit var adapter: DashboardCardAdapter

    lateinit var binding: FragmentDashboardCardBinding
    private lateinit var listener: DashboardCardAdapter.ItemListener

    override fun onCreate(savedInstanceState: Bundle?) {
        activity.applicationContext.let {
            (it as ScrumPokerApplication).appComponent.inject(this)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_dashboard_card, container, false
        ) as FragmentDashboardCardBinding

        val view = binding.root

        activity = getActivity() as DashboardCardActivity
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cards = ArrayList<Card>()
        val gridManager = GridLayoutManager(activity, 3)
        val recyclerView = binding.rvPresentCard
        recyclerView.layoutManager = gridManager

        addFibonacciCards(cards)

        adapter.setListener(listener)
        adapter.setCards(cards)

        recyclerView.adapter = adapter
    }

    private fun addFibonacciCards(cards: ArrayList<Card>) {
        val img: Byte = 0
        val sharedPref = activity.getSharedPreferences(
            getString(R.string.option_selected_deck_setting),
            Context.MODE_PRIVATE
        )
        val type = sharedPref.getInt(getString(R.string.option_selected_deck_setting), 0)
        val json = resources.openRawResource(R.raw.decks).bufferedReader().use { it.readLine() }
        val deckType = object : TypeToken<List<List<String>>>() {}.type
        val decks = Gson().fromJson<List<List<String>>>(json, deckType)
        val array = decks[type]
        array.forEach {
            cards.add(Card(it, it, img))
        }
        cards.add(Card("?", "?", img))
        cards.add(Card("∞", "∞", img))
        cards.add(Card("Coffee", "Coffee", img))
    }

    override fun onAttach(context: Context) {
        if (context is DashboardCardAdapter.ItemListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement DashboardCardAdapter.ItemListeners")
        }
        super.onAttach(context)
    }
}
