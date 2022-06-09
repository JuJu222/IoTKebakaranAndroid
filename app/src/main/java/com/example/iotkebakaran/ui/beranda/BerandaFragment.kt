package com.example.iotkebakaran.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iotkebakaran.R
import com.example.iotkebakaran.adapters.KebakaranRecyclerViewAdapter
import com.example.iotkebakaran.databinding.FragmentBerandaBinding
import com.example.iotkebakaran.models.Kebakaran

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val berandaViewModel =
            ViewModelProvider(this).get(BerandaViewModel::class.java)

        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.berandaRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.berandaConstraintLayout.visibility = View.GONE

        berandaViewModel.kebakaranList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val adapter = KebakaranRecyclerViewAdapter(it, R.id.action_BerandaFragment_to_infoFragment)
                binding.berandaRecyclerView.adapter = adapter

                val status = it.size.toString() + " Kebakaran"
                binding.berandaStatusTextView.text = status
                binding.berandaCardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.card_red))
            }

            binding.berandaConstraintLayout.visibility = View.VISIBLE
            binding.berandaProgressBar.visibility = View.GONE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}