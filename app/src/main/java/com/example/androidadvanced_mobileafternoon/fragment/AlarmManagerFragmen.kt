package com.example.androidadvanced_mobileafternoon.fragment

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.databinding.FragmentAlarmManagerBinding
import com.example.androidadvanced_mobileafternoon.databinding.HomefragmentBinding
import com.example.androidadvanced_mobileafternoon.utils.AlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class AlarmManagerFragmen : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAlarmManagerBinding
    private lateinit var timePicker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlarmManagerBinding.inflate(inflater, container, false)

        CreateNotificationChannel()
        binding.btnSelectTime.setOnClickListener(this)
        binding.btnSetTime.setOnClickListener(this)
        binding.btnCancelTime.setOnClickListener(this)

        return binding.root
    }

    private fun CreateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nama: CharSequence = "Kristo Pandapotan samosir"
            val deskripsi = "Channel AlarmManager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("kristo", nama, importance)
            channel.description = deskripsi

            val notificationManager = context?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_select_time -> {
                selectTime()
            }

            R.id.btn_set_time -> {
                setAlarm()
            }

            R.id.btn_cancel_time -> {
                cancelAlarm()
            }
        }
    }

    private fun cancelAlarm() {
        val intent = Intent(activity, AlarmReceiver::class.java)
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        pendingIntent =
            PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(activity, "Alarm Is Cancelled", Toast.LENGTH_SHORT).show()

    }

    private fun setAlarm() {

        val intent = Intent(activity, AlarmReceiver::class.java)
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        pendingIntent =
            PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(activity, "Alarm is set successfully", Toast.LENGTH_LONG).show()
    }

    private fun selectTime() {
        timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Time")
            .build()

        timePicker.show(requireActivity().supportFragmentManager, "kristo")

        timePicker.addOnPositiveButtonClickListener {
            binding.tvTime.text = String.format("%02d", timePicker.hour) + " : " + String.format(
                "%02d",
                timePicker.minute
            )

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = timePicker.hour
            calendar[Calendar.MINUTE] = timePicker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }
}