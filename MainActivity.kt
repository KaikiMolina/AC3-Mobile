package com.example.ac3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun botaoBusca(view: View){
        val inserirCep = findViewById<EditText>(R.id.cep)
        val cep = inserirCep.text.toString()

        if(cep.length != 8){
            Toast.makeText(applicationContext, "Deve ter 8 dígitos", Toast.LENGTH_LONG).show()
            return
        }

        val url = "https://viacep.com.br/ws/$cep/json"

        try {
            val resultadoLogradouro = findViewById<TextView>(R.id.logradouro)
            val resultadoComplemento = findViewById<TextView>(R.id.complemento)
            val resultadoBairro = findViewById<TextView>(R.id.bairro)
            val resultadoLocalidade = findViewById<TextView>(R.id.localidade)
            val resultadoUf = findViewById<TextView>(R.id.uf)
            val resultadoIbge = findViewById<TextView>(R.id.ibge)
            val resultadoGia = findViewById<TextView>(R.id.gia)
            val resultadoPrefixo = findViewById<TextView>(R.id.prefixo)
            val resultadoSiafi = findViewById<TextView>(R.id.siafi)

            val response = URL(url).readText()

            if(response.contains("\"erro\"")){
                Toast.makeText(applicationContext,"Cep não econtrado", Toast.LENGTH_LONG).show()

                resultadoLogradouro.setText("Logradouro: ")
                resultadoComplemento.setText("Complemento: ")
                resultadoBairro.setText("Bairro: ")
                resultadoLocalidade.setText("Localidade: ")
                resultadoUf.setText("UF: ")
                resultadoIbge.setText("IBGE: ")
                resultadoGia.setText("GIA: ")
                resultadoPrefixo.setText("DDD: ")
                resultadoSiafi.setText("SIAFI: ")
            } else{
                val address = JSONObject(response)

                resultadoLogradouro.setText("logradouro: ${address.getString("logradouro")}")
                resultadoComplemento.setText("Complemento: ${address.getString("complemento")}")
                resultadoBairro.setText("Bairro: ${address.getString("bairro")}")
                resultadoLocalidade.setText("Localidade: ${address.getString("localidade")}")
                resultadoUf.setText("UF: ${address.getString("uf")}")
                resultadoIbge.setText("IBGE: ${address.getString("ibge")}")
                resultadoGia.setText("GIA: ${address.getString("gia")}")
                resultadoPrefixo.setText("DDD: ${address.getString("ddd")}")
                resultadoSiafi.setText("SIAFI: ${address.getString("siafi")}")
            }
        }
    }
}