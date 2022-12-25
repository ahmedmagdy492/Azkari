package com.magdyradwan.azkari

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MainListAdapter(private val mList: List<String>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    lateinit var context: Context

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        this.context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel

        holder.card.setOnClickListener {
            val intent = Intent(context, AzkarActivity::class.java)
            if(holder.textView.text.equals("اذكار الصباح")) {
                intent.putExtra("id", 1)
            }
            else {
                intent.putExtra("id", 2)
            }
            context.startActivity(intent)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.title)
        val card: CardView = itemView.findViewById(R.id.main_card)
    }
}