package com.example.pokemon.presentation.listPoke

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pokemon.data.model.ModelListPoke
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ListPokeView(listPokeViewModel: ListPokeViewModel) {

    val shouldShowDialog = remember { mutableStateOf(true) }
    val loadingDialog by listPokeViewModel.showD.observeAsState(true)
    val list: List<ModelListPoke> = listPokeViewModel.listCharacters

    if (shouldShowDialog.value) {

        Dialog(onDismissRequest = {  }) {
            Column(Modifier.width(200.dp)) {
                Text("¡ Bienvenido a la mejor app de Pokemon !", textAlign = TextAlign.Center)
                Button(onClick = {
                    shouldShowDialog.value = false
                    listPokeViewModel.callGetList()
                }, Modifier.fillMaxWidth()) {
                    Text("Close")
                }
            }
        }

    }else{
        Column(
            Modifier
                .fillMaxSize()
        ){
            if(loadingDialog){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }else{
                val activity = (LocalContext.current as? Activity)
                Row(Modifier.align(Alignment.End)) {
                    Text(text = "Logout")
                    IconButton(
                        onClick = {
                            listPokeViewModel.logout(activity)
                        },
                        modifier = Modifier.size(24.dp),
                    ){
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "logout")
                    }
                }
                LazyColumn {
                    items(list){ item->
                        ListCharacters(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ListCharacters(item: ModelListPoke) {

    val statsShowDialog = remember { mutableStateOf(false) }

    Card(
        Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(top = 24.dp)
            .clickable { statsShowDialog.value = true },
        border = BorderStroke(2.dp, Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ){
        Row {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "Card view",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
            )
            Column(Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = "${item.name?.uppercase()}",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    maxLines = 1,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    if (statsShowDialog.value){
        Dialog(onDismissRequest = {  }) {
            Column(Modifier.width(200.dp)) {
                Text(text = "Name: "+ item.name!!.uppercase(), textAlign = TextAlign.Justify)
                Text(text = "Hp: "+ item.hp.toString(), textAlign = TextAlign.Justify)
                Text(text = "Attack: "+ item.attack.toString(), textAlign = TextAlign.Justify)
                Text(text = "Defense: "+ item.defense.toString(), textAlign = TextAlign.Justify)
                Text(text = "SpecialAttack: "+ item.specialAttack.toString(), textAlign = TextAlign.Justify)
                Text(text = "SpecialDef: "+ item.specialDefense.toString(), textAlign = TextAlign.Justify)
                Text(text = "Speed: "+ item.speed.toString(), textAlign = TextAlign.Justify)
                Button(onClick = {
                    statsShowDialog.value = false
                }, Modifier.fillMaxWidth()) {
                    Text("Back")
                }
            }
        }
    }
}

