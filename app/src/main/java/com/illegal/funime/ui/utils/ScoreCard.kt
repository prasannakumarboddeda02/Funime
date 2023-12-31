package com.illegal.funime.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illegal.funime.R

@Composable
fun ScoreCard(
    rating :String,
    people :String,
    status :String){
    /*Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.background)
    ) {*/
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .clip(RoundedCornerShape(8.dp))
    ){
    Row(
        modifier = Modifier
            .padding(all = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "star")
        Text(
            text = rating
        )
        Spacer(modifier = Modifier.width(13.dp))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.people_icon),
            contentDescription = "People icon")
        Text(
            text = people
        )
        Spacer(modifier = Modifier.width(13.dp))
        Icon(
            imageVector = when(status){
                stringResource(id = R.string.finished_airing) -> Icons.Filled.Check
                stringResource(id = R.string.currently_airing) -> ImageVector.vectorResource(id = R.drawable.schedule_icon)
                else -> Icons.Filled.Close
            }, contentDescription = "Icon status")
        Text(
            text = status)
    }
}
}

@Preview(showBackground = true)
@Composable
fun ScoreCardPreview(){
    ScoreCard(
        rating = "10.0",
        people = "100000",
        status = "Currently Airing")
}