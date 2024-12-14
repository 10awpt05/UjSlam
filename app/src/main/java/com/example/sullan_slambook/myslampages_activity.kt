package com.example.sullan_slambook

import android.widget.Toast
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sullan_slambook.adapter.SlamPageAdapter
import com.example.sullan_slambook.databinding.ActivityMyslampagesBinding
import com.example.sullan_slambook.model.SlambookInfo

class myslampages_activity : AppCompatActivity() {

    private lateinit var slamPageAdapter: SlamPageAdapter
    private lateinit var slamPageList: MutableList<SlambookInfo>
    private lateinit var binding: ActivityMyslampagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyslampagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the list of SlamPages passed via intent
        slamPageList = intent.getParcelableArrayListExtra("slamPages") ?: mutableListOf()

        slamPageAdapter = SlamPageAdapter(
            slamPageList,
            onItemClick = { slambookInfo ->
                // Handle item click (view details)
            },
            onEditClick = { slambookInfo, position ->
                editItem(slambookInfo, position)
            },
            onDeleteClick = { position ->
                deleteItem(position)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@myslampages_activity)
            adapter = slamPageAdapter
        }
    }

    private fun editItem(slambookInfo: SlambookInfo, position: Int) {
        // Navigate to EditActivity or show a dialog to edit the item
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("slambookInfo", slambookInfo)
        intent.putExtra("position", position)
        startActivityForResult(intent, REQUEST_EDIT)
    }

    private fun deleteItem(position: Int) {
        slamPageList.removeAt(position)
        slamPageAdapter.removeItem(position)
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null) {
            val updatedItem = data.getParcelableExtra<SlambookInfo>("updatedItem") ?: return
            val position = data.getIntExtra("position", -1)
            if (position != -1) {
                slamPageList[position] = updatedItem
                slamPageAdapter.updateItem(position, updatedItem)
                Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val REQUEST_EDIT = 100
    }
}

