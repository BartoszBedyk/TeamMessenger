package pl.example.gropemessenger.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController){

    Column {
        Button(onClick = {
            navController.navigate("contacts_screen")
        }) {
            Row{
                Text(text = "Utwórz zaspół")
                Icon(Icons.Default.Add, "Dodanie grupy")
            }

        }
    }

}