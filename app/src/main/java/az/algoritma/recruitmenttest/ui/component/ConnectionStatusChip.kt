package az.algoritma.recruitmenttest.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import az.algoritma.recruitmenttest.domain.model.ConnectionState
import az.algoritma.recruitmenttest.ui.theme.InverseOnSurface
import az.algoritma.recruitmenttest.ui.theme.RecruitmentTestTheme
import az.algoritma.recruitmenttest.ui.theme.Success

@Composable
fun ConnectionStatusChip(
    modifier: Modifier = Modifier,
    connectionState: ConnectionState
) {
    val (dotColor, label) = when (connectionState) {
        is ConnectionState.Connected -> Success to "Connected"
        is ConnectionState.Connecting -> MaterialTheme.colorScheme.tertiary to "Connecting"
        is ConnectionState.Disconnected -> MaterialTheme.colorScheme.error to "Disconnected"
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = InverseOnSurface
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape),
                color = dotColor
            ) {}
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConnectionStatusChipConnectedPreview() {
    RecruitmentTestTheme { ConnectionStatusChip(connectionState = ConnectionState.Connected) }
}

@Preview(showBackground = true)
@Composable
private fun ConnectionStatusChipConnectingPreview() {
    RecruitmentTestTheme { ConnectionStatusChip(connectionState = ConnectionState.Connecting) }
}

@Preview(showBackground = true)
@Composable
private fun ConnectionStatusChipDisconnectedPreview() {
    RecruitmentTestTheme { ConnectionStatusChip(connectionState = ConnectionState.Disconnected("")) }
}
