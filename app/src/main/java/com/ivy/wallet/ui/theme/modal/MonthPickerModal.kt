package com.ivy.wallet.ui.theme.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.wallet.base.*
import com.ivy.wallet.ui.IvyAppPreview
import com.ivy.wallet.ui.theme.*
import com.ivy.wallet.ui.theme.components.WrapContentRow
import com.ivy.wallet.ui.theme.modal.model.Month
import com.ivy.wallet.ui.theme.modal.model.Month.Companion.monthsList
import java.time.LocalDate
import java.util.*

@Composable
fun BoxWithConstraintsScope.MonthPickerModal(
    id: UUID = UUID.randomUUID(),
    initialDate: LocalDate,
    visible: Boolean,
    dismiss: () -> Unit,
    onMonthSelected: (Int) -> Unit
) {
    var selectedMonth by remember {
        mutableStateOf(initialDate.monthValue)
    }

    IvyModal(
        id = id,
        visible = visible,
        dismiss = dismiss,
        PrimaryAction = {
            ModalSave {
                onMonthSelected(selectedMonth)
                dismiss()
            }
        }
    ) {
        val view = LocalView.current
        onScreenStart {
            hideKeyboard(view)
        }

        Spacer(Modifier.height(32.dp))

        ModalTitle(
            text = "Choose month"
        )

        Spacer(Modifier.height(24.dp))

        MonthPicker(
            selectedMonth = selectedMonth,
            onMonthSelected = {
                selectedMonth = it
            }
        )

        Spacer(Modifier.height(56.dp))
    }
}

@Composable
private fun MonthPicker(
    selectedMonth: Int,
    onMonthSelected: (Int) -> Unit
) {
    val months = monthsList()

    WrapContentRow(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        horizontalMarginBetweenItems = 12.dp,
        verticalMarginBetweenRows = 12.dp,
        items = months
    ) {
        MonthButton(
            month = it,
            selected = it.monthValue == selectedMonth
        ) {
            onMonthSelected(it.monthValue)
        }
    }
}


@Composable
private fun MonthButton(
    month: Month,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val monthColor = Ivy

    Text(
        modifier = Modifier
            .thenIf(selected) {
                drawColoredShadow(monthColor)
            }
            .clip(Shapes.roundedFull)
            .clickable(onClick = onClick)
            .thenIf(!selected) {
                border(2.dp, IvyTheme.colors.medium, Shapes.roundedFull)
            }
            .thenIf(selected) {
                background(
                    brush = Gradient
                        .solid(monthColor)
                        .asHorizontalBrush(),
                    Shapes.roundedFull
                )
            }
            .padding(horizontal = 40.dp, vertical = 12.dp),
        text = month.name,
        style = Typo.body2.style(
            color = if (selected)
                findContrastTextColor(monthColor) else IvyTheme.colors.pureInverse,
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Preview
@Composable
private fun Preview() {
    IvyAppPreview {
        MonthPickerModal(
            initialDate = dateNowUTC(),
            visible = true,
            dismiss = {}
        ) {

        }
    }
}