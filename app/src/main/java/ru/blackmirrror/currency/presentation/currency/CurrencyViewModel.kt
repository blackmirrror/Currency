package ru.blackmirrror.currency.presentation.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.blackmirrror.currency.domain.models.ClientError
import ru.blackmirrror.currency.domain.models.CurrencyResponse
import ru.blackmirrror.currency.domain.models.NoContent
import ru.blackmirrror.currency.domain.models.NoInternet
import ru.blackmirrror.currency.domain.models.ResultState
import ru.blackmirrror.currency.domain.models.ServerError
import ru.blackmirrror.currency.domain.usecases.GetCurrencyUseCase
import ru.blackmirrror.currency.domain.usecases.GetInternetConnectionUseCase
import ru.blackmirrror.currency.domain.usecases.GetLastLoadDateUseCase

class CurrencyViewModel(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getInternetConnectionUseCase: GetInternetConnectionUseCase,
    private val getLastLoadDateUseCase: GetLastLoadDateUseCase
) : ViewModel() {

    private val _currency = MutableLiveData<CurrencyResponse?>()
    val currency: LiveData<CurrencyResponse?> = _currency

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _dateLoading = MutableLiveData<Long>()
    val dateLoading: LiveData<Long> = _dateLoading

    private lateinit var coroutineScope: CoroutineScope

    init {
        checkConnection()
    }

    fun checkConnection() {
        if (getInternetConnectionUseCase.invoke()) {
            startPeriodicRequests()
        } else {
            loadCurrency()
        }
    }

    private fun startPeriodicRequests() {
        coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            loadCurrency()
            while (true) {
                delay(30000)
                loadCurrency()
            }
        }
    }

    private fun loadCurrency() {
        viewModelScope.launch {
            _loading.postValue(true)
            when (val result = getCurrencyUseCase.invoke()) {
                is ResultState.Success -> {
                    _currency.postValue(result.data)
                }
                else -> handleError(result)
            }
            _loading.postValue(false)
            _dateLoading.postValue(getLastLoadDateUseCase.invoke())
        }
    }

    private fun <T> handleError(result: ResultState<T>) {
        if (result is ResultState.Error) {
            when (result.error) {
                is NoInternet -> {
                    _error.postValue("Соединение потеряно")
                    closeScope()
                }
                is NoContent -> _error.postValue("Пока здесь пусто")
                is ClientError -> _error.postValue("Проверьте правильность данных")
                is ServerError -> _error.postValue("Неполадки на сервере, уже работаем над этим")
            }
        }
    }

    private fun closeScope() {
        if (::coroutineScope.isInitialized)
            coroutineScope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        closeScope()
    }
}