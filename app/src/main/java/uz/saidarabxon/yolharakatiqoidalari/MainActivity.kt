package uz.saidarabxon.yolharakatiqoidalari

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.saidarabxon.yolharakatiqoidalari.databinding.ActivityMainBinding
import uz.saidarabxon.yolharakatiqoidalari.fragments.About_Fragment
import uz.saidarabxon.yolharakatiqoidalari.fragments.HomeFragment
import uz.saidarabxon.yolharakatiqoidalari.fragments.Like_fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
