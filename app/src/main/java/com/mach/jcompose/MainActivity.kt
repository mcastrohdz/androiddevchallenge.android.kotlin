package com.mach.jcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.mach.jcompose.data.model.Puppy
import com.mach.jcompose.data.repository.PuppiesRepository
import com.mach.jcompose.ui.app.PuppyDetailUi
import com.mach.jcompose.ui.app.PuppyListUi
import com.mach.jcompose.ui.library.CustomComposables.BaseUi

typealias onPuppyClickListener = (Puppy) -> Unit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaseUi {
                Box {
                    val isPuppyDetailShown = remember{ mutableStateOf(0) }

                    PuppyListUi.PuppiesList(puppyList = PuppiesRepository.getPuppiesList()) {
                        isPuppyDetailShown.value = it.id
                    }

                    if (isPuppyDetailShown.value != 0) {
                        PuppiesRepository.getPuppyById(isPuppyDetailShown.value)?.let {
                            PuppyDetailUi.PuppyDetail(puppy = it) {
                                isPuppyDetailShown.value = 0
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    BaseUi {
        PuppyListUi.PuppiesList(PuppiesRepository.getPuppiesList()) { /* no-op */ }
    }
}
