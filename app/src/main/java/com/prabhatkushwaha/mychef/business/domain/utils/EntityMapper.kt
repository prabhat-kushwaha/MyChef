package com.prabhatkushwaha.mychef.business.domain.utils

interface EntityMapper<Entity, Model> {
    fun fromEntity(entity: Entity): Model
    fun toEntity(model: Model): Entity
}