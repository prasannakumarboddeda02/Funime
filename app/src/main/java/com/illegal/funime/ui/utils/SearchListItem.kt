package com.illegal.funime.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illegal.funime.R

@Composable
fun SearchListItem(
    text : String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                contentDescription = "search clock"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.redirect_icon), 
            contentDescription = "search redirect")
    }
}
