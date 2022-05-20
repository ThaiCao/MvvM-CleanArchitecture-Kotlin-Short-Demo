package com.example.mydemo.presentation.models.movie

import android.os.Parcelable
import com.example.mydemo.presentation.models.ItemDifferent
import kotlinx.parcelize.Parcelize

sealed class IMovieItemUi(override val type: Int ) : ItemDifferent {
    companion object {
        const val TYPE_POPULAR_MOVIE = 1
    }
    @Parcelize
    data class MoviePresentation (
        var name: String? = "",
        var id: Long = 0,
        var posterPath: String? = "",
        var title: String? = "",
        var profilePath: String? = "",
        var voteAverage: Double? = 0.0,
    ): IMovieItemUi(TYPE_POPULAR_MOVIE), Parcelable {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            if (new !is MoviePresentation) return false

            return this.id == new.id
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            if (new !is MoviePresentation) return false

            return this.id == new.id &&
                    this.title == new.title
        }
    }
}

