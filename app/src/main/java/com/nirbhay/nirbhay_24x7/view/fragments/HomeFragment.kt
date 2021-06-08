package com.nirbhay.nirbhay_24x7.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.databinding.FragmentHomeBinding
import com.nirbhay.nirbhay_24x7.shakerModule.ShakerService
import com.nirbhay.nirbhay_24x7.utilities.isShakeEnabled
import com.nirbhay.nirbhay_24x7.utilities.setShakeEnabled


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.app_name)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        updateEnableBtnText()

        binding.enableBtn.setOnClickListener {
            btnClickListener()
        }
    }

    private fun btnClickListener() {
        val flag = !requireContext().isShakeEnabled()
        requireContext().setShakeEnabled(flag)
        val serviceIntent = Intent(
            requireContext().applicationContext,
            ShakerService::class.java
        )
        if (flag) {
            binding.enableBtn.text = getString(R.string.disable_shake_detector)
            ContextCompat.startForegroundService(requireContext().applicationContext, serviceIntent)
        } else {
            binding.enableBtn.text = getString(R.string.enable_shake_detector)
            context?.stopService(serviceIntent)
        }
    }

    private fun updateEnableBtnText() {
        if (requireContext().isShakeEnabled())
            binding.enableBtn.text = getString(R.string.disable_shake_detector)
        else
            binding.enableBtn.text = getString(R.string.enable_shake_detector)
    }
}