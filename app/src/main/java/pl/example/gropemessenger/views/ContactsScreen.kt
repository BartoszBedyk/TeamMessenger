package pl.example.gropemessenger.views

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import pl.example.gropemessenger.Contact
import pl.example.gropemessenger.viewModels.ContactsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen() {

    val viewModel = remember { ContactsViewModel() }


    val permissionManager = PermissionManager()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        permissionManager.checkContactsPermission(context)
    }

    val contactsSelected = remember { mutableStateListOf<Contact>() }


    if (permissionManager.hasPermission(context)) {
        viewModel.readContacts(context)
    }
    val contacts = viewModel.contacts.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(Modifier.fillMaxWidth().background(Color.Yellow)) {
            Button(onClick = {}, Modifier.align(Alignment.CenterEnd),) {
                Icon(Icons.Default.Add, "Ikona dodania listy")
            }
        }
//        Button(onClick = { contacts.value.asReversed() }) {
//            Text(text = "Odwróć")
//        }
        Text(text = "Lista kontaktów", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(contacts.value.size) { index ->
                TakeContactElement(contacts.value[index]) {
                    if (!contactsSelected.contains(contacts.value[index])) {
                        contactsSelected.add(contacts.value[index])
                    } else
                        contactsSelected.remove(contacts.value[index])
                }
                //sortContactsBy(contactsSelected)
                //xD
            }
            Log.i("CONTACTS", contactsSelected.size.toString())
        }
    }


}

@Composable
fun TakeContactElement(contact: Contact, onCheck: () -> Unit) {
    var checked by remember { mutableStateOf(false) }
    Row {
        Column {
            Text(text = contact.name)
            Text(text = contact.number.toString())
        }
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.align(Alignment.End)) {
                Checkbox(checked = checked, onCheckedChange = {
                    checked = it
                    Log.i("CONTACTS_CHECKED", checked.toString())
                    onCheck()
                })
            }
        }
    }

    HorizontalDivider(thickness = 1.dp)

}