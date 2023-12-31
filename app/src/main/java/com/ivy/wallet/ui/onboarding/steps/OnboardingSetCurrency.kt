package com.ivy.wallet.ui.onboarding.steps

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.ivy.wallet.base.setStatusBarDarkTextCompat
import com.ivy.wallet.model.IvyCurrency
import com.ivy.wallet.ui.IvyAppPreview
import com.ivy.wallet.ui.LocalIvyContext
import com.ivy.wallet.ui.theme.*
import com.ivy.wallet.ui.theme.components.BackButton
import com.ivy.wallet.ui.theme.components.CurrencyPicker
import com.ivy.wallet.ui.theme.components.GradientCutBottom
import com.ivy.wallet.ui.theme.components.OnboardingButton

@Composable
fun BoxWithConstraintsScope.OnboardingSetCurrency(
    preselectedCurrency: IvyCurrency,
    onSetCurrency: (IvyCurrency) -> Unit
) {
    setStatusBarDarkTextCompat(darkText = IvyTheme.colors.isLight)

    var currency by remember { mutableStateOf(preselectedCurrency) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Spacer(Modifier.height(16.dp))

        var keyboardVisible by remember {
            mutableStateOf(false)
        }

        val ivyContext = LocalIvyContext.current
        BackButton(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            ivyContext.onBackPressed()
        }

        if (!keyboardVisible) {
            Spacer(Modifier.height(24.dp))

            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = "Set currency",
                style = Typo.h2.style(
                    fontWeight = FontWeight.Black
                )
            )
        }

        Spacer(Modifier.height(24.dp))

        CurrencyPicker(
            modifier = Modifier
                .fillMaxSize(),
            initialSelectedCurrency = null,
            preselectedCurrency = preselectedCurrency,
            includeKeyboardShownInsetSpacer = true,
            lastItemSpacer = 120.dp,
            onKeyboardShown = { keyboardShown ->
                keyboardVisible = keyboardShown
            }
        ) {
            currency = it
        }
    }

    GradientCutBottom(
        height = 160.dp
    )

    OnboardingButton(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .align(Alignment.BottomCenter)
            .navigationBarsPadding()
            .padding(bottom = 20.dp),

        text = "Set",
        textColor = White,
        backgroundGradient = GradientIvy,
        hasNext = true,
        enabled = true
    ) {
        onSetCurrency(currency)
    }

}

@Preview
@Composable
private fun Preview() {
    IvyAppPreview {
        OnboardingSetCurrency(
            preselectedCurrency = IvyCurrency.getDefault()
        ) {

        }
    }
}