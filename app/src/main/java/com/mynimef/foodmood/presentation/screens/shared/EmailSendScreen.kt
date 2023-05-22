package com.mynimef.foodmood.presentation.screens.shared

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldSettings
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun EmailSendScreen() {
    val emailSubject = remember { mutableStateOf("") }
    val emailBody = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            emailSubject = emailSubject,
            emailBody = emailBody
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    emailSubject: MutableState<String>,
    emailBody: MutableState<String>,
) {
   val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextFieldSettings(
            value = emailSubject.value,
            label = "Email subject",
            onValueChange = { emailSubject.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            enabled = true,
        )
        MyTextFieldSettings(
            value = emailBody.value,
            label = "Email body",
            onValueChange = { emailBody.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            enabled = true,
        )
        MyLoginButton(text = "Send email",
            enabled = emailSubject.value.isNotEmpty() && emailBody.value.isNotEmpty(),
            onClick = {
            val i = Intent(Intent.ACTION_SEND)

            i.putExtra(Intent.EXTRA_EMAIL, "tultsovaa@gmail.com")
            i.putExtra(Intent.EXTRA_SUBJECT,emailSubject.value)
            i.putExtra(Intent.EXTRA_TEXT,emailSubject.value)

            i.setType("message/rfc822")

          ctx.startActivity(Intent.createChooser(i,"Choose an Email client: "))
        })
    }
}

@Preview
@Composable
fun EmailSendPrev() {
    FoodMoodTheme() {
        EmailSendScreen()
    }
}