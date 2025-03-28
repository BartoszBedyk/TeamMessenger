package pl.example.gropemessenger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.example.gropemessenger.ui.theme.GropeMessengerTheme
import pl.example.gropemessenger.views.ContactsScreen
import pl.example.gropemessenger.views.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GropeMessengerTheme {
                AppScaffold()
            }
        }
    }
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route ?: "unknown"
    val userId = navController.currentBackStackEntry?.arguments?.getString("userId")

    Scaffold(
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "main_screen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("contacts_screen") {
                ContactsScreen(navController)
            }
            composable("main_screen"){
                MainScreen(navController)
            }

        }
    }
}
