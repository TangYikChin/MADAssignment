package my.com.madassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import my.com.madassignment.databinding.FragmentStaffDetailBinding
import my.com.madassignment.databinding.FragmentStaffListingBinding


class StaffListingFragment : Fragment() {

    private lateinit var binding: FragmentStaffListingBinding
    private val nav by lazy { findNavController() }
    private val vm: StaffViewModel by activityViewModels()

    private lateinit var adapter: StaffAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentStaffListingBinding.inflate(inflater,container,false)
        binding.btnAddStaff.setOnClickListener { nav.navigate(R.id.addStaffFragment) }
        adapter = StaffAdapter() {
            holder, s ->  holder.root.setOnClickListener {nav.navigate(R.id.staffDetailFragment,
            bundleOf("id" to s.id))}
            holder.editBtn.setOnClickListener{(toast("Edit"))}
            holder.deleteBtn.setOnClickListener{(toast("Delete"))}
        }
        load()

        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return binding.root
    }

    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun load() {
        // TODO: Load data
        Firebase.firestore
            .collection("staffs")
            .get()
            .addOnSuccessListener { staffs-> val list = staffs.toObjects<Staff>()
                adapter.submitList(list)
                //var result=""
                //list.forEach { s->result += "${s.id} ${s.name} ${s.age} ${s.gender} ${s.address} " +
                        //"${s.postcode} ${s.state} ${s.phone}\n" }

            }
    }


}