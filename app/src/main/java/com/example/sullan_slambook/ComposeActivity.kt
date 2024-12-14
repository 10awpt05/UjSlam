package com.example.sullan_slambook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.net.Uri
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityComposeBinding
import com.example.sullan_slambook.model.SlambookInfo

class ComposeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComposeBinding
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val birthDateSpinner: Spinner = findViewById(R.id.birthDateSpinner)

        // Create list of dates and months
        val datesAndMonths = mutableListOf("Select Birth Date") // Default option
        for (i in 1..31) {
            datesAndMonths.add(i.toString()) // Add dates 1 to 31
        }
        datesAndMonths.add("January")
        datesAndMonths.add("February")
        datesAndMonths.add("March")
        datesAndMonths.add("April")
        datesAndMonths.add("May")
        datesAndMonths.add("June")
        datesAndMonths.add("July")
        datesAndMonths.add("August")
        datesAndMonths.add("September")
        datesAndMonths.add("October")
        datesAndMonths.add("November")
        datesAndMonths.add("December")

        // Set the adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datesAndMonths)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        birthDateSpinner.adapter = adapter



        // Initialize ViewBinding
        binding = ActivityComposeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Populate spinner with years
        populateBirthDateSpinner()

        // Set up back button
        binding.btnbackcompose.setOnClickListener {
            val togetfoward = Intent(this, getforward_activity::class.java)
            startActivity(togetfoward)
            finish()
        }

        // Set up profile picture click listener
        binding.profile.setOnClickListener {
            openGallery()
        }

        // Navigate to the Favorites screen when the submit button is clicked
        binding.btncomposeTofav.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val fullName = binding.fullName.text.toString()
            val selectedGenderId = binding.genderRadioGroup.checkedRadioButtonId
            val address = binding.address.text.toString()

            val number = binding.number.text.toString()
            val gmail = binding.gmail.text.toString()
            val birthDate = binding.birthDateSpinner.selectedItem.toString() // Use Spinner value

            // Ensure gender is selected
            if (selectedGenderId == -1) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

            if (username.isBlank() || password.isBlank() || fullName.isBlank() || address.isBlank() ||
                 number.isBlank() || gmail.isBlank() || birthDate.isBlank()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val slambookInfo = SlambookInfo(
                username = username,
                password = password,
                fullName = fullName,
                gender = selectedGender,
                address = address,
                number = number,
                gmail = gmail,
                birthDate = birthDate
            )

            // Pass the SlambookInfo object to the next activity
            val intent = Intent(this, FavoritesActivity::class.java)
            intent.putExtra("slambookInfo", slambookInfo)
            startActivity(intent)
        }
    }

    // Populate the Spinner with years
    private fun populateBirthDateSpinner() {
        val years = (1900..2024).map { it.toString() } // Generate a list of years
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.birthDateSpinner.adapter = adapter
    }

    // Open gallery to pick an image
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Handle the result of the image selection
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                binding.profile.setImageURI(selectedImageUri) // Set the selected image to the profile ImageView
            } else {
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
