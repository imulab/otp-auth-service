package io.imulab.astrea

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.coRouter

@SpringBootApplication
class App : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) = beans {
        bean { ValidationHandler() }
        bean {
            coRouter {
                POST("/auth", ref<ValidationHandler>()::validate)
            }
        }
    }.initialize(applicationContext)
}

fun main(args: Array<String>) {
    runApplication<App>(*args) {
        addInitializers(App())
    }
}
