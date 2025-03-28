package pl.example.gropemessenger.data

import java.util.Date

data class ContactTeam(
    val contactId : List<String>,
    val teamName: String,
    val lastUsed: Date,
    val active: Boolean,
    val type: TeamType
)



