package com.example.sullan_slambook
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sullan_slambook.adapter.SlamPageAdapter
import com.example.sullan_slambook.databinding.ActivityExperienceBinding
import com.example.sullan_slambook.model.SlambookInfo

class experience_activity : AppCompatActivity() {

    private lateinit var binding: ActivityExperienceBinding
    private lateinit var adapter: SlamPageAdapter
    private val slamPageList = mutableListOf<SlambookInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
                val newSlamPage = SlambookInfo(
                    biggestLifeChange = biggestLifeChange,
                    challengingMoment = challengingMoment,
                    meaningfulExperience = meaningfulExperience,
                    selfDiscovery = selfDiscovery,
                    motivation = motivation,
                    fear = fear,
                    proudestMoment = proudestMoment
                )
                slamPageList.add(newSlamPage)

                // Update the RecyclerView
                adapter.updateList(slamPageList)

                // Clear input fields after saving
                binding.inputBiggestLifeChange.text?.clear()
                binding.inputChallengingMoment.text?.clear()
                binding.inputMeaningfulExperience.text?.clear()
                binding.inputSelfDiscovery.text?.clear()
                binding.inputMotivation.text?.clear()
                binding.inputFear.text?.clear()
                binding.inputProudestMoment.text?.clear()

                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
