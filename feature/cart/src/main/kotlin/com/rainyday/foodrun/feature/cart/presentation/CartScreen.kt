package com.rainyday.foodrun.feature.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rainyday.foodrun.core.domain.model.CartItemDomain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onCheckout: (restaurantId: String) -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            if (!uiState.isEmpty) {
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Разом: ${"%.2f".format(uiState.totalPrice)} грн",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Button(onClick = {
                            val restaurantId = uiState.items.firstOrNull()?.restaurantId ?: return@Button
                            onCheckout(restaurantId)
                        }) {
                            Text("Оформити замовлення")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (uiState.isEmpty) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Кошик порожній", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.items, key = { it.menuItemId }) { item ->
                    CartItemCard(
                        item = item,
                        onIncrement = { viewModel.incrementQuantity(item.menuItemId) },
                        onDecrement = { viewModel.decrementQuantity(item.menuItemId) },
                        onRemove = { viewModel.removeItem(item.menuItemId) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CartItemCard(
    item: CartItemDomain,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.bodyLarge)
                Text(
                    "${"%.2f".format(item.price)} грн × ${item.quantity} = ${"%.2f".format(item.totalPrice)} грн",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDecrement) { Text("−") }
                Text("${item.quantity}", modifier = Modifier.padding(horizontal = 4.dp))
                IconButton(onClick = onIncrement) { Text("+") }
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Delete, contentDescription = "Видалити")
                }
            }
        }
    }
}