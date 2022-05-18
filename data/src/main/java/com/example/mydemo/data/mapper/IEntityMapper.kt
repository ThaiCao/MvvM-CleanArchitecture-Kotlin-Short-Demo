package com.example.mydemo.data.mapper

interface IEntityMapper<DataModel, DomainModel> {
    fun mapFromEntity(entity: DataModel): DomainModel
    fun mapToEntity(domain: DomainModel): DataModel
}