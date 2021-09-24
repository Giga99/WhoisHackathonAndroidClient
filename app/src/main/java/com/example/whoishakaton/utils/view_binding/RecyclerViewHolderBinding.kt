/*
 * Copyright (C) 2021 LotusFlare
 * All Rights Reserved.
 * Unauthorized copying and distribution of this file, via any medium is strictly prohibited.
 */

package com.example.whoishakaton.utils.view_binding

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class RecyclerViewHolderBinding<T : ViewBinding>(val viewBinding: T) :
    RecyclerView.ViewHolder(viewBinding.root)
