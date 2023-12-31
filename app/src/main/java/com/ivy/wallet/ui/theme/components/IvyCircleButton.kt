package com.ivy.wallet.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivy.wallet.R
import com.ivy.wallet.base.drawColoredShadow
import com.ivy.wallet.base.thenIf
import com.ivy.wallet.ui.theme.*

@Composable
fun IvyCircleButton(
    modifier: Modifier = Modifier,
    backgroundPadding: Dp = 0.dp,
    backgroundGradient: Gradient = GradientIvy,
    horizontalGradient: Boolean = true,
    @DrawableRes icon: Int,
    tint: Color = White,
    enabled: Boolean = true,
    hasShadow: Boolean = true,
    onClick: () -> Unit
) {
    IvyIcon(
        modifier = modifier
            .thenIf(enabled && hasShadow) {
                drawColoredShadow(
                    color = backgroundGradient.startColor,
                    borderRadius = 0.dp,
                    shadowRadius = 16.dp,
                    offsetX = 0.dp,
                    offsetY = 8.dp
                )
            }
            .clip(Shapes.roundedFull)
            .background(
                brush = if (enabled) {
                    if (horizontalGradient)
                        backgroundGradient.asHorizontalBrush() else backgroundGradient.asVerticalBrush()
                } else {
                    SolidColor(IvyTheme.colors.gray)
                },
                shape = Shapes.roundedFull
            )
            .clickable(onClick = onClick, enabled = enabled)
            .padding(all = backgroundPadding),
        icon = icon,
        tint = tint,
        contentDescription = "circle button"
    )
}

@Preview
@Composable
private fun PreviewIvyCircleButton_Enabled() {
    IvyComponentPreview {
        IvyCircleButton(
            icon = R.drawable.ic_delete,
            backgroundGradient = GradientRed,
            tint = White
        ) {

        }
    }
}

@Preview
@Composable
private fun PreviewIvyCircleButton_Disabled() {
    IvyComponentPreview {
        IvyCircleButton(
            icon = R.drawable.ic_delete,
            backgroundGradient = GradientRed,
            enabled = false,
            tint = White
        ) {

        }
    }
}