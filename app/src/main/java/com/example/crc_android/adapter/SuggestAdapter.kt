package com.example.crc_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.data.network.model.suggest.RegistList
import com.example.crc_android.databinding.CardNoticeBinding
import com.example.crc_android.databinding.CardSuggestBinding
import com.example.crc_android.util.App
import com.example.crc_android.viewmodel.suggest.SuggestViewModel

class SuggestAdapter (
    val arrayList :ArrayList<RegistList>,
    val context : Fragment
): RecyclerView.Adapter<SuggestAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = CardSuggestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)


        return holder
    }



    inner class ViewHolder(val binding : CardSuggestBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: RegistList){
            binding.data = note
            binding.sampledate1.text = note.suggest_time

            binding.arrow1.setOnClickListener {
                App.title = binding.sampletext1.text.toString()
                App.date = binding.sampledate1.text.toString()
                App.content=note.content
                App.nickname=note.nickname
                App.reply=note.reply
                App.id=note.suggestid

                context.findNavController().navigate(R.id.action_suggestionFragment_to_suggestcontentFragment)

            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int = arrayList.size

}
