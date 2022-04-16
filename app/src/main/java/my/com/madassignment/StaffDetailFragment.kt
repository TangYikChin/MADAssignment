package my.com.madassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.com.madassignment.databinding.FragmentStaffDetailBinding
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class StaffDetailFragment : Fragment() {
    private lateinit var binding: FragmentStaffDetailBinding
    private val nav by lazy { findNavController() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStaffDetailBinding.inflate(inflater,container,false)

        load()

        return binding.root
    }

    private fun load(){
        Firebase.firestore
            .collection("staffs")

    }


}