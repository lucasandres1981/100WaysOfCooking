package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun StoreScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

 // Ubicacion por default
    val defaultLocation = remember { GeoPoint(4.598056, -74.075833) }

    var searchText by remember { mutableStateOf("") }
    var zoomLevel by remember { mutableStateOf(18.0) }

            // Lista de ubicaciones de tiendas
    val tiendas = listOf(
        GeoPoint(4.5981, -74.0760) to "Frutas",
        GeoPoint(4.5990, -74.0775) to "Hierbas",
        GeoPoint(4.6005, -74.0750) to "Granos",
        GeoPoint(4.6020, -74.0720) to "Lacteos",
        GeoPoint(4.5970, -74.0792) to "Panaderia",
        GeoPoint(4.6300, -74.0960) to "Supermercado",
    )

    // Función para buscar y marcar tienda
    fun buscarTienda(nombre: String) {
        tiendas.firstOrNull { it.second.equals(nombre, ignoreCase = true) }?.let { (punto, nombreTienda) ->
            // Limpiar solo los marcadores anteriores de tiendas (conservar el inicial)
            mapView.overlays.removeAll { overlay ->
                overlay is Marker && overlay.title?.startsWith("📍") == true
            }

            // Asegurarse que el marcador inicial está presente
            if (mapView.overlays.none { overlay ->
                    overlay is Marker && overlay.title == "Ubicación inicial"
                }) {
                addMarker(mapView, defaultLocation, "Ubicación inicial").apply {
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                }
            }

            // Añadir marcador de destino
            addMarker(mapView, punto, "📍 $nombreTienda").apply {
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            }

            // se muuestran  ambos marcadores
            val boundingBox = org.osmdroid.util.BoundingBox(
                maxOf(defaultLocation.latitude, punto.latitude),
                maxOf(defaultLocation.longitude, punto.longitude),
                minOf(defaultLocation.latitude, punto.latitude),
                minOf(defaultLocation.longitude, punto.longitude)
            )

            mapView.zoomToBoundingBox(boundingBox, true, 40, 15.0, 1)
            mapView.invalidate()
        }
    }

    // Configuración inicial del mapa
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osm_prefs", 0x000))
        Configuration.getInstance().userAgentValue = context.packageName

        mapView.apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(zoomLevel)
            controller.setCenter(defaultLocation)
            addMarker(this, defaultLocation, "Ubicación inicial")
            invalidate()
        }
    }


    LaunchedEffect(zoomLevel) {
        mapView.controller.setZoom(zoomLevel)
    }

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(scope = scope, drawerState = drawerState, navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Mapa de Tiendas para tus Recetas", style = MaterialTheme.typography.titleLarge)

                // Barra de búsqueda
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Nombre de la tienda (ej: Frutas, Lácteos)") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            IconButton(onClick = { searchText = "" }) {
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Botón de búsqueda
                Button(
                    onClick = { buscarTienda(searchText) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = searchText.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Spacer(Modifier.width(16.dp))
                    Text("Buscar Tienda")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Selector de zoom por defecto
                Text("Nivel de zoom: ${zoomLevel.toInt()}")
                Slider(
                    value = zoomLevel.toFloat(),
                    onValueChange = { zoomLevel = it.toDouble() },
                    valueRange = 4f..19f,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Mapa
                AndroidView(
                    factory = { mapView },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    )
}

private fun addMarker(mapView: MapView, point: GeoPoint, title: String): Marker {
    return Marker(mapView).apply {
        position = point
        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        this.title = title
        mapView.overlays.add(this)
    }
}