package com.example.androidadvanced_mobileafternoon.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidadvanced_mobileafternoon.activity.SharePreferenceActivity
import com.example.androidadvanced_mobileafternoon.databinding.HomefragmentBinding
import com.example.androidadvanced_mobileafternoon.utils.CustomSharePreference

class HomeFragment : Fragment() {
    private lateinit var binding: HomefragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = HomefragmentBinding.inflate(inflater, container, false)
        animateImgView()

        return binding.root
    }

    private fun animateImgView() {
        val fadeOut = ObjectAnimator.ofFloat(binding.ivAnimate, "scaleX", 0f, 1f)
        fadeOut.duration = 3000 // Durasi animasi dalam milidetik (2 detik)
        fadeOut.repeatCount = ValueAnimator.INFINITE
        fadeOut.start()

    }


}