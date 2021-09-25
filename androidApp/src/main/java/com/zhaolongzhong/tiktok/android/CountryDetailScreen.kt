package com.zhaolongzhong.tiktok.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zhaolongzhong.tiktok.viewmodel.screens.CountryDetailState

@Composable
fun CountryDetailScreen(
    countryDetailState: CountryDetailState,
    onDismiss: () -> Unit
) {
    if (countryDetailState.isLoading) {
        LoadingScreen()
    } else {
        val data = countryDetailState.countryInfo
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Back", modifier = Modifier
                .padding(10.dp)
                .clickable { onDismiss() })
            DataElement("total population", data.population)
            DataElement("   with first dose", data.firstDoses, data.firstDosesPerc)
            DataElement("   fully vaccinated", data.fullyVaccinated, data.fullyVaccinatedPerc)

            Spacer(modifier = Modifier.size(24.dp))

            if (data.vaccinesList != null) {
                Text(
                    text = "Vaccines:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                for (vaccine in data.vaccinesList!!) {
                    Text(text = "   ‣ $vaccine", style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}

@Composable
fun DataElement(label: String, value: String = "", percentage: String = "") {
    Row {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Text(text = value, style = MaterialTheme.typography.body1)
        if (percentage != "") {
            Text(text = " ($percentage)", style = MaterialTheme.typography.body1)
        }
    }
}
