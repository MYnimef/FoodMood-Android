package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.fields.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyChangeLocale(
    weight: Float,
    setWeight: (Float) -> Unit,
    onDismiss:()->Unit,
    onConfirm:()->Unit) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(id = R.string.weight_title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            MyTextFieldLogin(
                value = weight.toString(),
                label = stringResource(id = R.string.weight),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None,
                isError = false,
                onValueChange = {}
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                    ,
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                    )
                }
                Button(
                    onClick = {
                        onConfirm()
                        setWeight(weight)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Text(text = stringResource(id = R.string.enter))
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyChangeLocalePreview() = FoodMoodTheme {
    MyChangeLocale(20.0f,{}, {}, {})
}