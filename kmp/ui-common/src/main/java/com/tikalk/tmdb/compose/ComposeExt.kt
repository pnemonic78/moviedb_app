package com.tikalk.tmdb.compose

import android.net.Uri
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

typealias ComposableContent = @Composable (() -> Unit)

typealias OnClickCallback = (() -> Unit)

typealias OnLinkCallback = ((uri: Uri) -> Unit)

fun ComposeView.withContent(content: ComposableContent): ComposeView {
    return apply {
        setContent(content)
    }
}

fun Modifier.aspectRatio(width: Dp, height: Dp): Modifier {
    return aspectRatio(width / height)
}

fun Modifier.aspectRatio(width: Float, height: Float): Modifier {
    return aspectRatio(width / height)
}

@Composable
fun stringResourceInspection(id: StringResource, count: Int = 1): String {
    val s = stringResource(id)
    if (LocalInspectionMode.current) {
        val buf = StringBuilder(s)
        for (i in 1 until count) {
            buf.append('\n').append(s)
        }
        return buf.toString()
    }
    return s
}

fun Size.toIntSize(): IntSize = IntSize(width.roundToInt(), height.roundToInt())
