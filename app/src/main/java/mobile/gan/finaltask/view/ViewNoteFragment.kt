package mobile.gan.finaltask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mobile.gan.finaltask.R
import mobile.gan.finaltask.model.api.NoteRetrofit
import mobile.gan.finaltask.model.model.StateFragments
import mobile.gan.finaltask.viewmodel.NoteViewModel

class ViewNoteFragment : Fragment(R.layout.fragment_view_note) {

    val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val noteApi = NoteRetrofit.getRetrofit()
        viewModel.getNotesFromServer(noteApi, requireContext())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotesFromDb(requireContext())

        val adapter = Adapter(requireContext(), emptyList())
        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }

        view.findViewById<RecyclerView>(R.id.noteList).adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.addNote)
            .setOnClickListener {
                viewModel.changeFragment(StateFragments.Note)
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ViewNoteFragment()
    }
}