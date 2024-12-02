package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Person

@Serializable
@SerialName("anonymous")
data object AnonymousPerson : Person()

@Serializable
@SerialName("identified")
data class IdentifiedPerson(
    val name: String,
) : Person()
