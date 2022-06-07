package com.example.iotkebakaran.models

class Kebakaran {
    var id = ""
    var info = Info()

    class Info {
        var status = true
        var alamat = ""
        var waktu = ""
        var tanggal = ""
        var gas = 0
        var temperatur = 0
        var koordinat = ""
    }
}