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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.algoritma.recruitmenttest.ui.component.MarketQuoteCard
import az.algoritma.recruitmenttest.ui.component.Shimmer
import az.algoritma.recruitmenttest.ui.component.TopBar
import az.algoritma.recruitmenttest.ui.theme.OutlineVariant
import az.algoritma.recruitmenttest.ui.theme.RecruitmentTestTheme

private const val SHIMMER_PLACEHOLDER_COUNT = 8

@Composable
fun MarketScreen(
    viewModel: MarketViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    MarketScreenContent(state = state)
}

@Composable
fun MarketScreenContent(
    modifier: Modifier = Modifier,
    state: MarketUiState
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column {
            TopBar(
                innerPadding = innerPadding,
                connectionState = state.connectionState
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (state.quotes.isEmpty()) {

                    item {
                        Shimmer(
                            itemHeight = 80.dp,
                            itemCount = SHIMMER_PLACEHOLDER_COUNT,
                            columnsCount = 1
                        )
                    }
                } else {
                    items(state.quotes) { quote ->
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
}

@Preview(showBackground = true)
@Composable
private fun MarketScreenPreview() {
    RecruitmentTestTheme {
        MarketScreenContent(
            state = MarketUiState()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketScreenLoadingPreview() {
    RecruitmentTestTheme {
        MarketScreenContent(
            state = MarketUiState(quotes = emptyList())
        )
    }
}
