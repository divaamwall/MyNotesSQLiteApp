package com.diva.mynotessqliteapp

data class Note(
    val id: Int ?= null,
    val title: String ?= null,
    val content: String ?= null,
)
