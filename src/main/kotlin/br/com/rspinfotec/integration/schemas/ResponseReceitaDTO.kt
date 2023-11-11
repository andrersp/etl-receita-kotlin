package br.com.rspinfotec.integration.schemas

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Deserializable
data class ResponseReceitaDTO(
    val resources: Set<ResourceDTO>,
    @JsonProperty("metadata_created")
    val metadataCreated: String,
    @JsonProperty("metadata_modified")
    val metadataModified: String
)
