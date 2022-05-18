package com.example.mydemo.local.mapper

interface ILocalMapper<LocalModel, DataModel> {
    fun mapFromLocal(local: LocalModel): DataModel
    fun mapToLocal(entity: DataModel): LocalModel
}