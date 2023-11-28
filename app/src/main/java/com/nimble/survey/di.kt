package com.nimble.survey

import android.app.Application
import com.nimble.data.DataModule
import com.nimble.usescases.UseCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(AppModule().module,DataModule().module, UseCasesModule().module)
    }
}

@Module
@ComponentScan
class AppModule {


}
