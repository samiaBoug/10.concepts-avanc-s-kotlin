package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                ToDoListApp()
            }
        }
    }
}

@Composable
fun ToDoListApp() {
    var text by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(mutableListOf("Tutorials", "Prototype", "Projet")) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Entrez une tâche") },
                placeholder = { Text("Entrez une tâche") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (text.isNotEmpty()) {
                    taskList.add(text)
                    text = ""
                }
            }) {
                Text("Ajouter")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ToDoList(taskList, onDelete = { task -> taskList.remove(task) })
    }
}

@Composable
fun ToDoList(tasks: List<String>, onDelete: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tasks) { task ->
            ToDoListCard(task, onDelete)
        }
    }
}

@Composable
fun ToDoListCard(task: String, onDelete: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            Text(task, Modifier.weight(1f))
            Button(onClick = { /* Ajouter la logique de modification ici */ }) {
                Text("Modifier")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onDelete(task) }) {
                Text("Supprimer")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListPreview() {
    ToDoListApp()
}

@Composable
fun ToDoListTheme(content: @Composable () -> Unit){
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color.White ,
            secondary = Color.Black,
            surface = Color(0xFF03DAC6) ,
            onSurface = Color.Red ,
            background = Color(0xFFAB9A4B)
        ),
        content = content ,
        typography = Typography(
            titleLarge = MaterialTheme.typography.titleLarge.copy(Color(0xFF6200EE))
        )
    )
}