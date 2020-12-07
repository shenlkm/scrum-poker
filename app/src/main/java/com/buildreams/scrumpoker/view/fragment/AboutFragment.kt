package com.buildreams.scrumpoker.view.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.buildreams.scrumpoker.BR
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.ScrumPokerApplication
import com.buildreams.scrumpoker.databinding.FragmentAboutBinding
import com.buildreams.scrumpoker.view.DashboardCardActivity
import com.buildreams.scrumpoker.viewModel.AboutViewModel
import com.buildreams.scrumpoker.viewModel.ViewModelFactory
import javax.inject.Inject

class AboutFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelFactory<AboutViewModel>
    lateinit var viewModel: AboutViewModel

    lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.applicationContext.let {
            (it as ScrumPokerApplication).appComponent.inject(this)
        }
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(context as DashboardCardActivity, viewModelProvider).get(
                AboutViewModel::class.java
            )
    }

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
            this.binding.devOne.text =
                HtmlCompat.fromHtml(it.developers[0], HtmlCompat.FROM_HTML_MODE_LEGACY)
            this.binding.devTwo.movementMethod = LinkMovementMethod.getInstance()
            this.binding.devTwo.text =
                HtmlCompat.fromHtml(it.developers[1], HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

    }
}