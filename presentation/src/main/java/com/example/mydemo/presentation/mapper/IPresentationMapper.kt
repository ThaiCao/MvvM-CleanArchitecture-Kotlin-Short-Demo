package com.example.mydemo.presentation.mapper

interface IPresentationMapper<PresentationModel, DomainModel> {
    fun mapToPresentation(domain: DomainModel): PresentationModel
}