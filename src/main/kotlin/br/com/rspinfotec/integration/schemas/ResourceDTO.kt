package br.com.rspinfotec.integration.schemas

import io.micronaut.serde.annotation.Serdeable.Deserializable

@Deserializable
data class ResourceDTO(
    val id: String,
    val format: String,
    val url: String,
    val name: String,
    val description: String
)
