package com.codingwithmitch.daggerhiltplayground.util

interface EntitiyMapper<Entity,DomainModel> {
    fun mapFromEntity(entity: Entity):DomainModel
    fun mapToEntity(domainModel: DomainModel):Entity
}