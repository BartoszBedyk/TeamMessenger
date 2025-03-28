package pl.example.gropemessenger.viewModels

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import pl.example.gropemessenger.Contact

class ContactsViewModel : ViewModel() {


    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: MutableStateFlow<List<Contact>> = _contacts

    fun readContacts(context: Context) {
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            "${ContactsContract.Contacts.DISPLAY_NAME} ASC"
        )


        cursor?.use {
            val idColumn = it.getColumnIndex(ContactsContract.Contacts._ID)
            val nameColumn = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val hasNumberColumn = it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
            while (it.moveToNext()) {
                val id = it.getString(idColumn)
                val name = it.getString(nameColumn)
                val hasNumber = it.getInt(hasNumberColumn)


                if(hasNumber > 0 ){
                    val phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                        arrayOf(id),
                        null
                    )

                    phoneCursor?.use { cursor ->
                        val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        while (cursor.moveToNext()){
                            val number = cursor.getString(numberIndex)
                            _contacts.value += Contact(id, name, hasNumber, number)
                        }

                    }
                }
            }

        }
    }
}