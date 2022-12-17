package mobile.gan.finaltask.model.api

import mobile.gan.finaltask.model.db.Note
import retrofit2.Response
import retrofit2.http.GET

interface NoteApi {
    @GET ("posts")
    suspend fun getNotes(): Response<List<Note>>
}