package com.example.waysofcooking.data

import com.example.waysofcooking.R

object RecetasVideoData {
    data class VideoInfo(val videoId: String, val imagenResId: Int)

    val videos = mapOf(
        "Arroz Chaufa" to VideoInfo("tf34QAHiono", R.drawable.arroz_chaufa),
        "Ajiaco Santafereño" to VideoInfo("R2DAkW3N_JY", R.drawable.ajiaco_santafereno),
        "Albóndigas en Salsa" to VideoInfo("Z8ZBRjLMpb8", R.drawable.albondigas_en_salsa),
        "Arepas con Queso" to VideoInfo("LRAZfDiet9Y", R.drawable.arepas_con_queso),
        "Bandeja Paisa" to VideoInfo("XeTuQ7UdA38", R.drawable.bandeja_paisa),
        "Canelones de Ricotta" to VideoInfo("IIun3wUUJNc", R.drawable.canelones_de_ricotta),
        "Ceviche de Camarón" to VideoInfo("s8Fmezppr8g", R.drawable.ceviche_de_camaron),
        "Ceviche Mixto" to VideoInfo("lpvdN8D9k9g", R.drawable.ceviche_mixto),
        "Chuleta Valluna" to VideoInfo("mYCyr-XPd2Q", R.drawable.chuleta_vallecaucana),
        "Croquetas de Pollo" to VideoInfo("SrmYBYpsj7M", R.drawable.croquetas_de_pollo),
        "Curry de Garbanzos" to VideoInfo("gaE3VpqjAT8", R.drawable.curry_de_garbanzos),
        "Empanada de Carne" to VideoInfo("widoXPkU8EQ", R.drawable.empanada_de_carne),
        "Ensalada Caprese" to VideoInfo("UT5wUC-D47I", R.drawable.ensalada_caprese),
        "Ensalada de Quínua" to VideoInfo("wvnmF0UMzSU", R.drawable.ensalada_de_quinua),
        "Fajitas de Res" to VideoInfo("8Zz6wBClbag", R.drawable.fajitas_de_res),
        "Pescado al Horno" to VideoInfo("50kRHG2uWXU", R.drawable.pescado_al_horno),
        "Pollo a la Naranja" to VideoInfo("BtVHz6DdNYs", R.drawable.pollo_a_la_naranja),
        "Sándwich Cubano" to VideoInfo("YH-A6rdsLKE", R.drawable.sandwich_cubano),
        "Sopa de Lentejas" to VideoInfo("d_X6C7IkzRM", R.drawable.sopa_de_lentejas),
        "Sopa de Tomate" to VideoInfo("BQn06MxDXbU", R.drawable.sopa_de_tomate),
        "Tortilla Española" to VideoInfo("ULle2vGte8k", R.drawable.tortilla_espanola),
        "Waffles de Avena" to VideoInfo("yGmHaPNqovg", R.drawable.waffles_de_avena),
        "Cazuela de Mariscos" to VideoInfo("1X3raTPmR70", R.drawable.cazuela_de_mariscos),
        "Hamburguesa Clásica con Queso" to VideoInfo("F6cM7Bbv920", R.drawable.hamburguesa_clasica_con_queso),
        "Grilled Cheese" to VideoInfo("CRSdpY_vI1s", R.drawable.grilled_cheese),
        "Pan de Ajo" to VideoInfo("TF9JY0mt9Hw", R.drawable.pan_de_ajo),
        "Papas Fritas" to VideoInfo("Bntgp3qjAoI", R.drawable.papas_fritas),
        "Tacos al Pastor" to VideoInfo("nqXc8hYJbd8", R.drawable.tacos_al_pastor),
        "Spaghetti Carbonara" to VideoInfo("OIs9tgALGNU", R.drawable.spaghetti_carbonara),
        "Ensalada César con Pollo" to VideoInfo("LhrapnF8Phk", R.drawable.ensalada_cesar_con_pollo)
    )
}
