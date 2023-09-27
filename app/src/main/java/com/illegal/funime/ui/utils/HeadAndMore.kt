package com.illegal.funime.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.illegal.funime.ui.theme.RobotoSlab

@Composable
fun HeadAndMore(
    head: String,
    onMoreClick :() -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = head,
            fontSize = 18.sp,
            fontFamily = RobotoSlab,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 20.dp)
        )
        Text(
            text = "more",
            fontSize = 18.sp,
            fontFamily = RobotoSlab,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(end = 20.dp)
                .clickable(onClick = onMoreClick, enabled = true),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}
