package com.test.asmplugin

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Internal

/**
 *
 * @author jun.wang04
 * created at 2023/11/25 21:34
 */
interface AsmParameters: InstrumentationParameters {

    @get: Internal
    val specificClass: ListProperty<String>
}