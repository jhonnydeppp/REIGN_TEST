package com.reign.reigntest.common.models

import com.google.gson.annotations.SerializedName

data class CommentText(

	@field:SerializedName("matchLevel")
	val matchLevel: String? = null,

	@field:SerializedName("fullyHighlighted")
	val fullyHighlighted: Boolean? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("matchedWords")
	val matchedWords: List<String?>? = null
)