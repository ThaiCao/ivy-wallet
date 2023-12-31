package com.ivy.wallet.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivy.wallet.R
import com.ivy.wallet.ui.theme.Gradient
import com.ivy.wallet.ui.theme.IvyComponentPreview
import com.ivy.wallet.ui.theme.IvyTheme


@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CircleButton(
        modifier = modifier,
        icon = R.drawable.ic_dismiss,
        contentDescription = "close",
        onClick = onClick
    )
}

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    contentDescription: String = "icon",
    backgroundColor: Color = IvyTheme.colors.pure,
    borderColor: Color = IvyTheme.colors.medium,
    tint: Color? = IvyTheme.colors.pureInverse,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .background(backgroundColor, CircleShape)
            .border(2.dp, borderColor, CircleShape)
            .padding(6.dp), //enlarge click area
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        tint = tint ?: Color.Unspecified
    )
}

@Composable
fun CircleButtonFilled(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    contentDescription: String = "icon",
    backgroundColor: Color = IvyTheme.colors.medium,
    tint: Color? = IvyTheme.colors.pureInverse,
    clickAreaPadding: Dp = 8.dp,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .background(backgroundColor, CircleShape)
            .padding(clickAreaPadding), //enlarge click area
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        tint = tint ?: Color.Unspecified
    )
}

@Composable
fun CircleButtonFilledGradient(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    contentDescription: String = "icon",
    iconPadding: Dp = 8.dp,
    backgroundGradient: Gradient = Gradient.solid(IvyTheme.colors.medium),
    tint: Color? = IvyTheme.colors.pureInverse,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .background(backgroundGradient.asHorizontalBrush(), CircleShape)
            .padding(iconPadding), //enlarge click area

        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        tint = tint ?: Color.Unspecified
    )
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CircleButton(
        modifier = modifier,
        icon = R.drawable.ic_back,
        contentDescription = "back",
        onClick = onClick
    )
}

@Preview
@Composable
private fun PreviewCloseButton() {
    IvyComponentPreview {
        CloseButton {

        }
    }
}

@Preview
@Composable
private fun PreviewBackButton() {
    IvyComponentPreview {
        BackButton {

        }
    }
}