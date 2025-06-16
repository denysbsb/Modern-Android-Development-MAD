package com.modernandroiddevelopment

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.modernandroiddevelopment.ui.theme.ModernAndroidDevelopmentTheme


class MainActivity : ComponentActivity() {
    private val listValidateFruits = arrayListOf("orange","banana","grape")
    private val listWriteCorrect = mutableStateListOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreateTitle(Message("Game","Fruit"))
        }
    }

    @Preview
    @Composable
    fun PreviewComposable(){
        CreateTitle(Message("Game","Fruit"))
    }

    @Composable
    fun CreateTitle(message: Message){
        val context = LocalContext.current
        var textUser by remember { mutableStateOf("") }

        ModernAndroidDevelopmentTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column( modifier = Modifier.padding(innerPadding)) {

                    Text(
                        text = message.body+message.message,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    )

                    Row {

                        TextField(
                            value = textUser,
                            onValueChange = { textUser = it},
                            label = { Text("Seu texto") }
                        )

                        Spacer(modifier = Modifier
                            .height(8.dp)
                            .width(10.dp)
                        )

                        Button(
                            onClick = { clickButtonOk(context,textUser)  },
                        ) { Text("OK") }
                    }
                    ListAnswer(listWriteCorrect)
                }
            }
        }

    }

    private fun clickButtonOk(context: Context, texto: String) {
        if(listValidateFruits.contains(texto) && !listWriteCorrect.contains(texto)){
            listWriteCorrect.add(texto)
        } else {
            Toast.makeText(context, "Você digitou $texto e nâo tem na lista", Toast.LENGTH_SHORT).show()
        }
    }

    @Composable
    fun ListAnswer(fruits: MutableList<String>){
        Column {
            fruits.forEach {
                MessageRow(it)
            }
        }
    }

    @Composable
    fun MessageRow(fruit: String){
        Column {
            Text(
                text = fruit
            )
        }
    }
}

data class Message(val body: String, val message: String)