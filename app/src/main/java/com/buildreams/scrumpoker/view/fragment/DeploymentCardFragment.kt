package com.buildreams.scrumpoker.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.databinding.FragmentDeploymentCardBinding
import com.buildreams.scrumpoker.domain.entity.Card
import com.buildreams.scrumpoker.view.DeploymentCardActivity
import com.buildreams.scrumpoker.view.adapter.DeploymentCardAdapter
import com.buildreams.scrumpoker.viewModel.UserViewModel
import dagger.android.support.AndroidSupportInjection
import java.util.*
import javax.inject.Inject

class DeploymentCardFragment(
    var activity: DeploymentCardActivity
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: DeploymentCardAdapter

    lateinit var binding: FragmentDeploymentCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_deployment_card, container, false
        ) as FragmentDeploymentCardBinding

        val view = binding.root

        activity = getActivity() as DeploymentCardActivity
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model: UserViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        val card = Card(1, "1", 0)
        binding.card = card
        binding.name = model.user
        val recyclerView = binding.rvPresentCard

        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        val cards = ArrayList<Card>()
        val img: Byte = 0
        val card1 = Card(1, "test1", img)
        val card2 = Card(2, "test2", img)
        val card3 = Card(3, "test3", img)
        cards.add(card1)
        cards.add(card2)
        cards.add(card3)
        adapter.setCards(cards)
        recyclerView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
