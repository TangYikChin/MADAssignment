package my.com.madassignment.managerLogIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.com.madassignment.R
import my.com.madassignment.databinding.FragmentUnblockBinding


class UnblockFragment : Fragment() {
    private lateinit var binding: FragmentUnblockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUnblockBinding.inflate(inflater, container, false)
        return binding.root
    }


}