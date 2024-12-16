package com.example.sullan_slambook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityComposeBinding
import com.example.sullan_slambook.model.UserInfo

class ComposeActivity : AppCompatActivity() {

    private lateinit var sharedPrefHelper: SharedPrefHelper
    private lateinit var binding: ActivityComposeBinding

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null // To store the selected image URI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComposeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)
        populateBirthDateSpinner()

        binding.btnbackcompose.setOnClickListener {
            val intent = Intent(this, getforward_activity::class.java)
            startActivity(intent)
            finish()
        }

        binding.profile.setOnClickListener {
            openGallery()
        }

        binding.btncomposeTofav.setOnClickListener {
            saveUserInfo()
        }
    }

    private fun saveUserInfo() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val fullName = binding.fullName.text.toString()
        val selectedGenderId = binding.genderRadioGroup.checkedRadioButtonId
        val address = binding.address.text.toString()
        val number = binding.number.text.toString()
        val gmail = binding.gmail.text.toString()
        val birthDate = binding.birthDateSpinner.selectedItem.toString()

        // Validate all fields
        if (selectedGenderId == -1 || username.isBlank() || password.isBlank() ||
            fullName.isBlank() || address.isBlank() || number.isBlank() ||
            gmail.isBlank() || birthDate == "Select Birth Date"
        ) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

        // Create a new UserInfo object
        val userInfo = UserInfo(
            username = username,
            password = password,
            fullName = fullName,
            gender = selectedGender,
            address = address,
            number = number,
            gmail = gmail,
            birthDate = birthDate
            // Add other fields if needed
        )

        // Load existing list of UserInfo objects
        val userInfoList = sharedPrefHelper.getUserInfoList().toMutableList()

        // Add the new UserInfo object to the list
        userInfoList.add(userInfo)

        // Save the updated list back to SharedPreferences
        sharedPrefHelper.saveUserInfoList(userInfoList)

        // Proceed to the next activity
        startActivity(Intent(this, FavoriteActivity::class.java))
    }

    private fun populateBirthDateSpinner() {
        val dates = (1..31).map { it.toString() }.toMutableList()
        val months = listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )

        val datesAndMonths = listOf("Select Birth Date") + dates + months

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datesAndMonths)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.birthDateSpinner.adapter = adapter
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            selectedImageUri?.let {
                binding.profile.setImageURI(it)
            } ?: run {
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
