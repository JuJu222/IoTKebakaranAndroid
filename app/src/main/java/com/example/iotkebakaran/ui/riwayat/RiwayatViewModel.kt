package com.example.iotkebakaran.ui.riwayat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iotkebakaran.models.Kebakaran
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RiwayatViewModel : ViewModel() {
    private val database = Firebase.database
    private val myRef = database.getReference("kebakaran")

    private val _kebakaranList = MutableLiveData<List<Kebakaran>>().apply {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<Kebakaran>()

                for (kebakaran in dataSnapshot.children) {
                    kebakaran.getValue(Kebakaran.Info::class.java)?.let {
                        val temp = Kebakaran()
                        temp.id = kebakaran.key.toString()
                        temp.info = it
                        list.add(temp)
                        Log.d("AAA", "Value is: $kebakaran")
                    }
                }
                value = list
                Log.d("AAA", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("AAA", "Failed to read value.", error.toException())
            }
        })
    }
    val kebakaranList: LiveData<List<Kebakaran>> = _kebakaranList
}