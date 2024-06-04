import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependency

object Android {
    object Version {
        const val compileSdk = 34
        const val minSdk = 24
        const val targetSdk = 34

        const val gradle = "8.4.1"
        const val hilt = "2.51"
    }
}

object Kotlin {
    object Version {
        const val kotlin = "2.0.0"
    }
}

// Alias with no (without) version.
fun PluginDependenciesSpec.aliasNV(notation: Provider<PluginDependency>) =
    id(notation.get().pluginId)
