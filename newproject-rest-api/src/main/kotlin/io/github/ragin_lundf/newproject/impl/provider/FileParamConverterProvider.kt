package io.github.ragin_lundf.newproject.impl.provider

import java.io.InputStream
import java.lang.reflect.Type
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider


@Provider
class FileParamConverterProvider: ParamConverterProvider {
    override fun <T : Any?> getConverter(
        rawType: Class<T>?,
        genericType: Type?,
        annotations: Array<out Annotation>?
    ): ParamConverter<T>? {
        return if (rawType!!.isAssignableFrom(InputStream::class.java)) {
            (FileConverterProvider() as ParamConverter<T>?)!!
        } else null
    }

}
