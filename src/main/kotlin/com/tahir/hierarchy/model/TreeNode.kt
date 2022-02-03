package com.tahir.hierarchy.model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class TreeNode<T>(val value: T, val children: List<TreeNode<T>> = emptyList()){

    fun toJson(): String {
        val objectMapper = jacksonObjectMapper()
        val root = objectMapper.createObjectNode()
        root.set<JsonNode>(
            value.toString(),
            childrenToJson(objectMapper)
        )
        return root.toPrettyString()
    }

    private fun childrenToJson(objectMapper: ObjectMapper): ObjectNode {
        val node = objectMapper.createObjectNode()
        children.map {
            node.set<JsonNode>(
                it.value.toString(),
                it.childrenToJson(objectMapper)
            )
        }
        return node
    }

}