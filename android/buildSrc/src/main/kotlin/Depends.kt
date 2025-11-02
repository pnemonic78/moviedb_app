import org.gradle.api.JavaVersion
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependency

object Android {
    object Version {
        const val compileSdk = 36
        const val minSdk = 24
        const val targetSdk = 36
    }
}

object Java {
    object Version {
        val jvm = JavaVersion.VERSION_11
    }
}

// Alias with no (without) version.
fun PluginDependenciesSpec.aliasId(notation: Provider<PluginDependency>) =
    id(notation.get().pluginId)
