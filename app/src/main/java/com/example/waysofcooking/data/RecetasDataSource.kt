package com.example.waysofcooking.data

import com.example.waysofcooking.R

data class Receta(
    val nombre: String,
    val nombreId: String,
    val imagenResId: Int,
    val ingredientes: List<String>,
    val pasos: List<String>,
    var esFavorita: Boolean = false
)

object RecetasDataSource {
    val recetas = listOf(

        Receta(
            nombre = "Ajiaco Santafereño",
            nombreId = "Ajiaco Santafereño",
            imagenResId = R.drawable.ajiaco_santafereno,
            ingredientes = listOf(
                "3 tipos de papa (criolla, sabanera, pastusa)",
                "1 pechuga de pollo",
                "Mazorca",
                "Guascas",
                "Crema de leche",
                "Alcaparras",
                "Sal al gusto"
            ),
            pasos = listOf(
                "Pelar y cortar las papas.",
                "Cocinar las papas con mazorca y pollo en agua.",
                "Agregar guascas y sal al gusto.",
                "Servir con crema de leche y alcaparras."
            )
        ),
        Receta(
            nombre = "Albóndigas en Salsa",
            nombreId = "Albóndigas en Salsa",
            imagenResId = R.drawable.albondigas_en_salsa,
            ingredientes = listOf(
                "500 g de carne molida",
                "1 huevo",
                "Pan rallado",
                "Cebolla",
                "Ajo",
                "Tomate",
                "Sal y pimienta"
            ),
            pasos = listOf(
                "Mezclar carne con huevo, pan rallado, sal y pimienta.",
                "Formar albóndigas y dorarlas.",
                "Preparar salsa con tomate, cebolla y ajo.",
                "Cocinar las albóndigas en la salsa por 15 minutos."
            )
        ),
        Receta(
            nombre = "Arepas con Queso",
            nombreId = "Arepas con Queso",
            imagenResId = R.drawable.arepas_con_queso,
            ingredientes = listOf(
                "2 tazas de harina de maíz precocida",
                "1 1/2 tazas de agua",
                "1 taza de queso rallado",
                "Sal al gusto",
                "Mantequilla"
            ),
            pasos = listOf(
                "Mezclar la harina con agua, sal y queso.",
                "Formar arepas con las manos.",
                "Cocinar en sartén con mantequilla hasta dorar.",
                "Servir calientes con más queso si se desea."
            )
        ),
        Receta(
            nombre = "Arroz Chaufa",
            nombreId = "Arroz Chaufa",
            imagenResId = R.drawable.arroz_chaufa,
            ingredientes = listOf(
                "2 tazas de arroz cocido",
                "1 pechuga de pollo en cubos",
                "2 huevos",
                "Cebolla larga",
                "Salsa de soya",
                "Aceite",
                "Sal y pimienta"
            ),
            pasos = listOf(
                "Freír el pollo en aceite caliente.",
                "Agregar cebolla larga picada y sofreír.",
                "Añadir arroz cocido y mezclar bien.",
                "Agregar los huevos batidos y revolver.",
                "Incorporar salsa de soya al gusto y servir caliente."
            )
        ),
        Receta(
            nombre = "Bandeja Paisa",
            nombreId = "Bandeja Paisa",
            imagenResId = R.drawable.bandeja_paisa,
            ingredientes = listOf(
                "Frijoles",
                "Arroz blanco",
                "Carne molida",
                "Chicharrón",
                "Huevo frito",
                "Arepa",
                "Plátano maduro",
                "Chorizo",
                "Aguacate"
            ),
            pasos = listOf(
                "Cocinar los frijoles con condimentos.",
                "Preparar arroz, carne, plátano y chicharrón por separado.",
                "Freír el huevo y el chorizo.",
                "Servir todo junto en una bandeja con arepa y aguacate."
            )
        ),
        Receta(
            nombre = "Canelones de Ricotta",
            nombreId = "Canelones de Ricotta",
            imagenResId = R.drawable.canelones_de_ricotta,
            ingredientes = listOf(
                "Placas de canelones",
                "Ricotta",
                "Espinaca",
                "Salsa bechamel",
                "Queso parmesano",
                "Sal y pimienta"
            ),
            pasos = listOf(
                "Hervir las placas de canelones.",
                "Mezclar ricotta con espinaca cocida, sal y pimienta.",
                "Rellenar los canelones y colocarlos en un molde.",
                "Cubrir con salsa bechamel y queso parmesano.",
                "Hornear a 180°C durante 25 minutos."
            )
        ),
        Receta(
            nombre = "Cazuela de Mariscos",
            nombreId = "Cazuela de Mariscos",
            imagenResId = R.drawable.cazuela_de_mariscos,
            ingredientes = listOf(
                "Camarones",
                "Calamares",
                "Mejillones",
                "Leche de coco",
                "Crema de leche",
                "Ajo",
                "Cebolla",
                "Ají dulce",
                "Sal y pimienta"
            ),
            pasos = listOf(
                "Sofreír el ajo, la cebolla y el ají.",
                "Agregar los mariscos y cocinar por 5 minutos.",
                "Incorporar leche de coco y crema de leche.",
                "Cocinar a fuego lento hasta espesar.",
                "Servir caliente con arroz blanco."
            )
        ),
        Receta(
            nombre = "Ceviche Mixto",
            nombreId = "Ceviche Mixto",
            imagenResId = R.drawable.ceviche_mixto,
            ingredientes = listOf(
                "Pescado blanco",
                "Camarones",
                "Pulpo cocido",
                "Jugo de limón",
                "Cebolla morada",
                "Cilantro",
                "Ají",
                "Sal"
            ),
            pasos = listOf(
                "Cortar el pescado en cubos y marinar en limón por 15 minutos.",
                "Agregar camarones y pulpo cocido.",
                "Incorporar cebolla en pluma, cilantro y ají.",
                "Mezclar todo y servir frío con galletas saladas o maíz chulpe."
            )
        ),
        Receta(
            nombre = "Chuleta Valluna",
            nombreId = "Chuleta Valluna",
            imagenResId = R.drawable.chuleta_vallecaucana,
            ingredientes = listOf(
                "Filetes de cerdo",
                "Huevo",
                "Pan rallado",
                "Harina de trigo",
                "Ajo en polvo",
                "Sal y pimienta",
                "Aceite para freír"
            ),
            pasos = listOf(
                "Aplastar los filetes y sazonar con sal, pimienta y ajo.",
                "Pasar por harina, huevo batido y luego pan rallado.",
                "Freír en aceite caliente hasta dorar.",
                "Servir con arroz, papa y ensalada."
            )
        ),
        Receta(
            nombre = "Ceviche de Camarón",
            nombreId = "Ceviche de Camarón",
            imagenResId = R.drawable.ceviche_de_camaron,
            ingredientes = listOf(
                "500 g de camarones cocidos",
                "1 cebolla morada",
                "2 tomates",
                "1 limón",
                "Cilantro fresco",
                "Sal y pimienta al gusto"
        ),
            pasos = listOf(
                "Picar la cebolla y el tomate en cubos pequeños.",
                "Mezclar con los camarones cocidos.",
                "Agregar jugo de limón, sal, pimienta y cilantro.",
                "Refrigerar 30 minutos antes de servir."
            )
        ),
        Receta(
            nombre = "Croquetas de Pollo",
            nombreId = "Croquetas de Pollo",
            imagenResId = R.drawable.croquetas_de_pollo,
            ingredientes = listOf(
                "2 tazas de pollo cocido y desmenuzado",
                "1 taza de bechamel espesa",
                "1 huevo",
                "Pan rallado",
                "Aceite para freír"
            ),
            pasos = listOf(
                "Mezclar el pollo con la bechamel y formar bolitas.",
                "Pasar por huevo y luego pan rallado.",
                "Freír en aceite caliente hasta dorar.",
                "Escurrir sobre papel absorbente y servir calientes."
            )
        ),
        Receta(
            nombre = "Curry de Garbanzos",
            nombreId = "Curry de Garbanzos",
            imagenResId = R.drawable.curry_de_garbanzos,
            ingredientes = listOf(
                "2 tazas de garbanzos cocidos",
                "1 cebolla",
                "1 tomate",
                "1 taza de leche de coco",
                "2 cucharadas de curry en polvo"
            ),
            pasos = listOf(
                "Sofreír cebolla picada hasta dorar.",
                "Agregar tomate picado y curry en polvo.",
                "Incorporar garbanzos y leche de coco.",
                "Cocinar a fuego medio por 15 minutos y servir con arroz."
            )
        ),
        Receta(
            nombre = "Empanada de Carne",
            nombreId = "Empanada de Carne",
            imagenResId = R.drawable.empanada_de_carne,
            ingredientes = listOf(
                "Masa para empanadas",
                "300 g de carne molida",
                "1 cebolla",
                "1 papa cocida",
                "Comino, sal y pimienta al gusto",
                "Aceite para freír"
            ),
            pasos = listOf(
                "Sofreír la cebolla y luego agregar la carne.",
                "Añadir la papa en cubos y sazonar.",
                "Rellenar la masa de empanadas con la mezcla.",
                "Cerrar y sellar los bordes, luego freír hasta dorar."
            )
        ),
        Receta(
            nombre = "Ensalada Caprese",
            nombreId = "Ensalada Caprese",
            imagenResId = R.drawable.ensalada_caprese,
            ingredientes = listOf(
                "Tomates frescos",
                "Queso mozzarella",
                "Hojas de albahaca",
                "Aceite de oliva",
                "Sal y pimienta"
            ),
            pasos = listOf(
                "Cortar los tomates y la mozzarella en rodajas.",
                "Alternar en un plato con hojas de albahaca.",
                "Rociar con aceite de oliva.",
                "Agregar sal y pimienta al gusto."
            )
        ),
        Receta(
            nombre = "Ensalada de Quinua",
            nombreId = "Ensalada de Quinua",
            imagenResId = R.drawable.ensalada_de_quinua,
            ingredientes = listOf(
                "1 taza de quinua cocida",
                "1 pepino",
                "1 tomate",
                "1/2 pimiento",
                "Jugo de 1 limón",
                "Aceite de oliva, sal y pimienta"
            ),
            pasos = listOf(
                "Cortar las verduras en cubos pequeños.",
                "Mezclar con la quinua cocida.",
                "Agregar jugo de limón, aceite de oliva, sal y pimienta.",
                "Refrigerar por 15 minutos antes de servir."
            )
        ),
        Receta(
            nombre = "Fajitas de Res",
            nombreId = "Fajitas de Res",
            imagenResId = R.drawable.fajitas_de_res,
            ingredientes = listOf(
                "500 g de carne de res en tiras",
                "1 pimiento rojo",
                "1 pimiento verde",
                "1 cebolla",
                "Tortillas de harina",
                "Condimentos: comino, sal, pimienta"
            ),
            pasos = listOf(
                "Saltear la carne en una sartén con condimentos.",
                "Agregar las verduras en tiras y cocinar hasta que estén tiernas.",
                "Servir en tortillas calientes."
            )
        ),
        Receta(
            nombre = "Pescado al Horno",
            nombreId = "Pescado al Horno",
            imagenResId = R.drawable.pescado_al_horno,
            ingredientes = listOf(
                "2 filetes de pescado blanco",
                "1 limón",
                "Ajo picado",
                "Perejil",
                "Aceite de oliva, sal y pimienta"
            ),
            pasos = listOf(
                "Precalentar el horno a 180°C.",
                "Colocar los filetes en una bandeja y sazonar con ajo, perejil, sal y pimienta.",
                "Rociar con jugo de limón y aceite de oliva.",
                "Hornear durante 20 minutos."
            )
        ),
        Receta(
            nombre= "Pollo a la Naranja",
            nombreId = "Pollo a la Naranja",
            imagenResId = R.drawable.pollo_a_la_naranja,
            ingredientes = listOf(
                "2 pechugas de pollo",
                "1 taza de jugo de naranja",
                "1 cucharada de salsa de soya",
                "1 cucharadita de miel",
                "Ajo picado, sal y pimienta"
            ),
            pasos = listOf(
                "Saltear el pollo hasta dorar.",
                "Agregar el jugo de naranja, soya, miel y ajo.",
                "Cocinar a fuego medio hasta reducir la salsa.",
                "Servir caliente con arroz blanco."
            )
        ),
        Receta(
            nombre = "Sándwich Cubano",
            nombreId = "Sándwich Cubano",
            imagenResId = R.drawable.sandwich_cubano,
            ingredientes = listOf(
                "Pan cubano o baguette",
                "Jamón",
                "Cerdo asado",
                "Pepinillos",
                "Mostaza",
                "Queso suizo"
            ),
            pasos = listOf(
                "Abrir el pan y untar con mostaza.",
                "Agregar capas de jamón, cerdo, queso y pepinillos.",
                "Cerrar el pan y prensar en una plancha o sartén hasta que esté dorado."
            )
        ),
        Receta(
            nombre = "Sopa de Lentejas",
            nombreId = "Sopa de Lentejas",
            imagenResId = R.drawable.sopa_de_lentejas,
            ingredientes = listOf(
                "1 taza de lentejas",
                "1 zanahoria",
                "1 papa",
                "1 tomate",
                "Cebolla, ajo y especias"
            ),
            pasos = listOf(
                "Lavar las lentejas y cocinar con agua.",
                "Agregar los vegetales picados y sazonar.",
                "Cocinar a fuego medio hasta que todo esté blando.",
                "Servir caliente."
            )
        ),
        Receta(
            nombre= "Sopa de Tomate",
            nombreId = "Sopa de Tomate",
            imagenResId = R.drawable.sopa_de_tomate,
            ingredientes = listOf(
                "4 tomates maduros",
                "1 diente de ajo",
                "1/2 cebolla",
                "Caldo de verduras",
                "Sal, pimienta y albahaca"
            ),
            pasos = listOf(
                "Saltear ajo y cebolla, agregar tomates picados.",
                "Cocinar por 10 minutos, luego licuar todo.",
                "Regresar a la olla, añadir caldo y sazonar.",
                "Hervir y servir con albahaca fresca."
            )
        ),
        Receta(
            nombre = "Tortilla Española",
            nombreId = "Tortilla Española",
            imagenResId = R.drawable.tortilla_espanola,
            ingredientes = listOf(
                "4 huevos",
                "3 papas",
                "1 cebolla",
                "Aceite de oliva",
                "Sal"
            ),
            pasos = listOf(
                "Cortar las papas y la cebolla en rodajas finas.",
                "Freír en aceite hasta que estén blandas.",
                "Batir los huevos y mezclar con las papas y cebolla.",
                "Cocinar la mezcla en sartén hasta que cuaje, voltear con ayuda de un plato."
            )
        ),
        Receta(
            nombre = "Waffles de Avena",
            nombreId = "Waffles de Avena",
            imagenResId = R.drawable.waffles_de_avena,
            ingredientes = listOf(
                "1 taza de avena molida",
                "1 huevo",
                "1/2 taza de leche",
                "1 plátano maduro",
                "1 cucharadita de polvo de hornear"
            ),
            pasos = listOf(
                "Licuar todos los ingredientes hasta obtener una mezcla homogénea.",
                "Verter en la wafflera caliente.",
                "Cocinar hasta que estén dorados.",
                "Servir con frutas o miel al gusto."
            )
        ),
        Receta(
            nombre = "Arroz con Pollo",
            nombreId = "Arroz con Pollo",
            imagenResId = R.drawable.arroz_con_pollo,
            ingredientes = listOf(
                "2 tazas de arroz cocido",
                "1 pechuga de pollo desmechada",
                "1 zanahoria rallada",
                "1/2 taza de arvejas",
                "Ajo y cebolla al gusto",
                "Sal, pimienta y comino"
            ),
            pasos = listOf(
                "Sofreír ajo y cebolla en una olla.",
                "Agregar la zanahoria, arvejas y pollo desmechado.",
                "Añadir el arroz y mezclar bien.",
                "Sazonar con sal, pimienta y comino al gusto.",
                "Cocinar por 5 minutos más y servir caliente."
            )
        ),
        Receta(
            nombre = "Ensalada César con Pollo",
            nombreId = "Ensalada César con Pollo",
            imagenResId = R.drawable.ensalada_cesar_con_pollo,
            ingredientes = listOf(
                "Lechuga romana",
                "Pechuga de pollo a la plancha",
                "Crutones",
                "Queso parmesano rallado",
                "Aderezo César"
            ),
            pasos = listOf(
                "Lavar y cortar la lechuga.",
                "Cocinar y cortar el pollo en tiras.",
                "Mezclar la lechuga con el pollo, crutones y queso.",
                "Agregar aderezo al gusto y servir frío."
            )
        ),
        Receta(
            nombre = "Grilled Cheese",
            nombreId = "Grilled Cheese",
            imagenResId = R.drawable.grilled_cheese,
            ingredientes = listOf(
                "2 rebanadas de pan de molde",
                "2 tajadas de queso",
                "Mantequilla"
            ),
            pasos = listOf(
                "Untar mantequilla por fuera de las rebanadas de pan.",
                "Colocar el queso entre las rebanadas.",
                "Cocinar en sartén hasta que esté dorado por ambos lados.",
                "Servir caliente con el queso derretido."
            )
        ),
        Receta(
            nombre = "Hamburguesa Clásica con Queso",
            nombreId = "Hamburguesa Clásica con Queso",
            imagenResId = R.drawable.hamburguesa_clasica_con_queso,
            ingredientes = listOf(
                "1 pan de hamburguesa",
                "1 carne de res para hamburguesa",
                "1 tajada de queso cheddar",
                "Lechuga, tomate y cebolla",
                "Salsas al gusto"
            ),
            pasos = listOf(
                "Cocinar la carne en sartén o parrilla.",
                "Colocar el queso sobre la carne caliente para que se derrita.",
                "Armar la hamburguesa con los vegetales y la carne.",
                "Agregar las salsas y servir caliente."
            )
        ),
        Receta(
            nombre = "Pan de Ajo",
            nombreId = "Pan de Ajo",
            imagenResId = R.drawable.pan_de_ajo,
            ingredientes = listOf(
                "1 baguette o pan francés",
                "2 cucharadas de mantequilla",
                "2 dientes de ajo",
                "Perejil fresco picado",
                "Queso rallado (opcional)"
            ),
            pasos = listOf(
                "Mezclar la mantequilla con el ajo picado y el perejil.",
                "Cortar el pan en rebanadas sin llegar al fondo.",
                "Untar la mezcla de ajo entre las rebanadas.",
                "Agregar queso si se desea y hornear a 180°C por 10 minutos.",
                "Servir caliente y crujiente."
            )
        ),
        Receta(
            nombre = "Papas Fritas",
            nombreId = "Papas Fritas",
            imagenResId = R.drawable.papas_fritas,
            ingredientes = listOf(
                "4 papas grandes",
                "Aceite para freír",
                "Sal al gusto"
            ),
            pasos = listOf(
                "Pelar y cortar las papas en bastones.",
                "Remojar en agua fría durante 30 minutos.",
                "Escurrir y secar bien las papas.",
                "Freír en aceite caliente hasta dorar.",
                "Escurrir sobre papel y agregar sal."
            )
        ),
        Receta(
            nombre = "Spaghetti Carbonara",
            nombreId = "Spaghetti Carbonara",
            imagenResId = R.drawable.spaghetti_carbonara,
            ingredientes = listOf(
                "200 g de espaguetis",
                "100 g de panceta o tocineta",
                "2 yemas de huevo",
                "Queso parmesano rallado",
                "Pimienta negra"
            ),
            pasos = listOf(
                "Cocinar la pasta según las instrucciones.",
                "Saltear la panceta hasta dorar.",
                "Batir las yemas con queso y pimienta.",
                "Escurrir la pasta y mezclar con la panceta.",
                "Agregar la mezcla de yemas y revolver rápidamente.",
                "Servir caliente con más queso si se desea."
            )
        ),
        Receta(
            nombre = "Tacos al Pastor",
            nombreId = "Tacos al Pastor",
            imagenResId = R.drawable.tacos_al_pastor,
            ingredientes = listOf(
                "500 g de carne de cerdo en tiras",
                "Tortillas de maíz",
                "Piña en cubos",
                "Cebolla y cilantro picados",
                "Salsa al gusto"
            ),
            pasos = listOf(
                "Marinar la carne con especias al pastor y cocinarla.",
                "Calentar las tortillas.",
                "Servir la carne sobre las tortillas con piña, cebolla y cilantro.",
                "Acompañar con salsa y limón."
            )
        )
// para poner más recetas - no olvidar las fotos de cada receta.
    )
}
