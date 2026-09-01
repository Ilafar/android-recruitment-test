package az.algoritma.recruitmenttest.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.algoritma.recruitmenttest.R
import az.algoritma.recruitmenttest.ui.theme.RecruitmentTestTheme
import az.algoritma.recruitmenttest.ui.theme.Success

data class UiMarketQuote(
    val symbol: String,
    val price: String,
    val dayRange: String,
    val metaText: String,
    val changeText: String,
    val isPositive: Boolean
)

@Composable
fun MarketQuoteCard(
    quote: UiMarketQuote,
    modifier: Modifier = Modifier
) {
    val accentColor = if (quote.isPositive) Success else MaterialTheme.colorScheme.error
    val icon = if (quote.isPositive) R.drawable.ic_up else R.drawable.ic_down

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(text = quote.symbol, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = quote.dayRange,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = quote.metaText,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = quote.price, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = quote.changeText,
                    style = MaterialTheme.typography.labelMedium,
                    color = accentColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketQuoteCardUpPreview() {
    RecruitmentTestTheme {
        MarketQuoteCard(
            quote = UiMarketQuote(
                symbol = "AIG",
                price = "76.10",
                dayRange = "L 75.94 · H 77.30",
                metaText = "Spread: 5 pts · Ask 76.15 · 19:24:22",
                changeText = "↑1.20%",
                isPositive = true
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketQuoteCardDownPreview() {
    RecruitmentTestTheme {
        MarketQuoteCard(
            quote = UiMarketQuote(
                symbol = "ALIBABA",
                price = "112.89",
                dayRange = "L 112.22 · H 113.83",
                metaText = "Spread: 5 pts · Ask 112.94 · 19:24:09",
                changeText = "↓0.83%",
                isPositive = false
            )
        )
    }
}
