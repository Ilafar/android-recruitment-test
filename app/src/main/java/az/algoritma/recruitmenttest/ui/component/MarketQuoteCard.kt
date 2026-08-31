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

data class MarketQuote(
    val symbol: String,
    val price: String,
    val volume: String,
    val changePercent: Double,
    val isPositive: Boolean
)

@Composable
fun MarketQuoteCard(
    quote: MarketQuote,
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
                    text = "Vol: ${quote.volume}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = quote.price, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "${if (quote.isPositive) "↑" else "↓"}${quote.changePercent}%",
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
            quote = MarketQuote(
                symbol = "AIG",
                price = "60.25",
                volume = "1.2M",
                changePercent = 1.2,
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
            quote = MarketQuote(
                symbol = "ALIBABA",
                price = "75.50",
                volume = "3.4M",
                changePercent = 2.5,
                isPositive = false
            )
        )
    }
}
