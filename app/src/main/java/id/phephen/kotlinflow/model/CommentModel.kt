package id.phephen.kotlinflow.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Phephen on 25/03/2022.
 */
data class CommentModel(
    val postId: Int? = null,
    val id: Int? = null,
    val email: String? = null,
    val name: String? = null,
    @SerializedName("body")
    val comment: String? = null
)
