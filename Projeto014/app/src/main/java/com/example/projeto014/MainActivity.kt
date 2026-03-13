package com.example.projeto014

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.projeto014.ui.theme.Projeto014Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrangAndDropScreen()
        }
    }
}
data class Produtos(
    val id: Int,
    val nome:String,
    val preco:Double,
)
@Composable
fun ListaProdutosScreen(){
    val produtos = remember {
        mutableStateListOf(
            Produtos(1, "Rest", 19.90),
            Produtos(2, "RestFull", 29.90),
            Produtos(3, "Monolito", 9.90),
            Produtos(4, "Rest", 19.90),
            Produtos(5, "Rest", 19.90),
            Produtos(6, "Rest", 19.90),
            Produtos(7, "Rest", 19.90),
            Produtos(8, "Rest", 19.90),
        )
    }

    var draggedIndex by remember { mutableStateOf<Int?> (null) }

    LazyColumn {
        itemsIndexed(produtos, key = {_ , item -> item.id }) { index, produto ->
           val isDragging = draggedIndex == index

            ProdutoItem(
                produto = produto,
                isDragging = isDragging,
                modifier = Modifier .pointerInput(produto.id) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = {
                                draggedIndex = index
                            },
                            onDragEnd = {
                                draggedIndex = null
                            },
                            onDragCancel = {
                                draggedIndex = null
                            },
                            onDrag = {_, dragAmount ->
                                val from  = draggedIndex ?: return@detectDragGesturesAfterLongPress

                                if(dragAmount.y > 40 && from < produtos.lastIndex){
                                    produtos.add(from + 1, produtos.removeAt(from))
                                    draggedIndex = from+1;

                                }
                                if(dragAmount.y < -40 && from > 0){
                                    produtos.add(from -1, produtos.removeAt(from))
                                    draggedIndex = from -1
                                }
                            }

                        )
                    }
            )
    }
}
}
@Composable
fun ProdutoItem(
    produto: Produtos,
    isDragging: Boolean,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = if (isDragging) 1.06f else 1f,
        label = "scale_animation"
    )
    val elevation by animateDpAsState(
        targetValue = if (isDragging) 12.dp else 2.dp,
        label ="elevation_animation"
    )
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            ),
        tonalElevation = elevation
    ) {
        Text(
            text = produto.nome,
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Preview
@Composable
fun PreviewListaProdutosScreen() {
    ListaProdutosScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrangAndDropScreen() {
    var textoRecebido by remember { mutableStateOf("Solte aqui") }

    var target = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val item = event.toAndroidDragEvent().clipData?.getItemAt(0);
                textoRecebido = item?.text?.toString() ?: "Nada recebido"
                return true
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = "Arraste este Texto",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Green)
                .dragAndDropSource {
                    DragAndDropTransferData(
                        clipData = ClipData.newPlainText(
                            "texto",
                            "Olá, alunos!"
                        )
                    )
                },
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = textoRecebido,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Magenta)
                .padding(16.dp)
                .dragAndDropTarget(
                    shouldStartDragAndDrop = {
                        event ->
                            event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                    },
                    target = target
                ),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
@Preview
fun PreviewDrangAndDropScreen(){
    DrangAndDropScreen()
}