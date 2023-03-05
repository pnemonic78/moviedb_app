import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependency

object Android {
    object Version {
        const val compileSdk = 33
        const val minSdk = 24
        const val targetSdk = 33

        const val gradle = "7.4.2"
        const val hilt = "2.45"
    }
}

object Kotlin {
    object Version {
        const val kotlin = "1.8.10"
    }
}

// Alias without version.
fun PluginDependenciesSpec.aliasWV(notation: Provider<PluginDependency>) =
    id(notation.get().pluginId)
