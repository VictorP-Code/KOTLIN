package com.example.pokeminions

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val editEntrada = findViewById<EditText>(R.id.editEntrada)
        val btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        btnPesquisar.setOnClickListener {

            val pokemonArea = editEntrada.text.toString().trim()

            if (pokemonArea.isEmpty()) {

                Toast.makeText(
                    this,
                    "Digite uma área para pesquisar!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                txtResultado.text =
                    "Área encontrada: $pokemonArea. Prepare-se para encontrar Pokémon!"

                Toast.makeText(
                    this,
                    "Pesquisa realizada com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                editEntrada.text.clear()
            }
        }
    }
}