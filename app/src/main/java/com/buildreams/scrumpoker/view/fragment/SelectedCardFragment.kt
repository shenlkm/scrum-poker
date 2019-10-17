package com.buildreams.scrumpoker.view.fragment


import android.R
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.buildreams.scrumpoker.BR
import com.buildreams.scrumpoker.CardBinding
import com.buildreams.scrumpoker.view.DashboardCardActivity
import com.buildreams.scrumpoker.viewModel.CardViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import androidx.cardview.widget.CardView



open class SelectedCardFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var isCardFlipped: Boolean = false
    lateinit var binding: CardBinding
    lateinit var viewModel: CardViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
            com.buildreams.scrumpoker.R.layout.item_card, container, false
        ) as CardBinding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(context as DashboardCardActivity, viewModelFactory).get(CardViewModel::class.java)

        binding.apply {
            setVariable(BR.card, viewModel.card.value)
            executePendingBindings()
        }
        binding.infoText.setTextColor(context!!.getColor(R.color.darker_gray))
        var layoutParams = binding.cardView.getLayoutParams()
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        binding.cardView.layoutParams = layoutParams

        binding.cardContainer .setOnClickListener { view ->
            if (isCardFlipped) {
                parentFragmentManager.popBackStack()
            } else {
                flipCard(view)
            }
        }
    }

    private fun flipCard(view: View) {
        val objectAnimatorFlipLeft = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
        val objectAnimatorFlipRight = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
        objectAnimatorFlipLeft.interpolator = DecelerateInterpolator()
        objectAnimatorFlipRight.interpolator = AccelerateDecelerateInterpolator()
        objectAnimatorFlipLeft.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                binding.cardContainer.setBackgroundColor(context!!.getColor(R.color.white))
                objectAnimatorFlipRight.start()
                isCardFlipped = true
            }
        })
        objectAnimatorFlipLeft.start()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectedCardFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
