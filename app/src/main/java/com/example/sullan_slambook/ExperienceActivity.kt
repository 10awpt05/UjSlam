package com.example.sullan_slambook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityExperienceBinding
import com.example.sullan_slambook.model.UserInfo

class ExperienceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExperienceBinding
    private val slamPageList = mutableListOf<UserInfo>()
    private lateinit var sharedPrefHelper: SharedPrefHelper  // SharedPrefHelper instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)  // Initialize SharedPrefHelper
        loadSavedData()  // Load data when the activity is created

        // Save Button Listener
        binding.btnSave.setOnClickListener {
            val biggestLifeChange = binding.inputBiggestLifeChange.text?.toString().orEmpty().trim()
            val challengingMoment = binding.inputChallengingMoment.text?.toString().orEmpty().trim()
            val meaningfulExperience = binding.inputMeaningfulExperience.text?.toString().orEmpty().trim()
            val selfDiscovery = binding.inputSelfDiscovery.text?.toString().orEmpty().trim()
            val motivation = binding.inputMotivation.text?.toString().orEmpty().trim()
            val fear = binding.inputFear.text?.toString().orEmpty().trim()
            val proudestMoment = binding.inputProudestMoment.text?.toString().orEmpty().trim()

            // Validate input fields
            if (biggestLifeChange.isEmpty() || challengingMoment.isEmpty() ||
                meaningfulExperience.isEmpty() || selfDiscovery.isEmpty() ||
                motivation.isEmpty() || fear.isEmpty() || proudestMoment.isEmpty()
            ) {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            } else {
                // Add a new SlambookInfo object to the list
                val newSlamPage = UserInfo(
                    biggestLifeChange = biggestLifeChange,
                    challengingMoment = challengingMoment,
                    meaningfulExperience = meaningfulExperience,
                    selfDiscovery = selfDiscovery,
                    motivation = motivation,
                    fear = fear,
                    proudestMoment = proudestMoment
                )
                slamPageList.add(newSlamPage)

                // Save the updated list to SharedPreferences using SharedPrefHelper
                saveData()

                // Clear input fields after saving
                binding.inputBiggestLifeChange.text?.clear()
                binding.inputChallengingMoment.text?.clear()
                binding.inputMeaningfulExperience.text?.clear()
                binding.inputSelfDiscovery.text?.clear()
                binding.inputMotivation.text?.clear()
                binding.inputFear.text?.clear()
                binding.inputProudestMoment.text?.clear()

                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to the next activity
                val intent = Intent(this, MySlampagesActivity::class.java)
                intent.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
                startActivity(intent)
            }
        }
    }

    // Save the list of SlambookInfo objects using SharedPrefHelper
    private fun saveData() {
        sharedPrefHelper.saveUserInfoList(slamPageList)  // Save list of SlambookInfo objects
    }

    // Load the list of SlambookInfo objects using SharedPrefHelper
    private fun loadSavedData() {
        slamPageList.clear()
        slamPageList.addAll(sharedPrefHelper.getUserInfoList())  // Load list of SlambookInfo objects
    }
}
