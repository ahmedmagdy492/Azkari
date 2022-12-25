package com.magdyradwan.azkari

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class AzkarAdapter(private val mList: ArrayList<Zaker>) : RecyclerView.Adapter<AzkarAdapter.ViewHolder>() {

    lateinit var context: Context
    var totalCount: Int

    init {
        totalCount = countAzkarRepeations()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        this.context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.zaker_item, parent, false)

        return ViewHolder(view)
    }

    private fun countAzkarRepeations(): Int {
        var totalCount = 0;
        for(item in mList) {
            totalCount += item.noOfRepeations
        }

        return totalCount
    }

    private fun getSharedPreferencesValue() : Int {
        val sharedPreferences = context.getSharedPreferences("azkarCount", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("totalCount", 0)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.content
        holder.button.text = String.format("%d مرة", ItemsViewModel.noOfRepeations)

        holder.card.setOnClickListener {
            this.totalCount--

            if(this.totalCount == 0) {
                var totalCount = countAzkarRepeations()
                totalCount += getSharedPreferencesValue()
                val intent = Intent(context, FinishAzkarActivity::class.java)
                intent.putExtra("totalCount", totalCount)
                context.startActivity(intent)
            }

            if(ItemsViewModel.noOfRepeations > 0) {
                holder.button.text = String.format("%d مرة", --ItemsViewModel.noOfRepeations)
                vibratePhone()
                if(ItemsViewModel.noOfRepeations == 0) {
                    mList.remove(ItemsViewModel)
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mList.size);
                }
            }
        }

        holder.button.setOnClickListener {

            this.totalCount--

            if(this.totalCount == 0) {
                var totalCount = countAzkarRepeations()
                totalCount += getSharedPreferencesValue()
                val intent = Intent(context, FinishAzkarActivity::class.java)
                intent.putExtra("totalCount", totalCount)
                context.startActivity(intent)
            }

            if(ItemsViewModel.noOfRepeations > 0) {
                holder.button.text = String.format("%d مرة", --ItemsViewModel.noOfRepeations)
                vibratePhone()
                if(ItemsViewModel.noOfRepeations == 0) {
                    mList.remove(ItemsViewModel)
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mList.size);
                }
            }
        }
    }

    private fun vibratePhone() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.zaker_content)
        val card: CardView = itemView.findViewById(R.id.zaker_card)
        val button: Button = itemView.findViewById(R.id.card_button)
    }
}