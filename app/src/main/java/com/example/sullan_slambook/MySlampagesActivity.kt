package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sullan_slambook.databinding.ActivityMyslampagesBinding
import com.example.sullan_slambook.model.UserInfo

class MySlampagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyslampagesBinding
    private lateinit var slamPageAdapter: SlamPageAdapter
    private val slamPageList = mutableListOf<UserInfo>()
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyslampagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPrefHelper
        sharedPrefHelper = SharedPrefHelper(this)

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter before using it
        slamPageAdapter = SlamPageAdapter(this, slamPageList) { slamPage ->
            // Handle the "View" button click (optional)
            // You can pass the clicked item to another activity or show more details
        }
        binding.recyclerView.adapter = slamPageAdapter

        // Navigate back to the previous activity (you can change this to your actual back navigation)
        binding.btnback.setOnClickListener {
            val intent = Intent(this, getforward_activity::class.java)
            startActivity(intent)
        }

        // Load saved data into the RecyclerView
        loadSavedData()
    }

    // Load the list of UserInfo objects from SharedPreferences and update the RecyclerView
    private fun loadSavedData() {
        slamPageList.clear()  // Clear the existing list to avoid duplicates
        val userList = sharedPrefHelper.getUserInfoList()  // Load saved UserInfo list from SharedPreferences
        slamPageList.addAll(userList)  // Add the retrieved data to slamPageList
        slamPageAdapter.updateData(slamPageList)  // Update the RecyclerView with the new data
    }
}
