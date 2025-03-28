package pl.example.gropemessenger.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import pl.example.gropemessenger.viewModels.ContactsViewModel

@Composable
fun ContactsScreen() {

    val viewModel = remember { ContactsViewModel() }


    val permissionManager: PermissionManager = PermissionManager()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        permissionManager.checkContactsPermission(context)
    }


    if (permissionManager.hasPermission(context)) {
        viewModel.readContacts(context)
    }
    val contacts = viewModel.contacts.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Lista kontaktów", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(contacts.value.size) { index ->
                Text(text = contacts.value[index].name)
                Text(text = contacts.value[index].number.toString())
                HorizontalDivider(thickness = 1.dp)
            }

        }
    }


}