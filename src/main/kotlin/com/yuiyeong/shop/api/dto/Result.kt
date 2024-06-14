package com.yuiyeong.shop.api.dto

open class Result<T>(open val data: T)

class ListResult<T>(list: List<T>) : Result<List<T>>(list)
