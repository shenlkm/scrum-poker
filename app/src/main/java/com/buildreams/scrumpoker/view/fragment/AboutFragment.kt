package com.buildreams.scrumpoker.view.fragment

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.buildreams.scrumpoker.BR
import com.buildreams.scrumpoker.CardBinding
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.databinding.FragmentAboutBinding
import com.buildreams.scrumpoker.view.DashboardCardActivity
import com.buildreams.scrumpoker.viewModel.AboutViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AboutFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: AboutViewModel

    lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_about, container, false
        ) as FragmentAboutBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            context as DashboardCardActivity,
            viewModelFactory
        ).get(AboutViewModel::class.java)
        viewModel.buildAbout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            setVariable(BR.about, viewModel.about.value)
            executePendingBindings()
        }

        viewModel.about.value?.let {

            this.binding.devOne.movementMethod = LinkMovementMethod.getInstance()
            this.binding.devOne.text = Html.fromHtml(it.developers[0])
            this.binding.devTwo.movementMethod = LinkMovementMethod.getInstance()
            this.binding.devTwo.text = Html.fromHtml(it.developers[1])
        }

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}