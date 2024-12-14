package com.example.sullan_slambook

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.model.SlambookInfo

class ProfileViewActivity : AppCompatActivity() {

    private lateinit var profileName: TextView
    private lateinit var profileImage: ImageView
    private lateinit var btnView: Button
    private lateinit var slambookInfo: SlambookInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileview)

        // Initialize views
        profileName = findViewById(R.id.profile_name)
        profileImage = findViewById(R.id.profile_image)
        btnView = findViewById(R.id.btn_view)

        // Retrieve the SlambookInfo object from the intent
        slambookInfo = intent.getParcelableExtra("slambookInfo") ?: return

        // Set the profile name
        profileName.text = slambookInfo.username // or slambookInfo.fullName if preferred

        // If you have an image URI or resource, set it
        // Example: If you have a URI in your SlambookInfo object
        // profileImage.setImageURI(Uri.parse(slambookInfo.profileImageUri))
        // You may need to handle URI or resources for images in your model

        // Show all information on button click
        btnView.setOnClickListener {
            showAllInformation()
        }
    }

    private fun showAllInformation() {
        // Display all the information in a Toast, or you could show it in a new activity or dialog
        val allInfo = """
            Username: ${slambookInfo.username}
            Full Name: ${slambookInfo.fullName}
            Gender: ${slambookInfo.gender}
            Address: ${slambookInfo.address}
            Age: ${slambookInfo.age}
            Number: ${slambookInfo.number}
            Gmail: ${slambookInfo.gmail}
            Birth Date: ${slambookInfo.birthDate}
            Color: ${slambookInfo.color}
            Place: ${slambookInfo.place}
            Fruit: ${slambookInfo.fruit}
            Food: ${slambookInfo.food}
            Song: ${slambookInfo.song}
            Movie: ${slambookInfo.movie}
            Artist: ${slambookInfo.artist}
            Favorite Hobby: ${slambookInfo.favoriteHobby}
            Relaxation: ${slambookInfo.wayToRelax}
            Happiest Moment: ${slambookInfo.happiestMoment}
            Inspiration: ${slambookInfo.dailyInspiration}
            Peace Definition: ${slambookInfo.peace}
            Joy Source: ${slambookInfo.joy}
            Anger Trigger: ${slambookInfo.angerTrigger}
            Sadness Embrace: ${slambookInfo.sadnessEmbrace}
            Biggest Dream: ${slambookInfo.biggestDream}
            Safety Source: ${slambookInfo.feelSafe}
            Life Change: ${slambookInfo.biggestLifeChange}
            Challenging Moment: ${slambookInfo.challengingMoment}
            Meaningful Experience: ${slambookInfo.meaningfulExperience}
            Self Discovery: ${slambookInfo.selfDiscovery}
            Motivation: ${slambookInfo.motivation}
        """.trimIndent()

        // Show information in a Toast (or use a dialog, etc.)
        Toast.makeText(this, allInfo, Toast.LENGTH_LONG).show()
    }
    private fun savefriend() {

    }
}
