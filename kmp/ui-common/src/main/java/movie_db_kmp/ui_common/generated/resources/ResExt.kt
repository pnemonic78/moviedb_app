package movie_db_kmp.ui_common.generated.resources

import kotlin.reflect.KProperty
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

private const val classNameDrawable = "movie_db_kmp.shared.generated.resources.CommonMainDrawable0"
private const val classNameString = "movie_db_kmp.shared.generated.resources.CommonMainString0"

fun getDrawableResource(name: String): DrawableResource {
    val klass = Class.forName(classNameDrawable).kotlin
    val instance  = klass.objectInstance
    val property = klass.members.first { it.name == name } as KProperty<*>
    return property.getter.call(instance) as DrawableResource
}

fun getStringResource(name: String): StringResource {
    val klass = Class.forName(classNameString).kotlin
    val instance  = klass.objectInstance
    val property = klass.members.first { it.name == name } as KProperty<*>
    return property.getter.call(instance) as StringResource
}

internal val Res.drawable.ic_grid_on: DrawableResource by lazy { getDrawableResource("ic_grid_on") }

internal val Res.drawable.ic_movie_black: DrawableResource by lazy { getDrawableResource("ic_movie_black") }

internal val Res.string.budget_label: StringResource by lazy { getStringResource("budget_label") }

internal val Res.string.genres_label: StringResource by lazy { getStringResource("genres_label") }

internal val Res.string.movie_details: StringResource by lazy { getStringResource("movie_details") }

internal val Res.string.movie_poster: StringResource by lazy { getStringResource("movie_poster") }

internal val Res.string.popularity_label: StringResource by lazy { getStringResource("popularity_label") }

internal val Res.string.release_date_label: StringResource by lazy { getStringResource("release_date_label") }

internal val Res.string.revenue_label: StringResource by lazy { getStringResource("revenue_label") }

internal val Res.string.runtime_label: StringResource by lazy { getStringResource("runtime_label") }

internal val Res.string.summary_label: StringResource by lazy { getStringResource("summary_label") }
