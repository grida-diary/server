package org.grida.databaserds

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DatabaseRdsApplication

fun main(args: Array<String>) {
    runApplication<DatabaseRdsApplication>(*args)
}
