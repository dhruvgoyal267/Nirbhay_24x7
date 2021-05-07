package com.nirbhay.nirbhay_24x7.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var flag = true
        binding = FragmentHomeBinding.bind(view)
        binding.enableBtn.setOnClickListener {
            if (flag)
                binding.enableBtn.text = getString(R.string.disable_shake_detector)
            else
                binding.enableBtn.text = getString(R.string.enable_shake_detector)
            flag = !flag
        }
    }
}