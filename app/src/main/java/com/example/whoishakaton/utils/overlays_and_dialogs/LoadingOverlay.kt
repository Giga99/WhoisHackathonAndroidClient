/*
 * Copyright (C) 2021 LotusFlare
 * All Rights Reserved.
 * Unauthorized copying and distribution of this file, via any medium is strictly prohibited.
 */

package com.example.whoishakaton.utils.overlays_and_dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whoishakaton.R

class LoadingOverlay : AutoDismissDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.loading_view, container, false)
    }

    override fun dialogTag(): String = "LoadingOverlay"
}
