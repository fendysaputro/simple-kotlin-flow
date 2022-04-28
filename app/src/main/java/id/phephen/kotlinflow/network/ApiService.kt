package id.phephen.kotlinflow.network

import id.phephen.kotlinflow.model.CommentModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Phephen on 25/03/2022.
 */
interface ApiService {

    @GET("/comments/{id}")
    suspend fun getComments(@Path("id") id: Int): CommentModel

}