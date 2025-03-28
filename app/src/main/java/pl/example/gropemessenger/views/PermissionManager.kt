package pl.example.gropemessenger.views

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionManager {

    private val CONTACT_PERMISSION_REQUEST = 1

    fun checkContactsPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

            val activity = context as? Activity
            activity?.let {

                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    CONTACT_PERMISSION_REQUEST
                )
            }
        } else {
            //readContacts(activity)
        }
    }

    fun hasPermission(context: Context): Boolean {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED)

    }


}