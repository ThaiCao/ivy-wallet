package com.ivy.wallet.ui.theme.modal

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.wallet.base.onScreenStart
import com.ivy.wallet.base.selectEndTextFieldValue
import com.ivy.wallet.ui.IvyAppPreview
import com.ivy.wallet.ui.theme.IvyTheme
import com.ivy.wallet.ui.theme.Typo
import com.ivy.wallet.ui.theme.components.IvyTitleTextField
import com.ivy.wallet.ui.theme.style
import java.util.*

@Composable
fun BoxWithConstraintsScope.AddKeywordModal(
    id: UUID = UUID.randomUUID(),
    keyword: String,
    visible: Boolean,
    dismiss: () -> Unit,
    onKeywordChanged: (String) -> Unit
) {
    var modalKeyword by remember(id) { mutableStateOf(selectEndTextFieldValue(keyword)) }

    IvyModal(
        id = id,
        visible = visible,
        dismiss = dismiss,
        PrimaryAction = {
            ModalAdd {
                onKeywordChanged(modalKeyword.text)
                dismiss()
            }
        }
    ) {
        Spacer(Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(start = 32.dp),
            text = "Add keyword",
            style = Typo.body1.style(
                fontWeight = FontWeight.ExtraBold,
                color = IvyTheme.colors.pureInverse
            )
        )

        Spacer(Modifier.height(32.dp))

        val inputFocus = FocusRequester()

        onScreenStart {
            inputFocus.requestFocus()
        }

        IvyTitleTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .focusRequester(inputFocus),
            dividerModifier = Modifier.padding(horizontal = 24.dp),
            value = modalKeyword,
            hint = "Keyword"
        ) {
            modalKeyword = it
        }

        Spacer(Modifier.height(48.dp))
    }
}

@Preview
@Composable
private fun Preview() {
    IvyAppPreview {
        AddKeywordModal(
            visible = true,
            keyword = "",
            dismiss = {}
        ) {

        }
    }
}