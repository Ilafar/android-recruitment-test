package az.algoritma.recruitmenttest.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.algoritma.recruitmenttest.ui.component.MarketQuote
import az.algoritma.recruitmenttest.ui.component.MarketQuoteCard
import az.algoritma.recruitmenttest.ui.component.TopBar
import az.algoritma.recruitmenttest.ui.theme.OutlineVariant
import az.algoritma.recruitmenttest.ui.theme.RecruitmentTestTheme

private val mockQuotes = listOf(
    MarketQuote(
        symbol = "AIG",
        price = "60.25",
        volume = "1.2M",
        changePercent = 1.2,
        isPositive = true
    ),
    MarketQuote(
        symbol = "ALIBABA",
        price = "75.50",
        volume = "3.4M",
        changePercent = 2.5,
        isPositive = false
    ),
    MarketQuote(
        symbol = "AAPL",
        price = "185.30",
        volume = "8.1M",
        changePercent = 0.8,
        isPositive = true
    ),
    MarketQuote(
        symbol = "MSFT",
        price = "410.15",
        volume = "5.2M",
        changePercent = 1.5,
        isPositive = true
    )
)

@Composable
fun MarketScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column {
            TopBar(
                innerPadding = innerPadding,
                isConnected = true
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(mockQuotes) { quote ->
                    MarketQuoteCard(quote = quote)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .offset(x = (56).dp),
                        color = OutlineVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketScreenPreview() {
    RecruitmentTestTheme {
        MarketScreen()
    }
}
