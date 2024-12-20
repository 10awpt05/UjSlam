package com.example.sullan_slambook

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sullan_slambook.databinding.ActivityFieldsBinding
import com.example.sullan_slambook.model.UserInfo
import java.util.*

class FieldsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFieldsBinding
    private lateinit var sharedPrefHelper: SharedPrefHelper

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)

        setupBirthDatePicker()

        val colors = resources.getStringArray(R.array.colors_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinner.adapter = adapter

        binding.profile.setOnClickListener {
            openGallery()
        }

        binding.btnbackcompose.setOnClickListener {
            val intent = Intent(this, getforward_activity::class.java)
            startActivity(intent)
            finish()
        }

        setupToggleCards()
        setupSaveButton()
    }

    private fun setupBirthDatePicker() {
        binding.birthDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.birthDateButton.text = selectedDate
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }
    }

    private fun setupToggleCards() {
        // Your existing toggle card logic for visibility
        binding.cardMore.setOnClickListener {
            toggleVisibility(binding.scrollView2, binding.cardEmo, binding.cardExp, binding.cardFav, binding.cardHob)
        }

        binding.cardFav.setOnClickListener {
            toggleVisibility(binding.scrollFav, binding.cardEmo, binding.cardExp, binding.cardMore, binding.cardHob)
        }

        binding.cardEmo.setOnClickListener {
            toggleVisibility(binding.scrollEmo, binding.cardFav, binding.cardExp, binding.cardMore, binding.cardHob)
        }

        binding.cardExp.setOnClickListener {
            toggleVisibility(binding.scrollExp, binding.cardFav, binding.cardEmo, binding.cardMore, binding.cardHob)
        }

        binding.cardHob.setOnClickListener {
            toggleVisibility(binding.scrollHob, binding.cardFav, binding.cardEmo, binding.cardMore, binding.cardExp)
        }

        // Done buttons logic
        binding.favDone.setOnClickListener { resetCardVisibility() }
        binding.hobbiesDone.setOnClickListener { resetCardVisibility() }
        binding.moreDone.setOnClickListener { resetCardVisibility() }
        binding.ExperDone.setOnClickListener { resetCardVisibility() }
        binding.EmoDone.setOnClickListener { resetCardVisibility() }
    }

    private fun toggleVisibility(targetView: View, vararg otherViews: View) {
        if (targetView.visibility == View.GONE) {
            targetView.visibility = View.VISIBLE
            otherViews.forEach { it.visibility = View.GONE }
        } else {
            targetView.visibility = View.GONE
            otherViews.forEach { it.visibility = View.VISIBLE }
        }
    }

    private fun resetCardVisibility() {
        binding.scrollFav.visibility = View.GONE
        binding.scrollHob.visibility = View.GONE
        binding.scrollView2.visibility = View.GONE
        binding.scrollExp.visibility = View.GONE
        binding.scrollEmo.visibility = View.GONE

        binding.cardFav.visibility = View.VISIBLE
        binding.cardEmo.visibility = View.VISIBLE
        binding.cardMore.visibility = View.VISIBLE
        binding.cardExp.visibility = View.VISIBLE
        binding.cardHob.visibility = View.VISIBLE
    }

    private fun setupSaveButton() {
        binding.save.setOnClickListener {
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
        val birthDate = binding.birthDateButton.text.toString()
        val color = binding.colorSpinner.selectedItem.toString()
        val place = binding.place.text.toString()
        val fruit = binding.fruit.text.toString()
        val food = binding.food.text.toString()
        val song = binding.song.text.toString()
        val movie = binding.movie.text.toString()
        val artist = binding.artist.text.toString()
        val favoriteHobby = binding.edtHobby.text.toString()
        val relaxation = binding.edtRelax.text.toString()
        val indispensableHobby = binding.edtIndispensableHobby.text.toString()
        val happiestMoment = binding.edtHappiestMoment.text.toString()
        val inspiration = binding.edtInspiration.text.toString()
        val peace = binding.edtPeace.text.toString().trim()
        val joy = binding.edtJoy.text.toString().trim()
        val angerTrigger = binding.edtAngerTrigger.text.toString().trim()
        val sadnessEmbrace = binding.edtSadnessEmbrace.text.toString().trim()
        val biggestDream = binding.edtBiggestDream.text.toString().trim()
        val feelSafe = binding.edtFeelSafe.text.toString().trim()
        val biggestLifeChange = binding.inputBiggestLifeChange.text?.toString().orEmpty().trim()
        val challengingMoment = binding.inputChallengingMoment.text?.toString().orEmpty().trim()
        val meaningfulExperience = binding.inputMeaningfulExperience.text?.toString().orEmpty().trim()
        val selfDiscovery = binding.inputSelfDiscovery.text?.toString().orEmpty().trim()
        val motivation = binding.inputMotivation.text?.toString().orEmpty().trim()
        val fear = binding.inputFear.text?.toString().orEmpty().trim()
        val proudestMoment = binding.inputProudestMoment.text?.toString().orEmpty().trim()

        if (selectedGenderId == -1 || username.isBlank() || password.isBlank() ||
            fullName.isBlank() || address.isBlank() || number.isBlank() || gmail.isBlank()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

        val userInfo = UserInfo(
            username, password, fullName, selectedGender, address, number, gmail,
            birthDate, color, place, fruit, food, song, movie, artist, favoriteHobby,
            relaxation, indispensableHobby, happiestMoment, inspiration, peace, joy,
            angerTrigger, sadnessEmbrace, biggestDream, feelSafe, biggestLifeChange,
            challengingMoment, meaningfulExperience, selfDiscovery, motivation, fear, proudestMoment
        )

        val userInfoList = sharedPrefHelper.getUserInfoList().toMutableList()
        userInfoList.add(userInfo)
        sharedPrefHelper.saveUserInfoList(userInfoList)

        Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MySlampagesActivity::class.java)
        startActivity(intent)
        finish()
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
