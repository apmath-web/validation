package com.apmath.validation

import com.apmath.validation.basics.Message

class PathMessage(override val path: String, message: String) : Message(message), PathMessageInterface
