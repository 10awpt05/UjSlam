package com.example.sullan_slambook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            val intent = Intent(this, view_activity::class.java)
            intent.putExtra("userInfo", slamPage) // Pass the UserInfo object
            startActivity(intent)
        }
        binding.recyclerView.adapter = slamPageAdapter

        // Attach swipe-to-delete functionality
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val userName = slamPageList[position].username

                if (userName != null) {
                    // Show a confirmation dialog before deleting
                    showDeleteConfirmationDialog(userName, position)
                } else {
                    // Handle null username (you can show an error or do nothing)
                    Toast.makeText(this@MySlampagesActivity, "Username is null", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Attach ItemTouchHelper to RecyclerView
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        // Navigate back to the previous activity
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

    // Function to show a confirmation dialog before deleting
    private fun showDeleteConfirmationDialog(userName: String, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to delete \"$userName\"?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Proceed with deletion if "Yes"
                deleteUser(userName, position)
            }
            .setNegativeButton("No") { dialog, id ->
                // Restore the item if "No"
                slamPageAdapter.notifyItemChanged(position)
            }

        val alert = builder.create()
        alert.show()
    }

    // Function to delete the user from the list and SharedPreferences
    private fun deleteUser(userName: String, position: Int) {
        // Remove the user from SharedPreferences
        removeFromSharedPreferences(userName)

        // Remove the user from the list and update the adapter
        slamPageList.removeAt(position)
        slamPageAdapter.updateData(slamPageList)

        // Show a toast message
        Toast.makeText(this@MySlampagesActivity, "$userName deleted", Toast.LENGTH_SHORT).show()
    }

    // Helper function to remove the user from SharedPreferences
    private fun removeFromSharedPreferences(userName: String) {
        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Get the current user names list from SharedPreferences
        val userNamesSet = sharedPreferences.getStringSet("userNames", mutableSetOf())?.toMutableSet()

        // Remove the user name from the set
        userNamesSet?.remove(userName)

        // Update the SharedPreferences with the new set
        editor.putStringSet("userNames", userNamesSet)
        editor.apply()
    }
}
