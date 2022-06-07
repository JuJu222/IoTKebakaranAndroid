package com.example.iotkebakaran.ui.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.iotkebakaran.databinding.FragmentInfoBinding
import com.example.iotkebakaran.models.Kebakaran
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val infoViewModel =
            ViewModelProvider(this).get(InfoViewModel::class.java)

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val id = arguments?.getString("id")

        infoViewModel.getKebakaran(id.toString())
        infoViewModel.kebakaran.observe(viewLifecycleOwner) {
            binding.infoAlamatTextView.text = it.alamat
            binding.infoWaktuTextView.text = it.waktu
            binding.infoWaktuSejakTextView.text = it.waktu
            binding.infoTemperaturTextView.text = it.temperatur.toString()
            binding.infoGasTextView.text = it.gas.toString()
        }

        binding.button5.setOnClickListener {

        }

        binding.infoBackImageButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}