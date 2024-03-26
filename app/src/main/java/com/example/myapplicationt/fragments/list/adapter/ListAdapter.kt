package com.example.myapplicationt.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationt.R
import com.example.myapplicationt.data.models.Priority
import com.example.myapplicationt.data.models.ToDoData
import com.example.myapplicationt.databinding.RowLayoutBinding
import com.example.myapplicationt.fragments.list.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.cardTitle.text = dataList[position].title
        holder.binding.cardDescription.text = dataList[position].description


        holder.binding.rowBackground.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }


        when(dataList[position].priority) {
            Priority.HIGH -> holder.binding.priorityIndicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.priorityIndicator.context,
                    R.color.red
                )
            )
            Priority.MEDIUM -> holder.binding.priorityIndicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.priorityIndicator.context,
                    R.color.yellow
                )
            )
            Priority.LOW -> holder.binding.priorityIndicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.priorityIndicator.context,
                    R.color.green
                )
            )
        }

    }

    fun setData(toDoDataList: List<ToDoData>) {
        this.dataList = toDoDataList
        notifyDataSetChanged()
    }

}