package uz.saidarabxon.yolharakatiqoidalari.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.saidarabxon.yolharakatiqoidalari.fragments.PagerFragment
import uz.saidarabxon.yolharakatiqoidalari.models.TabItem

class StateAdapter(val list:ArrayList<TabItem>, fragment: Fragment)
    : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return PagerFragment.newInstance(list[position].type.toString(), "")
    }
}