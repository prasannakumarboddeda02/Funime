package com.illegal.funime.ui.utils


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun TextWithShadow(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    fontFamily: FontFamily,
    style: TextStyle,
) {
    Box{
        Text(
            modifier = modifier
                .offset(2.dp, 2.dp)
                .alpha(0.5f),
            text = text,
            color = Color.DarkGray,
            fontFamily = fontFamily,
            style = style
        )
        Text(
            modifier = modifier,
            text = text,
            color = color,
            fontFamily = fontFamily,
            style = style
        )
    }
}