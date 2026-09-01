package az.algoritma.recruitmenttest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.algoritma.recruitmenttest.R
import az.algoritma.recruitmenttest.domain.model.ConnectionState
import az.algoritma.recruitmenttest.ui.theme.RecruitmentTestTheme
import az.algoritma.recruitmenttest.ui.theme.SurfaceVariant

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    connectionState: ConnectionState
) {
    val topPadding = innerPadding.calculateTopPadding() + 12.dp
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceVariant)
            .padding(
                top = topPadding,
                bottom = 12.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(R.drawable.ic_launcher),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = "Invest Az",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
        ConnectionStatusChip(connectionState = connectionState)
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    RecruitmentTestTheme {
        TopBar(
            innerPadding = PaddingValues(4.dp),
            connectionState = ConnectionState.Connected
        )
    }
}
