package com.example.primeiroprojeto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.primeiroprojeto.ui.theme.PrimeiroProjetoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinhaTela()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Teste()
    }

}
@Composable
fun Teste(){
   Text(
       text = "OI,eu sou um texto"
   )
}
@Composable
fun MinhaTela(){

    var texto by remember { mutableStateOf("Texto Inicial") }
    var numero by remember { mutableStateOf(0) }
    var nome by remember { mutableStateOf("") }
    Column() {
        Text(
            text = texto

        )
        Button(onClick = { texto = "ABC"  }) {
            Text("Clique Aqui")
        }
        Column() {
            Text("Contador: $numero")
            Button(onClick = { numero++}) {
                Text("+1")
            }
        }
        Column() {
            TextField(
                value = nome,
                onValueChange = { nome = it }
            )
            Text("Nome digitado: $nome")
        }
        Column() {
            CalculadoraSimples()
        }
    }
}
@Composable
fun CalculadoraSimples()
{
    //Criar um novo composable com:
    //n1 e n2
    //Receber esses valores atraves de 2 textField
    //e mostrar a soma deles

    var n1 by remember { mutableStateOf("") }
    var n2 by remember { mutableStateOf("") }
    var r by remember { mutableStateOf(0) }

    Column() {
        TextField(
            value = n1,
            onValueChange = { n1 = it },
            label = {Text("Primeiro Numero")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        TextField(
            value = n2,
            onValueChange = { n2 = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        Button(onClick = {
            val num1 = n1.toIntOrNull() ?: 0
            val num2 = n2.toIntOrNull() ?: 0
            r = num1 + num2
        }) {
            Text("Somar")
        }
        Text("$r")
    }
    Column() {
        Text(
            "Item1",
            modifier = Modifier.background(Color.Gray).padding(16.dp).fillMaxWidth()
        )
        Text("Item2")
        Text("Item3")
    }
}



@Preview(showBackground = true)
@Composable
fun MinhaTelaPreviw(){
    MinhaTela()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrimeiroProjetoTheme {
        Greeting("Android")
    }
}