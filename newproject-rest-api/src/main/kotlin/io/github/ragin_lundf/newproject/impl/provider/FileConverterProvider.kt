package io.github.ragin_lundf.newproject.impl.provider

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.Provider

@Provider
class FileConverterProvider: ParamConverter<InputStream> {
    override fun toString(value: InputStream): String {
        return String(value.readAllBytes(), StandardCharsets.UTF_8)
    }

    override fun fromString(value: String): InputStream {
        return ByteArrayInputStream(value.toByteArray())
    }
}
