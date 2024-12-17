package com.example.sullan_slambook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sullan_slambook.databinding.ActivityViewBinding
import com.example.sullan_slambook.model.UserInfo

class view_activity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding

    @SuppressLint("MissingInflatedId")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // Inflate the layout using View Binding
            binding = ActivityViewBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Retrieve the UserInfo object from the Intent
            val userInfo = intent.getParcelableExtra<UserInfo>("userInfo")

            // Populate the data if the UserInfo object is not null
            userInfo?.let {
                binding.tvFullName.text = it.fullName
                binding.tvSurname.text = it.username
                binding.tvAddress.text = it.address
                //binding.tvAge.text = it.age.toString()
                binding.tvNumber.text = it.number
                binding.tvGmail.text = it.gmail
                binding.tvBirthDate.text = it.birthDate
                binding.tvGender.text = it.gender
                binding.tvColor.text = it.color
                binding.tvPlace.text = it.place
                binding.tvFruit.text = it.fruit
                binding.tvFood.text = it.food
                binding.tvSong.text = it.song
                binding.tvMovie.text = it.movie
                binding.tvArtist.text = it.artist
                binding.tvHobby.text = it.favoriteHobby
            //    binding.tvRelax.text = it.relaxation
            //    binding.tvCantLiveWithout.text = it.indispensableHobby
                binding.tvHappiestMoment.text = it.happiestMoment
             //   binding.tvInspiration.text = it.inspiration
                binding.tvPeace.text = it.peace
                binding.tvJoy.text = it.joy
                binding.tvAngerTrigger.text = it.angerTrigger
                binding.tvSadness.text = it.sadnessEmbrace
                binding.tvBiggestDream.text = it.biggestDream
                binding.tvSafe.text = it.feelSafe
                binding.tvLifeChange.text = it.biggestLifeChange
                binding.tvChallengingMoment.text = it.challengingMoment
                binding.tvMeaningfulExperience.text = it.meaningfulExperience
                binding.tvSelfDiscovery.text = it.selfDiscovery
                binding.tvMotivation.text = it.motivation
                binding.tvFear.text = it.fear
                binding.tvProudestMoment.text = it.proudestMoment
            //    binding.tvSexExperience.text = it.sexExperience
            } ?: run {
                // Handle null UserInfo case
                binding.tvFullName.text = "No data available"
            }

            // Add a back button click listener (optional)
            binding.btnbackview.setOnClickListener {
                finish() // Navigate back to the previous activity
            }
        }
    }


