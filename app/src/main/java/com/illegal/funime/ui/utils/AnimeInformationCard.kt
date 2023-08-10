package com.illegal.funime.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimeInformationCard(
    type : String?,
    episodes :Any?,
    season :String?,
    year :Int?,
    rating :String?,
    duration :String?,
){
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 5.dp),
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(
                text = "Information",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            //  Type
            if(type!=null) {
                Row {
                    Text(text = "type")
                    Text(
                        text = type,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Episodes
            if (episodes!=null) {
                Row {
                    Text(text = "episode")
                    Text(
                        text = episodes.toString(),
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Season & Year
            if (season!=null) {
                val seasonWithYear = "$season $year"

                Row {
                    Text(text = "season")
                    Text(
                        text = seasonWithYear,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Airing Information
            /*if (startedDate.isNotNullOrEmpty()) {
                val airingPeriod =
                    if (anime.finishedDate.isNullOrEmpty()) {
                        dateParser(anime.startedDate as String)
                    } else {
                        val startedDate = dateParser(anime.startedDate as String)
                        val finishedDate = dateParser(anime.finishedDate)
                        "$startedDate - $finishedDate"
                    }
                Row {
                    Text(text = stringResource(id = R.string.aired))
                    Text(
                        text = airingPeriod,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Studio
            if (anime.studios.isNotEmpty()) {
                val studios = listToString(anime.studios)
                Row {
                    Text(text = "studio")
                    Text(
                        text = studios,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }*/

            //  Duration
            if(duration!=null) {
                Row {
                    Text(text = "duration")
                    Text(
                        text = duration,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Rating
            if(rating!=null) {
                Row {
                    Text(text = "rating")
                    Text(
                        text = rating,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewInformation(){
    AnimeInformationCard(
        type = "Anime",
        episodes = "1071",
        season = "5",
        year = 1991,
        rating = "9.0",
        duration = "50 years")
}


