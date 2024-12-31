package com.example

import java.lang.Exception

open class ApplicationException : Exception()

class EntityNotFoundException : ApplicationException()