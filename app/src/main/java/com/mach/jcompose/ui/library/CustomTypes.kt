package com.mach.jcompose.ui.library

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mach.jcompose.ui.theme.*

enum class DimensTypes(val dimens: Dp) {
    XXLARGE(Dimens.XXLARGE.dp),
    XLARGE(Dimens.XLARGE.dp),
    LARGE(Dimens.LARGE.dp),
    MEDIUM(Dimens.MEDIUM.dp),
    SMALL(Dimens.SMALL.dp),
    SMALLX(Dimens.XSMALL.dp),
    SMALLXX(Dimens.XXSMALL.dp),
    SMALLXXX(Dimens.XXXSMALL.dp),
}

object CustomTexts {
    enum class TextTypes(val maxLines: Int, val textOverflow: TextOverflow = TextOverflow.Clip) {
        TITLE(2, TextOverflow.Ellipsis),
        SUBTITLE(4),
        CONTENT(Int.MAX_VALUE),
        CAPTION(2)
    }

    @Composable
    fun TextCaption(text: String) {
        CustomText(text = text, textType = TextTypes.CAPTION)
    }

    @Composable
    fun TextContent(text: String) {
        CustomText(text = text)
    }

    @Composable
    fun TextTitle(text: String) {
        CustomText(text = text, textType = TextTypes.TITLE)
    }

    @Composable
    fun CustomText(textType: TextTypes = TextTypes.CONTENT, text: String) {
        val typography = MaterialTheme.typography

        Text(
            text = text,
            style = when (textType) {
                TextTypes.TITLE -> typography.h6
                TextTypes.SUBTITLE -> typography.subtitle1
                TextTypes.CAPTION -> typography.caption
                TextTypes.CONTENT -> typography.body1
            },
            maxLines = textType.maxLines,
            overflow = textType.textOverflow
        )
    }
}

object CustomSurfaces {
    enum class RoundedSurfaceTypes(val roundedCornerShape: RoundedCornerShape) {
        LARGE(RoundedCornerShape(RoundedDimens.LARGE.dp)),
        MEDIUM(RoundedCornerShape(RoundedDimens.MEDIUM.dp)),
        SMALL(RoundedCornerShape(RoundedDimens.SMALL.dp))
    }

    @Composable
    fun RoundedSurface(
        roundedSurfaceType: RoundedSurfaceTypes = RoundedSurfaceTypes.LARGE,
        padding: DimensTypes? = null,
        isScrollable: Boolean = false,
        backgroundColor: Color = MaterialTheme.colors.surface,
        content: @Composable () -> Unit,
    ) {
        Surface(
            shape = roundedSurfaceType.roundedCornerShape,
            elevation = 4.dp,
            modifier = let {
                var modifier: Modifier = Modifier

                modifier = modifier.then(modifier.background(color = backgroundColor))

                if (isScrollable) {
                    modifier = modifier.then(modifier.verticalScroll(rememberScrollState()))
                }

                if (padding != null) {
                    modifier = modifier.then(modifier.padding(padding.dimens))
                }

                modifier
            }
        ) {
            content()
        }
    }
}

object CustomColumns {
    enum class PaddedColumnsTypes(val dimens: Dp) {
        XLARGE(Dimens.XLARGE.dp),
        LARGE(Dimens.LARGE.dp),
        MEDIUM(Dimens.MEDIUM.dp),
        SHORT(Dimens.SMALL.dp)
    }

    @Composable
    fun PaddedColumn(
        paddedColumnsType: PaddedColumnsTypes = PaddedColumnsTypes.LARGE,
        hasExpandedWeightedContent: Boolean = false,
        content: @Composable () -> Unit
    ) {
        Column(
            modifier = Modifier.padding(paddedColumnsType.dimens).let {
                if (hasExpandedWeightedContent) {
                    it.then(it.fillMaxHeight())
                } else {
                    it
                }
            }
        ) {
            content()
        }
    }

    @Composable
    fun PaddedLazyColumn(
        paddedColumnsType: PaddedColumnsTypes = PaddedColumnsTypes.LARGE,
        hasExpandedWeightedContent: Boolean = false,
        itemsCount: Int,
        addSpaceAfterEachElement: Boolean = true,
        itemContent: @Composable (index: Int) -> Unit
    ) {
        LazyColumn(
            modifier = let {
                var modifier: Modifier = Modifier

                if (hasExpandedWeightedContent) {
                    modifier = modifier.then(modifier.fillMaxHeight())
                }

                modifier
            },
            contentPadding = PaddingValues(paddedColumnsType.dimens)
        ) {
            items(count = itemsCount) { index ->
                itemContent(index)
                if (addSpaceAfterEachElement && index < itemsCount - 1) {
                    CustomComposables.Space(DimensTypes.XLARGE)
                }
            }
        }
    }
}

object CustomImages {

    enum class RoundedImageTypes(val roundedCornerShape: RoundedCornerShape) {
        ROUNDED_LARGE(RoundedCornerShape(RoundedDimens.LARGE.dp)),
        ROUNDED_MEDIUM(RoundedCornerShape(RoundedDimens.MEDIUM.dp)),
        ROUNDED_SMALL(RoundedCornerShape(RoundedDimens.SMALL.dp))
    }

    @Composable
    fun CustomImage(
        @DrawableRes drawableId: Int,
        fillParentWidth: Boolean = false,
        roundedImageType: RoundedImageTypes? = null,
        desiredHeight: Int? = null,
        contentDescription: String? = null
    ) {
        Image(
            painter = painterResource(id = drawableId),
            modifier = let {
                var modifier: Modifier = Modifier

                if (fillParentWidth) {
                    modifier = modifier.then(modifier.fillMaxWidth())
                }

                roundedImageType?.let { roundedImageType ->
                    modifier =
                        modifier.then(modifier.clip(shape = roundedImageType.roundedCornerShape))
                }

                desiredHeight?.let {
                    modifier = modifier.then(modifier.height(it.dp))
                }

                modifier
            },
            contentDescription = contentDescription,
            contentScale = if (fillParentWidth) {
                ContentScale.Crop
            } else {
                ContentScale.Fit
            }
        )
    }
}

object CustomBoxes {

    @Composable
    fun ScrollableFloatingCustomBox(
        onClick: () -> Unit,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier
                .background(color = BlackTransparent)
                .clickable(enabled = true, onClick = { onClick() })
                .verticalScroll(rememberScrollState())
        ) {
            content()
        }
    }
}

object CustomComposables {

    @Composable
    fun BaseUi(content: @Composable () -> Unit) {
        JComposeTheme(true) {
            Surface {
                content()
            }
        }
    }

    @Composable
    fun LineDivider(dimensType: DimensTypes = DimensTypes.SMALLXXX) {
        Divider(
            color = Teal200,
            modifier = Modifier.height(dimensType.dimens)
        )
    }

    @Composable
    fun Space(dimensType: DimensTypes = DimensTypes.LARGE) {
        Spacer(modifier = Modifier.height(dimensType.dimens))
    }
}