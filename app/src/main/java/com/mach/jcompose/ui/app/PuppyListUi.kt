package com.mach.jcompose.ui.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mach.jcompose.data.model.Puppy
import com.mach.jcompose.data.repository.PuppiesRepository
import com.mach.jcompose.onPuppyClickListener
import com.mach.jcompose.ui.library.*

object PuppyListUi {

    @Composable
    fun PuppiesList(puppyList: List<Puppy>, onPuppyClick: onPuppyClickListener) {
        CustomColumns.PaddedLazyColumn(
            paddedColumnsType = CustomColumns.PaddedColumnsTypes.XLARGE,
            itemsCount = puppyList.size
        ) { index ->
            PuppyItem(puppyList[index]) {
                onPuppyClick.invoke(it)
            }
        }
    }

    @Composable
    private fun PuppyItem(puppy: Puppy, onPuppyClick: onPuppyClickListener) {
        CustomSurfaces.RoundedSurface {
            Column(Modifier.clickable(enabled = true, onClick = { onPuppyClick(puppy) })) {
                CustomImages.CustomImage(
                    drawableId = puppy.picture,
                    fillParentWidth = true,
                    desiredHeight = 180
                )
                CustomColumns.PaddedColumn {
                    CustomTexts.TextTitle(text = puppy.name)
                    CustomComposables.Space(DimensTypes.MEDIUM)
                    CustomComposables.LineDivider()
                    CustomComposables.Space(DimensTypes.MEDIUM)
                    CustomTexts.TextCaption(text = "${puppy.age} - ${puppy.size.size}")
                    CustomTexts.TextCaption(text = puppy.sex.what)
                    CustomComposables.Space()
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    CustomComposables.BaseUi {
        PuppyListUi.PuppiesList(PuppiesRepository.getPuppiesList()) {
            /* no-op */
        }
    }
}
