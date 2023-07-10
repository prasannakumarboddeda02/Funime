package com.illegal.funime.ui.utils

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
            text = "all",
            fontSize = 18.sp,
            fontFamily = RobotoSlab,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(end = 20.dp),
            color = Color.Blue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeadAndMore(){
    HeadAndMore(head = "Airing")
}