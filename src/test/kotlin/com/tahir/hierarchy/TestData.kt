package com.tahir.hierarchy

object TestData {

    val testHierarchyMap: Map<String, String> = mapOf(
        "Peter" to "Nicku",
        "Barbara" to "Nicku",
        "Nicku" to "Sophie",
        "Sophie" to "Jonas",
    )

    val testCycleErrorHierarchyMap: Map<String, String> = mapOf(
        "Peter" to "Nicku",
        "Barbara" to "Nicku",
        "Nicku" to "Sophie",
        "Sophie" to "Jonas",
        "Jonas" to "Nicku"
    )

    val testTwoBossErrorHierarchyMap: Map<String, String> = mapOf(
        "Peter" to "Nicku",
        "Barbara" to "Nicku",
        "Nicku" to "Sophie",
        "Sophie" to "Jonas",
        "Tahir" to "Emre"
    )

    val testHierarchyJson: String = """
        {
          "Pete": "Nicku",
          "Barbara": "Nicku",
          "Nicku": "Sophie",
          "Sophie": "Jonas"
        }
    """.trimIndent()

    const val expectedTestData: String = "{\n" +
            "  \"Jonas\" : {\n" +
            "    \"Sophie\" : {\n" +
            "      \"Nicku\" : {\n" +
            "        \"Pete\" : { }\n" +
            "        \"Barbara\" : { }\n" +
            "       },\n" +
            "    },\n" +
            "  }\n" +
            "}"
}