package com.example.sullan_slambook

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sullan_slambook.databinding.ItemSlambookBinding


import com.example.sullan_slambook.model.UserInfo
class SlamPageAdapter(
    private val context: Context,
    private var slamPageList: List<UserInfo>, // A list with only one item
    private val onViewClick: (UserInfo) -> Unit
) : RecyclerView.Adapter<SlamPageAdapter.SlamPageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamPageViewHolder {
        val binding = ItemSlambookBinding.inflate(LayoutInflater.from(context), parent, false)
        return SlamPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlamPageViewHolder, position: Int) {
        val slamPage = slamPageList[position]
        holder.bind(slamPage)
    }

    override fun getItemCount(): Int {
        return slamPageList.size  // Only one item
    }

    fun updateData(newData: List<UserInfo>) {
        slamPageList = newData
        notifyDataSetChanged()
    }

    inner class SlamPageViewHolder(private val binding: ItemSlambookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(slamPage: UserInfo) {
            binding.profileName.text = slamPage.fullName
            binding.biggestLifeChange.text = slamPage.biggestLifeChange
            binding.challengingMoment.text = slamPage.challengingMoment


            binding.btnView.setOnClickListener {
                onViewClick(slamPage)  // Handle the "View" button click
            }
        }
    }
}
