package id.phephen.kotlinflow.repository

import id.phephen.kotlinflow.model.CommentModel
import id.phephen.kotlinflow.network.ApiService
import id.phephen.kotlinflow.network.CommentApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Phephen on 25/03/2022.
 */
class CommentRepository(private val apiService: ApiService) {
    suspend fun getComment(id: Int): Flow<CommentApiState<CommentModel>> {
        return flow {
            val comment = apiService.getComments(id)
            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}