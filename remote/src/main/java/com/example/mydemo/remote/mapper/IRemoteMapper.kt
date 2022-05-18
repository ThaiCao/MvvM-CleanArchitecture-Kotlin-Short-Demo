package com.example.mydemo.remote.mapper

interface IRemoteMapper<RemoteModel, DataEntity> {

    fun mapFromApiResponseModel(remote: RemoteModel): DataEntity
}