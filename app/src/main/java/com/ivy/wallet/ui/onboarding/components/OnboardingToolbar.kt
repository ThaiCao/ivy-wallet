package com.ivy.wallet.ui.onboarding.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.wallet.ui.theme.*
import com.ivy.wallet.ui.theme.components.IvyToolbar

@Composable
fun OnboardingToolbar(
    hasSkip: Boolean,

    onBack: () -> Unit,
    onSkip: () -> Unit
) {
    IvyToolbar(onBack = onBack) {
        if (hasSkip) {
            Spacer(Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .clip(Shapes.roundedFull)
                    .clickable {
                        onSkip()
                    }
                    .padding(all = 16.dp), //enlarge click area
                text = "Skip",
                style = Typo.body2.style(
                    color = Gray,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.width(32.dp))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    IvyComponentPreview {
        OnboardingToolbar(
            hasSkip = true, onBack = {}
        ) {

        }
    }
}