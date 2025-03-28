package pl.example.gropemessenger.database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.example.gropemessenger.data.TeamType
import java.util.Date
import java.util.UUID

@Entity(tableName = "contact_team")
data class ContactTeam(
    @PrimaryKey
    @ColumnInfo(name = "team_id")
    val teamId : UUID,
    @ColumnInfo(name = "contact_id")
    val contactId : UUID,
    @ColumnInfo(name = "team_name")
    val teamName: String,
    @ColumnInfo(name = "last_used")
    val lastUsed: Date,
    @ColumnInfo(name = "active")
    val active: Boolean,
    @ColumnInfo(name="type")
    val type: TeamType
    )
