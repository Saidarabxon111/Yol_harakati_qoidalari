package uz.saidarabxon.yolharakatiqoidalari.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import uz.saidarabxon.yolharakatiqoidalari.R
import uz.saidarabxon.yolharakatiqoidalari.databinding.FragmentAddLabelBinding
import uz.saidarabxon.yolharakatiqoidalari.db.MyDbHelper
import uz.saidarabxon.yolharakatiqoidalari.models.Models
import uz.saidarabxon.yolharakatiqoidalari.models.MyObject
import uz.saidarabxon.yolharakatiqoidalari.models.TabItem
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddLabelFragment : Fragment() {
    private lateinit var list:ArrayList<String>
    private lateinit var spinnerAdapter: SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list= ArrayList()
        list.add(TabItem("Ogohlantiruvchi").type.toString().trim())
        list.add(TabItem("Imtiyozli").type.toString().trim())
        list.add(TabItem("Taqiqlovchi").type.toString().trim())
        list.add(TabItem("Buyuruvchi").type.toString().trim())
    }
    private lateinit var binding: FragmentAddLabelBinding
    private lateinit var uriPath:String
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =FragmentAddLabelBinding.inflate(layoutInflater)


        uriPath=""
        myDbHelper= MyDbHelper(binding.root.context)


        when(MyObject.edit){
            true->{
                binding.name.setText(MyObject.models!!.name)
                binding.info.setText(MyObject.models!!.info)
                binding.addImg.setImageURI(Uri.parse(MyObject.models!!.img))
                binding.addImg.setOnClickListener {
                    getImageContent.launch("image/*")
                }
                spinnerAdapter =uz.saidarabxon.yolharakatiqoidalari.adapters.SpinnerAdapter(list)
                binding.spinner.adapter=spinnerAdapter

                binding.btnSave.setOnClickListener {
                    if (uriPath==""){
                        uriPath=MyObject.models!!.img.toString()
                    }

                    MyObject.models!!.name=binding.name.text.toString()
                    MyObject.models!!.info=binding.info.text.toString()
                    MyObject.models!!.img=uriPath
                    MyObject.models!!.type=list[binding.spinner.selectedItemPosition]
                    myDbHelper.editLabel(MyObject.models!!)
                    findNavController().popBackStack()
                }
            }
            false->{
                binding.addImg.setOnClickListener {
                    getImageContent.launch("image/*")
                }
                spinnerAdapter= uz.saidarabxon.yolharakatiqoidalari.adapters.SpinnerAdapter(list)
                binding.spinner.adapter=spinnerAdapter

                binding.btnSave.setOnClickListener {
                    val label=Models(
                        name = binding.name.text.toString(),
                        info = binding.info.text.toString(),
                        img = uriPath,
                        like = "0",
                        type = list[binding.spinner.selectedItemPosition]
                    )
                    myDbHelper.addLabel(label)
                    findNavController().popBackStack()
                }
            }
        }


        return binding.root
    }
    @SuppressLint("SimpleDateFormat")
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.addImg.setImageURI(it)
            val inputStream = requireActivity().contentResolver.openInputStream(it)
            val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
            val file = File(requireActivity().filesDir, "$title.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            uriPath = file.absolutePath
        }



}