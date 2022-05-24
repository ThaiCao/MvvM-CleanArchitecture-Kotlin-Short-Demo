package com.example.mydemo.domain.usecases.base

import androidx.lifecycle.LiveData
import com.example.mydemo.domain.models.Resource

interface ILiveDataUseCase <out R : Any, in P> {
    suspend operator fun invoke(params: P): LiveData<Resource<@UnsafeVariance R>>
    class None
}
