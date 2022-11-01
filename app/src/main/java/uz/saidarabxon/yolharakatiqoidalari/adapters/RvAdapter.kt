package uz.saidarabxon.yolharakatiqoidalari.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.databinding.ItemRvBinding
import uz.saidarabxon.yolharakatiqoidalari.fragments.Like_fragment
import uz.saidarabxon.yolharakatiqoidalari.fragments.PagerFragment
import uz.saidarabxon.yolharakatiqoidalari.models.Models

class RvAdapter(private val list: ArrayList<Models>, var rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.VH>() {
    inner class VH(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(models: Models) {
            binding.tvName.text = models.name
            binding.image.setImageURI(Uri.parse(models.img))


            list.forEach {
                if (it.like == "1") {
                    binding.dislike.setImageResource(R.drawable.selected_heart)
                } else if (it.like == "0") {
                    binding.dislike.setImageResource(R.drawable.unselected_heart)
                }
            }

            binding.delete.setOnClickListener {
                rvClick.deleteClick(models)
            }
            binding.edit.setOnClickListener {
                rvClick.editClick(models)
            }
            binding.item.setOnClickListener {
                rvClick.itemClick(models)

            }
            binding.dislike.setOnClickListener {
                rvClick.likeClick(models)
                if (models.like == "1") {
                    binding.dislike.setImageResource(R.drawable.selected_heart)
                } else {
                    binding.dislike.setImageResource(R.drawable.unselected_heart)
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface RvClick {
        fun editClick(models: Models)
        fun deleteClick(models: Models)
        fun itemClick(models: Models)
        fun likeClick(models: Models)
    }
}
