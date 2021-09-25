/*
 * Copyright (C) 2021 LotusFlare
 * All Rights Reserved.
 * Unauthorized copying and distribution of this file, via any medium is strictly prohibited.
 */

package com.example.whoishakaton.utils

/**
 * Use to make anything an expression, making the compiler force you to cover all cases (such as in 'when')
 */
val <T> T.exhaustive
    get() = this

/**
 * Useful for ignoring the result via returning [Unit]. Can be handy when function return type is [Unit]
 * and expression body returns non-[Unit] value.
 */
val <T> T.ignore
    get() = Unit
