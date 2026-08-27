package com.example.miniaplicativos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import kotlin.random.nextInt

enum class Tela {
    MENU, CONTADOR, CONVERSOR, DADO
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
// Inicializa a variável controladora de tela
            var telaAtual by remember { mutableStateOf(Tela.MENU) }
// Analisa o estado atual e desenha a tela correspondente
            when (telaAtual) {
                Tela.MENU -> TelaMenu(onNavegar = { destino -> telaAtual = destino })
                Tela.CONTADOR -> TelaContador(onVoltar = { telaAtual = Tela.MENU })
                Tela.CONVERSOR -> TelaConversor(onVoltar = { telaAtual = Tela.MENU })
                Tela.DADO -> TelaDado(onVoltar = { telaAtual = Tela.MENU })
            }
        }
    }
}

@Composable
fun TelaMenu(onNavegar: (Tela) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)) // Fundo cinza azulado claro
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hub de Exercícios Compose",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A)
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Botão para o App 1
        Button(
            onClick = { onNavegar(Tela.CONTADOR) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("1. Contador de Cliques")
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Botão para o App 2
        Button(
            onClick = { onNavegar(Tela.CONVERSOR) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7))
        ) {
            Text("2. Conversor de Temperatura")
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Botão para o App 3
        Button(
            onClick = { onNavegar(Tela.DADO) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669))
        ) {
            Text("3. Dado Virtual")
        }
    }
}

@Composable
fun TelaContador(onVoltar: () -> Unit) {
    var cliques by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Contador de Cliques", fontSize = 20.sp, fontWeight =
            FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Cliques: $cliques", fontSize = 36.sp, color = Color(0xFF3B82F6))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { cliques++ }) {
            Text("Incrementar (+1)")
        }
        Spacer(modifier = Modifier.height(48.dp))
        // Botão de navegação reversa
        OutlinedButton(onClick = onVoltar) {
            Text("Voltar ao Menu")
        }
    }
}

@Composable fun TelaConversor(onVoltar: () -> Unit) {
    var temperatura by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Conversor de Celcius para Fahrenheit", fontSize = 20.sp, fontWeight =
            FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = temperatura,
            onValueChange = { novoTexto -> temperatura = novoTexto }, // Atualiza enquanto digita
            label = { Text("Valor em Celcius") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Temperatura em Fahrenheit: ${fahrenheit}", fontSize = 16.sp, color = Color(0xFF3B82F6))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { fahrenheit = ((temperatura.toDouble() * 1.8) + 32) }) {
            Text("Converter")
        }
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedButton(onClick = onVoltar) {
            Text("Voltar ao Menu")
        }
    }
}

@Composable fun TelaDado(onVoltar: () -> Unit) {
    var resultado by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Rolagem de dados", fontSize = 20.sp, fontWeight =
            FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { resultado = Random.nextInt(1..6) }) {
            Text("Rolar 1D6")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = resultado.toString(), fontSize = 72.sp, color = Color(0xFF3B82F6))
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedButton(onClick = onVoltar) {
            Text("Voltar ao Menu")
        }
    }
}