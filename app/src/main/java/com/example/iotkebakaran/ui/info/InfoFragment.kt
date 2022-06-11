package com.example.iotkebakaran.ui.info

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.iotkebakaran.databinding.FragmentInfoBinding

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

        binding.infoScrollView.visibility = View.GONE

        infoViewModel.getKebakaran(id.toString())
        infoViewModel.kebakaran.observe(viewLifecycleOwner) {
            val temperatur = it.temperatur.toString() + "°C"
            val gas = it.gas.toString() + " ppm"
            val longitude = it.longitude.toString()
            val latitude = it.latitude.toString()
            binding.googleMapButton.setOnClickListener{
                val map_url = Intent(android.content.Intent.ACTION_VIEW)
                map_url.data = Uri.parse("https://www.google.com/maps/search/?api=1&query="+longitude+"%2C-"+latitude)
                startActivity(map_url)
            }
            binding.infoAlamatTextView.text = it.alamat
            binding.infoWaktuTextView.text = it.waktu
            binding.infoWaktuSejakTextView.text = it.waktu
            binding.infoTemperaturTextView.text = temperatur
            binding.infoGasTextView.text = gas
            if (!it.status) {
                binding.infoWaktuSejakTextView.text = "Sudah Selesai"
                binding.infoKebakaranSelesaiButton.isEnabled = false
                binding.infoKebakaranSelesaiButton.backgroundTintList = ColorStateList.valueOf((ContextCompat.getColor(requireContext(), androidx.appcompat.R.color.material_grey_600)))
            }

            binding.infoScrollView.visibility = View.VISIBLE
            binding.infoProgressBar.visibility = View.GONE
        }

        binding.infoKebakaranSelesaiButton.setOnClickListener {
            infoViewModel.kebakaranSelesai(id.toString())
            Toast.makeText(requireContext(), "Status kebakaran telah diganti menjadi selesai", Toast.LENGTH_SHORT).show()
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