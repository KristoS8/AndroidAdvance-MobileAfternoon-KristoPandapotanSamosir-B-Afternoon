package com.example.androidadvanced_mobileafternoon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.databinding.FragmentKeduaBinding
import com.example.androidadvanced_mobileafternoon.utils.workManager

class FragmentKedua : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentKeduaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentKeduaBinding.inflate(layoutInflater, container, false)

        binding.btnWorkManager.setOnClickListener(this)

        return binding.root
    }

    private fun OneTimeWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(true)
            .build()

        val work = OneTimeWorkRequest.Builder(workManager::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueue(work)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_work_manager -> {
                OneTimeWork()
            }
        }
    }
}