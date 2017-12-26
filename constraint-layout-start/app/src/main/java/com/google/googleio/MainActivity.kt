// Copyright 2016 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//      http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.googleio

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.Barrier
import android.support.constraint.ConstraintSet.PARENT_ID
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DoneUI().setContentView(this)
    }
}

class DoneUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        constraintLayout {
            val header = imageView(R.drawable.singapore) {
                id = R.id.header
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams(0, 0)

            val favorite = imageView(R.drawable.ic_star) {
                id = R.id.favorite
                backgroundResource = R.drawable.info_background
                padding = dip(5)
            }.lparams(dip(36), dip(36))

            val title = textView(R.string.singapore) {
                id = R.id.title
                textSize = 24f
            }

            val cameraLabel = textView(R.string.camera) {
                id = R.id.cameraLabel
                labelFor = R.id.cameraType
            }

            val cameraType = editText(R.string.camera_value) {
                id = R.id.cameraType
                ems = 10
            }

            val settingsLabel = textView(R.string.settings) {
                id = R.id.settingsLabel
                labelFor = R.id.settings
            }

            val settings = editText(R.string.camera_settings) {
                id = R.id.settings
                ems = 10
            }

            val description = textView(R.string.singapore_description) {
                id = R.id.description
                textSize = 15f
                ellipsize = TextUtils.TruncateAt.END
                isVerticalFadingEdgeEnabled = true
            }.lparams(0, 0)

            val upload = button(R.string.upload) {
                id = R.id.upload
            }

            val discard = button(R.string.discard) {
                id = R.id.discard
            }

            applyConstraintSet {
                header {
                    connect(
                            TOP to TOP of PARENT_ID,
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID,
                            BOTTOM to BOTTOM of favorite margin dip(16)
                    )

                    horizontalBias = 1.0f
                    verticalBias = 0.0f
                }

                favorite {
                    connect(
                            TOP to TOP of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID margin dip(16),
                            BOTTOM to BOTTOM of PARENT_ID margin dip(16)
                    )

                    verticalBias = 0.19f
                }

                title {
                    connect(
                            LEFT to LEFT of PARENT_ID margin dip(16),
                            TOP to BOTTOM of header margin dip(16)
                    )
                }

                cameraLabel {
                    connect(
                            LEFT to LEFT of PARENT_ID margin dip(16),
                            BASELINE to BASELINE of cameraType
                    )
                }

                settingsLabel {
                    connect(
                            LEFT to LEFT of PARENT_ID margin dip(16),
                            BASELINE to BASELINE of settings
                    )
                }

                createBarrier(
                        R.id.labelBarrier,
                        Barrier.END,
                        R.id.settingsLabel,
                        R.id.cameraLabel)

                cameraType {
                    connect(
                            LEFT to LEFT of settings,
                            TOP to BOTTOM of title margin dip(8),
                            RIGHT to RIGHT of settings,
                            START to END of R.id.labelBarrier margin dip(8)
                    )
                }

                settings {
                    connect(
                            TOP to BOTTOM of cameraType margin dip(8),
                            RIGHT to RIGHT of description,
                            START to END of R.id.labelBarrier margin dip(8)
                    )
                }

                upload {
                    connect(
                            RIGHT to RIGHT of PARENT_ID margin dip(16),
                            BOTTOM to BOTTOM of PARENT_ID margin dip(16)
                    )
                }

                discard {
                    connect(
                            RIGHT to LEFT of upload margin dip(16),
                            BASELINE to BASELINE of upload
                    )
                }

                description {
                    connect(
                            LEFT to LEFT of PARENT_ID margin dip(16),
                            TOP to BOTTOM of settings margin dip(8),
                            RIGHT to RIGHT of PARENT_ID margin dip(16),
                            BOTTOM to TOP of discard margin dip(8)
                    )
                }
            }
        }
    }
}
