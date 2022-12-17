package mobile.gan.finaltask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mobile.gan.finaltask.R
import mobile.gan.finaltask.model.model.StateFragments
import mobile.gan.finaltask.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by viewModels()

    private lateinit var noteFragment: NoteFragment
    private lateinit var viewNoteFragment: ViewNoteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteFragment = NoteFragment.newInstance()
        viewNoteFragment = ViewNoteFragment.newInstance()

        noteViewModel.currentFragment.observe(this){
            when(it){
                StateFragments.View -> noteViewModel.goToView(supportFragmentManager, viewNoteFragment)
                StateFragments.Note -> noteViewModel.goToNote(supportFragmentManager, noteFragment)
            }
        }
    }
}