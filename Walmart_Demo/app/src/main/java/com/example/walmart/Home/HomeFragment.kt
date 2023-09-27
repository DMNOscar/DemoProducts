package com.example.walmart.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.walmark.R
import com.example.walmark.databinding.FragmentHomeBinding
import com.example.walmark.databinding.FragmentProductsBinding


class HomeFragment : Fragment(), OnClickListener {


    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnProductsLis.setOnClickListener(this)

        return _binding.root
    }



    override fun onClick(view: View?) {


        when (view?.id) {
            R.id.btnProductsLis -> {

                findNavController().navigate(R.id.action_homeFragment_to_productsFragment, null)


            }
        }


    }


}