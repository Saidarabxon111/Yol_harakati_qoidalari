package uz.saidarabxon.yolharakatiqoidalari.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.adapters.StateAdapter
import uz.saidarabxon.yolharakatiqoidalari.databinding.FragmentHomeBinding
import uz.saidarabxon.yolharakatiqoidalari.databinding.TabItemBinding
import uz.saidarabxon.yolharakatiqoidalari.models.TabItem

class HomeFragment : Fragment() {
    private lateinit var list: ArrayList<TabItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList()
        list.add(TabItem("Ogohlantiruvchi"))
        list.add(TabItem("Imtiyozli"))
        list.add(TabItem("Taqiqlovchi"))
        list.add(TabItem("Buyuruvchi"))
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var stateAdapter: StateAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

//        binding.home.setOnClickListener {
//
//        }

            binding.like.setOnClickListener {
                findNavController().navigate(R.id.like_fragment)
            }
                binding.about.setOnClickListener {
                    findNavController().navigate(R.id.about_Fragment)
                }

                stateAdapter = StateAdapter(list, this)
                binding.myViewpager.adapter = stateAdapter


                binding.myTablayout.addOnTabSelectedListener(object :
                    TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        val customView = tab?.customView
                        customView?.findViewById<TextView>(R.id.tab_item_tv)!!
                            .setTextColor(Color.parseColor("#005CA1"))
                        customView.findViewById<TextView>(R.id.tab_item_tv)!!
                            .setBackgroundColor(Color.WHITE)
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        val customView = tab?.customView
                        customView?.findViewById<TextView>(R.id.tab_item_tv)!!
                            .setBackgroundColor(Color.parseColor("#005CA1"))
                        customView?.findViewById<TextView>(R.id.tab_item_tv)!!
                            .setTextColor(Color.WHITE)
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }

                })


                TabLayoutMediator(binding.myTablayout, binding.myViewpager) { tab, position ->
                    val tabItemView = TabItemBinding.inflate(layoutInflater)
                    //write tablayout properties
                    tabItemView.tabItemTv.text = list[position].type
                    tab.customView = tabItemView.root
                }.attach()

                binding.btnAdd.setOnClickListener {
                    findNavController().navigate(R.id.addLabelFragment)
                }



        return binding.root
    }

}













