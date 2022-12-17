package mobile.gan.finaltask.viewmodel

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mobile.gan.finaltask.R
import mobile.gan.finaltask.model.api.NoteApi
import mobile.gan.finaltask.model.db.Note
import mobile.gan.finaltask.model.db.NoteDB
import mobile.gan.finaltask.model.model.StateFragments
import mobile.gan.finaltask.view.NoteFragment
import mobile.gan.finaltask.view.ViewNoteFragment

class NoteViewModel: ViewModel() {

    private val mutableNotesLiveData: MutableLiveData<List<Note>> = MutableLiveData(emptyList())
    val notesLiveData: LiveData<List<Note>> = mutableNotesLiveData

    private val mutableCurrentFragment: MutableLiveData<StateFragments> = MutableLiveData(StateFragments.View)
    val currentFragment: LiveData<StateFragments> = mutableCurrentFragment

    var db: NoteDB? = null

    fun goToView(fragmentManager: FragmentManager, viewNoteFragment: ViewNoteFragment){
        fragmentManager.beginTransaction()
            .replace(R.id.container, viewNoteFragment)
            .commit()
    }

    fun goToNote(fragmentManager: FragmentManager, noteFragment: NoteFragment){
        fragmentManager.beginTransaction()
            .replace(R.id.container, noteFragment)
            .commit()
    }

    fun getNotesFromServer(noteApi: NoteApi, context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            val response = noteApi.getNotes()
            putToDB(response.body()!!, context)
        }
    }

    fun putToDB(notes: List<Note>, context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            if (db == null) {
                db = Room.databaseBuilder(context, NoteDB::class.java, "notes")
                    .build()
            }
            db!!.getDao().insertNotes(*notes.toTypedArray())
        }
    }

    fun getNotesFromDb(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            if (db == null) {
                db = Room.databaseBuilder(context, NoteDB::class.java, "notes")
                    .build()
            }
            mutableNotesLiveData.postValue(db!!.getDao().getNotes())
        }
    }

    fun putNoteToDb(note: Note,context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            if (db == null) {
                db = Room.databaseBuilder(context, NoteDB::class.java, "notes")
                    .build()
            }
            db!!.getDao().insertNote(note)
        }
    }

    fun changeFragment(stateFragments: StateFragments){
        mutableCurrentFragment.postValue(stateFragments)
    }
}