package com.test.asmplugin

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * @author jun.wang04
 * created at 2023/11/25 21:20
 */
class AsmPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("asmconfig", AsmExtension::class.java)

        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            val extension = project.extensions.getByType(AsmExtension::class.java)
            println("AsmPlugin ${extension.specificClass}")
            variant.instrumentation.setAsmFramesComputationMode(
                FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
            )
            variant.instrumentation.transformClassesWith(
                TestClassVistorFactory::class.java,
                InstrumentationScope.PROJECT

            ) { params ->
                params.specificClass.set(extension.specificClass)
            }
        }


    }

}