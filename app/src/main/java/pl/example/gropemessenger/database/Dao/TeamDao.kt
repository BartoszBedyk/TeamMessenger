package pl.example.gropemessenger.database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.example.gropemessenger.database.Entity.ContactTeam

@Dao
interface TeamDao {
    @Query("SELECT * FROM contact_team")
    fun getAll(): List<ContactTeam>

    @Insert
    fun insertAll(vararg team : ContactTeam)

    @Delete
    fun delete(team: ContactTeam)

    @Query("DELETE FROM contact_team WHERE contact_id = :id")
    fun deleteById(id: String)

    @Query("SELECT * FROM contact_team WHERE team_id = :teamId")
    fun getTeamById(teamId: String)

    @Query("SELECT * FROM contact_team WHERE team_name = :teamName")
    fun getTeamByName(teamName: String)

    @Query("UPDATE contact_team SET  team_name = :newName WHERE team_name = :oldName ")
    fun changeTeamName(oldName: String, newName: String)
}