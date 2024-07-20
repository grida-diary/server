package org.grida.support.requestlogger

import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Import(LogFilterRegistrar::class)
annotation class EnableLogRequest()
