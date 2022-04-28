package id.phephen.kotlinflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.phephen.kotlinflow.model.CommentModel
import id.phephen.kotlinflow.network.CommentApiState
import id.phephen.kotlinflow.network.Status
import id.phephen.kotlinflow.repository.CommentRepository
import id.phephen.kotlinflow.utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Phephen on 25/03/2022.
 */
class CommentViewModel : ViewModel() {

    private val repository = CommentRepository(
        AppConfig.ApiService()
    )

    val commentState = MutableStateFlow(
        CommentApiState(
            Status.LOADING, CommentModel(), ""
        )
    )

    init {
        getNewComment(1)
    }

    fun getNewComment(id: Int) {
        commentState.value = CommentApiState.loading()

        viewModelScope.launch {
            repository.getComment(id)
                .catch {
                    commentState.value = CommentApiState.error(it.message.toString())
                }
                .collect {
                    commentState.value = CommentApiState.success(it.data)
                }
        }
    }

}