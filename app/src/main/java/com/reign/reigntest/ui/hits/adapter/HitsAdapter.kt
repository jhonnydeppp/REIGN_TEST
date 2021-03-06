package com.reign.reigntest.ui.hits.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reign.reigntest.R
import com.reign.reigntest.common.models.HitItem
import com.reign.reigntest.databinding.HitItemBinding
import com.reign.reigntest.ui.hits.HitInterface
import java.text.SimpleDateFormat
import java.util.*


class HitsAdapter(private val context: Context) : RecyclerView.Adapter<HitsAdapter.HitsHolder>() {
    private lateinit var listener: HitInterface
    private var hitsList: ArrayList<HitItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HitsHolder(HitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HitsHolder, position: Int) {
        holder.mHitItemBinding.hit = hitsList[position]
        holder.mHitItemBinding.rightView.setOnClickListener{
            listener.deleteElement(hitsList[position])
        }
        holder.mHitItemBinding.dragItem.setOnClickListener{

            hitsList[position].storyUrl?.let { storyUrl ->
                listener.goToWeb(storyUrl)
            }
            if(hitsList[position].storyUrl.isNullOrEmpty())
                listener.showMessage(context.getString(R.string.no_url_hit))
        }
        hitsList[position].createdAt?.let {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

            val date = sdf.parse(it);
            date?.let { simpleDate ->
                //parse date to relative time string
                val milliseconds = simpleDate.time
                holder.mHitItemBinding.tvTime.text = DateUtils.getRelativeTimeSpanString(milliseconds)
            }
        }

    }

    override fun getItemCount()= hitsList.size

    fun setListener(listener: HitInterface) {
        this.listener = listener
    }

    fun setHitsList(list: List<HitItem>) {
        hitsList.clear()
        hitsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class HitsHolder(
        val mHitItemBinding: HitItemBinding
    ) : RecyclerView.ViewHolder(mHitItemBinding.root)


}