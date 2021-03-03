package com.mach.jcompose.ui.app

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mach.jcompose.data.model.Puppy
import com.mach.jcompose.data.repository.PuppiesRepository
import com.mach.jcompose.onPuppyClickListener
import com.mach.jcompose.ui.library.*

object PuppyDetailUi {

    @Composable
    fun PuppyDetail(puppy: Puppy, onPuppyClick: onPuppyClickListener) {
        CustomBoxes.ScrollableFloatingCustomBox(onClick = { onPuppyClick(puppy) }) {
            CustomSurfaces.RoundedSurface(padding = DimensTypes.XXLARGE) {
                Column {
                    CustomImages.CustomImage(
                        drawableId = puppy.picture,
                        fillParentWidth = true
                    )
                    CustomColumns.PaddedColumn {
                        CustomTexts.TextTitle(text = puppy.name)
                        CustomComposables.Space(DimensTypes.MEDIUM)
                        CustomComposables.LineDivider()
                        CustomComposables.Space(DimensTypes.MEDIUM)
                        CustomTexts.TextCaption(text = "${puppy.age} - ${puppy.size.size}")
                        CustomTexts.TextCaption(text = puppy.sex.what)
                        CustomComposables.Space()
                        CustomTexts.TextContent(text = puppy.description)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPuppyDetail() {
    PuppyDetailUi.PuppyDetail(PuppiesRepository.getPuppyById(1)!!) {/* no-op */ }
}