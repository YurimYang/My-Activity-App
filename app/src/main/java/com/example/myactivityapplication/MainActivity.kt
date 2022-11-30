package com.example.myactivityapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
//import androidx.constraintlayout.widget.Constraints
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
//import androidx.work.Constraints
import com.example.myactivityapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn2ndActivity.setOnClickListener{
            //intent = 의도를 전달하기 위해 사용됨
            //context끼리는 자원을 공유하지 않음 -> intent를 갖고 정보를 공유함
            val intent = Intent(this, SecondActivity::class.java )
            startActivity(intent)
        }

        binding.btnService.setOnClickListener{
            val intent = Intent(this, ProgressService::class.java)
            //startService(intent) : 과거오 달리 이게 부족하게됨
            ContextCompat.startForegroundService(this,intent)
        }

        binding.btnWorker.setOnClickListener{
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresCharging(true)
                .build()
            val workRequest = OneTimeWorkRequest.Builder(CountWorker::class.java)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(this).enqueue(workRequest)
        }

        binding.btnAlarm.setOnClickListener{
            val intent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )
            intent.putExtra("message", "Exact Alarm Trigger")

            getSystemService(AlarmManager::class.java).setExact(
                AlarmManager.RTC_WAKEUP,
                    SystemClock.elapsedRealtime() +10 * 1000,
                    pendingIntent
            )
        }
    }
}