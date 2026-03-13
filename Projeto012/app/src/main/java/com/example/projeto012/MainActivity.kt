package com.example.projeto012

import android.R.attr.onClick
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun ListaSimplesScreen(){
    val nomes = listOf("Ian iOs", "Ana Android","Lindomar Linux","Wanessa Windows")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        nomes.forEach { nome ->
            Text(
                text = nome,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
@Composable
fun ListaLazyScreen(){
    val nomes = listOf("Ander ArrayList", "Lidiane List", "Mateus Map", "Ricardo Room",
        "Carlos Collection", "Vanessa Vetor", "Sergio Set", "Paula Pilha", "Fernando Fila",
        "Daniela Deque", "Henrique HashMap", "Beatriz Binary", "Gustavo Graph",
        "Tatiana Tree", "Lucas LinkedList", "Patricia Priority", "Roberto Repository",
        "Ander ArrayList", "Lidiane List", "Mateus Map", "Ricardo Room",
        "Carlos Collection", "Vanessa Vetor", "Sergio Set", "Paula Pilha", "Fernando Fila",
        "Daniela Deque", "Henrique HashMap", "Beatriz Binary", "Gustavo Graph",
        "Tatiana Tree", "Lucas LinkedList", "Patricia Priority", "Roberto Repository",
        "Ander ArrayList", "Lidiane List", "Mateus Map", "Ricardo Room",
        "Carlos Collection", "Vanessa Vetor", "Sergio Set", "Paula Pilha", "Fernando Fila",
        "Daniela Deque", "Henrique HashMap", "Beatriz Binary", "Gustavo Graph",
        "Tatiana Tree", "Lucas LinkedList", "Patricia Priority", "Roberto Repository",
        "Ander ArrayList", "Lidiane List", "Mateus Map", "Ricardo Room",
        "Carlos Collection", "Vanessa Vetor", "Sergio Set", "Paula Pilha", "Fernando Fila",
        "Daniela Deque", "Henrique HashMap", "Beatriz Binary", "Gustavo Graph",
        "Tatiana Tree", "Lucas LinkedList", "Patricia Priority", "Roberto Repository")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(nomes){ nome ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 4.dp
            ) {
                Text(
                    text = nome,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(20.dp),
                    letterSpacing = 1.sp
                )
            }

        }
    }
}
data class Produto(
    val nome:String,
    val preco:Double
)
@Composable
fun ListaProdutosScreen(){
    val produtos =  listOf(
        Produto("Mouse", 59.90),
        Produto("Teclado", 59.90),
        Produto("Monitor", 59.90),
        Produto("Notebook", 59.90),
        Produto("Hub USB", 59.90),
        Produto("Garrafa", 59.90),
    )
    val context  = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(produtos) { produto ->
         ProdutoItem(produto = produto){
             Toast.makeText(
                 context,
                 "Voce em clicou em: ${produto.nome}",
                 Toast.LENGTH_SHORT
             ).show()
         }
      }
    }
}
@Composable
fun ProdutoItem(produto: Produto, function: () -> Unit){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { function() },
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 3.dp,

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = produto.nome,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "R$ ${produto.preco}",
                style = MaterialTheme.typography.bodyMedium
            )


        }
    }
}

@Preview
@Composable
fun PreviewListaSimplesScreen(){
    Column() {
        ListaProdutosScreen()
    }

}