package uz.saidarabxon.yolharakatiqoidalari.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.adapters.RvAdapter
import uz.saidarabxon.yolharakatiqoidalari.databinding.FragmentLikeFragmentBinding
import uz.saidarabxon.yolharakatiqoidalari.db.MyDbHelper
import uz.saidarabxon.yolharakatiqoidalari.models.Models
import uz.saidarabxon.yolharakatiqoidalari.models.MyObject


class Like_fragment : Fragment(), RvAdapter.RvClick {
    private lateinit var binding: FragmentLikeFragmentBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Models>
    private lateinit var rvAdapter: RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikeFragmentBinding.inflate(layoutInflater)


        binding = FragmentLikeFragmentBinding.inflate(layoutInflater)
        list = ArrayList()
        myDbHelper = MyDbHelper(binding.root.context)
        myDbHelper.getAllLabel().forEach {
            if (it.like == "1") {
                list.add(it)
            }
        }
        rvAdapter = RvAdapter(list, this)
        binding.myRv.adapter = rvAdapter
        return binding.root

    }

    override fun editClick(models: Models) {
        MyObject.edit = true
        MyObject.models = models
        findNavController().navigate(R.id.addLabelFragment, bundleOf("key" to models))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun deleteClick(models: Models) {
        myDbHelper.deleteLabel(models)
        list.remove(models)
        rvAdapter.notifyDataSetChanged()
    }

    override fun itemClick(models: Models) {
        findNavController().navigate(R.id.LInfoFragment, bundleOf("key" to models))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun likeClick(models: Models) {
        if (models.like == "1") {
            models.like = "0"
            myDbHelper.editLabel(models)
            rvAdapter.notifyDataSetChanged()
        } else if (models.like == "0") {
            models.like = "1"
            myDbHelper.editLabel(models)
            rvAdapter.notifyDataSetChanged()
        }
    }


}