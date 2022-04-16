package my.com.madassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import my.com.madassignment.databinding.FragmentStaffDetailBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase


class StaffDetailFragment : Fragment() {
    private lateinit var binding: FragmentStaffDetailBinding
    private val nav by lazy { findNavController() }
    private val vm: StaffViewModel by activityViewModels()
    private val id by lazy { requireArguments().getString("id") ?: "" }

    private lateinit var adapter: StaffAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStaffDetailBinding.inflate(inflater, container, false)
        adapter = StaffAdapter()
        reset()

        return binding.root
    }

    private fun reset(){
        val s = vm.getID(id)
        if(s == null) {
            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
            nav.navigateUp()
            return
        }
        load(s)
    }
    private fun load(s:Staff) {
        binding.displayName.text=s.name
        binding.displayAge.text=s.age.toString()
        binding.displayAddress.text=s.address
        binding.displayGender.text=s.gender
        binding.displayID.text=s.id
        binding.displayPhone.text=s.phone.toString()
        binding.displayPostcode.text=s.postcode.toString()
        binding.displayState.text=s.state

    }

}