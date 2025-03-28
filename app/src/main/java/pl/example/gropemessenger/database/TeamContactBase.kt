package pl.example.gropemessenger.database

import androidx.room.Database
import androidx.room.Room
import pl.example.gropemessenger.database.Dao.TeamDao
import pl.example.gropemessenger.database.Entity.ContactTeam

@Database(entities = [ContactTeam::class], version = 1)
abstract class TeamContactBase {
    abstract fun teamDao(): TeamDao
}

