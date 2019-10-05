package com.buildreams.scrumpoker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.databinding.ActivityDeploymentCardBinding
import com.buildreams.scrumpoker.domain.entity.Card
import com.buildreams.scrumpoker.view.adapter.DeploymentCardAdapter
import com.buildreams.scrumpoker.viewModel.UserViewModel
import dagger.android.AndroidInjection
import java.util.ArrayList
import javax.inject.Inject

class DeploymentCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDeploymentCardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: DeploymentCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val model: UserViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deployment_card)
        val card = Card(1, "1", 0)
        binding.card = card
        binding.name = model.user

        val recyclerView = binding.rvPresentCard

        recyclerView.layoutManager = GridLayoutManager(this, 2)
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
}
