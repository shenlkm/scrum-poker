package com.buildreams.scrumpoker.domain.entity

data class About
    (val authors: String,
     val version: String,
     val developers: List<String>,
     val repository: String,
     val openSourceLibs: List<String>)