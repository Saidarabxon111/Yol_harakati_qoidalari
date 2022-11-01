package uz.saidarabxon.yolharakatiqoidalari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.databinding.FragmentAboutBinding


class About_Fragment : Fragment() {
 private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentAboutBinding.inflate(layoutInflater)



    return binding.root
    }


}