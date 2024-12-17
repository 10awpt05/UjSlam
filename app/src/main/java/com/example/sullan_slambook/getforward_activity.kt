package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityGetforwardBinding


class getforward_activity : AppCompatActivity() {

    private lateinit var binding: ActivityGetforwardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetforwardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myslampages.setOnClickListener{
            val toSlam = Intent(this, MySlampagesActivity::class.java)
            startActivity(toSlam)
        }

        binding.compose.setOnClickListener {
            val toCompose = Intent(this, FieldsActivity::class.java)
            startActivity(toCompose)
            // finish()
        }
    }

}