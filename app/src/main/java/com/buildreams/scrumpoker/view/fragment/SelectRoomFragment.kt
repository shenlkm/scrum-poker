package com.buildreams.scrumpoker.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buildreams.scrumpoker.R
import com.buildreams.scrumpoker.view.DashboardCardActivity
import kotlinx.android.synthetic.main.fragment_select_room.*

class SelectRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_offline_mode.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            with(transaction) {
                setCustomAnimations(
                    com.buildreams.scrumpoker.R.anim.fragment_fade_enter, com.buildreams.scrumpoker.R.anim.fragment_close_exit
                )
                replace(com.buildreams.scrumpoker.R.id.fl_deployment_card, DashboardCardFragment(
                    activity as DashboardCardActivity
                ))
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectRoomFragment()
    }
}