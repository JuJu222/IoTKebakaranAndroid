package com.example.iotkebakaran.ui.info

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iotkebakaran.models.Kebakaran
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class InfoViewModel : ViewModel() {
    private val database = Firebase.database
    private val myRef = database.getReference("kebakaran")

    private val _kebakaran = MutableLiveData<Kebakaran.Info>()
    val kebakaran: LiveData<Kebakaran.Info> = _kebakaran

    fun getKebakaran(id: String) {
        myRef.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _kebakaran.value = dataSnapshot.getValue<Kebakaran.Info>()
                Log.d("AAA", "Value is: $_kebakaran.value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("AAA", "Failed to read value.", error.toException())
            }
        })
    }
}