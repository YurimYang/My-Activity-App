package com.example.myactivityapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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
    }
}