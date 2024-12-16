package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityMyemotionBinding
import com.example.sullan_slambook.model.UserInfo

class MyEmotionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyemotionBinding
    private val slamPageList = mutableListOf<UserInfo>()
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyemotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)  // Initialize SharedPrefHelper
        loadSavedData()  // Load data when the activity is created

        // Handle "Next to Experience" button click
        binding.btnemotiontoexperience.setOnClickListener {
            val peace = binding.edtPeace.text.toString().trim()
            val joy = binding.edtJoy.text.toString().trim()
            val angerTrigger = binding.edtAngerTrigger.text.toString().trim()
            val sadnessEmbrace = binding.edtSadnessEmbrace.text.toString().trim()
            val biggestDream = binding.edtBiggestDream.text.toString().trim()
            val feelSafe = binding.edtFeelSafe.text.toString().trim()

            // Check if all fields are filled
            if (peace.isBlank() || joy.isBlank() || angerTrigger.isBlank() ||
                sadnessEmbrace.isBlank() || biggestDream.isBlank() || feelSafe.isBlank()
            ) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create UserInfo object for emotions
            val userInfo = UserInfo(
                peace = peace,
                joy = joy,
                angerTrigger = angerTrigger,
                sadnessEmbrace = sadnessEmbrace,
                biggestDream = biggestDream,
                feelSafe = feelSafe
            )
            slamPageList.add(userInfo)

            // Save the updated list to SharedPreferences
            saveData()

            // Navigate to Experience Activity
            val toExperience = Intent(this, ExperienceActivity::class.java)
            toExperience.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
            startActivity(toExperience)
        }

        // Back to Favorites Activity
        binding.myemotionbtnback.setOnClickListener {
            val backToFavorites = Intent(this, HobbiesActivity::class.java)
            startActivity(backToFavorites)
            finish() // End current activity to avoid backstack issues.
        }
    }

    // Save the list of UserInfo objects using SharedPrefHelper
    private fun saveData() {
        sharedPrefHelper.saveUserInfoList(slamPageList)  // Save list of UserInfo objects
  //      Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
    }

    // Load the list of UserInfo objects using SharedPrefHelper
    private fun loadSavedData() {
        slamPageList.clear()
        slamPageList.addAll(sharedPrefHelper.getUserInfoList())  // Load list of UserInfo objects
    }
}
