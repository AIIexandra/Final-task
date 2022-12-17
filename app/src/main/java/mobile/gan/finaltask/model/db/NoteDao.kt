package mobile.gan.finaltask.model.db

import androidx.room.*
import mobile.gan.finaltask.model.db.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM NOTE")
    suspend fun getNotes(): List<Note>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg post: Note)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(post: Note)
    @Update
    suspend fun updateNote(post: Note)
    @Delete
    suspend fun deleteNote(post: Note)
}