package mobile.gan.finaltask.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mobile.gan.finaltask.model.db.Note
import mobile.gan.finaltask.model.db.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class NoteDB: RoomDatabase() {
    abstract fun getDao(): NoteDao
}