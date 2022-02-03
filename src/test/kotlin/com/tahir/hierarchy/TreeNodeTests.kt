package com.tahir.hierarchy

import com.tahir.hierarchy.model.TreeNode
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class TreeNodeTests {

    @Test
    fun testJson() {
        val tree: TreeNode<String> =
            TreeNode("Tahir",
            listOf(
                TreeNode("Emre",
                    listOf(
                        TreeNode("Philip"),
                        TreeNode("Pellumb")
                    )
                ),
                TreeNode("Ant")
            )
        )
        val actual: String = tree.toJson()
        val expected: String = "{\n" +
                "  \"Tahir\" : {\n" +
                "    \"Emre\" : {\n" +
                "      \"Philip\" : { },\n" +
                "      \"Pellumb\" : { }\n" +
                "    },\n" +
                "    \"Ant\" : { }\n" +
                "  }\n" +
                "}"

        assertEquals(expected, actual)
    }
}