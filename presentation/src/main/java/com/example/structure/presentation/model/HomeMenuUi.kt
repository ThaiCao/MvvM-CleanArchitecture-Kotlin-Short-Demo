package com.example.structure.presentation.model

sealed class HomeItemUi(override val type: Int): ItemDifferent {
    companion object {
        const val TYPE_SPACE = 0
        const val TYPE_MOVIE_HOT = 1
        const val TYPE_MOVIE_NEW = 2
        const val TYPE_MOVIE_POPULAR = 3
    }

    data class SpaceItem(val height: Int = 8, val colorAttr: Int) :
        HomeItemUi(TYPE_SPACE) {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            return this.type == TYPE_SPACE
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            return hashCode() == new.hashCode()
        }
    }

    data class HotMovieRowItem(
        val id: Int,
        val name: String,
        val imageUrl: String,
    ) : SimpleItem() {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            if (new !is HotMovieRowItem) return false

            return this.id == new.id
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            if (new !is HotMovieRowItem) return false

            return this.id == new.id &&
                this.name == new.name &&
                this.imageUrl == new.imageUrl
        }
    }

    data class HotMovieItem(val items: List<HotMovieRowItem>) :
        HomeItemUi(TYPE_MOVIE_HOT) {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            return this.type == TYPE_MOVIE_HOT
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            return hashCode() == new.hashCode()
        }
    }

    data class NewMovieRowItem(
        val id: Int,
        val name: String,
        val imageUrl: String,
    ) : SimpleItem() {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            if (new !is NewMovieRowItem) return false

            return this.id == new.id
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            if (new !is NewMovieRowItem) return false

            return this.id == new.id &&
                this.name == new.name &&
                this.imageUrl == new.imageUrl
        }
    }

    data class NewMovieItem(val items: List<NewMovieRowItem>) :
        HomeItemUi(TYPE_MOVIE_NEW) {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            return this.type == TYPE_MOVIE_NEW
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            return hashCode() == new.hashCode()
        }
    }

    data class PopularMovieRowItem(
        val id: Int,
        val name: String,
        val imageUrl: String,
    ) : SimpleItem() {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            if (new !is PopularMovieRowItem) return false

            return this.id == new.id
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            if (new !is PopularMovieRowItem) return false

            return this.id == new.id &&
                this.name == new.name &&
                this.imageUrl == new.imageUrl
        }
    }

    data class PopularMovieItem(val items: List<PopularMovieRowItem>) :
        HomeItemUi(TYPE_MOVIE_POPULAR) {
        override fun areItemsTheSame(new: ItemDifferent): Boolean {
            return this.type == TYPE_MOVIE_POPULAR
        }

        override fun areContentsTheSame(new: ItemDifferent): Boolean {
            return hashCode() == new.hashCode()
        }
    }
}
