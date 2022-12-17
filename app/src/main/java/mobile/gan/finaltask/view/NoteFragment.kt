package mobile.gan.finaltask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import mobile.gan.finaltask.R
import mobile.gan.finaltask.model.db.Note
import mobile.gan.finaltask.model.model.StateFragments
import mobile.gan.finaltask.viewmodel.NoteViewModel

class NoteFragment : Fragment(R.layout.fragment_note) {

    val viewModel: NoteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.saveNote)
            .setOnClickListener {
                viewModel.putNoteToDb(
                    Note(
                        id = 0,
                        userId = 1,
                        title = view.findViewById<TextInputEditText>(R.id.titleText).text.toString(),
                        body = view.findViewById<TextInputEditText>(R.id.noteText).text.toString()
                    ),
                    requireContext()
                )
                viewModel.changeFragment(StateFragments.View)
            }
        view.findViewById<FloatingActionButton>(R.id.backButton).setOnClickListener {
            viewModel.changeFragment(StateFragments.View)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}