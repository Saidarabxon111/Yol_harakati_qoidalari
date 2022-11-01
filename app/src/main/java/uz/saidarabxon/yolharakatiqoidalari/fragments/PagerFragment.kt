package uz.saidarabxon.yolharakatiqoidalari.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.adapters.RvAdapter
import uz.saidarabxon.yolharakatiqoidalari.databinding.FragmentPagerBinding
import uz.saidarabxon.yolharakatiqoidalari.db.MyDbHelper
import uz.saidarabxon.yolharakatiqoidalari.models.Models
import uz.saidarabxon.yolharakatiqoidalari.models.MyObject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PagerFragment : Fragment(), RvAdapter.RvClick {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentPagerBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var list: ArrayList<Models>
    private lateinit var myDbHelper: MyDbHelper
    private var TAG = "PagerFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)
        list = ArrayList()
        if (param1 == "Ogohlantiruvchi") {
            myDbHelper.getAllLabel().forEach {
                Log.d(TAG, "onCreateView: ${myDbHelper.getAllLabel()}")
                if (it.type == param1) {
                    list.add(it)

                }
            }
            rvAdapter = RvAdapter(list, this)
            binding.rv.adapter = rvAdapter

        }
        if (param1=="Imtiyozli"){
            myDbHelper.getAllLabel().forEach {
                if (it.type==param1){
                    list.add(it)
                }
            }
            rvAdapter= RvAdapter(list,this)
            binding.rv.adapter=rvAdapter
        }
        if (param1=="Taqiqlovchi"){
            myDbHelper.getAllLabel().forEach {
                if (it.type==param1){
                    list.add(it)
                }
            }
            rvAdapter= RvAdapter(list,this)
            binding.rv.adapter=rvAdapter
        }
        if (param1=="Buyuruvchi"){
            myDbHelper.getAllLabel().forEach {
                if (it.type==param1){
                    list.add(it)
                }
            }
            rvAdapter= RvAdapter(list,this)
            binding.rv.adapter=rvAdapter
        }

        return binding.root


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun editClick(models: Models) {
        MyObject.edit = true
        MyObject.models = models
        findNavController().navigate(R.id.addLabelFragment, bundleOf("key" to models))
    }

    override fun deleteClick(models: Models) {
        myDbHelper.deleteLabel(models)
        list.remove(models)
        rvAdapter.notifyDataSetChanged()
    }

    override fun itemClick(models: Models) {
        findNavController().navigate(R.id.LInfoFragment, bundleOf("key" to models))
    }

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
