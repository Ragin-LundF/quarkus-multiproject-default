package io.github.ragin_lundf.testcontainers

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.PostgreSQLContainer
import java.util.Collections

class PostgresDbResource : QuarkusTestResourceLifecycleManager {
    override fun start(): Map<String, String> {
        db.start()
        return Collections.singletonMap(
            "quarkus.datasource.url", db.jdbcUrl
        )
    }

    override fun stop() {
        db.stop()
    }

    companion object {
        val db: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13")
            .withDatabaseName("tc")
            .withUsername("tc")
            .withPassword("tc")
    }
}
