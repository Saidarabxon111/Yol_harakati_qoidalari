package uz.saidarabxon.yolharakatiqoidalari.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.databinding.FragmentLInfoBinding
import uz.saidarabxon.yolharakatiqoidalari.models.Models

class LInfoFragment : Fragment() {
   private lateinit var binding: FragmentLInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
   binding =FragmentLInfoBinding.inflate(layoutInflater)

        val model =arguments?.getSerializable("key")as Models
binding.infoToolbar.text =model.name
binding.infoBack.setOnClickListener {
findNavController().popBackStack()
}

        binding.infoTv1.text =model.name
        binding.infoTv2.text =model.info
        binding.infoImg.setImageURI(Uri.parse(model.img))

        return binding.root
    }


}