package com.example.projeto013

import android.R
import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.projeto013.ui.theme.Projeto013Theme
import kotlinx.coroutines.delay
import kotlin.math.max
import kotlin.math.roundToInt

data class Aluno(
    val id:Int,
    val nome:String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}
@Composable
fun ListaAlunosScreen(){

    var texto by remember { mutableStateOf("") }
    var proximoId by remember { mutableStateOf(6) }
    var itemSelecionadoId by remember { mutableStateOf(-1) }

    val alunos = remember {
        mutableStateListOf(
            Aluno(1, "Oto Objeto"),
            Aluno(2, "Leticia Lista"),
            Aluno(3, "Vitor Variavel"),
            Aluno(4, "Constancia Constante"),
            Aluno(5, "Carlos Classe")
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = texto,
            onValueChange = {texto = it},
            label = {Text("Novo Aluno")},
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if(texto.isNotBlank()){
                    alunos.add(Aluno(proximoId,texto))
                    proximoId++
                    texto = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar")
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(alunos, key = { it.id}) { aluno ->
                AlunoItem(
                    aluno = aluno,
                    selecionado = aluno.id == itemSelecionadoId,
                    onClick = { itemSelecionadoId = aluno.id },
                    onRemove = { alunos.remove(aluno) }
                )
            }
        }
    }

}
@Composable
fun AlunoItem(
    aluno: Aluno,
    selecionado: Boolean,
    onClick: () -> Unit,
    onRemove: () -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        tonalElevation = 3.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = aluno.nome,
                modifier = Modifier.padding(16.dp),
                color = if(selecionado) Color.Red else Color.Black,
                style = if (selecionado) {
                    MaterialTheme.typography.titleLarge
                }else{
                    MaterialTheme.typography.bodyLarge
                }
            )
            Text(
                text = "x",
                color = Color.Red,
                modifier = Modifier.clickable( onClick = onRemove )
            )
        }
    }
}

@Preview
@Composable
fun PreviewListaAlunosScreen(){
    ListaAlunosScreen()
}

data class Produto(
    val id:Int,
    val nome:String,
    val preco: Double
)

@Composable
fun ListaArrastavelScreen(){
    val produtos = listOf(
        Produto(1, "SaaS", 19.90),
        Produto(2, "PaaS", 29.90),
        Produto(3, "IaaS", 9.90),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(produtos, key = {it.id}){ produto ->
            itemArrastavel(produto)
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun itemArrastavel(produto: Produto){


    BoxWithConstraints() {
        val larguraTela = with(LocalDensity.current){
            maxHeight.toPx()
        }
        var offSetX by remember {mutableStateOf(0f)}

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offSetX.roundToInt(),0 ) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState {
                        delta ->
                        offSetX = (offSetX+delta).coerceIn(0f,larguraTela)
                    }
                ),
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 3.dp

        ){
            Text(
                text = produto.nome,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}

@Composable
@Preview
fun PreviewListaArrastavel(){
    ListaArrastavelScreen()
}







