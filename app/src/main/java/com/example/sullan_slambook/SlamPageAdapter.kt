package com.example.sullan_slambook.adapter

import android.content.Intent
import android.media.RouteListingPreference.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sullan_slambook.R
import com.example.sullan_slambook.databinding.ItemSlambookBinding
import com.example.sullan_slambook.model.SlambookInfo
import com.example.sullan_slambook.view_activity

class SlamPageAdapter(
    private var slamPageList: MutableList<SlambookInfo>,
    private val onItemClick: (SlambookInfo) -> Unit,
    private val onEditClick: (SlambookInfo, Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<SlamPageAdapter.SlamPageViewHolder>() {

    inner class SlamPageViewHolder(private val binding: ItemSlambookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(slamPage: SlambookInfo, position: Int) {
            binding.profileName.text = slamPage.fullName
            binding.biggestLifeChange.text = slamPage.biggestLifeChange
            binding.challengingMoment.text = slamPage.challengingMoment

            binding.root.setOnClickListener { onItemClick(slamPage) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamPageViewHolder {
        val binding = ItemSlambookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlamPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlamPageViewHolder, position: Int) {
        holder.bind(slamPageList[position], position)
    }

    override fun getItemCount(): Int = slamPageList.size

    fun updateList(newList: List<SlambookInfo>) {
        slamPageList = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        slamPageList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItem(position: Int, updatedItem: SlambookInfo) {
        slamPageList[position] = updatedItem
        notifyItemChanged(position)
    }
}


