package com.example.iotkebakaran.ui.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iotkebakaran.R
import com.example.iotkebakaran.adapters.KebakaranRecyclerViewAdapter
import com.example.iotkebakaran.databinding.FragmentBerandaBinding
import com.example.iotkebakaran.databinding.FragmentRiwayatBinding
import com.example.iotkebakaran.ui.beranda.BerandaViewModel

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val riwayatViewModel =
            ViewModelProvider(this).get(RiwayatViewModel::class.java)

        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.riwayatRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.riwayatConstraintLayout.visibility = View.GONE

        riwayatViewModel.kebakaranList.observe(viewLifecycleOwner) {
            val adapter = KebakaranRecyclerViewAdapter(it, R.id.action_RiwayatFragment_to_infoFragment)
            binding.riwayatRecyclerView.adapter = adapter

            binding.riwayatConstraintLayout.visibility = View.VISIBLE
            binding.riwayatProgressBar.visibility = View.GONE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}