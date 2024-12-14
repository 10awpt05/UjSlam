package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityMyemotionBinding
import com.example.sullan_slambook.model.SlambookInfo

class myemotion_activity : AppCompatActivity() {

    private lateinit var binding: ActivityMyemotionBinding
    private val slamPageList = mutableListOf<SlambookInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyemotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigate to Experience Activity with Validation
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
            } else {
                val toExperience = Intent(this, experience_activity::class.java)
                startActivity(toExperience)
            }
            val slambookInfo = SlambookInfo(
                peace = peace,
                joy = joy,
                angerTrigger = angerTrigger,
                sadnessEmbrace = sadnessEmbrace,
                biggestDream = biggestDream,
                feelSafe = feelSafe,)
            slamPageList.add(slambookInfo)

            // If all fields are filled, proceed to experience Activity
            val toHobbies = Intent(this, experience_activity::class.java)
            intent.putParcelableArrayListExtra("slambookInfo", ArrayList(slamPageList))
            startActivity(toHobbies)
        }

        // Back to Favorites Activity
        binding.myemotionbtnback.setOnClickListener {
            val backToFavorites = Intent(this, FavoritesActivity::class.java)
            startActivity(backToFavorites)
            finish() // End current activity to avoid backstack issues.
        }
    }
}
