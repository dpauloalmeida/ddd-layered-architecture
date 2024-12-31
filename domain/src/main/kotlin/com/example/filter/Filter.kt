package com.example.filter

interface Filter<T, E> {
    fun apply(list: List<T>, filter: E): List<T>
}